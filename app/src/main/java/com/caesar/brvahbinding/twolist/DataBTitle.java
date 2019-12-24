package com.caesar.brvahbinding.twolist;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class DataBTitle implements MultiItemEntity {

    private String title;

    public DataBTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
