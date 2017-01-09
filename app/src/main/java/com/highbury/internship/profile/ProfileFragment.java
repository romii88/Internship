package com.highbury.internship.profile;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.highbury.internship.R;
import com.highbury.internship.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by han on 2016/12/28.
 */

public class ProfileFragment extends BaseFragment{
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_profile;
    }

    @Override
    protected void setupViews(View rootView) {
        int spanCount = 1;

        ProfileAdapter adapter = new ProfileAdapter();

        // We are using a multi span grid to show many color models in each row. To set this up we need
        // to set our span count on the adapter and then get the span size lookup object from
        // the adapter. This look up object will delegate span size lookups to each model.
        adapter.setSpanCount(spanCount);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), spanCount);
        gridLayoutManager.setSpanSizeLookup(adapter.getSpanSizeLookup());

        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new VerticalGridCardSpacingDecoration());
        mRecyclerView.setAdapter(adapter);
    }
}
