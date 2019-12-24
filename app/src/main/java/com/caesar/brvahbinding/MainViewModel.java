package com.caesar.brvahbinding;

import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import com.caesar.brvahbinding.animation.AnimationActivity;
import com.caesar.brvahbinding.animation.AnimationCustomActivity;
import com.caesar.brvahbinding.base.BaseBindingViewModel;
import com.caesar.brvahbinding.base.FramGroble;
import com.caesar.brvahbinding.customadapter.CustomAdapterActivity;
import com.caesar.brvahbinding.drag.DragActivity;
import com.caesar.brvahbinding.emptyrefreshview.EmptyRefreshActivity;
import com.caesar.brvahbinding.expand.ExpandActivity;
import com.caesar.brvahbinding.headfoot.HeadFootActivity;
import com.caesar.brvahbinding.loadmore.LoadMoreChatActivity;
import com.caesar.brvahbinding.loadmore.LoadMoreLineActivity;
import com.caesar.brvahbinding.multiple.MultipleLineActivity;
import com.caesar.brvahbinding.nonMultiple.NonMultipleActivity;
import com.caesar.brvahbinding.swipe.SwipeActivity;
import com.caesar.brvahbinding.twolist.TwoListActivity;
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

    @Override//获取绑定的布局和数据,单布局写0即可,多布局,按照itemType写,可以看多布局的例子,对了,其中的bean和action,要跟布局里面命名的要一直
    protected Map<Integer, CSBravhItemBinding> getItemBinding() {
        Map<Integer, CSBravhItemBinding> mp = new HashMap<>();
        mp.put(0, new CSBravhItemBinding(BR.bean, R.layout.item_main, BR.action, new Action()));
        return mp;
    }

    @Override//已经集成好的加载数据的方法
    public void load() {
        load(getData());
    }

    //另外的点击事件动作,我为了方便这样写,也可以mvvm模式默认是写在data数据中
    public class Action implements CSAction1<MainData> {

        @Override
        public void call(MainData param) {
            Intent intent = new Intent(FramGroble.INSTANCE.getTopActivity(), param.activity);
            FramGroble.INSTANCE.getTopActivity().startActivity(intent);
        }
    }

    //模拟网络请求的数据
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
                data.add(new MainData("2个列表的绑定,仿外卖", TwoListActivity.class));
                data.add(new MainData("用自己的适配器(继承万能适配器)", CustomAdapterActivity.class));
                emitter.onNext(data);
                emitter.onComplete();
            }
        }, BackpressureStrategy.BUFFER);
    }

    @Override//设置自定义item的间距
    public RecyclerView.ItemDecoration onitemDecoration() {
        return new NormalLineDecoration(30, true);
    }
}
