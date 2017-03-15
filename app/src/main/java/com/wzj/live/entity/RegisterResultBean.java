package com.wzj.live.entity;

/**
 * Created by MR_Wang on 2017/3/15.
 */

public class RegisterResultBean {

    /**
     * request :
     * error_code : 20022
     * error_msg : 手机号已经被注册
     */

    private String request;
    private int error_code;
    private String error_msg;

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }


    @Override
    public String toString() {
        return "RegisterResultBean{" +
                "request='" + request + '\'' +
                ", error_code=" + error_code +
                ", error_msg='" + error_msg + '\'' +
                '}';
    }
}
