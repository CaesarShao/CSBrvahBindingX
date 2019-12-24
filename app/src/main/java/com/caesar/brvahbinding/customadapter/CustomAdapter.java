package com.caesar.brvahbinding.customadapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.caesar.brvahbinding.usal.SimpleData;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class CustomAdapter extends BaseQuickAdapter<SimpleData, BaseViewHolder> {


    public CustomAdapter(int layoutResId, @Nullable List<SimpleData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, SimpleData item) {
        ViewDataBinding binding= DataBindingUtil.getBinding(helper.itemView);
        binding.setVariable(com.caesar.brvahbinding.BR.bean,item);
        binding.executePendingBindings();
    }

    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {

        ViewDataBinding binding = DataBindingUtil.inflate(mLayoutInflater,layoutResId,parent,false);
        return  binding.getRoot();
    }
}
