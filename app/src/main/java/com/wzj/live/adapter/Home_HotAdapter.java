package com.wzj.live.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wzj.live.R;
import com.wzj.live.adapter.base.BaseAdapterViewHolder;
import com.wzj.live.adapter.base.SimpleAdapter;
import com.wzj.live.entity.base.ListBean;

import java.util.List;

/**
 * Created by MR_Wang on 2017/3/9.
 */

public class Home_HotAdapter extends SimpleAdapter<ListBean> {
    private Context mContext;


    public Home_HotAdapter(Context context, List<ListBean> datas) {
        super(context,R.layout.home_item, datas);
        this.mContext=context;
    }


    @Override
    protected void convert(BaseAdapterViewHolder holder, ListBean item) {
        SimpleDraweeView view = (SimpleDraweeView) holder.getView(R.id.home_choiceness_item_head);


            view.setImageURI(item.getHeadIcon());

        holder.getTextView(R.id.home_choiceness_item_name).setText(item.getName());
        holder.getTextView(R.id.home_choiceness_item_path).setText(item.getPlace());
        ImageView img = holder.getImageView(R.id.home_choiceness_item_img);
        holder.getTextView(R.id.home_choiceness_item_state).setText(item.getStatus());

        //跳过内存缓存
        Glide.with( mContext ).load(item.getInformationImage()).skipMemoryCache(true).into(img);
    }
}
