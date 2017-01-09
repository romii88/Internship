package com.highbury.internship.util;

import android.content.Context;

import com.umeng.analytics.MobclickAgent;

/**
 * Created by han on 2016/12/26.
 */

public class AnalyticsHelper {
    public void startConfig(Context ctx){
        MobclickAgent. startWithConfigure(new MobclickAgent.UMAnalyticsConfig(ctx,"5860921507fe657f84001907",""));
    }

    public void onResume(Context ctx){
        MobclickAgent.onResume(ctx);
    }

    public void onPause(Context ctx){
        MobclickAgent.onPause(ctx);
    }

    public void onPageStart(String title){
        MobclickAgent.onPageStart(title);
    }

    public void onPageEnd(String title){
        MobclickAgent.onPageEnd(title);
    }

    public void setDebugMode(boolean debugMode){
        MobclickAgent.setDebugMode(debugMode);
    }
}
