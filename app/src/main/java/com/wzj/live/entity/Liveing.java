package com.wzj.live.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by MR_Wang on 2017/3/10.
 */

public class Liveing implements Serializable {


    /**
     * result : {"list":[{"created_at":1489060174322,"updated_at":1489060881404,"id":1157939525124102,"data":{"status":0,"live_name":"张贺直播","pic":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489070127900&di=9bb269dd704b9e4ceb3ee6ab6a2d2a3c&imgtype=0&src=http%3A%2F%2Fg.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F2fdda3cc7cd98d10efc8b338233fb80e7bec909f.jpg","live_type":1},"uid":1157677280460801,"user":{"user_data":{"user_name":"小源","avatar":"http://d.hiphotos.baidu.com/zhidao/wh%3D600%2C800/sign=44efbe491e30e924cff194377c38423e/dcc451da81cb39dbf51ac417d1160924aa18309c.jpg"},"id":1157677280460801,"created_at":1489044543163,"updated_at":1489044543225}},{"created_at":1489060197281,"updated_at":1489060903628,"id":1157939911000071,"data":{"status":0,"live_name":"张子良直播","pic":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489070127890&di=38288d77bad74c055c9f1d963670cffb&imgtype=0&src=http%3A%2F%2Fa.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F472309f790529822c2b480c3d5ca7bcb0a46d42f.jpg","live_type":1},"uid":1157677280460801,"user":{"user_data":{"user_name":"小源","avatar":"http://d.hiphotos.baidu.com/zhidao/wh%3D600%2C800/sign=44efbe491e30e924cff194377c38423e/dcc451da81cb39dbf51ac417d1160924aa18309c.jpg"},"id":1157677280460801,"created_at":1489044543163,"updated_at":1489044543225}},{"created_at":1489060224222,"updated_at":1489060917984,"id":1157940363984904,"data":{"status":0,"live_name":"王子良直播","pic":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489070127888&di=7be09d77debdb3bf64ec86b64edf0bf9&imgtype=0&src=http%3A%2F%2Ff.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F95eef01f3a292df515463915be315c6034a8732d.jpg","live_type":1},"uid":1157677280460801,"user":{"user_data":{"user_name":"小源","avatar":"http://d.hiphotos.baidu.com/zhidao/wh%3D600%2C800/sign=44efbe491e30e924cff194377c38423e/dcc451da81cb39dbf51ac417d1160924aa18309c.jpg"},"id":1157677280460801,"created_at":1489044543163,"updated_at":1489044543225}},{"created_at":1489060239349,"updated_at":1489060945620,"id":1157940615643145,"data":{"status":0,"live_name":"小源直播","pic":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489070127888&di=00f1a4155009eaf200a0e9d354a57953&imgtype=0&src=http%3A%2F%2Fd.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F0dd7912397dda1444e88deafb0b7d0a20cf4862e.jpg","live_type":1},"uid":1157677280460801,"user":{"user_data":{"user_name":"小源","avatar":"http://d.hiphotos.baidu.com/zhidao/wh%3D600%2C800/sign=44efbe491e30e924cff194377c38423e/dcc451da81cb39dbf51ac417d1160924aa18309c.jpg"},"id":1157677280460801,"created_at":1489044543163,"updated_at":1489044543225}},{"created_at":1489060273398,"updated_at":1489060932493,"id":1157941186068490,"data":{"status":0,"live_name":"高龙龙直播","pic":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489070322042&di=a37c150bc138b0ec5e2c1b749ca0e868&imgtype=0&src=http%3A%2F%2Ff.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Fb151f8198618367abfffecd72c738bd4b31ce542.jpg","live_type":1},"uid":1157677280460801,"user":{"user_data":{"user_name":"小源","avatar":"http://d.hiphotos.baidu.com/zhidao/wh%3D600%2C800/sign=44efbe491e30e924cff194377c38423e/dcc451da81cb39dbf51ac417d1160924aa18309c.jpg"},"id":1157677280460801,"created_at":1489044543163,"updated_at":1489044543225}}]}
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



            private List<ListBean> list;

            public List<ListBean> getList() {
            return list;
        }

            public void setList(List<ListBean> list) {
            this.list = list;
        }

            public static class ListBean implements Serializable{
            /**
             * created_at : 1489060174322
             * updated_at : 1489060881404
             * id : 1157939525124102
             * data : {"status":0,"live_name":"张贺直播","pic":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489070127900&di=9bb269dd704b9e4ceb3ee6ab6a2d2a3c&imgtype=0&src=http%3A%2F%2Fg.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F2fdda3cc7cd98d10efc8b338233fb80e7bec909f.jpg","live_type":1}
             * uid : 1157677280460801
             * user : {"user_data":{"user_name":"小源","avatar":"http://d.hiphotos.baidu.com/zhidao/wh%3D600%2C800/sign=44efbe491e30e924cff194377c38423e/dcc451da81cb39dbf51ac417d1160924aa18309c.jpg"},"id":1157677280460801,"created_at":1489044543163,"updated_at":1489044543225}
             */

            private long created_at;
            private long updated_at;
            private long id;
            private DataBean data;
            private long uid;
            private UserBean user;

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

            public DataBean getData() {
                return data;
            }

            public void setData(DataBean data) {
                this.data = data;
            }

            public long getUid() {
                return uid;
            }

            public void setUid(long uid) {
                this.uid = uid;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public static class DataBean implements Serializable{
                /**
                 * status : 0
                 * live_name : 张贺直播
                 * pic : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489070127900&di=9bb269dd704b9e4ceb3ee6ab6a2d2a3c&imgtype=0&src=http%3A%2F%2Fg.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F2fdda3cc7cd98d10efc8b338233fb80e7bec909f.jpg
                 * live_type : 1
                 */

                private int status;
                private String live_name;
                private String pic;
                private int live_type;

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public String getLive_name() {
                    return live_name;
                }

                public void setLive_name(String live_name) {
                    this.live_name = live_name;
                }

                public String getPic() {
                    return pic;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }

                public int getLive_type() {
                    return live_type;
                }

                public void setLive_type(int live_type) {
                    this.live_type = live_type;
                }
            }

            public static class UserBean implements Serializable{
                /**
                 * user_data : {"user_name":"小源","avatar":"http://d.hiphotos.baidu.com/zhidao/wh%3D600%2C800/sign=44efbe491e30e924cff194377c38423e/dcc451da81cb39dbf51ac417d1160924aa18309c.jpg"}
                 * id : 1157677280460801
                 * created_at : 1489044543163
                 * updated_at : 1489044543225
                 */

                private UserDataBean user_data;
                private long id;
                private long created_at;
                private long updated_at;

                public UserDataBean getUser_data() {
                    return user_data;
                }

                public void setUser_data(UserDataBean user_data) {
                    this.user_data = user_data;
                }

                public long getId() {
                    return id;
                }

                public void setId(long id) {
                    this.id = id;
                }

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

                public static class UserDataBean implements Serializable{
                    /**
                     * user_name : 小源
                     * avatar : http://d.hiphotos.baidu.com/zhidao/wh%3D600%2C800/sign=44efbe491e30e924cff194377c38423e/dcc451da81cb39dbf51ac417d1160924aa18309c.jpg
                     */

                    private String user_name;
                    private String avatar;

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
                }
            }
        }
    }
}
