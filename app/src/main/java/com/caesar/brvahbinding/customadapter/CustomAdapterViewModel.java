package com.caesar.brvahbinding.customadapter;

import androidx.recyclerview.widget.RecyclerView;

import com.caesar.brvahbinding.R;
import com.caesar.brvahbinding.base.BaseBindingViewModel;
import com.caesar.brvahbinding.other.CreateData;
import com.caesar.brvahbinding.usal.NormalLineDecoration;
import com.caesar.brvahbinding.usal.SimpleData;
import com.caesarlib.brvahbinding.CSBravhItemBinding;

import java.util.Map;

public class CustomAdapterViewModel extends BaseBindingViewModel<SimpleData> {
    public CustomAdapter customAdapter;

    public CustomAdapterViewModel() {
        super();
        customAdapter = new CustomAdapter(R.layout.item_simple, items);
        customAdapter.isFirstOnly(false);
    }

    @Override
    protected Map<Integer, CSBravhItemBinding> getItemBinding() {
        return null;
    }

    @Override
    public void load() {
        load(CreateData.getSimpleData());
    }

    @Override
    public void onDataLoadComplete() {
        customAdapter.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ItemDecoration onitemDecoration() {
        return new NormalLineDecoration(30, true);
    }
}
