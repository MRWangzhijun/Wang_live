package com.wzj.live.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.wzj.live.R;

import java.util.List;

/**
 * Created by MR_Wang on 2017/3/13.
 */
public class GvAdapter extends BaseAdapter {
    private Context context;
    private List datas;


    public GvAdapter(Context context, List datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder  vh;

        if(convertView==null) {
            convertView=View.inflate(context,R.layout.gift_item,null);
            vh=new ViewHolder();
            vh.iv= (ImageView) convertView.findViewById(R.id.gift_item_iv);
            convertView.setTag(vh);
        }else {
            vh= (ViewHolder) convertView.getTag();
        }
            vh.iv.setImageResource((Integer) datas.get(position));
        return convertView;
    }


    class ViewHolder{
        ImageView iv;
    }
}
