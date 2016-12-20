package com.highbury.internship.base;

import android.content.Context;
import android.net.Uri;

/**
 * Created by han on 2016/12/12.
 */

public class ResourceHelper {
    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";

    private static Uri resourceIdToUri(Context context, int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + context.getPackageName() + FOREWARD_SLASH + resourceId);
    }
}
