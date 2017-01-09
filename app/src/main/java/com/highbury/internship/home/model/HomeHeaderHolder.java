package com.highbury.internship.home.model;

import android.widget.Button;
import android.widget.TextView;

import com.highbury.internship.R;
import com.highbury.internship.base.BaseHolder;

import butterknife.BindView;

/**
 * Created by han on 2017/1/9.
 */

public class HomeHeaderHolder extends BaseHolder{
    @BindView(R.id.btn_login)
    Button btnLogin;

    @BindView(R.id.tv_header_prompt)
    TextView tvHeaderPrompt;
}
