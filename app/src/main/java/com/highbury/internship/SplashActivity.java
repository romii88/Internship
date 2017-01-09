package com.highbury.internship;

import android.content.Intent;
import android.view.View;
import android.view.animation.AlphaAnimation;

import com.highbury.internship.base.BaseActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by han on 2016/12/20.
 */

public class SplashActivity extends BaseActivity {
    @BindView(R.id.splash)
    View mSplashContainer;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void setupViews() {
        super.setupViews();
        AlphaAnimation animation = new AlphaAnimation(0.3f, 1.0f);
        animation.setDuration(1500);
        mSplashContainer.startAnimation(animation);
        finishActivity();
    }

    private void finishActivity() {
        Observable.timer(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        overridePendingTransition(0, android.R.anim.fade_out);
                        finish();
                    }
                });
    }
}
