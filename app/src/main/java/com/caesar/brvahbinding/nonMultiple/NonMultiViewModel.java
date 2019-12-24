package com.caesar.brvahbinding.nonMultiple;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.caesar.brvahbinding.R;
import com.caesar.brvahbinding.base.BaseBindingViewModel;
import com.caesar.brvahbinding.usal.NormaltemDecoration;
import com.caesarlib.brvahbinding.CSBravhItemBinding;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.util.MultiTypeDelegate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;

public class NonMultiViewModel extends BaseBindingViewModel<customData> {
    //在构造方法中,设置SpanSizeLookup,这个是每个item占几格的回调.MultiTypeDelegat这个是itemType的回调判断了,如果不想继承MultiItemEntity的话
    //就要由该回调来判断
    public NonMultiViewModel() {
        super();
        setSpan(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int i) {
                if (items.get(i).getItemType() == 0) {
                    return 1;
                } else if (items.get(i).getItemType() == 1) {
                    return 2;
                } else if (items.get(i).getItemType() == 2) {
                    return 4;
                } else {
                    return 0;
                }
            }
        });
        setMultiTypeDelegat(new MultiTypeDelegate<customData>() {
            @Override
            protected int getItemType(customData customData) {
                return customData.getItemType();
            }
        });
    }

    @Override//这里面还是跟其他的一样
    protected Map<Integer, CSBravhItemBinding> getItemBinding() {
        Map<Integer, CSBravhItemBinding> mp = new HashMap<>();
        mp.put(0, new CSBravhItemBinding(com.caesar.brvahbinding.BR.data, R.layout.item_nomulti_zero));
        mp.put(1, new CSBravhItemBinding(com.caesar.brvahbinding.BR.data, R.layout.item_nomulti_one));
        mp.put(2, new CSBravhItemBinding(com.caesar.brvahbinding.BR.data, R.layout.item_nomulti_two));
        //这边的0,1,2要跟上面setMultiTypeDelegat返回的要对应起来
        return mp;
    }

    @Override
    public void load() {
        load(getData());
    }

    private Flowable<List<customData>> getData() {
        return Flowable.create(new FlowableOnSubscribe<List<customData>>() {
            @Override
            public void subscribe(FlowableEmitter<List<customData>> emitter) throws Exception {
                ArrayList<customData> data = new ArrayList<>();
                data.add(new NeoDataZero("这货是个标题", "这货是个内容加描述", R.mipmap.head_img1));
                data.add(new NeoDataOne("这货是个标题", "这货是个内容加描述", R.mipmap.head_img0));
                data.add(new NeoDataZero("这货是个标题", "这货是个内容加描述", R.mipmap.head_img1));
                data.add(new NeoDataTwo("这货是个标题", "这货是个内容加描述", R.mipmap.head_img1));
                data.add(new NeoDataZero("这货是个标题", "这货是个内容加描述", R.mipmap.head_img2));
                data.add(new NeoDataZero("这货是个标题", "这货是个内容加描述", R.mipmap.head_img1));
                data.add(new NeoDataOne("这货是个标题", "这货是个内容加描述", R.mipmap.head_img1));
                data.add(new NeoDataOne("这货是个标题", "这货是个内容加描述", R.mipmap.head_img0));
                data.add(new NeoDataOne("这货是个标题", "这货是个内容加描述", R.mipmap.head_img0));
                data.add(new NeoDataTwo("这货是个标题", "这货是个内容加描述", R.mipmap.head_img2));
                data.add(new NeoDataOne("这货是个标题", "这货是个内容加描述", R.mipmap.head_img0));
                data.add(new NeoDataZero("这货是个标题", "这货是个内容加描述", R.mipmap.head_img1));
                data.add(new NeoDataZero("这货是个标题", "这货是个内容加描述", R.mipmap.head_img1));
                data.add(new NeoDataTwo("这货是个标题", "这货是个内容加描述", R.mipmap.head_img2));
                data.add(new NeoDataZero("这货是个标题", "这货是个内容加描述", R.mipmap.head_img1));
                data.add(new NeoDataZero("这货是个标题", "这货是个内容加描述", R.mipmap.head_img1));
                data.add(new NeoDataZero("这货是个标题", "这货是个内容加描述", R.mipmap.head_img1));
                data.add(new NeoDataZero("这货是个标题", "这货是个内容加描述", R.mipmap.head_img1));
                data.add(new NeoDataTwo("这货是个标题", "这货是个内容加描述", R.mipmap.head_img1));
                data.add(new NeoDataZero("这货是个标题", "这货是个内容加描述", R.mipmap.head_img1));
                data.add(new NeoDataZero("这货是个标题", "这货是个内容加描述", R.mipmap.head_img1));
                data.add(new NeoDataOne("这货是个标题", "这货是个内容加描述", R.mipmap.head_img0));
                emitter.onNext(data);
                emitter.onComplete();
            }
        }, BackpressureStrategy.BUFFER);
    }

    @Override
    public RecyclerView.ItemDecoration onitemDecoration() {
        return new NormaltemDecoration(10);
    }
}
