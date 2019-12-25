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

    //在viewModel中,初始化自己的构造器
    public CustomAdapterViewModel() {
        super();
        customAdapter = new CustomAdapter(R.layout.item_simple, items);
        customAdapter.isFirstOnly(false);//这个是使动画每次滑动都会显示出来
    }

    //因为是用自己的适配器,所以这个返回为空
    @Override
    protected Map<Integer, CSBravhItemBinding> getItemBinding() {
        return null;
    }

    @Override
    public void load() {
        load(CreateData.getSimpleData());
    }

    //因为自己的适配器没有监听items的改变事件,所以不会主动去刷新适配器,所以在数据加载完成的回调中,要主动刷新适配器.
    @Override
    public void onDataLoadComplete() {
        customAdapter.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ItemDecoration onitemDecoration() {
        return new NormalLineDecoration(30, true);
    }


}
