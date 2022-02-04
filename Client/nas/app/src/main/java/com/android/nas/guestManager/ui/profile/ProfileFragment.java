package com.android.nas.guestManager.ui.profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.nas.util.LogUtils;
import com.android.nas.R;
import com.android.nas.MainActivity;


public class ProfileFragment extends Fragment {
    private MainActivity mMainActivity = null;
    public boolean isNeedLogin = true;
    public ProfileFragment(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.myLod(ProfileFragment.class,"onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtils.myLod(ProfileFragment.class,"onCreateView");
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }


}