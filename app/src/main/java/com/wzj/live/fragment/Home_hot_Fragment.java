package com.wzj.live.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wzj.live.R;
import com.wzj.live.fragment.base.BaseFragment;


/**
 * Created by MR_Wang on 2017/3/8.
 */

public class Home_hot_Fragment extends BaseFragment {

    public static Home_hot_Fragment newInstance() {
        Home_hot_Fragment fragment = new Home_hot_Fragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_hot_fragment, null);
        return view;
    }



}
