package com.wzj.live.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzj.live.R;
import com.wzj.live.entity.base.LiveBean;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by MR_Wang on 2017/2/22.
 */

public class BaseAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private SparseArray<View> views;
    protected BaseAdapter.OnItemClickListener mmOnItemClickListener;


    public BaseAdapterViewHolder(View itemView ,
                                 BaseAdapter.OnItemClickListener mOnItemClickListener) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.views=new SparseArray<>();
        this.mmOnItemClickListener=mOnItemClickListener;

    }

    private <V extends View> V retrieveView(int viewId){
        View view=views.get(viewId);
        //判断如果数组中有从数组中取，数组中没有就从findViewByid中取
        if(view==null) {
            view=itemView.findViewById(viewId);
            views.put(viewId,view);
        }
        return (V) view;

    }

    public  View getView(int viewId ){
      return   retrieveView(viewId);
    }
    public TextView getTextView(int viewId){
        return retrieveView(viewId);
    }
    public Button getButton(int viewId){
        return retrieveView(viewId);
    }
    public ImageView getImageView(int viewId){
        return retrieveView(viewId);
    }


    @Override
    public void onClick(View v) {
        if(mmOnItemClickListener !=null){
            //条目点击
            mmOnItemClickListener.onItemClick(v,getLayoutPosition());
        }
    }

    /**
     * Created by MR_Wang on 2017/2/22.
     */

    public static class WaresAdapter extends SimpleAdapter<LiveBean> {

        public WaresAdapter(Context context, List<LiveBean> datas) {
            super(context, R.layout.home_item, datas);
        }

        @Override
        protected void convert(BaseAdapterViewHolder holder, LiveBean item) {
            CircleImageView draweeView= (CircleImageView) holder.getView(R.id.home_choiceness_item_head);
           // draweeView.setImageURI(Uri.parse(item.getList().get()));
    //      holder.getTextView(R.id.hot_wares_img_sdv)



        }
    }
}
