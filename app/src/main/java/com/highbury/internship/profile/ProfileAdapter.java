package com.highbury.internship.profile;

import com.highbury.internship.base.BaseRecyclerViewAdapter;
import com.highbury.internship.profile.model.HeaderModel;
import com.highbury.internship.profile.model.HeaderModel_;
import com.highbury.internship.profile.model.ItemModel;
import com.highbury.internship.profile.model.ItemModel_;

/**
 * Created by han on 2016/12/28.
 */

public class ProfileAdapter extends BaseRecyclerViewAdapter{

    public ProfileAdapter(){
        enableDiffing();
        HeaderModel headerModel=new HeaderModel_();
        ItemModel itemModel1=new ItemModel_();

    }
}
