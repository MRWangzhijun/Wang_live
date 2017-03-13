package com.wzj.live.adapter;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by MR_Wang on 2017/2/22.
 */

public class ViewPage_adapter extends PagerAdapter {
    private List<ImageView> list;

    public ViewPage_adapter(List<ImageView> lists) {
        this.list = lists;
    }

    //获取当前窗体界面数
    @Override
    public int getCount() {
        Log.e("TAG", "list============="+list);
        return list.size();
    }


    //判断是否由对象生成界面
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    //在Viewpager中添加显示控件
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
            container.addView(list.get(position));
        return list.get(position    );
    }


    //移除控件
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
