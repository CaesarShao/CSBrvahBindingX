package com.caesar.brvahbinding.loadmore;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class ChatDataRight implements MultiItemEntity {

    private String talk;
    private int resImg;

    public ChatDataRight(String talk, int resImg) {
        this.talk = talk;
        this.resImg = resImg;
    }

    public String getTalk() {
        return talk;
    }

    public void setTalk(String talk) {
        this.talk = talk;
    }

    public int getResImg() {
        return resImg;
    }

    public void setResImg(int resImg) {
        this.resImg = resImg;
    }

    @Override
    public int getItemType() {
        return 1;
    }
}
