package com.caesar.brvahbinding.headfoot;

import android.view.View;

import androidx.databinding.ObservableField;

public class HeadOneData {
    public ObservableField<String> name = new ObservableField<>();


    public HeadOneData() {
        name.set("点击我清空头部");
    }

}
