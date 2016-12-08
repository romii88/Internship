package com.highbury.internship;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.highbury.internship.home.HomeFragment;

/**
 * Created by han on 2016/12/6.
 */

public class MainActivity extends AppCompatActivity {
//    private BottomNavigationView mBottomNavigationView;
    protected static final String TAG="MainActivity";
    private Button[] mTabs;
    private Fragment[] mFragments;
    private int mIndex;
    private int mCurrentTabIndex;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
//        mBottomNavigationView=(BottomNavigationView)findViewById(R.id.bottom_navigation);
//        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Fragment fragment = null;
//                switch(item.getItemId()){
//                    case R.id.action_main:
//                        break;
//                    case R.id.action_group:
//                        break;
//                    case R.id.action_test:
//                        break;
//                    case R.id.action_me:
//                        break;
//                    default:
//                        break;
//                }
//                if(fragment!=null){
//                    replaceFragment(fragment);
//                }
//                return true;
//            }
//        });
    }

    private void setupViews(){
        mTabs=new Button[4];
        mTabs[0]=(Button)findViewById(R.id.btn_home);
        mTabs[1]=(Button)findViewById(R.id.btn_group);
        mTabs[2]=(Button)findViewById(R.id.btn_test);
        mTabs[3]=(Button)findViewById(R.id.btn_me);
        mTabs[0].setSelected(true);
        initTabContainer();
    }

    private void initTabContainer(){
        HomeFragment homeFragment=new HomeFragment();
        HomeFragment homeFragment1=new HomeFragment();
        mFragments=new Fragment[]{homeFragment,homeFragment1,homeFragment1,homeFragment1};
        getSupportFragmentManager().beginTransaction().add(R.id.fragment, homeFragment)
                .add(R.id.fragment, homeFragment1).hide(homeFragment1).show(homeFragment)
                .commit();
    }

    /**
     * on tab clicked
     * @param view
     */
    public void onTabClicked(View view){
        switch (view.getId()){
            case R.id.btn_home:
                mIndex=0;
                break;
            case R.id.btn_group:
                mIndex=1;
                break;
            case R.id.btn_test:
                mIndex=2;
                break;
            case R.id.btn_me:
                mIndex=3;
                break;
        }
        if(mCurrentTabIndex!=mIndex){
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            trx.hide(mFragments[mCurrentTabIndex]);
            if (!mFragments[mIndex].isAdded()) {
                trx.add(R.id.fragment, mFragments[mIndex]);
            }
            trx.show(mFragments[mIndex]).commit();
        }
        mTabs[mCurrentTabIndex].setSelected(false);
        mTabs[mIndex].setSelected(true);
        mCurrentTabIndex=mIndex;
    }

    private void replaceFragment(Fragment fragment) {
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment, fragment);
        fragmentTransaction.commit();
    }
}
