package com.caesar.brvahbinding.twolist;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class DataBGoods implements MultiItemEntity {
    private int imgRes;

    private String title;

    public DataBGoods(int imgRes, String title) {
        this.imgRes = imgRes;
        this.title = title;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int getItemType() {
        return 1;
    }
}
