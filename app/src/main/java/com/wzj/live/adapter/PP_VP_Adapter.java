package com.wzj.live.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.List;

/**
 * Created by MR_Wang on 2017/3/13.
 */

public class PP_VP_Adapter extends PagerAdapter{
    private Context context;
    private List<GridView> list;
    private List<List> sublist;


    public PP_VP_Adapter(Context context, List<GridView> list,List<List> sublist) {
        this.context = context;
        this.list = list;
        this.sublist=sublist;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView(list.get(position));
    }

    @Override
    public Object instantiateItem(final ViewGroup container, int position) {

        Log.e("TAG", "1111111111111111111111");
        GridView gridView = list.get(position);
        gridView.setNumColumns(3);
        gridView.setAdapter(new GvAdapter(context,sublist.get(position)));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // context.showGift(position+"");
                Log.e("TAG", "postion");
            }

        });

        container.addView(list.get(position));
        return list.get(position);
    }




}
