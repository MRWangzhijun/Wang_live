package com.wzj.live.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.wzj.live.R;
import com.wzj.live.adapter.Home_ChioncenessAdapter;
import com.wzj.live.adapter.base.DividerItemDecoration;
import com.wzj.live.entity.Liveing;
import com.wzj.live.fragment.base.BaseFragment;
import com.wzj.live.utils.Contants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;


/**
 * Created by MR_Wang on 2017/3/8.
 *
 *
 *
 *
 *
 *
 */

public class Home_choiceness_Fragment extends BaseFragment {
    private static final int STATE_NORMAL=0;//正常状态
    private static final int STATE_REFRESH=1;//刷新
    private static final int STATE_MORE=2;//加载更多
    private static  int state=STATE_NORMAL;//默认状态是正常状态

    private int page=1;
    private int totalPag=3;
    private int pageSize=3;
    private int type=0;



    @BindView(R.id.choiceness_rv)
    RecyclerView mRecyclerView;

    @BindView(R.id.refresh)
    MaterialRefreshLayout mRefreshLayout;


    private  Home_ChioncenessAdapter mChAdapter;


    private List<Liveing.ResultBean.ListBean> list;



    public static Home_choiceness_Fragment newInstance() {
        Home_choiceness_Fragment fragment = new Home_choiceness_Fragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_choiceness_fragment, null);
        ButterKnife.bind(this, view);
        initRefreshLayout();
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        requestDataByPost();
       // initRefreshLayout();

    }


    /**
     * 请求数据通过Post请求
     */
    private void requestDataByPost() {

        Log.e("TAG", "page===" + page);

        OkHttpUtils
                .post()
                .url(Contants.BASE_LIVE)
                .addParams("page", String.valueOf(page))
                .addParams("type", String.valueOf(type))
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "请求失败==" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {

                        Gson gson = new Gson();
                        Liveing liveing = gson.fromJson(response, Liveing.class);

                        if (liveing != null) {
                            if (liveing.getResult() != null) {
                                if (liveing.getResult().getList() != null) {
                                    list = liveing.getResult().getList();
                                    showData();
                                }
                            }
                        }
                    }
                });
    }


    /**
     * 展示数据   通过不停的状态执行不同的数据展示形式
     *
     */
    private void showData() {
        switch (state) {
            case  STATE_NORMAL://首次显示创建适配器并 填充数据，设置到RecyclerView
                mChAdapter = new Home_ChioncenessAdapter( list);
                mRecyclerView.setAdapter(mChAdapter);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                        DividerItemDecoration.HORIZONTAL_LIST));
                break;
            case STATE_REFRESH://刷新数据时需要清空数据源并填充新的数据源，并刷新
                    mChAdapter.clearData();
                    mChAdapter.addData(list);
                    mRecyclerView.scrollToPosition(0);
                    mRefreshLayout.finishRefresh();
                break;

            case STATE_MORE://在原来的 基础上再次添加数据，并将数据定位到刷新后的位置
                    mChAdapter.addData(mChAdapter.getItemCount(), list);
                    mRecyclerView.scrollToPosition(mChAdapter.getItemCount());
                    mRefreshLayout.finishRefreshLoadMore();
                break;
        }
         }



     private void initRefreshLayout(){
         mRefreshLayout.setLoadMore(true);
         mRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
             @Override
             public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                 //下拉刷新...
                 refreshData();
             }

             @Override
             public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                 //上拉加载更多...
                 if(page<=totalPag) {
                     loadMoreData();
                 }else {
                     Toast.makeText(getContext(), "已经没有更多数据了", Toast.LENGTH_SHORT).show();
                     materialRefreshLayout.finishRefreshLoadMore();
                 }
             }
         });

                // 结束下拉刷新...
                 mRefreshLayout.finishRefresh();

                // 结束上拉刷新...
                mRefreshLayout.finishRefreshLoadMore();

    }


    @Override
    public void onStart() {
        super.onStart();
        state=STATE_NORMAL;
    }



    /**
     * 下拉刷新数据
     */
    private void refreshData(){
        page=1;
        state=STATE_REFRESH;
        requestDataByPost();
    }


    /**
     * 上拉加载更多
     */
    private void loadMoreData(){
        page+=1;
        state=STATE_MORE;
        requestDataByPost();
    }
}
