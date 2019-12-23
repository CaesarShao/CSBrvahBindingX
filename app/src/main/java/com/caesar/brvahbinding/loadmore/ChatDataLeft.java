package com.caesar.brvahbinding.loadmore;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class ChatDataLeft implements MultiItemEntity {


    private String talk;
    private int imgRes;

    public ChatDataLeft(String talk, int imgRes) {
        this.talk = talk;
        this.imgRes = imgRes;
    }

    public String getTalk() {
        return talk;
    }

    public void setTalk(String talk) {
        this.talk = talk;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
