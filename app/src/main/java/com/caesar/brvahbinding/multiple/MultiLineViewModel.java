package com.caesar.brvahbinding.multiple;

import androidx.recyclerview.widget.RecyclerView;

import com.caesar.brvahbinding.R;
import com.caesar.brvahbinding.base.BaseBindingViewModel;
import com.caesar.brvahbinding.usal.NormalLineDecoration;
import com.caesarlib.brvahbinding.CSBravhItemBinding;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;

public class MultiLineViewModel extends BaseBindingViewModel<MultiItemEntity> {
    @Override//多布局根据data的itemtype返回的值,将绑定类型的写上去
    protected Map<Integer, CSBravhItemBinding> getItemBinding() {
        Map<Integer, CSBravhItemBinding> mp = new HashMap<>();
        mp.put(0, new CSBravhItemBinding(com.caesar.brvahbinding.BR.data, R.layout.item_multi_zero));
        mp.put(1, new CSBravhItemBinding(com.caesar.brvahbinding.BR.data, R.layout.item_multi_one));
        mp.put(2, new CSBravhItemBinding(com.caesar.brvahbinding.BR.data, R.layout.item_multi_two));
        return mp;
    }

    @Override
    public void load() {
        load(getData());
    }

    private Flowable<List<MultiItemEntity>> getData() {
        return Flowable.create(new FlowableOnSubscribe<List<MultiItemEntity>>() {
            @Override
            public void subscribe(FlowableEmitter<List<MultiItemEntity>> emitter) throws Exception {
                ArrayList<MultiItemEntity> data = new ArrayList<>();
                data.add(new MultiDataOne("这货是个标题", "这货是个内容加描述", R.mipmap.head_img1));
                data.add(new MultiDataZero("这货是个标题", "这货是个内容加描述", R.mipmap.head_img0));
                data.add(new MultiDataTwo("这货是个标题", "这货是个内容加描述", R.mipmap.head_img2));
                data.add(new MultiDataZero("这货是个标题", "这货是个内容加描述", R.mipmap.head_img1));
                data.add(new MultiDataZero("这货是个标题", "这货是个内容加描述", R.mipmap.head_img1));
                data.add(new MultiDataOne("这货是个标题", "这货是个内容加描述", R.mipmap.head_img1));
                data.add(new MultiDataTwo("这货是个标题", "这货是个内容加描述", R.mipmap.head_img2));
                data.add(new MultiDataOne("这货是个标题", "这货是个内容加描述", R.mipmap.head_img1));
                data.add(new MultiDataOne("这货是个标题", "这货是个内容加描述", R.mipmap.head_img0));
                data.add(new MultiDataTwo("这货是个标题", "这货是个内容加描述", R.mipmap.head_img0));
                data.add(new MultiDataZero("这货是个标题", "这货是个内容加描述", R.mipmap.head_img1));
                data.add(new MultiDataOne("这货是个标题", "这货是个内容加描述", R.mipmap.head_img1));
                emitter.onNext(data);
                emitter.onComplete();
            }
        }, BackpressureStrategy.BUFFER);
    }

    @Override
    public RecyclerView.ItemDecoration onitemDecoration() {
        return new NormalLineDecoration(30, true);
    }

}
