package com.highbury.internship.profile.model;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.view.View;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.highbury.internship.R;

/**
 * Created by han on 2016/12/28.
 */

public class ItemModel extends EpoxyModelWithHolder<ItemHolder>{
    @EpoxyAttribute
    View.OnClickListener onClickListener;
    @EpoxyAttribute @DrawableRes
    int drawable;
    @EpoxyAttribute @StringRes
    int text;

    @Override
    protected ItemHolder createNewHolder() {
        return new ItemHolder();
    }

    @Override
    public void bind(ItemHolder holder) {
        super.bind(holder);
        holder.tvProfile.setText(text);
        holder.container.setOnClickListener(onClickListener);
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.item_profile;
    }
}
