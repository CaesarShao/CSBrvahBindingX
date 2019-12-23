package com.caesar.brvahbinding.loadmore;

import androidx.recyclerview.widget.RecyclerView;

import com.caesar.brvahbinding.R;
import com.caesar.brvahbinding.base.LoadMoreBindingViewModel;
import com.caesar.brvahbinding.usal.NormalLineDecoration;
import com.caesar.brvahbinding.usal.SimpleData;
import com.caesarlib.brvahbinding.CSBravhItemBinding;
import com.caesarlib.brvahbinding.CSLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;

public class LoadMoreLineViewModel extends LoadMoreBindingViewModel<SimpleData> {
    private boolean isFirst = true;

    public LoadMoreLineViewModel() {
        super();
        setPageSize(15);
    }

    @Override
    protected Map<Integer, CSBravhItemBinding> getItemBinding() {
        Map<Integer, CSBravhItemBinding> mp = new HashMap<>();
        mp.put(0, new CSBravhItemBinding(com.caesar.brvahbinding.BR.bean, R.layout.item_simple));
        return mp;
    }

    @Override
    public void load(int mPage) {
        CSLog.Print("当前加载的页:" + mPage);
        if (mPage == 2) {
            if (isFirst) {
                isFirst = false;
                load(getErr());
            } else {
                load(getSimp());
            }
        } else if (mPage == 3) {
            load(getFsa());
        } else {
            load(getSimp());
        }
    }


    public Flowable<List<SimpleData>> getSimp() {
        return Flowable.create(new FlowableOnSubscribe<List<SimpleData>>() {
            @Override
            public void subscribe(FlowableEmitter<List<SimpleData>> emitter) throws Exception {
                ArrayList<SimpleData> data = new ArrayList<>();
                data.add(new SimpleData("这货是个标题", "这货是个内容加描述", R.mipmap.animation_img1));
                data.add(new SimpleData("这货是个标题", "这货是个内容加描述", R.mipmap.animation_img2));
                data.add(new SimpleData("这货是个标题", "这货是个内容加描述", R.mipmap.animation_img3));
                data.add(new SimpleData("这货是个标题", "这货是个内容加描述", R.mipmap.animation_img2));
                data.add(new SimpleData("这货是个标题", "这货是个内容加描述", R.mipmap.animation_img1));
                emitter.onNext(data);
                emitter.onComplete();
            }
        }, BackpressureStrategy.BUFFER).delay(3, TimeUnit.SECONDS);
    }


    public Flowable<List<SimpleData>> getErr() {
        return Flowable.create(new FlowableOnSubscribe<List<SimpleData>>() {
            @Override
            public void subscribe(FlowableEmitter<List<SimpleData>> emitter) throws Exception {
                emitter.onError(null);
            }
        }, BackpressureStrategy.BUFFER).delay(3, TimeUnit.SECONDS);
    }

    public Flowable<List<SimpleData>> getFsa() {
        return Flowable.create(new FlowableOnSubscribe<List<SimpleData>>() {
            @Override
            public void subscribe(FlowableEmitter<List<SimpleData>> emitter) throws Exception {
                ArrayList<SimpleData> data = new ArrayList<>();
                data.add(new SimpleData("这货是个标题", "这货是个内容加描述", R.mipmap.animation_img1));
                data.add(new SimpleData("这货是个标题", "这货是个内容加描述", R.mipmap.animation_img2));
                emitter.onNext(data);
                emitter.onComplete();
            }
        }, BackpressureStrategy.BUFFER).delay(3, TimeUnit.SECONDS);
    }

    @Override
    public RecyclerView.ItemDecoration onitemDecoration() {
        return new NormalLineDecoration(30, true);
    }

}
