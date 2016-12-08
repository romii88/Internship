package com.highbury.internship.util;

import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;

/**
 * Created by han on 2016/12/8.
 */

public class DensityUtil {
    private DensityUtil(){

    }

    public static int dip2px(float value) {
        float density = Resources.getSystem().getDisplayMetrics().density;
        return (int)(value * density + 0.5F);
    }

    public static int px2dip(float value) {
        float density = Resources.getSystem().getDisplayMetrics().density;
        return (int)(value / density + 0.5F);
    }

    public static int sp2px(float value) {
        float density = Resources.getSystem().getDisplayMetrics().scaledDensity;
        return (int)(value * density + 0.5F);
    }

    public static int px2sp(float value) {
        float density = Resources.getSystem().getDisplayMetrics().scaledDensity;
        return (int)(value / density + 0.5F);
    }

    /**
     * 获取屏幕区域
     */
    public static Rect getScreenRect() {
        DisplayMetrics displayMetric = new DisplayMetrics();
        displayMetric = Resources.getSystem().getDisplayMetrics();
        Rect rect = new Rect(0, 0, displayMetric.widthPixels, displayMetric.heightPixels);
        return rect;
    }

    /**
     * 获取屏幕宽度
     *
     */
    public static int getScreenWidth() {
        return getScreenRect().width();
    }

    /**
     * 获取屏幕高度
     *
     */
    public static int getScreenHeight() {
        return getScreenRect().height();
    }
}
