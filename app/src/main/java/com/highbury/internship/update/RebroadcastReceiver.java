package com.highbury.internship.update;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by han on 2016/12/29.
 */

public class RebroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        LocalBroadcastManager manager=LocalBroadcastManager.getInstance(context);
        if(manager==null){
            return;
        }
        Intent modifiedIntent=new Intent(intent);
        modifiedIntent.setComponent(null);
        manager.sendBroadcast(modifiedIntent);
    }
}
