package com.wzj.live.adapter;

import android.content.Context;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wzj.live.R;
import com.wzj.live.adapter.base.BaseAdapterViewHolder;
import com.wzj.live.adapter.base.SimpleAdapter;
import com.wzj.live.entity.TempBean;

import java.util.List;

/**
 * Created by MR_Wang on 2017/3/9.
 */

public class Home_Live_Two_User_Adapter extends SimpleAdapter<TempBean> {
    private Context mContext;


    public Home_Live_Two_User_Adapter(Context context, List<TempBean> datas) {
        super(context,R.layout.home_live_two_rl_user_item, datas);
        this.mContext=context;
    }


    @Override
    protected void convert(BaseAdapterViewHolder holder, TempBean item) {
        SimpleDraweeView head = (SimpleDraweeView) holder.getView(R.id.homw_live_two_rl_uhead);
        head.setImageURI(item.getUrl());
    }
}
