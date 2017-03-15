package com.wzj.live.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wzj.live.R;
import com.wzj.live.adapter.Home_Live_Two_Im_Adapter;
import com.wzj.live.adapter.Home_Live_Two_User_Adapter;
import com.wzj.live.adapter.base.DividerItemDecoration;
import com.wzj.live.entity.TempBean;
import com.wzj.live.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import tyrantgit.widget.HeartLayout;

/**
 * Created by MR_Wang on 2017/3/12.
 */

public class Framgnet_Two extends Fragment implements View.OnClickListener {

    /**
     * 临时数据
     */
    private String hard_url="https://tower.im/assets/default_avatars/jokul.jpg";




    @BindView(R.id.live_tow_im)
    ImageView live_tow_im_img;

    @BindView(R.id.live_two_love)
    ImageView live_two_love;

    @BindView(R.id.live_tow_gift)
    ImageView live_tow_gift;

    @BindView(R.id.live_two_head)
    SimpleDraweeView live_two_head;

    @BindView(R.id.live_tow_rl)
    RecyclerView live_tow_rl;


    @BindView(R.id.live_tow_im_rl)
    RecyclerView live_tow_im_rl;

    @BindView(R.id.two_fragment_hear)
    HeartLayout two_fragment_hear;

    @BindView(R.id.two_fragment_rl)
    RelativeLayout relativeLayout;


    private Handler handler=new Handler();
    private Home_Live_Two_User_Adapter mTwo_user_adapter;
    private Home_Live_Two_Im_Adapter   mTwo_im_adapter;
    // 图片封装为一个数组
    private List icon=new ArrayList() ;
    private PopupWindow pop;
    private View popupView;
    private static final int NUM=9;

    ViewPager vp;

    private List<GridView> mFragments = new ArrayList<>();
    private View pPview;
    private int count;
    private List<List> sublist;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view=inflater.inflate(R.layout.two_fragment,container,false);
        ButterKnife.bind(this,view);
        initData();
        initListener();
        getLove();
        return view;
    }



    private void initListener(){

        live_tow_gift.setOnClickListener(this);
        live_tow_im_rl.setOnClickListener(this);

        popupView = getActivity().getLayoutInflater().inflate(R.layout.layout_popupwindow, null);

        pop = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setOutsideTouchable(true);
        pop.setFocusable(true);// 点击back退出pop
        pop.setAnimationStyle(R.style.add_new_style);
        pop.setBackgroundDrawable(new ColorDrawable(0x33ff0000));// 设置背景透明，点击back退出pop
    }

    private void initData(){
        live_two_head.setImageURI(hard_url);

        mTwo_user_adapter=new Home_Live_Two_User_Adapter(getContext(),getHeadData());

        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        live_tow_rl.setLayoutManager(linearLayoutManager);
        live_tow_rl.setItemAnimator(new DefaultItemAnimator());
        live_tow_rl.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.HORIZONTAL_LIST));
        live_tow_rl.setAdapter(mTwo_user_adapter);



        mTwo_im_adapter=new Home_Live_Two_Im_Adapter(getContext(),getHeadData());
        live_tow_im_rl.setLayoutManager(new LinearLayoutManager(getContext()));
        live_tow_im_rl.setItemAnimator(new DefaultItemAnimator());
        live_tow_im_rl.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.HORIZONTAL_LIST));
        live_tow_im_rl.setAdapter(mTwo_im_adapter);


        pPview = LayoutInflater.from(getActivity()).inflate(R.layout.layout_popupwindow, null, false);

        vp= (ViewPager) pPview.findViewById(R.id.pop_wn);

    }


    /**
     * 获取随机颜色
     * @return
     */
    private int randomColor() {
        return Color.rgb(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
    }


    private void getLove(){
        two_fragment_hear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                two_fragment_hear.addHeart(randomColor());


            }
        });




        Timer timer=new Timer(true);
        TimerTask task=new TimerTask(){
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        two_fragment_hear.addHeart(randomColor());
                    }
                });
            }
        };
        timer.schedule(task,500,500);
    }
    private List<TempBean> getHeadData() {

        List<TempBean>  list=new ArrayList<>();
        TempBean tempBean=new TempBean();
        for(int i = 0; i < 100; i++) {

            tempBean.setUrl("http://bj.c.tedu.cn/zt/tg/images/cyy_03.jpg");
            tempBean.setMessage("你谁啊？直播不错啊");
           list.add(i,tempBean);
        }
        return list;

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.live_tow_gift :
                ToastUtils.show(getActivity(),"进来了");
                showBottomDialog(v);
                break;
            case R.id.live_tow_im_rl:
                showBottomDialog(v);
                break;
        }
    }

    /**
     * 显示底部Dialog
     *
     * @param view 视图
     */
    public void showBottomDialog(View view) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        BottomDialogFragment editNameDialog = new BottomDialogFragment();
        editNameDialog.show(fm, "fragment_bottom_dialog");
    }


//    private void getPoPWinData(){
//        for (int i=0;i<30;i++){
//            icon.add(R.mipmap.xiaoying001);
//        }
//        List templist=null;
//        count = icon.size() / NUM + (icon.size() % NUM == 0 ? 0 : 1);
//
//
//        Log.e("TAG", "几页"+ count);
//        sublist = new ArrayList();
//
//        int a=0;
//        for (int i = 0; i <= count; i++) {
//
//            if(i==0) {
//                templist=icon.subList(a,i+NUM-1);
//                sublist.add(templist);
//            }else {
//                templist=icon.subList(a,a+NUM);
//                sublist.add(templist);
//            }
//            a=i+NUM;
//        }
//    }
//
//
//    private void initPopWinData() {
//        getPoPWinData();
//        ImageView img;
//        int [] image={R.mipmap.xiaoying003,R.mipmap.xiaoying006,R.mipmap.xiaoying007};
//        List alist = new ArrayList<>();
//        for(int i = 0; i < image.length; i++) {
//            img=new ImageView(getActivity());
//            img.setImageResource(image[i]);
//            alist.add(img);
//        }
//
//        Log.e("TAG", "集合==="+sublist.toString());
//
//        for (int i = 0; i < count; i++) {
//            GridView gv = new GridView(getActivity());
//            mFragments.add(gv);
//        }
//        //PP_VP_Adapter scalePagerAdapter = new PP_VP_Adapter(getActivity(),alist);
//        ViewPage_adapter adapter=new ViewPage_adapter(alist);
//        vp.setAdapter(adapter);
//    }

}


