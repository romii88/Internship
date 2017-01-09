package com.highbury.internship.home;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.highbury.internship.R;
import com.highbury.internship.base.BaseFragment;
import com.highbury.internship.profile.VerticalGridCardSpacingDecoration;

import butterknife.BindView;

/**
 * Created by han on 2016/12/8.
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void setupViews(View rootView) {
        HomeAdapter adapter = new HomeAdapter();

        // We are using a multi span grid to show many color models in each row. To set this up we need
        // to set our span count on the adapter and then get the span size lookup object from
        // the adapter. This look up object will delegate span size lookups to each model.
        adapter.setSpanCount(3);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        gridLayoutManager.setSpanSizeLookup(adapter.getSpanSizeLookup());

        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new VerticalGridCardSpacingDecoration());
        mRecyclerView.setAdapter(adapter);
    }
}
