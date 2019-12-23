package com.caesar.brvahbinding.headfoot;

import androidx.recyclerview.widget.RecyclerView;

import com.caesar.brvahbinding.BR;
import com.caesar.brvahbinding.R;
import com.caesar.brvahbinding.base.BaseBindingViewModel;
import com.caesar.brvahbinding.other.CreateData;
import com.caesar.brvahbinding.usal.NormalLineTopHeadDecoration;
import com.caesar.brvahbinding.usal.SimpleData;
import com.caesarlib.brvahbinding.CSBravhItemBinding;
import com.caesarlib.brvahbinding.action.CSAction0;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HeadFootViewModel extends BaseBindingViewModel<SimpleData> {
    @Override
    protected Map<Integer, CSBravhItemBinding> getItemBinding() {
        Map<Integer, CSBravhItemBinding> mp = new HashMap<>();
        mp.put(0, new CSBravhItemBinding(com.caesar.brvahbinding.BR.bean, R.layout.item_simple));
        return mp;
    }

    @Override
    public ArrayList<CSBravhItemBinding> getHeadBinding() {
        ArrayList<CSBravhItemBinding> heads = new ArrayList<>();
        heads.add(new CSBravhItemBinding(BR.data, R.layout.layout_head_one, new HeadOneData(), BR.action, new brvah()));
        heads.add(new CSBravhItemBinding(BR.data, R.layout.layout_head_two, new HeadTwoData()));
        return heads;
    }

    @Override
    public ArrayList<CSBravhItemBinding> getFootBinding() {
        ArrayList<CSBravhItemBinding> foots = new ArrayList<>();
        foots.add(new CSBravhItemBinding(BR.data, R.layout.layout_foot_one, new FootOneData()));
        foots.add(new CSBravhItemBinding(BR.data, R.layout.layout_foot_two, new FootTwoData()));
        return foots;
    }

    @Override
    public void load() {
        load(CreateData.getSimpleData());
    }


    @Override
    public RecyclerView.ItemDecoration onitemDecoration() {
        return new NormalLineTopHeadDecoration(30, true);
    }

    public class brvah implements CSAction0 {

        @Override
        public void call() {
            bindingAdapter.removeAllHeaderView();
        }
    }
}
