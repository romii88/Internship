package com.highbury.internship.profile.model;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.highbury.internship.R;

/**
 * Created by han on 2016/12/28.
 */

public class HeaderModel extends EpoxyModelWithHolder<HeaderHolder> {
    @EpoxyAttribute @StringRes
    int text;
    @EpoxyAttribute @DrawableRes
    int drawable;
    @Override
    public void bind(HeaderHolder holder) {
        super.bind(holder);
        holder.tvName.setText("");
    }

    @Override
    protected HeaderHolder createNewHolder() {
        return new HeaderHolder();
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.item_profile_header;
    }
}
