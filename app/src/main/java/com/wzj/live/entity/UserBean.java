package com.wzj.live.entity;

import java.io.Serializable;

/**
 * Created by MR_Wang on 2017/3/15.
 */

public class UserBean implements Serializable {


    /**
     * result : {"created_at":1489547455408,"updated_at":1489547455428,"id":1166114743713817,"user_data":{"phone":"18334789277","user_name":"111","avatar":"http://img.17gexing.com/uploadfile/2015/01/2/20150103071558112.jpg","sign":"猜猜我是谁"}}
     * error_code : 0
     */

    private ResultBean result;
    private int error_code;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean implements Serializable{
        /**
         * created_at : 1489547455408
         * updated_at : 1489547455428
         * id : 1166114743713817
         * user_data : {"phone":"18334789277","user_name":"111","avatar":"http://img.17gexing.com/uploadfile/2015/01/2/20150103071558112.jpg","sign":"猜猜我是谁"}
         */

        private long created_at;
        private long updated_at;
        private long id;
        private UserDataBean user_data;

        public long getCreated_at() {
            return created_at;
        }

        public void setCreated_at(long created_at) {
            this.created_at = created_at;
        }

        public long getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(long updated_at) {
            this.updated_at = updated_at;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public UserDataBean getUser_data() {
            return user_data;
        }

        public void setUser_data(UserDataBean user_data) {
            this.user_data = user_data;
        }

        public static class UserDataBean implements Serializable{
            /**
             * phone : 18334789277
             * user_name : 111
             * avatar : http://img.17gexing.com/uploadfile/2015/01/2/20150103071558112.jpg
             * sign : 猜猜我是谁
             */

            private String phone;
            private String user_name;
            private String avatar;
            private String sign;

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            @Override
            public String toString() {
                return "UserDataBean{" +
                        "phone='" + phone + '\'' +
                        ", user_name='" + user_name + '\'' +
                        ", avatar='" + avatar + '\'' +
                        ", sign='" + sign + '\'' +
                        '}';
            }
        }


        @Override
        public String toString() {
            return "ResultBean{" +
                    "created_at=" + created_at +
                    ", updated_at=" + updated_at +
                    ", id=" + id +
                    ", user_data=" + user_data +
                    '}';
        }
    }


    @Override
    public String toString() {
        return "UserBean{" +
                "result=" + result +
                ", error_code=" + error_code +
                '}';
    }
}
