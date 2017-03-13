package com.wzj.live.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wzj.live.R;
import com.wzj.live.adapter.PP_VP_Adapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 底部的DialogFragment
 * <p/>
 * Created by wangchenlong on 16/6/20.
 */
public class BottomDialogFragment extends DialogFragment {

    // 礼物类别

    @BindView(R.id.pop_wn)
    ViewPager vp;

    private List icon=new ArrayList();
    private int count;
    private int NUM=9;
    private List  sublist;
    private List<GridView> mFragments=new ArrayList<>();
    private ArrayList<LinearLayout> mLayouts; // 框架
    private ArrayList<TextView> mTvTypes; // 文字类型
    private ArrayList<ImageView> mIvTypes; // 图像类型
    private int mType = 0; // 当前类型
    private int mCoinCount = 2310; // 当前金币数

    @NonNull @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(getActivity(), R.style.BottomDialog);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(R.layout.fragment_dialog);
        dialog.setCanceledOnTouchOutside(true); // 外部点击取消

        // 设置宽度为屏宽, 靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        window.setAttributes(lp);

        ButterKnife.bind(this, dialog); // Dialog即View

        initClickTypes();
        initPopWinData();
        Log.e("TAG", "vp==="+vp);


        return dialog;
    }

    /**
     * 初始化点击类型
     */
    private void initClickTypes() {
        initViewArray(); // 初始化控件组
        initLayout(); // 初始化布局

    }

    /**
     * 初始化布局
     */
    private void initLayout() {
        chooseRegardsType(mType); // 选择默认类型

        for (int i = 0; i < mLayouts.size(); i++) {
            final int tmp = i;
            LinearLayout ll = mLayouts.get(i);
            ll.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    mType = tmp;
                    chooseRegardsType(mType);
                }
            });
        }
    }


    /**
     * 初始化类型数组, 文字和图片
     */
    private void initViewArray() {
        mLayouts = new ArrayList<>();
        mTvTypes = new ArrayList<>();
        mIvTypes = new ArrayList<>();
    }

    /**
     * 选择类型
     *
     * @param type 类型
     */
    private void chooseRegardsType(int type) {
        int size = mTvTypes.size();
        for (int i = 0; i < size; ++i) {
            if (i != type) {
                mTvTypes.get(i).setEnabled(true);
                mIvTypes.get(i).setEnabled(true);
            } else {
                mTvTypes.get(i).setEnabled(false);
                mIvTypes.get(i).setEnabled(false);
            }
        }
    }

    private void getPoPWinData(){
        for (int i=0;i<30;i++){
            icon.add(R.mipmap.xiaoying001);
        }
        List templist=null;
        count = icon.size() / NUM + (icon.size() % NUM == 0 ? 0 : 1);


        Log.e("TAG", "几页"+ count);
        sublist = new ArrayList();

        int a=0;
        for (int i = 0; i <= count; i++) {

            if(i==0) {
                templist=icon.subList(a,i+NUM-1);
                sublist.add( templist);
            }else {
                templist=icon.subList(a,a+NUM);
                sublist.add(templist);
            }
            a=i+NUM;
        }
    }


    private void initPopWinData() {
        getPoPWinData();
        ImageView img;
        int [] image={R.mipmap.xiaoying003,R.mipmap.xiaoying006,R.mipmap.xiaoying007};
        List alist = new ArrayList<>();
        for(int i = 0; i < image.length; i++) {
            img=new ImageView(getContext());
            img.setImageResource(image[i]);
            alist.add(img);
        }

        Log.e("TAG", "集合==="+sublist.toString());

        for (int i = 0; i < count; i++) {
            GridView gv = new GridView(getContext());
            mFragments.add(gv);
        }
        PP_VP_Adapter scalePagerAdapter = new PP_VP_Adapter(getContext(),mFragments,sublist);
        vp.setAdapter(scalePagerAdapter);
    }



}
