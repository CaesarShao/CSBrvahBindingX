package com.caesar.brvahbinding.loadmore;

import androidx.recyclerview.widget.RecyclerView;

import com.caesar.brvahbinding.BR;
import com.caesar.brvahbinding.R;
import com.caesar.brvahbinding.base.FetchLoadBindingViewModel;
import com.caesar.brvahbinding.usal.NormalLineDecoration;
import com.caesarlib.brvahbinding.CSBravhItemBinding;
import com.caesarlib.brvahbinding.action.CSAction0;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;

public class LoadMoreChatViewModel extends FetchLoadBindingViewModel<MultiItemEntity> {
    private boolean isFirst = true;

    public LoadMoreChatViewModel(RecyclerView recyclerView) {
        super(recyclerView);

    }

    @Override
    protected Map<Integer, CSBravhItemBinding> getItemBinding() {
        Map<Integer, CSBravhItemBinding> mp = new HashMap<>();
        mp.put(0, new CSBravhItemBinding(com.caesar.brvahbinding.BR.data, R.layout.item_chat_left));
        mp.put(1, new CSBravhItemBinding(com.caesar.brvahbinding.BR.data, R.layout.item_chat_right));
        mp.put(30, new CSBravhItemBinding(com.caesar.brvahbinding.BR.data, R.layout.item_nomore_data));
        mp.put(31, new CSBravhItemBinding(com.caesar.brvahbinding.BR.data, R.layout.item_fetch_error, com.caesar.brvahbinding.BR.action,new brvah()));
        return mp;
    }


    @Override
    public void load(int mPage) {
        if (mPage == 3) {
            if (isFirst) {
                isFirst = false;
                load(getError());
            } else {
                load(getSimp());
            }
        } else if (mPage == 4) {
            load(getLast());
        } else {

            load(getSimp());
        }

    }


    public Flowable<List<MultiItemEntity>> getSimp() {
        return Flowable.create(new FlowableOnSubscribe<List<MultiItemEntity>>() {
            @Override
            public void subscribe(FlowableEmitter<List<MultiItemEntity>> emitter) throws Exception {
                ArrayList<MultiItemEntity> data = new ArrayList<>();
                data.add(new ChatDataLeft("左边说的话大吊灯", R.mipmap.head_img0));
                data.add(new ChatDataLeft("左边说的话大吊灯", R.mipmap.head_img0));
                data.add(new ChatDataRight("右边说的话大吊灯", R.mipmap.head_img1));
                data.add(new ChatDataLeft("左边说的话大吊灯", R.mipmap.head_img1));
                data.add(new ChatDataLeft("左边说的话大吊灯", R.mipmap.head_img0));
                data.add(new ChatDataRight("右边说的话大吊灯", R.mipmap.head_img1));
                data.add(new ChatDataLeft("左边说的话大吊灯", R.mipmap.head_img0));
                data.add(new ChatDataRight("右边说的话大吊灯", R.mipmap.head_img1));
                data.add(new ChatDataLeft("左边说的话大吊灯", R.mipmap.head_img0));
                data.add(new ChatDataRight("右边说的话大吊灯", R.mipmap.head_img1));
                data.add(new ChatDataRight("右边说的话大吊灯", R.mipmap.head_img1));
                data.add(new ChatDataLeft("左边说的话大吊灯", R.mipmap.head_img0));
                data.add(new ChatDataRight("右边说的话大吊灯", R.mipmap.head_img1));
                data.add(new ChatDataLeft("左边说的话大吊灯", R.mipmap.head_img0));
                data.add(new ChatDataRight("右边说的话大吊灯", R.mipmap.head_img1));
                emitter.onNext(data);
                emitter.onComplete();
            }
        }, BackpressureStrategy.BUFFER).delay(2, TimeUnit.SECONDS);
    }


    public Flowable<List<MultiItemEntity>> getError() {
        return Flowable.create(new FlowableOnSubscribe<List<MultiItemEntity>>() {
            @Override
            public void subscribe(FlowableEmitter<List<MultiItemEntity>> emitter) throws Exception {
                emitter.onError(null);
            }
        }, BackpressureStrategy.BUFFER).delay(2, TimeUnit.SECONDS);
    }

    public Flowable<List<MultiItemEntity>> getLast() {
        return Flowable.create(new FlowableOnSubscribe<List<MultiItemEntity>>() {
            @Override
            public void subscribe(FlowableEmitter<List<MultiItemEntity>> emitter) throws Exception {
                ArrayList<MultiItemEntity> data = new ArrayList<>();
                data.add(new ChatDataLeft("左边说的话大吊灯", R.mipmap.head_img0));
                data.add(new ChatDataLeft("左边说的话大吊灯", R.mipmap.head_img0));
                data.add(new ChatDataRight("右边说的话大吊灯", R.mipmap.head_img1));
                emitter.onNext(data);
                emitter.onComplete();
            }
        }, BackpressureStrategy.BUFFER).delay(2, TimeUnit.SECONDS);
    }


    @Override
    public RecyclerView.ItemDecoration onitemDecoration() {
        return new NormalLineDecoration(30, true);
    }


    public class brvah implements CSAction0 {

        @Override
        public void call() {
            bindingAdapter.setUpFetchEnable(true);
            load();
        }
    }

}
