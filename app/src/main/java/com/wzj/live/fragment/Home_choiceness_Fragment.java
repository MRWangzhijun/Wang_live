package com.wzj.live.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cjj.MaterialRefreshLayout;
import com.google.gson.reflect.TypeToken;
import com.wzj.live.R;
import com.wzj.live.adapter.Home_ChioncenessAdapter;
import com.wzj.live.adapter.base.DividerItemDecoration;
import com.wzj.live.entity.base.ListBean;
import com.wzj.live.entity.base.PageResult;
import com.wzj.live.fragment.base.BaseFragment;
import com.wzj.live.http.OkHttpHelper;
import com.wzj.live.utils.Contants;
import com.wzj.live.utils.PageUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by MR_Wang on 2017/3/8.
 */

public class Home_choiceness_Fragment extends BaseFragment {
    private static final int STATE_NORMAL=0;//正常状态
    private static final int STATE_REFRESH=1;//刷新
    private static final int STATE_MORE=2;//加载更多
    private static  int state=STATE_NORMAL;//默认状态是正常状态

    @BindView(R.id.choiceness_refresh)
    MaterialRefreshLayout mRefreshLayout;
    @BindView(R.id.choiceness_rv)
    RecyclerView mRecyclerView;


    private Home_ChioncenessAdapter home_chioncenessAdapter;
    private OkHttpHelper okHttpHelper=OkHttpHelper.getInstance();

    private int CarrPage=1;
    private int totalPag=1;
    private int pageSize=5;



    public static Home_choiceness_Fragment newInstance() {
        Home_choiceness_Fragment fragment = new Home_choiceness_Fragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_choiceness_fragment, null);
        ButterKnife.bind(this, view);

        PageUtils pageUtils=PageUtils.newBuilder()
                .setUrl(Contants.URL)
                .setLoadMore(true)
                .setOnPageListener(new PageUtils.onPageListener<ListBean>() {
                    @Override
                    public void load(List datas, int totalPage, int totalCount) {
                        if (home_chioncenessAdapter == null) {
                            home_chioncenessAdapter = new Home_ChioncenessAdapter(getContext(), datas);
                        } else {
                            home_chioncenessAdapter.clearData();
                            home_chioncenessAdapter.addData(datas);
                        }

//                        home_chioncenessAdapter.setmOnItemClickListener(new BaseAdapter.OnItemClickListener() {
//                            @Override
//                            public void onItemClick(View view, int psition) {
//                                ListBean bean=home_chioncenessAdapter.getItem(psition);
//                                Intent intent=new Intent(getActivity(), WareDetailActivity.class);
//                                intent.putExtra(Contants.URL,wares);
//                                startActivity(intent);
//                            }
//                        });

                        mRecyclerView.setAdapter(home_chioncenessAdapter);
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
                    }

                    @Override
                    public void refresh(List datas, int totalPage, int totalCount) {
                        home_chioncenessAdapter.clearData();
                        home_chioncenessAdapter.addData(datas);
                        mRecyclerView.scrollToPosition(0);
                    }

                    @Override
                    public void loadMore(List datas, int totalPage, int totalCount) {
                        home_chioncenessAdapter.addData(home_chioncenessAdapter.getItemCount(),datas);
                        mRecyclerView.scrollToPosition(home_chioncenessAdapter.getItemCount());
                    }
                })
                .setPageSize(20)
                .setRefreshLayout(mRefreshLayout)
                .build(getContext(),new TypeToken<PageResult<ListBean>>(){}.getType());
        pageUtils.request();
        return view;

    }
}
