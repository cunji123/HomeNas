package com.android.nas.guestManager.ui.cloud;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.nas.util.LogUtils;
import com.android.nas.R;

public class CloudFragment extends Fragment {

    public CloudFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.myLod(CloudFragment.class,"onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtils.myLod(CloudFragment.class,"onCreateView");
        return inflater.inflate(R.layout.fragment_cloud, container, false);
    }
}