package com.wzj.live.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wzj.live.R;
import com.wzj.live.adapter.base.BaseAdapterViewHolder;
import com.wzj.live.adapter.base.SimpleAdapter;
import com.wzj.live.entity.Liveing;

import java.util.List;

/**
 * Created by MR_Wang on 2017/3/9.
 */

public class Home_HotAdapter extends SimpleAdapter<Liveing.ResultBean.ListBean> {
    private Context mContext;


    public Home_HotAdapter(Context context, List<Liveing.ResultBean.ListBean> datas) {
        super(context,R.layout.home_item, datas);
        this.mContext=context;
    }


    @Override
    protected void convert(BaseAdapterViewHolder holder, Liveing.ResultBean.ListBean item) {
        SimpleDraweeView view = (SimpleDraweeView) holder.getView(R.id.home_choiceness_item_head);


            view.setImageURI(item.getUser().getUser_data().getAvatar());

        holder.getTextView(R.id.home_choiceness_item_name).setText(item.getUser().getUser_data().getUser_name());
        holder.getTextView(R.id.home_choiceness_item_path).setText(item.getData().getLive_name());
        ImageView img = holder.getImageView(R.id.home_choiceness_item_img);
        holder.getTextView(R.id.home_choiceness_item_state).setText(((item.getData().getStatus()==0)?"正在直播中":"直播已结束"));

        //跳过内存缓存
        Glide.with( mContext ).load(item.getData().getPic()).skipMemoryCache(true).into(img);
    }
}
