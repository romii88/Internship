package com.highbury.internship.home.model;

import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.highbury.internship.R;

/**
 * Created by han on 2017/1/9.
 */

public class HomeHeaderModel extends EpoxyModelWithHolder<HomeHeaderHolder>{

    @Override
    public void bind(HomeHeaderHolder holder) {
        super.bind(holder);
    }

    @Override
    protected HomeHeaderHolder createNewHolder() {
        return new HomeHeaderHolder();
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.item_home_header;
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return 3;
    }
}
