package com.caesarlib.brvahbinding;

import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;
import java.util.Map;

public  class CSBindingAdapter<T> extends CSItemBindingAdapter<T, BaseViewHolder> {
    public CSBindingAdapter(Map<Integer, CSBravhItemBinding> itemBinding, List<T> data) {
        super(itemBinding, data);
    }
}
