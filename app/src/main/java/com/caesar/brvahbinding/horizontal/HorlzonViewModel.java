package com.caesar.brvahbinding.horizontal;

import androidx.recyclerview.widget.RecyclerView;

import com.caesar.brvahbinding.R;
import com.caesar.brvahbinding.base.BaseBindingViewModel;
import com.caesar.brvahbinding.other.CreateData;
import com.caesar.brvahbinding.usal.NormaltemDecoration;
import com.caesar.brvahbinding.usal.SimpleData;
import com.caesarlib.brvahbinding.CSBravhItemBinding;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Caesar
 * email : caesarshao@163.com
 */
public class HorlzonViewModel extends BaseBindingViewModel<SimpleData> {
    @Override
    protected Map<Integer, CSBravhItemBinding> getItemBinding() {
        Map<Integer, CSBravhItemBinding> mp = new HashMap<>();
        mp.put(0, new CSBravhItemBinding(com.caesar.brvahbinding.BR.bean, R.layout.item_horlin_simple));
        return mp;
    }

    @Override
    public void load() {
        load(CreateData.getSimpleData());
    }
    @Override
    public RecyclerView.ItemDecoration onitemDecoration() {
        return new NormaltemDecoration(30);
    }
}
