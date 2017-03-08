package com.wzj.live.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wzj.live.R;
import com.wzj.live.adapter.base.MyAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by MR_Wang on 2017/3/8.
 */

public class HomeFragment extends Fragment {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private LayoutInflater mInflater;
    private List<String> mTitleList = new ArrayList<>();//页卡标题集合
    private View view1, view2;//页卡视图
    private List<View> mViewList = new ArrayList<>();//页卡视图集合


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, null);
        initView(savedInstanceState, view);

        return view;
    }


    protected void initView(Bundle savedInstanceState, View view) {
        super.onCreate(savedInstanceState);

        mViewPager = (ViewPager) view.findViewById(R.id.vp_view);
        mTabLayout = (TabLayout) view.findViewById(R.id.tabs);

        mInflater = LayoutInflater.from(getContext());
        view1 = mInflater.inflate(R.layout.home_choiceness_fragment, null);
        view2 = mInflater.inflate(R.layout.home_hot_fragment, null);

        //添加页卡视图
        mViewList.add(view1);
        mViewList.add(view2);


        //添加页卡标题
        mTitleList.add("精选");
        mTitleList.add("热门");


        mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(0)));//添加tab选项卡
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(1)));


        MyAdapter mAdapter = new MyAdapter(mViewList, mTitleList);
        mViewPager.setAdapter(mAdapter);//给ViewPager设置适配器
        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
        mTabLayout.setTabsFromPagerAdapter(mAdapter);//给Tabs设置适配器

    }


}
