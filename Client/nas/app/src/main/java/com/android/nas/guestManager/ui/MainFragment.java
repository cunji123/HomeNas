package com.android.nas.guestManager.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.nas.util.LogUtils;
import com.android.nas.R;

public class MainFragment extends Fragment {
    public MainFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.myLod(MainFragment.class,"onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtils.myLod(MainFragment.class,"onCreateView");
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}