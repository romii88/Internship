package com.highbury.internship.home.model;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.view.View;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.highbury.internship.R;

/**
 * Created by han on 2017/1/9.
 */

public class HomeContentModel extends EpoxyModelWithHolder<HomeContentHolder>{
    @EpoxyAttribute @DrawableRes
    int imgId;

    @EpoxyAttribute @StringRes
    int text;

    @EpoxyAttribute
    View.OnClickListener clickListener;

    @Override
    public void bind(HomeContentHolder holder) {
        super.bind(holder);
        holder.ivHomeItem.setBackgroundResource(imgId);
        holder.tvHomeItem.setText(text);
        holder.container.setOnClickListener(clickListener);
    }

    @Override
    protected HomeContentHolder createNewHolder() {
        return new HomeContentHolder();
    }



    @Override
    protected int getDefaultLayout() {
        return R.layout.item_home_content;
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return 1;
    }
}
