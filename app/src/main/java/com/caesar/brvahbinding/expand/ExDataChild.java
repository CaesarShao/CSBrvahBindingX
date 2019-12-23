package com.caesar.brvahbinding.expand;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class ExDataChild implements MultiItemEntity {
    private String name;
    private int imgRes;

    public ExDataChild(String name, int imgRes) {
        this.name = name;
        this.imgRes = imgRes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    @Override
    public int getItemType() {
        return 2;
    }
}
