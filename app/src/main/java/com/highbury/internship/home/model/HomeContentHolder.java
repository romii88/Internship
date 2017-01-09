package com.highbury.internship.home.model;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.highbury.internship.R;
import com.highbury.internship.base.BaseHolder;

import butterknife.BindView;

/**
 * Created by han on 2017/1/9.
 */

public class HomeContentHolder extends BaseHolder{
    @BindView(R.id.iv_home_item)
    ImageView ivHomeItem;

    @BindView(R.id.tv_home_item)
    TextView tvHomeItem;

    @BindView(R.id.home_item_container)
    ViewGroup container;
}
