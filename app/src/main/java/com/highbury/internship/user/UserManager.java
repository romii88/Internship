package com.highbury.internship.user;

import android.content.Context;
import android.content.SharedPreferences;

import com.highbury.internship.base.Applications;

/**
 * Created by han on 2016/12/28.
 */

public class UserManager {
    private static final String SP_NAME = "intership";
    private static volatile UserManager instance;

    private SharedPreferences mSp;

    private UserManager(){
        mSp= Applications.getCurrent().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    public static UserManager getInstance(){
        if(instance==null){
            synchronized (UserManager.class){
                if(instance==null){
                    instance=new UserManager();
                }
            }
        }
        return instance;
    }

    public void login(){

    }

    public void logout(){

    }
}
