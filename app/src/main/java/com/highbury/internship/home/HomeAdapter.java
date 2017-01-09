package com.highbury.internship.home;

import android.view.View;

import com.highbury.internship.R;
import com.highbury.internship.base.BaseRecyclerViewAdapter;
import com.highbury.internship.home.model.HomeContentModel_;
import com.highbury.internship.home.model.HomeHeaderModel;

/**
 * Created by han on 2017/1/9.
 */

public class HomeAdapter extends BaseRecyclerViewAdapter {
    private final HomeContentModel_ model1=new HomeContentModel_();
    private final HomeContentModel_ model2=new HomeContentModel_();
    private final HomeContentModel_ model3=new HomeContentModel_();
    private final HomeContentModel_ model4=new HomeContentModel_();
    private final HomeContentModel_ model5=new HomeContentModel_();
    private final HomeContentModel_ model6=new HomeContentModel_();
    private final HomeContentModel_ model7=new HomeContentModel_();
    private final HomeContentModel_ model8=new HomeContentModel_();
    private final HomeContentModel_ model9=new HomeContentModel_();
    public HomeAdapter() {
        enableDiffing();
        HomeHeaderModel headerModel = new HomeHeaderModel();
        model1.text(R.string.weekly).imgId(R.drawable.ease_btn_cancel_bj).clickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        model2.text(R.string.monthly).imgId(R.drawable.ease_btn_cancel_bj).clickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        model3.text(R.string.clock).imgId(R.drawable.ease_btn_cancel_bj).clickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        model4.text(R.string.internship_apply).imgId(R.drawable.ease_btn_cancel_bj).clickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        model5.text(R.string.internship_change).imgId(R.drawable.ease_btn_cancel_bj).clickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        model6.text(R.string.internship_summary).imgId(R.drawable.ease_btn_cancel_bj).clickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        model7.text(R.string.leave_apply).imgId(R.drawable.ease_btn_cancel_bj).clickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        addModels(
                headerModel,
                model1,
                model2,
                model3,
                model4,
                model5,
                model6,
                model7
        );

    }
}