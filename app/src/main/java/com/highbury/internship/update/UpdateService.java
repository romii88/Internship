package com.highbury.internship.update;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;

import com.highbury.internship.R;
import com.highbury.internship.base.Applications;
import com.highbury.internship.constant.BundleKey;
import com.highbury.internship.util.NetworkUtil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import timber.log.Timber;

/**
 * Created by han on 2016/12/28.
 */

public class UpdateService extends IntentService {
    public final static int STATE_START = 0;
    public final static int STATE_FINISH = 1;
    public final static int STATE_UPDATE_PROGRESS = 2;
    public final static int STATE_FAIL = 3;
    public final static int STATE_CANCEL=4;
    private NotificationManager mNm;
    private int mNotificationId = 1234;
    private MyHandler mHandler;
    private boolean mCancelUpdate = false;
    private NotificationCompat.Builder mBuilder;
    private String mUpdateUrl;
    private boolean isDownloading;
    private String mTitle;
    HttpURLConnection httpURLConnection=null;

    public UpdateService() {
        super("UpdateService");
        mHandler = new MyHandler(Looper.getMainLooper());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        final String deletedAction = getString(R.string.notification_dismissed);
        LocalBroadcastManager.getInstance(this).registerReceiver(
                new BroadcastReceiver()
                {
                    @Override public void onReceive(Context context, Intent intent)
                    {
                        if (intent.getAction() == null)
                            return;
                        if (!intent.getAction().equals(deletedAction))
                            return;
                        Message message = mHandler.obtainMessage(STATE_CANCEL, null);
                        mHandler.sendMessage(message);
                    }
                },
                new IntentFilter(deletedAction));
    }

    private void createNotification(String contentTitle, String contentText) {
        mNm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(this)
                .setTicker(contentTitle).setAutoCancel(true)
                .setContentTitle(contentTitle).setContentText(contentText)
                .setProgress(100, 0, false)
                .setSmallIcon(getApplicationInfo().icon)
                .setWhen(System.currentTimeMillis());
        PendingIntent updatePendingIntent = PendingIntent.getActivity(
                UpdateService.this, 0, new Intent(),
                PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(updatePendingIntent);
        Intent intent = new Intent(Applications.getCurrent(), RebroadcastReceiver.class);
        intent.setAction(getString(R.string.notification_dismissed));
        PendingIntent deletePending =
                PendingIntent.getBroadcast(Applications.getCurrent(),
                        mNotificationId, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        mBuilder.setDeleteIntent(deletePending);
        mNm.notify(mNotificationId, mBuilder.build());
    }

    private void updateProgress(int progress) {
        // "正在下载:" + progress + "%"
        mBuilder.setContentText(
                this.getString(R.string.download_progress, progress))
                .setProgress(100, progress, false);
        PendingIntent pendingintent = PendingIntent.getActivity(this, 0,
                new Intent(), PendingIntent.FLAG_CANCEL_CURRENT);
        mBuilder.setContentIntent(pendingintent);
        mNm.notify(mNotificationId, mBuilder.build());
    }

    private void finishDownload(File file) {
        installApk(file, this);
        // sendBroadcast("action_update_complete", true);
        stopSelf();
    }

    private static String getSaveFileName(String downloadUrl) {
        if (downloadUrl == null || TextUtils.isEmpty(downloadUrl)) {
            return "noName.apk";
        }
        return downloadUrl.substring(downloadUrl.lastIndexOf("/"));
    }

    private static File getDownloadDir(UpdateService service){
        File downloadDir = null;
        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED)) {
                downloadDir = new File(service.getExternalCacheDir(), "update");
        } else {
            downloadDir = new File(service.getCacheDir(), "update");
        }
        if (!downloadDir.exists()) {
            downloadDir.mkdirs();
        }
        return downloadDir;
    }

    /**
     * 发送进度广播
     *
     * @param progress
     */
    public void sendBroadcast(int progress) {
        Intent intent = new Intent();
        intent.setAction(BundleKey.UPDATE_PROGRESS_BROADCAST);
        intent.putExtra("action_update_download_progress", progress);
        sendBroadcast(intent);
    }

    public void sendBroadcast(String name, boolean value) {
        Intent intent = new Intent();
        intent.setAction(BundleKey.UPDATE_PROGRESS_BROADCAST);
        intent.putExtra(name, value);
        sendBroadcast(intent);
    }

    private void onDownloadFailed() {
        mNm.cancel(mNotificationId);
        stopSelf();
    }

