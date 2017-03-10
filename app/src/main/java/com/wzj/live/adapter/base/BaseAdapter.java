package com.wzj.live.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MR_Wang on 2017/2/22.
 */

public abstract class BaseAdapter<T,H extends BaseAdapterViewHolder> extends RecyclerView.Adapter<BaseAdapterViewHolder> {

    protected List<T> datas;
    private T item;
    protected  final Context mContext;
    protected  int mLayoutResId;
    private  OnItemClickListener mOnItemClickListener=null;

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public BaseAdapter(List<T> datas, Context mContext, int mLayoutResId){
        this.datas=datas==null? new ArrayList<T>():datas;
        this.mContext=mContext;
        this.mLayoutResId= mLayoutResId;
    }

    @Override
    public BaseAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(mLayoutResId,parent,false);
        return new BaseAdapterViewHolder(view,mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(BaseAdapterViewHolder holder, int position) {
        T item = getItem(position);
        convert((H)holder,item);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    protected  abstract void convert(H holder,T item);


    //得到泛型类型
    public T getItem(int position) {
        return position>=datas.size()? null:datas.get(position);
    }


    public interface OnItemClickListener{
        void onItemClick(View view, int psition);
    }

    /**
     * 添加数据
     */
    public void addData(List<T> list){
        addData(0,list);
        Log.e("TAG", "添加的集合=="+list);
    }

    public void addData(int position,List<T> list){
        if(list!=null&&list.size()>=0) {
            datas.addAll(list);
            notifyItemRangeChanged(position,datas.size());
        }
    }

    /**
     * 清空数据源
     */
    public void clearData(){
        int i=datas.size();
        datas.clear();
        notifyItemRangeChanged(0,i);
        //notifyDataSetChanged();
    }


    /**
     *  刷新
     * @param list
     */
    public void refreshData(List<T> list){
        if(list!=null&&list.size()>0) {
            clearData();
            int size=list.size();
            for(int i = 0; i < size; i++) {
              datas.add(i,list.get(i));
                notifyItemInserted(i);
            }
        }
    }

    /**
     * 加载更多
     * @param list
     */
    public void loadMoreData(List<T> list){
        if(list!=null&&list.size()>0){
            int size=list.size();
            int begin=datas.size();
            for(int i = 0; i < size; i++) {
              datas.add(list.get(i));
               notifyItemInserted(i+begin);
            }
        }
    }

}
