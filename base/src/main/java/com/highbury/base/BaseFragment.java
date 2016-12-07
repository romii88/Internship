package com.highbury.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by han on 2016/12/6.
 */

public abstract class BaseFragment extends Fragment {
    private Unbinder mUnbinder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(getLayoutId(),container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder= ButterKnife.bind(this,view);
        setupViews(view);
        initFields();
        startBusiness();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbindView();
    }

    protected abstract int getLayoutId();

    protected abstract void setupViews(View rootView);

    protected void startBusiness(){

    }

    protected void initFields(){

    }

    protected void unbindView(){
        if(mUnbinder!=null){
            mUnbinder.unbind();
        }
    }
}
