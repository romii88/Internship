package com.highbury.internship.profile;

import com.highbury.internship.R;
import com.highbury.internship.base.BaseActivity;
import com.highbury.internship.user.UserManager;

import butterknife.OnClick;

/**
 * Created by han on 2016/12/28.
 */

public class SettingActivity extends BaseActivity{
    @Override
    public void setupViews() {
        super.setupViews();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @OnClick(R.id.btn_logout)
    public void logout(){
        UserManager.getInstance().logout();
    }
}
