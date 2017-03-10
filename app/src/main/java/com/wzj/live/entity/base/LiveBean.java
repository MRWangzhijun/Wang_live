package com.wzj.live.entity.base;

import java.util.List;

/**
 * Created by MR_Wang on 2017/3/9.
 */

public class LiveBean {


    /**
     * totalCount : 100
     * currentPage : 1
     * totalPage : 20
     * pageSize : 5
     * list : [{"name":"主播1号","place":"上海","status":"重播","headIcon":"http://192.168.155.101:80/RealTimePlay/head/1.jpg","informationImage":"http://192.168.155.101:80/RealTimePlay/information/1.jpg","id":1},{"name":"主播2号","place":"北京","status":"重播","headIcon":"http://192.168.155.101:80/RealTimePlay/head/2.jpg","informationImage":"http://192.168.155.101:80/RealTimePlay/information/1.jpg","id":2},{"name":"主播3号","place":"上海","status":"直播","headIcon":"http://192.168.155.101:80/RealTimePlay/head/3.jpg","informationImage":"http://192.168.155.101:80/RealTimePlay/information/1.jpg","id":3},{"name":"主播4号","place":"北京","status":"重播","headIcon":"http://192.168.155.101:80/RealTimePlay/head/4.jpg","informationImage":"http://192.168.155.101:80/RealTimePlay/information/1.jpg","id":4},{"name":"主播5号","place":"上海","status":"重播","headIcon":"http://192.168.155.101:80/RealTimePlay/head/5.jpg","informationImage":"http://192.168.155.101:80/RealTimePlay/information/1.jpg","id":5}]
     */

    private int totalCount;
    private int currentPage;
    private int totalPage;
    private int pageSize;
    private List<ListBean> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }
}
