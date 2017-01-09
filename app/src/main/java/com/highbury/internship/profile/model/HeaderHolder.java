package com.highbury.internship.profile.model;

import android.widget.ImageView;
import android.widget.TextView;

import com.highbury.internship.R;
import com.highbury.internship.base.BaseHolder;

import butterknife.BindView;

/**
 * Created by han on 2016/12/28.
 */

public class HeaderHolder extends BaseHolder{
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
}
