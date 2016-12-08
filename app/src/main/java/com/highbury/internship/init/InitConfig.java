package com.highbury.internship.init;

import android.app.Application;
import android.util.Log;

import com.facebook.stetho.Stetho;
import com.highbury.internship.BuildConfig;
import com.highbury.internship.logger.FakeCrashLibrary;

import timber.log.Timber;

/**
 * Created by han on 2016/12/8.
 */

public class InitConfig {
    public void init(Application application){
        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }else{
            Timber.plant(new CrashReportingTree());
        }
        Stetho.initializeWithDefaults(application);
    }

    /** A tree which logs important information for crash reporting. */
    private static class CrashReportingTree extends Timber.Tree {
        @Override protected void log(int priority, String tag, String message, Throwable t) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return;
            }

            FakeCrashLibrary.log(priority, tag, message);

            if (t != null) {
                if (priority == Log.ERROR) {
                    FakeCrashLibrary.logError(t);
                } else if (priority == Log.WARN) {
                    FakeCrashLibrary.logWarning(t);
                }
            }
        }
    }
}
