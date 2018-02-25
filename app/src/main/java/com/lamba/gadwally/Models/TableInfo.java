package com.lamba.gadwally.Models;

/**
  Created by Whelllava on 2/17/2018.
 */

public class TableInfo {

    String title;
    String dattime1;
    String dattime2;
    String desc;
    String datdate1;
    int img;

    public TableInfo() {
    }

    public TableInfo(String title, String dattime1, String dattime2, String datdate1, String desc, int img) {
        this.title = title;
        this.dattime1 = dattime1;
        this.dattime2 = dattime2;
        this.desc = desc;
        this.img = img;
        this.datdate1 = datdate1;
    }

    public String getDatdate1() {
        return datdate1;
    }

    public void setDatdate1(String datdate1) {
        this.datdate1 = datdate1;
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
