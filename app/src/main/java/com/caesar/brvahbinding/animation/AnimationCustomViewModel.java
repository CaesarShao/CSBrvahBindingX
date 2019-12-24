package com.caesar.brvahbinding.animation;

import androidx.recyclerview.widget.RecyclerView;

import com.caesar.brvahbinding.R;
import com.caesar.brvahbinding.base.BaseBindingViewModel;
import com.caesar.brvahbinding.other.CreateData;
import com.caesar.brvahbinding.other.CustomAnimation;
import com.caesar.brvahbinding.usal.GridSpacingItemDecoration;
import com.caesar.brvahbinding.usal.SimpleData;
import com.caesarlib.brvahbinding.CSBravhItemBinding;
import com.chad.library.adapter.base.animation.BaseAnimation;

import java.util.HashMap;
import java.util.Map;

public class AnimationCustomViewModel extends BaseBindingViewModel<SimpleData> {

    @Override
    protected Map<Integer, CSBravhItemBinding> getItemBinding() {
        Map<Integer, CSBravhItemBinding> mp = new HashMap<>();
        mp.put(0, new CSBravhItemBinding(com.caesar.brvahbinding.BR.bean, R.layout.item_simple));
        return mp;
    }

    @Override
    public void load() {
        load(CreateData.getSimpleData());
    }

    @Override
    public RecyclerView.ItemDecoration onitemDecoration() {
        return new GridSpacingItemDecoration(2, 30, true);
    }

    @Override//设置自定义动画
    public BaseAnimation onCustomAnimation() {
        return new CustomAnimation();
    }
}
