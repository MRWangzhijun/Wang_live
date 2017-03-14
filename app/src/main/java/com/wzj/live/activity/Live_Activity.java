package com.wzj.live.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.wzj.live.R;
import com.wzj.live.activity.base.BaseActivity;
import com.wzj.live.fragment.Fragment_Three;
import com.wzj.live.fragment.Framgnet_One;
import com.wzj.live.fragment.Framgnet_Two;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MR_Wang on 2017/3/12.
 */

public class Live_Activity extends BaseActivity{


    @BindView(R.id.live_mViewPager)
    ViewPager mViewPager;
    FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragmentList = new ArrayList<>();//页卡视图集合


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.live_activity);
        ButterKnife.bind(this);
        initViewPager();
        Intent intent = getIntent();
        int page = intent.getIntExtra("viewPager",0);
        mViewPager.setCurrentItem(page);//定位到某个界面

    }


    private void initViewPager(){
        Framgnet_One view1 = new Framgnet_One();
        Framgnet_Two view2 =new Framgnet_Two();

        Fragment_Three view3=new Fragment_Three();
        //添加页卡视图
        mFragmentList.add(view1);
       // mFragmentList.add(view2);
        mFragmentList.add(view3);

        mAdapter = new FragmentPagerAdapter(this.getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return mFragmentList.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

        };
        mViewPager.setAdapter(mAdapter);//给ViewPager设置适配器
    }


}
