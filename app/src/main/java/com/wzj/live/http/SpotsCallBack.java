package com.wzj.live.http;

import android.content.Context;
import java.io.IOException;
import dmax.dialog.SpotsDialog;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by MR_Wang on 2017/2/19.
 */
public abstract class SpotsCallBack<T> extends BaseCallBack<T>{
    private Context mContext;
    private SpotsDialog mDialog;

    public SpotsCallBack(Context mContext) {
        this.mContext = mContext;
        mDialog=new SpotsDialog(mContext,"拼命加载中...");
    }

    /**
     * 显示Dialog
     */
    public void showDialog(){
        mDialog.show();
    }


    /**
     * 关闭对话框
     */

    public  void  dissmissDialog(){
        if(mDialog!=null) {
            mDialog.dismiss();
        }
    }


    /**
     * 修改加载文字
     * @param message
     */
    public void setMessage(String message){
        mDialog.setMessage(message);
    }


    /**
     * 出错的时候关闭对话框
     * @param response
     * @param e
     */
    @Override
    public void onFailure(Request response, IOException e) {
       dissmissDialog();
    }


    /**
     * 成功或者失败的时候关闭对话框
     * @param response
     */
    @Override
    public void onResponse(Response response) {
        dissmissDialog();
    }


    @Override
    public void onRequestBefore(Request request) {
        showDialog();
    }
}
