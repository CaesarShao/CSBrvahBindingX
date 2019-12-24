package com.caesar.brvahbinding.twolist;

import androidx.recyclerview.widget.RecyclerView;

import com.caesar.brvahbinding.BR;
import com.caesar.brvahbinding.MainData;
import com.caesar.brvahbinding.R;
import com.caesar.brvahbinding.base.TwoListBindingViewModel;
import com.caesarlib.brvahbinding.CSBravhItemBinding;
import com.caesarlib.brvahbinding.action.CSAction1;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;

public class TwoListViewModel extends TwoListBindingViewModel<MainData, MultiItemEntity> {

    public TwoListViewModel(RecyclerView recyclerView) {
        super();
        mRecyclerViewB = recyclerView;
    }

    @Override
    protected Map<Integer, CSBravhItemBinding> getItemBindingA() {
        Map<Integer, CSBravhItemBinding> mp = new HashMap<>();
        mp.put(0, new CSBravhItemBinding(com.caesar.brvahbinding.BR.bean, R.layout.item_main, BR.action, new Action()));
        return mp;
    }

    @Override
    protected Map<Integer, CSBravhItemBinding> getItemBindingB() {
        Map<Integer, CSBravhItemBinding> mp = new HashMap<>();
        mp.put(0, new CSBravhItemBinding(com.caesar.brvahbinding.BR.bean, R.layout.item_title));
        mp.put(1, new CSBravhItemBinding(com.caesar.brvahbinding.BR.data, R.layout.item_data_goods));
        return mp;
    }

    @Override
    public void loadA() {
        loadA(getData());
    }

    @Override
    public void loadB() {
        loadB(getGoodsData());
    }

    private Flowable<List<MainData>> getData() {
        return Flowable.create(new FlowableOnSubscribe<List<MainData>>() {
            @Override
            public void subscribe(FlowableEmitter<List<MainData>> emitter) throws Exception {
                ArrayList<MainData> data = new ArrayList<>();
                data.add(new MainData("进店必买", 3));
                data.add(new MainData("牛肉粉丝汤", 7));
                data.add(new MainData("特色锅贴", 12));
                data.add(new MainData("爆款套餐", 16));
                data.add(new MainData("主食套餐", 20));
                data.add(new MainData("经济套餐", 26));
                data.add(new MainData("进店必买", 30));
                emitter.onNext(data);
                emitter.onComplete();
            }
        }, BackpressureStrategy.BUFFER);
    }


    private Flowable<List<MultiItemEntity>> getGoodsData() {
        return Flowable.create(new FlowableOnSubscribe<List<MultiItemEntity>>() {
            @Override
            public void subscribe(FlowableEmitter<List<MultiItemEntity>> emitter) throws Exception {
                ArrayList<MultiItemEntity> data = new ArrayList<>();
                data.add(new DataBTitle("进店必买"));
                data.add(new DataBGoods(R.mipmap.m_img1, "必点套餐1"));
                data.add(new DataBGoods(R.mipmap.m_img2, "必点套餐2"));
                data.add(new DataBGoods(R.mipmap.m_img1, "必点套餐3"));
                data.add(new DataBTitle("牛肉粉丝汤"));
                data.add(new DataBGoods(R.mipmap.m_img2, "牛肉粉丝汤1"));
                data.add(new DataBGoods(R.mipmap.m_img1, "牛肉粉丝汤1"));
                data.add(new DataBGoods(R.mipmap.m_img1, "牛肉粉丝汤1"));
                data.add(new DataBTitle("特色锅贴"));
                data.add(new DataBGoods(R.mipmap.m_img1, "特色锅贴1"));
                data.add(new DataBGoods(R.mipmap.m_img2, "特色锅贴1"));
                data.add(new DataBGoods(R.mipmap.m_img2, "牛肉粉丝汤1"));
                data.add(new DataBGoods(R.mipmap.m_img1, "牛肉粉丝汤1"));
                data.add(new DataBGoods(R.mipmap.m_img1, "特色锅贴1"));
                data.add(new DataBTitle("爆款套餐"));
                data.add(new DataBGoods(R.mipmap.m_img1, "必点套餐1"));
                data.add(new DataBGoods(R.mipmap.m_img2, "必点套餐1"));
                data.add(new DataBGoods(R.mipmap.m_img1, "牛肉粉丝汤1"));
                data.add(new DataBGoods(R.mipmap.m_img1, "牛肉粉丝汤1"));
                data.add(new DataBGoods(R.mipmap.m_img1, "必点套餐1"));
                data.add(new DataBTitle("主食套餐"));
                data.add(new DataBGoods(R.mipmap.m_img1, "必点套餐1"));
                data.add(new DataBGoods(R.mipmap.m_img1, "必点套餐1"));
                data.add(new DataBGoods(R.mipmap.m_img1, "必点套餐1"));
                data.add(new DataBGoods(R.mipmap.m_img1, "牛肉粉丝汤1"));
                data.add(new DataBGoods(R.mipmap.m_img1, "牛肉粉丝汤1"));
                data.add(new DataBGoods(R.mipmap.m_img1, "牛肉粉丝汤1"));
                data.add(new DataBGoods(R.mipmap.m_img1, "牛肉粉丝汤1"));
                data.add(new DataBTitle("经济套餐"));
                data.add(new DataBGoods(R.mipmap.m_img1, "必点套餐1"));
                data.add(new DataBGoods(R.mipmap.m_img1, "牛肉粉丝汤1"));
                data.add(new DataBGoods(R.mipmap.m_img1, "牛肉粉丝汤1"));
                data.add(new DataBGoods(R.mipmap.m_img1, "牛肉粉丝汤1"));
                data.add(new DataBGoods(R.mipmap.m_img1, "牛肉粉丝汤1"));
                data.add(new DataBGoods(R.mipmap.m_img1, "牛肉粉丝汤1"));
                data.add(new DataBGoods(R.mipmap.m_img1, "牛肉粉丝汤1"));
                data.add(new DataBGoods(R.mipmap.m_img1, "牛肉粉丝汤1"));
                data.add(new DataBGoods(R.mipmap.m_img1, "牛肉粉丝汤1"));
                data.add(new DataBGoods(R.mipmap.m_img1, "必点套餐1"));
                data.add(new DataBGoods(R.mipmap.m_img1, "必点套餐1"));
                data.add(new DataBTitle("进店必买"));
                data.add(new DataBGoods(R.mipmap.m_img1, "牛肉粉丝汤1"));
                data.add(new DataBGoods(R.mipmap.m_img1, "牛肉粉丝汤1"));
                data.add(new DataBGoods(R.mipmap.m_img1, "牛肉粉丝汤1"));
                data.add(new DataBGoods(R.mipmap.m_img1, "牛肉粉丝汤1"));
                data.add(new DataBGoods(R.mipmap.m_img1, "必点套餐1"));
                data.add(new DataBGoods(R.mipmap.m_img1, "必点套餐1"));
                data.add(new DataBGoods(R.mipmap.m_img1, "必点套餐1"));
                emitter.onNext(data);
                emitter.onComplete();
            }
        }, BackpressureStrategy.BUFFER);
    }


    public class Action implements CSAction1<MainData> {

        @Override
        public void call(MainData param) {
            mRecyclerViewB.scrollToPosition(param.position);
        }
    }
}
