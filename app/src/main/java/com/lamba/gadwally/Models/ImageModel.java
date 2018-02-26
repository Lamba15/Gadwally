package com.lamba.gadwally.Models;

/**
 * Created by Whelllava on 2/18/2018.
 */

public class ImageModel {
    int img;
    String title;

    public ImageModel() {
    }

    public ImageModel(int img, String title) {
        this.img = img;
        this.title = title;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
