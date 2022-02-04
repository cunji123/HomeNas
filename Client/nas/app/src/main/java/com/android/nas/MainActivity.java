package com.android.nas;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.MenuItem;


import com.android.nas.guestManager.GuestManagerBinder;
import com.android.nas.guestManager.GuestManagerService;
import com.android.nas.guestManager.ui.MainFragment;
import com.android.nas.guestManager.ui.cloud.CloudFragment;
import com.android.nas.guestManager.ui.profile.LoginActivity;
import com.android.nas.guestManager.ui.profile.ProfileFragment;
import com.android.nas.util.LogUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;



public class MainActivity extends AppCompatActivity {
    public static String yt = "yt shi nan tong";
    public static GuestManagerBinder mGuestManagerBinder = null;
    private MainFragment mMainFragment = null;
    private CloudFragment mCloudFragment = null;
    private ProfileFragment mProfileFragment = null;
    private static final int FRAGMENT_MAIN = 0;
    private static final int FRAGMENT_CLOUD = 1;
    private static final int FRAGMENT_PROFILE = 2;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    showFragment(FRAGMENT_MAIN);
                    return true;
                case R.id.navigation_notifications:
                    showFragment(FRAGMENT_CLOUD);
                    return true;
                case R.id.navigation_profile:
                    boolean isNeedShowLoginActivity = mProfileFragment == null?true && !mGuestManagerBinder.isLoginState():mProfileFragment.isHidden() && !mGuestManagerBinder.isLoginState();
                    showFragment(FRAGMENT_PROFILE);

                    if(isNeedShowLoginActivity){
                        Intent loginIntent = new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(loginIntent);
                    }
                    return true;

            }
            return false;
        }
    };

    private void showFragment(int index) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragment(transaction);
        switch (index) {
            case FRAGMENT_MAIN:
                if (mMainFragment == null) {
                    mMainFragment = new MainFragment();
                    transaction.add(R.id.content, mMainFragment);
                } else {
                    transaction.show(mMainFragment);
                }
                break;
            case FRAGMENT_CLOUD:
                if (mCloudFragment == null) {
                    mCloudFragment = new CloudFragment();
                    transaction.add(R.id.content, mCloudFragment);
                } else {
                    transaction.show(mCloudFragment);
                }

                break;
            case FRAGMENT_PROFILE:
                if (mProfileFragment == null) {
                    mProfileFragment = new ProfileFragment(this);
                    transaction.add(R.id.content, mProfileFragment);
                } else {
                    transaction.show(mProfileFragment);
                }
                break;
        }
        transaction.commit();
    }
    //隐藏
    private void hideFragment(FragmentTransaction transaction) {
        if (mMainFragment != null) {
            transaction.hide(mMainFragment);
        }
        if (mCloudFragment != null) {
            transaction.hide(mCloudFragment);
        }
        if (mProfileFragment != null) {
            transaction.hide(mProfileFragment);

        }
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mGuestManagerBinder = (GuestManagerBinder)service;
            LogUtils.myLod(MainActivity.class,"onServiceConnected","connected");
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            mGuestManagerBinder = null;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.navigation);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        showFragment(FRAGMENT_MAIN);

        //绑定GusetManagerService
        Intent guestManagerService = new Intent(this, GuestManagerService.class);
        bindService(guestManagerService,conn,BIND_AUTO_CREATE);
        if(mGuestManagerBinder == null){
            Log.d("MYTAG","is null");
        }

    }

}
