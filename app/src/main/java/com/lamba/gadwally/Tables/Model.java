package com.lamba.gadwally.Tables;

/**
  Created by Whelllava on 2/17/2018.
 */

public class Model {

    String title;
    String dattime1;
    String dattime2;
    String desc;
    int img;

    public Model() {
    }

    public Model(String title, String dattime1, String dattime2, String desc, int img) {
        this.title = title;
        this.dattime1 = dattime1;
        this.dattime2 = dattime2;
        this.desc = desc;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDattime1() {
        return dattime1;
    }

    public void setDattime1(String dattime1) {
        this.dattime1 = dattime1;
    }

    public String getDattime2() {
        return dattime2;
    }

    public void setDattime2(String dattime2) {
        this.dattime2 = dattime2;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
