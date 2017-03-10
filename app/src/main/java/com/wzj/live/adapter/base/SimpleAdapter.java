package com.wzj.live.adapter.base;

import android.content.Context;

import java.util.List;

/**
 * Created by MR_Wang on 2017/2/22.
 */

public  abstract  class SimpleAdapter<T> extends BaseAdapter<T,BaseAdapterViewHolder> {

    public SimpleAdapter(Context context, int layoutId , List<T> datas){
        super(datas,context,layoutId);
    }
}
