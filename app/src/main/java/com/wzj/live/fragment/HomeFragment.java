package com.wzj.live.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wzj.live.R;
import com.wzj.live.fragment.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by MR_Wang on 2017/3/8.
 */

public class HomeFragment extends BaseFragment {
     @BindView(R.id.tabs)
     TabLayout mTabLayout;

     @BindView(R.id.vp_view)
     ViewPager mViewPager;

    private List<Fragment> mFragmentList = new ArrayList<>();//页卡视图集合
    private List<String>   title = new ArrayList<>();//Tab集合
    private FragmentPagerAdapter mAdapter;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Log.e("TAG", "111111111111111111111");
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, null);
        ButterKnife.bind(this,view);
        onCreated(view,savedInstanceState);
        return view;
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden) {
            Log.e("TAG", "隐藏了吧");
        }else {
            Log.e("TAG", "显示了吧");
        }
    }

    public void onCreated(View view, Bundle savedInstanceState) {
        Home_choiceness_Fragment view1 = Home_choiceness_Fragment.newInstance();
        Home_hot_Fragment        view2 = Home_hot_Fragment.newInstance();

        title.add("精选");
        title.add("发现");

        //添加页卡视图
        mFragmentList.add(view1);
        mFragmentList.add(view2);

        mAdapter = new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return mFragmentList.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public CharSequence getPageTitle(int position) {

                return title.get(position);
            }
        };
        mViewPager.setAdapter(mAdapter);//给ViewPager设置适配器
        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
    }
}
