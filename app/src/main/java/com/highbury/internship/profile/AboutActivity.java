package com.highbury.internship.profile;

import android.widget.TextView;

import com.highbury.internship.R;
import com.highbury.internship.base.BaseActivity;
import com.highbury.internship.util.AppUtil;

import butterknife.BindView;

/**
 * Created by han on 2016/12/28.
 */

public class AboutActivity extends BaseActivity{
    @BindView(R.id.tv_version)
    TextView tvVersion;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    public void setupViews() {
        super.setupViews();
        tvVersion.setText(AppUtil.versionName());
    }
}
