package com.wzj.live.http;

import com.google.gson.internal.$Gson$Types;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by MR_Wang on 2017/2/19.
 */
public abstract class BaseCallBack<T> {
    public Type mType;

    /**
     * 将泛型T转为Type
     */
    static Type getSuperclassTypeParameter(Class<?> subclass){
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class){
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterized = (ParameterizedType)superclass;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }

    public BaseCallBack(){
        mType = getSuperclassTypeParameter(getClass());
    }




    //执行网络请求之前要执行的代码
    public  abstract void onRequestBefore(Request request);

    //网络请求出现错误时
    public abstract void onFailure(Request response, IOException e);


    //请求网络成功时调用
    public abstract void onSuccess(Response response,T t);

    public abstract void onError(Response response,int code,Exception e);

    public abstract void onResponse(Response response);

}
