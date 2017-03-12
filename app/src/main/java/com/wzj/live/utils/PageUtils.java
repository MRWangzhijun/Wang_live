package com.wzj.live.utils;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.wzj.live.entity.base.PageResult;
import com.wzj.live.http.OkHttpHelper;
import com.wzj.live.http.SpotsCallBack;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Response;

/**
 * Created by LYM on 2017/2/28.
 */

public class PageUtils {
    private static  Builder builder;


    private Context context;
    private Type type;
    private String url;
    private MaterialRefreshLayout refreshLayout;
    private boolean canLoadMore;
    private onPageListener onPageListener;

    private int totalPage;
    private int pageIndex=1;
    private int pageSize;

    private HashMap<String,Object> params=new HashMap<>(5);

    private static final int STATE_NORMAL = 0;
    private static final int STATE_REFRESH = 1;
    private static final int STATE_MORE = 2;

    private int state = STATE_NORMAL;

    private OkHttpHelper okHttpHelper;


    private PageUtils(){
        okHttpHelper = OkHttpHelper.getInstance();
    }

    public static Builder newBuilder(){
        builder=new Builder();
        return builder;
    }

    private  void initRefreshLayout(){
        this.refreshLayout.setLoadMore(this.canLoadMore);
        this.refreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                refreshData();;
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                Log.e("LYM_TAG", "pageIndex="+pageIndex+"totalPage="+totalPage+"pageSize="+pageSize);
                if(pageIndex<totalPage) {
                    loadMore();
                }else {
                    Toast.makeText(context, "服务器没更多数据了", Toast.LENGTH_SHORT).show();
                    refreshLayout.finishRefreshLoadMore();
                }
            }
        });
    }

    private void refreshData(){
        this.pageIndex=1;
        state=STATE_REFRESH;
        requestData();

    }

    private void loadMore(){
        this.pageIndex+=1;
        state=STATE_MORE;
        requestData();
    }

//    private void requestData() {
//        String url=BuildUrl();
//        Log.d("TAG", "请求路径："+url);
//        okHttpHelper.get(url,new RequestCallBack(this.context));
//    }




    private void requestData() {
        String url=BuildUrl();
        Log.d("TAG", "请求路径："+url);
        //okHttpHelper.post(url,buildPostMap(),new RequestCallBack(this.context));
    }


    private String BuildUrl(){
        return this.url.toString();
    }

//    private String buildUrlParams(){
//        HashMap<String,Object> map=this.params;
//        map.put("curPage",this.pageIndex);
//        map.put("pageSize",this.pageSize);
//        StringBuffer sb=new StringBuffer();
//        for (Map.Entry<String,Object> entry:map.entrySet()){
//            sb.append(entry.getKey()+"="+entry.getValue());
//            sb.append("&");
//        }
//        String s=sb.toString();
//        if(s.endsWith("&")) {
//            s=s.substring(0,s.length()-1);
//        }
//        return s;
//    }


    /**
     * get请求添加参数
     * @return
     */
    private Map buildUrlParams(){
        HashMap<String,Object> map=this.params;
        map.put("page",this.pageIndex);
        map.put("type",this.type);
        StringBuffer sb=new StringBuffer();
        for (Map.Entry<String,Object> entry:map.entrySet()){
            sb.append(entry.getKey()+"="+entry.getValue());
            sb.append("&");
        }
        String s=sb.toString();
        if(s.endsWith("&")) {
            s=s.substring(0,s.length()-1);
        }
        return map;
    }


    /**
     * 显示数据
     * @param datas
     * @param totalPage
     * @param totalCount
     * @param <T>
     */
    private<T> void showData(List<T> datas,int totalPage,int totalCount) {
        switch (state){
            case STATE_NORMAL:
                if(this.onPageListener!=null) {
                    this.onPageListener.load(datas,totalPage,totalCount);
                }
                break;
            case STATE_REFRESH:
                if(this.onPageListener!=null) {
                    this.onPageListener.refresh(datas,totalPage,totalCount);
                }
                this.refreshLayout.finishRefresh();
                break;
            case STATE_MORE:
                if(this.onPageListener!=null) {
                    this.onPageListener.loadMore(datas,totalPage,totalCount);
                }
                this.refreshLayout.finishRefreshLoadMore();
                break;

        }
    }





    public void request(){
        requestData();
    }

    public void putParam(String key,Object value){
        builder.setParam(key,value);
    }

    public interface onPageListener<T>{
        void load(List<T> datas, int totalPage, int totalCount);
        void refresh(List<T> datas, int totalPage, int totalCount);
        void loadMore(List<T> datas, int totalPage, int totalCount);
    }


    public static class Builder{
        private Context context;
        private Type type;
        private String url;
        private MaterialRefreshLayout refreshLayout;
        private boolean canLoadMore;
        private onPageListener onPageListener;


        private int totalPage=1;
        private int pageIndex=1;
        private int pageSize=10;

        private HashMap<String,Object> params=new HashMap<>(5);

        public Builder setOnPageListener(onPageListener onPageListener){
            this.onPageListener=onPageListener;
            return builder;
        }

        public Builder setUrl(String url){
            this.url=url;
            return builder;
        }

        public Builder setParam(String key,Object value){
            params.put(key,value);
            return builder;
        }

        public Builder setPageSize(int pageSize){
            this.pageSize=pageSize;
            return builder;
        }

        public Builder setRefreshLayout(MaterialRefreshLayout refreshLayout){
            this.refreshLayout=refreshLayout;
            return builder;
        }

        public Builder setLoadMore(boolean loadMore){
            this.canLoadMore=loadMore;
            return builder;
        }



        public PageUtils build(Context context,Type type){
            this.type=type;
            this.context=context;
            volidata();
            PageUtils pageUtils=new PageUtils();
            pageUtils.context=this.context;
            pageUtils.type=this.type;
            pageUtils.url=this.url;
            pageUtils.refreshLayout=this.refreshLayout;
            pageUtils.canLoadMore=this.canLoadMore;
            pageUtils.onPageListener=this.onPageListener;
            pageUtils.totalPage=this.totalPage;
            pageUtils.pageIndex=this.pageIndex;
            pageUtils.pageSize=this.pageSize;
            pageUtils.params=this.params;
            pageUtils.initRefreshLayout();
            return pageUtils;
        }

        private  void volidata(){
            if(context==null) {
                throw new RuntimeException("Context不能为空");
            }
            if(this.url==null||"".equals(this.url)) {
                throw new RuntimeException("url不能为空");
            }
            if(this.refreshLayout==null) {
                throw new RuntimeException("MaterialRefreshLayout不能为空");
            }
        }

    }

    class RequestCallBack<T> extends SpotsCallBack<PageResult<T>> {

        public RequestCallBack(Context mContext) {
            super(mContext);
            super.mType=builder.type;
        }

        @Override
        public void onSuccess(Response response, PageResult<T> pageResult) {
            Log.d("TAG", "===请求成功===");
            pageIndex=pageResult.getCurrentPage();
            totalPage=pageResult.getTotalPage();
            showData(pageResult.getList(),builder.totalPage,pageResult.getTotalCount());

        }

        @Override
        public void onError(Response response, int code, Exception e) {
            Log.d("TAG", "===请求失败===");
        }
    }


}
