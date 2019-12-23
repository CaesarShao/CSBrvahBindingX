package com.caesar.brvahbinding.nonMultiple;

public class customData {//可以是接口,可以是类,只要有type类型判断
    public int itemType;
    public String title;
    public String discribe;
    public int imgRes;

    public customData(String title, String discribe, int imgRes) {
        this.title = title;
        this.discribe = discribe;
        this.imgRes = imgRes;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiscribe() {
        return discribe;
    }

    public void setDiscribe(String discribe) {
        this.discribe = discribe;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }
}
