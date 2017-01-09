package com.highbury.internship.base;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by han on 2016/12/20.
 */

public class Versions {
    public static int code(final Context context) {
        if (sVersionCode == 0) loadVersionInfo(context);
        return sVersionCode;
    }

    public static String name(final Context context) {
        if (sVersionName == null) loadVersionInfo(context);
        return sVersionName;
    }

    public static long lastUpdateTime(final Context context) {
        if (sLastUpdateTime == 0) loadVersionInfo(context);
        return sLastUpdateTime;
    }

    private static void loadVersionInfo(final Context context) {
        try {
            final PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            sVersionCode = info.versionCode;
            sVersionName = info.versionName;
            sLastUpdateTime = info.lastUpdateTime;
        } catch (final PackageManager.NameNotFoundException e) { /* Should never happen */ }
    }

    private static int sVersionCode;
    private static String sVersionName;
    private static long sLastUpdateTime;
}
