package com.wzj.live.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import com.wzj.live.adapter.Home_HotAdapter;
import com.wzj.live.adapter.base.BaseAdapter;
import com.wzj.live.adapter.base.DividerItemDecoration;
import com.wzj.live.entity.Liveing;
import com.wzj.live.fragment.base.BaseFragment;
import com.wzj.live.fun_sdk.NetDbAdapter;
import com.wzj.live.fun_sdk.Settings;
import com.wzj.live.fun_sdk.TextureVideoActivity;
import com.wzj.live.fun_sdk.TextureVodActivity;
import com.wzj.live.utils.Contants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;


/**
 * Created by MR_Wang on 2017/3/8.
 */

public class Home_hot_Fragment extends BaseFragment {
    private static final int STATE_NORMAL=0;//正常状态
    private static final int STATE_REFRESH=1;//刷新
    private static final int STATE_MORE=2;//加载更多
    private static  int state=STATE_NORMAL;//默认状态是正常状态

    @BindView(R.id.choiceness_refresh)
    MaterialRefreshLayout mRefreshLayout;
    @BindView(R.id.choiceness_rv)
    RecyclerView mRecyclerView;


    private Home_HotAdapter mChAdapter;
    private List<Liveing.ResultBean.ListBean> list;

    private int page=0;
    private int type=1;
    private int totalPag=3;

    private NetDbAdapter NetDb;
    private SharedPreferences settings;



    public static Home_hot_Fragment newInstance() {
        Home_hot_Fragment fragment = new Home_hot_Fragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_hot_fragment, null);
        ButterKnife.bind(this,view);
        requestDataByPost();
        initRefreshLayout();
        return view;
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
                if(mChAdapter==null) {
                    mChAdapter = new Home_HotAdapter(getContext(), list);
                }else {
                    mChAdapter.clearData();
                    mChAdapter.addData(list);
                }
                mRecyclerView.setAdapter(mChAdapter);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                        DividerItemDecoration.HORIZONTAL_LIST));
                mChAdapter.setmOnItemClickListener(new BaseAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int psition) {
                        String path ="rtmp://live.hkstv.hk.lxdns.com/live/hks";
                        NetDb = new NetDbAdapter(getActivity());
                        NetDb.open();

                        if (NetDb.getData(path)) {
                            NetDb.updateData(path);
                        } else {
                            NetDb.createDate(path);
                        }
                        NetDb.close();
                        String playerType = "live";
                        if (playerType.equals(Settings.VOD)) {
                            Intent intent = new Intent(getActivity(), TextureVodActivity.class);
                            intent.putExtra("path", path);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(getActivity(), TextureVideoActivity.class);
                            intent.putExtra("path", path);
                            startActivity(intent);
                        }
                    }
                });

                break;
            case STATE_REFRESH://刷新数据时需要清空数据源并填充新的数据源，并刷新
                    mChAdapter.clearData();
                    mChAdapter.addData(list);
                   // mRecyclerView.scrollToPosition(0);
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
        requestDataByPost();
    }


    @Override
    public void onResume() {
        super.onResume();


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
