package com.wzj.live.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.wzj.live.R;
import com.wzj.live.activity.base.BaseActivity;
import com.wzj.live.fragment.HomeFragment;
import com.wzj.live.fragment.MyFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener{

    @BindView(R.id.radioGroup)
    RadioGroup mRadioGroup;
    @BindView(R.id.menu_img)
    ImageView img;


    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        changFragment(new HomeFragment(), false);
    }



    private void initView(){
        fm=getSupportFragmentManager();
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "dsfsdfsdfsdfsdf", Toast.LENGTH_SHORT).show();
                showBottonLayout();
            }
        });
        //已在布局中设置
        // home_but.setChecked(true);
        //给按钮组实现监听
        mRadioGroup.setOnCheckedChangeListener(this);

    }



    /**
     * 切换不同的Fragment
     * @param fragment
     * @param isInit  判断是否添加到回退栈
     */
    public  void  changFragment(Fragment fragment, boolean isInit){

        //开启事物
        FragmentTransaction ft=fm.beginTransaction();
        //替换Fragemnt
        ft.replace(R.id.main_fragment,fragment);

        //防止出现多个碎片重叠效果
        if(!isInit) {
            ft.addToBackStack(null);
        }

        //提交事务
        ft.commit();
    }



    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId) {
            case R.id.home ://首页
                changFragment(new HomeFragment(),true);
                break;
            case R.id.my ://我得
                changFragment(new MyFragment(),true);
                break;
        }



    }



    private void showBottonLayout() {



    }
}
