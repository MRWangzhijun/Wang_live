package com.wzj.live.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wzj.live.R;
import com.wzj.live.activity.Login_Activity;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by MR_Wang on 2017/3/8.
 */

public class MyFragment extends Fragment {
    @BindView(R.id.login)
    Button login;


    public static MyFragment newInstance() {
        MyFragment fragment = new MyFragment();
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View  view=inflater.inflate(R.layout.my_fragment,null);
        ButterKnife.bind(this,view);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Login_Activity.class));
            }
        });



        return view;
    }
}
