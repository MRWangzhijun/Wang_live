package com.wzj.live.http;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by MR_Wang on 2017/2/19.
 */
public class OkHttpHelper {
    private static OkHttpClient client;
    private Gson mGson;
    private static  OkHttpHelper Instance;
    private Handler handler;//导包为os包下
    static {
        Instance=new OkHttpHelper();
    }

    public OkHttpHelper(){
        client=new OkHttpClient.Builder().
                connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .build();
        mGson=new Gson();
        handler=new Handler(Looper.getMainLooper());

    }

    public static OkHttpHelper getInstance(){
        return Instance;
    }


    public void doRequest(final Request request, final BaseCallBack callback){

        /**
         * 在访问网络之前执行一些事情
         */
        callback.onRequestBefore(request);


        /**
         * 通过异步访问网络
         */
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(request,e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()) {
                    Log.e("TAG", "获取数据中。。。。。。。。。。。。"+response);
                    /**
                     * toString() 是将一个对象转成字符串
                     * String() 是将一个流转转换成字符串
                     */
                    String resultStr=response.body().string();

                    Log.e("TAG", "得到的数据流===="+resultStr);

                    //判断callback泛型是否是字符串
                    if(callback.mType==String.class) {
                 //       callback.onSuccess(response,resultStr);
                        //调用成功在主线程更新UI
                        callbackSuccess(callback,response,resultStr);
                    }else{
                        try {//此处抓取Json语法异常

                            Object obj=mGson.fromJson(resultStr,callback.mType);
                           // callback.onSuccess(response,obj);
                            //调用成功在主线程更新UI
                            callbackSuccess(callback,response,obj);
                        } catch (JsonSyntaxException e) {
                          //  callback.onError(response,response.code(),e);

                            callbackError(callback,response,e);
                        } catch (Exception e) {
                         //   callback.onError(response,response.code(),e);

                            callbackError(callback,response,e);
                        }
                    }
                }else{
               //     callback.onError(response,response.code(),null);

                    callbackError(callback,response,null);
                }


                //不管成功还是失败对话框都要关闭
                callback.onResponse(response);
            }
        });
    }


    public void get(String url ,BaseCallBack callback){
        Request request=bulidRequest(url,null, HttpMethodType.GET);
        doRequest(request,callback);
    }

    public void post(String url, Map<String,Object> params,BaseCallBack callback){
        Request request=bulidRequest(url,params, HttpMethodType.POST);
        doRequest(request,callback);

    }


    /**
     * 构建Request
     * @param url
     * @param params
     * @param type
     * @return
     */
    private Request bulidRequest(String url, Map<String,Object> params,HttpMethodType type){
            Request.Builder builder=new Request.Builder();
            builder.url(url);
            if(type== HttpMethodType.GET) {
                builder.get();
            }else { RequestBody body=buildFromData(params);

                builder.post(body);
            }

        return builder.build();
    }



    private RequestBody buildFromData(Map<String,Object> params ){
        FormBody.Builder builder=new FormBody.Builder();
        if(params!=null) {
            for (Map.Entry<String,Object> entry : params.entrySet()){
                builder.add(entry.getKey(),entry.getValue()==null? "":entry.getValue().toString());
            }
        }
        return builder.build();
    }


    /**
     * callback成功时调用，使用handler保证在主线程更新UI
     * @param callback
     * @param response
     * @param object
     */
    private void callbackSuccess(final BaseCallBack callback, final Response response, final Object object){
        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(response,object);
            }
        });
    }


    /**
     * callback出错时调用，使用handler保证在主线程更新UI
     * @param callback
     * @param response
     * @param e
     */
    private void callbackError(final BaseCallBack callback, final Response response,final Exception e){
        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.onError(response,response.code(),e);
            }
        });
    }

    /**
     * 联网类型
     */
    enum HttpMethodType{
        GET,
        POST
    }
}
