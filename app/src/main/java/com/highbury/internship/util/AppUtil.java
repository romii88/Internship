package com.highbury.internship.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;

import com.highbury.internship.base.Applications;

/**
 * Created by han on 2016/12/28.
 */

public class AppUtil {
    public static int versionCode(){
        try {
            PackageManager manager = Applications.getCurrent().getPackageManager();
            PackageInfo info = manager.getPackageInfo(Applications.getCurrent().getPackageName(),
                    PackageManager.GET_ACTIVITIES);
            if (info != null) {
                int versionCode = info.versionCode;
                return versionCode;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String versionName(){
        try {
            PackageManager manager = Applications.getCurrent().getPackageManager();
            PackageInfo info = manager.getPackageInfo(Applications.getCurrent().getPackageName(),
                    PackageManager.GET_ACTIVITIES);
            if (info != null) {
                String versionName = info.versionName;
                return versionName;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String imei(){
        TelephonyManager mTelephonyMgr = (TelephonyManager) Applications.getCurrent()
                .getSystemService(Context.TELEPHONY_SERVICE);
        return mTelephonyMgr.getDeviceId();
    }
}
