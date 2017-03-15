package com.wzj.live.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wzj.live.R;
import com.wzj.live.activity.base.BaseActivity;
import com.wzj.live.entity.UserBean;
import com.wzj.live.utils.Contants;
import com.wzj.live.utils.RegularUtil;
import com.wzj.live.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by MR_Wang on 2017/3/15.
 */

public class Login_Activity extends BaseActivity{

    @BindView(R.id.login_phone)
    EditText login_phone;
    @BindView(R.id.login_pwd)
    EditText login_pwd;
    @BindView(R.id.login_btn)
    Button login_btn;

    @BindView(R.id.res_btn)
    Button register_btn;

    private Handler handler=new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);


        Intent intent = getIntent();

        Log.e("TAG", intent.getStringExtra("phone")+"%%%"+intent.getStringExtra("password"));



        if(!"".equals(intent.getStringExtra("phone"))){
            login_phone.setText(intent.getStringExtra("phone"));
        }

        if(!"".equals(intent.getStringExtra("password"))){
            login_pwd.setText(intent.getStringExtra("password"));
        }


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               initView() ;
            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login_Activity.this,Register_Activity.class));
            }
        });
    }

    private boolean initView() {
        String phone=login_phone.getText().toString();
        String pwd=login_pwd.getText().toString();


        if("".equals(phone)||"".equals(pwd)) {
            ToastUtils.show(this,"输入内容不能为空");
            return false;
        }
        
        if(!RegularUtil.isMobileNO(phone)) {
            ToastUtils.show(this,"手机号格式不正确");
            return false;

        }

        requestLogin(phone, pwd);
        return false;
    }

    private void requestLogin(String phone, String pwd) {

        OkHttpUtils.post()
                .url(Contants.BASE_LOGIN)
                .addParams("phone",phone)
                .addParams("password",pwd)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtils.show(Login_Activity.this,"网络连接失败");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson=new Gson();

                        final UserBean userBean = gson.fromJson(response, UserBean.class);
                        Log.e("TAG", "response==="+userBean);

                        if(userBean.getResult()!=null) {
                            Toast.makeText(Login_Activity.this,"登录成功！！！", Toast.LENGTH_SHORT).show();
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                        Intent intent = new Intent(Login_Activity.this, MainActivity.class);
                                        intent.putExtra("user",userBean);
                                        startActivity(intent);
                                    }
                            });
                        }else {
                            ToastUtils.show(Login_Activity.this,"用户名或密码输入错误！");
                        }
                    }
                });
    }


}
