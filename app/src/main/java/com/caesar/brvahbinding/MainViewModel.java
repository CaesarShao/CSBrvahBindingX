package com.caesar.brvahbinding;

import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import com.caesar.brvahbinding.animation.AnimationActivity;
import com.caesar.brvahbinding.animation.AnimationCustomActivity;
import com.caesar.brvahbinding.base.BaseBindingViewModel;
import com.caesar.brvahbinding.base.FramGroble;
import com.caesar.brvahbinding.drag.DragActivity;
import com.caesar.brvahbinding.emptyrefreshview.EmptyRefreshActivity;
import com.caesar.brvahbinding.emptyrefreshview.EmptyRefrshViewModel;
import com.caesar.brvahbinding.expand.ExpandActivity;
import com.caesar.brvahbinding.headfoot.HeadFootActivity;
import com.caesar.brvahbinding.loadmore.LoadMoreChatActivity;
import com.caesar.brvahbinding.loadmore.LoadMoreLineActivity;
import com.caesar.brvahbinding.multiple.MultipleLineActivity;
import com.caesar.brvahbinding.nonMultiple.NonMultipleActivity;
import com.caesar.brvahbinding.swipe.SwipeActivity;
import com.caesar.brvahbinding.usal.NormalLineDecoration;
import com.caesarlib.brvahbinding.CSBravhItemBinding;
import com.caesarlib.brvahbinding.action.CSAction1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;

public class MainViewModel extends BaseBindingViewModel<MainData> {
    @Override
    protected Map<Integer, CSBravhItemBinding> getItemBinding() {
        Map<Integer, CSBravhItemBinding> mp = new HashMap<>();
        mp.put(0, new CSBravhItemBinding(BR.bean, R.layout.item_main, BR.action, new Action()));
        return mp;
    }

    @Override
    public void load() {
        load(getData());
    }

    public class Action implements CSAction1<MainData> {

        @Override
        public void call(MainData param) {
            Intent intent = new Intent(FramGroble.INSTANCE.getTopActivity(), param.activity);
            FramGroble.INSTANCE.getTopActivity().startActivity(intent);
        }
    }


    private Flowable<List<MainData>> getData() {
        return Flowable.create(new FlowableOnSubscribe<List<MainData>>() {
            @Override
            public void subscribe(FlowableEmitter<List<MainData>> emitter) throws Exception {
                ArrayList<MainData> data = new ArrayList<>();
                data.add(new MainData("Animation,line,加载动画效果", AnimationActivity.class));
                data.add(new MainData("Animation,Grid,加载自定义动画效果", AnimationCustomActivity.class));
                data.add(new MainData("MultipleItem,line,多布局", MultipleLineActivity.class));
                data.add(new MainData("非MultipleItem,Grid,多布局,(不想继承MultiItemEntity,用自己的bean类)", NonMultipleActivity.class));
                data.add(new MainData("添加多个头部和尾部,有各自的数据,优雅", HeadFootActivity.class));
                data.add(new MainData("空布局及下拉刷新", EmptyRefreshActivity.class));
                data.add(new MainData("侧滑删除", SwipeActivity.class));
                data.add(new MainData("长按拖动,多布局", DragActivity.class));
                data.add(new MainData("可扩展的,多布局", ExpandActivity.class));
                data.add(new MainData("下拉刷新,上拉加载", LoadMoreLineActivity.class));
                data.add(new MainData("聊天界面,下拉加载", LoadMoreChatActivity.class));
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
