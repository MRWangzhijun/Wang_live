package com.wzj.live.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wzj.live.R;


/**
 * Created by MR_Wang on 2017/3/8.
 */

public class MyFragment extends Fragment {


    public static MyFragment newInstance() {
        MyFragment fragment = new MyFragment();
        Log.e("TAG", "2222222222222222222222");
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View  view=inflater.inflate(R.layout.my_fragment,null);




        return view;
    }
}