    @Override
    public void onDestroy() {
        isDownloading = false;
        if(mNm!=null) {
            mNm.cancel(mNotificationId);
        }
        if(httpURLConnection!=null){
            httpURLConnection.disconnect();
        }
        super.onDestroy();
    }

    // 安装下载后的apk文件
    public void installApk(File file, Context context) {
        if (file != null) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(android.content.Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file),
                    "application/vnd.android.package-archive");
            mBuilder.setContentText(getString(R.string.download_success))
                    .setProgress(0, 0, false);
            PendingIntent updatePendingIntent = PendingIntent.getActivity(
                    UpdateService.this, 0, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(updatePendingIntent);
            mBuilder.build().flags = android.app.Notification.FLAG_AUTO_CANCEL;
            mNm.notify(mNotificationId, mBuilder.build());
            // if (mAutoInstall) {
            context.startActivity(intent);
            mNm.cancel(mNotificationId);
            // }
        }
    }

    class MyHandler extends Handler {
        public MyHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg != null) {
                switch (msg.what) {
                    case STATE_FINISH:
                        isDownloading = false;
                        File file = (File) msg.obj;
                        finishDownload(file);
                        break;
                    case STATE_UPDATE_PROGRESS:
                        int progress = (Integer) msg.obj;
                        updateProgress(progress);
                        break;
                    case STATE_FAIL:
                        isDownloading = false;
                        onDownloadFailed();
                        break;
                    case STATE_CANCEL:
                        isDownloading = false;
                        onDownloadFailed();
                        break;
                }
            }
        }
    }

    private void download(String url){
        File file=new File(UpdateService.getDownloadDir(this),UpdateService.getSaveFileName(url));
        File dir=file.getParentFile();
        if(!dir.exists()){
            dir.mkdirs();
        }

        InputStream is=null;
        FileOutputStream fos=null;
        int updateTotalSize=0;
        URL connectUrl;
        try{
            connectUrl=new URL(url);
            httpURLConnection=(HttpURLConnection)connectUrl.openConnection();
            httpURLConnection.setConnectTimeout(20000);
            httpURLConnection.setReadTimeout(20000);
            if(httpURLConnection.getResponseCode()!=200){
                Message message = mHandler.obtainMessage(STATE_FAIL, null);
                mHandler.sendMessage(message);
                return;
            }
            updateTotalSize=httpURLConnection.getContentLength();
            if(file.exists()){
                if(updateTotalSize==file.length()){
                    Message message = mHandler
                            .obtainMessage(STATE_FINISH, file);
                    mHandler.sendMessage(message);
                    return;
                }else{
                    file.delete();
                }
            }
            file.createNewFile();
            is=httpURLConnection.getInputStream();
            fos=new FileOutputStream(file,false);
            byte buffer[]=new byte[4096];
            int readSize = 0;
            int currentSize = 0;
            BufferedInputStream bis = new BufferedInputStream(is);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            int oldProgress = 0;
            while ((readSize = bis.read(buffer)) != -1 && !mCancelUpdate) {
                currentSize += readSize;
                bos.write(buffer, 0, readSize);
                int progress = (int) (currentSize * 100L / updateTotalSize);
                // 如果进度与之前进度相等，则不更新，如果更新太频繁，否则会造成界面卡顿
                if (progress != oldProgress) {
                    Message message = mHandler.obtainMessage(
                            STATE_UPDATE_PROGRESS, progress);
                    mHandler.sendMessage(message);
                }
                oldProgress = progress;
            }
            if (!mCancelUpdate) {
                Message message = mHandler
                        .obtainMessage(STATE_FINISH, file);
                mHandler.sendMessage(message);
            } else {
                isDownloading = false;
                file.delete();
            }
        }catch (Exception e){
            e.printStackTrace();
            Message message = mHandler.obtainMessage(STATE_FAIL, null);
            mHandler.sendMessage(message);
        }finally {
            if(httpURLConnection!=null){
                httpURLConnection.disconnect();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (isDownloading) {
            return;
        }
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            mTitle = bundle.getString("notify_title");
            mUpdateUrl = bundle.getString("url");
        }
        if (mTitle == null || mUpdateUrl == null) {
            Timber.d("数据获取异常");
            stopSelf();
            return;
        }
        createNotification(mTitle, "");
        if (!NetworkUtil.isConnected()) {
            Timber.d("网络异常，请检查网络状态后重试");
        } else {
            download(mUpdateUrl);
//            download("http://cdn1.down.apk.gfan.com/gfan/product/a/gfanmobile/beta/GfanMarket2.3.1.apk");
        }
    }

}
