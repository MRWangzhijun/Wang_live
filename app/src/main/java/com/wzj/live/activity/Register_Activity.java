package com.wzj.live.activity;

import android.app.Activity;
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
import com.wzj.live.entity.RegisterResultBean;
import com.wzj.live.other.CustomRoundView;
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

public class Register_Activity extends Activity {

    @BindView(R.id.register_heard)
    CustomRoundView  register_heard;

    @BindView(R.id.register_phone)
    EditText register_phone;

    @BindView(R.id.register_name)
    EditText register_name;

    @BindView(R.id.register_pwd)
    EditText register_pwd;

    @BindView(R.id.register_pwd2)
    EditText register_pwd2;

    @BindView(R.id.register_btn)
    Button register_btn;

    private Handler handler= new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        ButterKnife.bind(this);
        
        
        
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               userRegister();
            }
        });

    }

    private void userRegister() {
        String heard="http://img.17gexing.com/uploadfile/2015/01/2/20150103071558112.jpg";
        String phone=register_phone.getText().toString();
        String name=register_name.getText().toString();
        String pwd=register_pwd.getText().toString();
        String pwd2=register_pwd2.getText().toString();
        
        
        if("".equals(heard)||"".equals(phone)||"".equals(name)||"".equals(pwd)||"".equals(pwd2)) {
            ToastUtils.show(this,"输入内容不能为空");
            return ;
        }

        if(!RegularUtil.isMobileNO(phone)) {
            ToastUtils.show(this,"手机号格式不正确");
                return ;
        }

        if(!RegularUtil.isENG_NUM(pwd)){
            ToastUtils.show(this,"密码是6~16位字母数字符号组合");
            return ;
        }


        if(!pwd.equals(pwd2)){
            ToastUtils.show(this,"两次密码输入不至");
            return ;
        }
            requestRegister_User(heard,phone,name,pwd);
    }

    private void requestRegister_User(String heard, final String phone, String name, final String pwd) {



        OkHttpUtils.post()
                .url(Contants.BASE_REGISTER)
                .addParams("phone",phone)
                .addParams("user_name",name)
                .addParams("avatar",heard)
                .addParams("sign","猜猜我是谁")
                .addParams("password",pwd)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtils.show(Register_Activity.this,"网络连接失败");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson=new Gson();

                        RegisterResultBean bean = gson.fromJson(response, RegisterResultBean.class);
                        Log.e("TAG", "bean========"+bean);
                        if(bean.getError_code()==0) {
                            handler.post(new Runnable() {
                                  @Override
                                  public void run() {
                                        Toast.makeText(Register_Activity.this,"注册成功！！！", Toast.LENGTH_SHORT).show();
                                      Intent intent = new Intent(Register_Activity.this, Login_Activity.class);
                                      intent.putExtra("phone",phone);
                                      intent.putExtra("password",pwd);

                                      Log.e("TAG", "3333333333333333333====="+phone+"#"+pwd);
                                    startActivity(intent);
                                  }
                              });
                        }else if(bean.getError_code()==20022){
                        Toast.makeText(Register_Activity.this,bean.getError_msg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }
}
