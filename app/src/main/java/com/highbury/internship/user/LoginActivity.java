package com.highbury.internship.user;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.highbury.internship.R;
import com.highbury.internship.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by han on 2016/12/20.
 */

public class LoginActivity extends BaseActivity{
    @BindView(R.id.et_user_name)
    EditText mUserNameView;
    @BindView(R.id.et_password)
    EditText mPasswordView;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.login_form)
    View mLoginFormView;
    @BindView(R.id.login_progress)
    View mProgressView;



    @Override
    protected int getLayoutId() {
        return R.layout.act_login;
    }

    @Override
    public void setupViews() {
        super.setupViews();
        mUserNameView.setText("11111");
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    Toast.makeText(LoginActivity.this,"hahaha",Toast.LENGTH_LONG).show();
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
    }

//    @OnEditorAction(R.id.et_password)
//    public boolean onEditorAction(KeyEvent key){
//        Toast.makeText(this,"hahaha",Toast.LENGTH_LONG).show();
//        attemptLogin();
//        return true;
//    }

    @OnClick(R.id.btn_login)
    public void attemptLogin(){
        Toast.makeText(this,"111111",Toast.LENGTH_LONG).show();
        String userName=mUserNameView.getText().toString();
        String password=mPasswordView.getText().toString();
        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
//            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(userName)) {
//            mUserNameView.setError(getString(R.string.error_field_required));
            focusView = mUserNameView;
            cancel = true;
        } else if (!isUserNameValid(userName)) {
            mUserNameView.setError(getString(R.string.error_invalid_email));
            focusView = mUserNameView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
        }
    }

    private boolean isUserNameValid(String userName) {
        return !TextUtils.isEmpty(userName);
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}
