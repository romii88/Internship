package com.highbury.internship.user;

import com.highbury.internship.R;
import com.highbury.internship.base.BaseActivity;
import com.highbury.internship.widget.CountDownButton;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by han on 2016/12/28.
 */

public class RegisterActivity extends BaseActivity{
    @BindView(R.id.btn_count_down)
    CountDownButton btnCountDown;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void setupViews() {
        super.setupViews();
    }

    @OnClick(R.id.btn_count_down)
    public void requestVerifyCode(){

    }

    @OnClick(R.id.btn_register)
    public void attemptRegister(){

    }
}
