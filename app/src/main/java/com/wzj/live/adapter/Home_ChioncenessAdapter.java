package com.wzj.live.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wzj.live.R;
import com.wzj.live.entity.Liveing;

import java.util.List;

/**
 * Created by MR_Wang on 2017/3/9.
 */

public class Home_ChioncenessAdapter extends RecyclerView.Adapter<Home_ChioncenessAdapter.Home_ViewHolder>{
    private List<Liveing.ResultBean.ListBean> liveings;
    private LayoutInflater minflater;


    public Home_ChioncenessAdapter(List<Liveing.ResultBean.ListBean> datas) {
        this.liveings = datas;
    }

    @Override
    public Home_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        minflater=LayoutInflater.from(parent.getContext());
        return new Home_ViewHolder(minflater.inflate(R.layout.home_item,parent,false));
    }

    @Override
    public void onBindViewHolder(Home_ViewHolder holder, int position) {
        Liveing.ResultBean.ListBean listBean = liveings.get(position);
           holder.home_choiceness_item_head.setImageURI(Uri.parse(listBean.getUser().getUser_data().getAvatar()));
           holder.home_choiceness_item_img.setImageURI(Uri.parse(listBean.getData().getPic()));
           holder.home_choiceness_item_name.setText(listBean.getData().getLive_name());
           holder.home_choiceness_item_path.setText("北京");
            if("0".equals(listBean.getData().getStatus())) {
                holder.home_choiceness_item_state.setText("正在直播中");
            }else {
                holder.home_choiceness_item_state.setText("直播已结束");
            }
    }

    @Override
    public int getItemCount() {
        Log.e("TAG", "长度===="+liveings.size());
        return liveings.size();
    }


    class Home_ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        SimpleDraweeView home_choiceness_item_head;
        SimpleDraweeView home_choiceness_item_img;
        TextView home_choiceness_item_name;
        TextView home_choiceness_item_path;
        TextView home_choiceness_item_state;

        public Home_ViewHolder(View itemView) {
            super(itemView);
            home_choiceness_item_head = (SimpleDraweeView) itemView.findViewById(R.id.home_choiceness_item_head);
            home_choiceness_item_img = (SimpleDraweeView) itemView.findViewById(R.id.home_choiceness_item_img);
            home_choiceness_item_name = (TextView) itemView.findViewById(R.id.home_choiceness_item_name);
            home_choiceness_item_path = (TextView) itemView.findViewById(R.id.home_choiceness_item_path);
            home_choiceness_item_state = (TextView) itemView.findViewById(R.id.home_choiceness_item_state);
        }




        @Override
        public void onClick(View v) {

        }
    }


    /**
     * 添加数据
     */
    public void addData(List<Liveing.ResultBean.ListBean> datas){
        addData(0,datas);
    }

    /**
     * 添加数据
     */
    public void addData(int position ,List<Liveing.ResultBean.ListBean> datas){

        if(datas!=null&&datas.size()>0)
        liveings.addAll(datas);
        notifyItemRangeChanged(position,liveings.size());
    }



    /** l
     * 清空数据源
     */
    public void clearData(){
        int i=liveings.size();
        liveings.clear();
        notifyItemRangeChanged(0,liveings.size());
        //notifyDataSetChanged();
    }
}
