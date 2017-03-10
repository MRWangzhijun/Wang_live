package com.wzj.live.entity.base;

/**
 * Created by MR_Wang on 2017/3/9.
 */

public class ListBean {
        /**
         * name : 主播1号
         * place : 上海
         * status : 重播
         * headIcon : http://192.168.155.101:80/RealTimePlay/head/1.jpg
         * informationImage : http://192.168.155.101:80/RealTimePlay/information/1.jpg
         * id : 1
         */

        private String name;
        private String place;
        private String status;
        private String headIcon;
        private String informationImage;
        private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHeadIcon() {
        return headIcon;
    }

    public void setHeadIcon(String headIcon) {
        this.headIcon = headIcon;
    }

    public String getInformationImage() {
        return informationImage;
    }

    public void setInformationImage(String informationImage) {
        this.informationImage = informationImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
