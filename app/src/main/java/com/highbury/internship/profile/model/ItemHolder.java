package com.highbury.internship.profile.model;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.highbury.internship.R;
import com.highbury.internship.base.BaseHolder;

import butterknife.BindView;

/**
 * Created by han on 2016/12/28.
 */
public class ItemHolder extends BaseHolder{
    @BindView(R.id.item_container)
    ViewGroup container;
    @BindView(R.id.tv_item_profile)
    TextView tvProfile;
    @BindView(R.id.iv_item_profile)
    ImageView ivProfile;
}
