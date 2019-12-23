package com.caesar.brvahbinding.expand;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.caesar.brvahbinding.BR;
import com.caesar.brvahbinding.R;
import com.caesar.brvahbinding.base.BaseBindingViewModel;
import com.caesar.brvahbinding.usal.NormaltemDecoration;
import com.caesarlib.brvahbinding.CSBravhItemBinding;
import com.caesarlib.brvahbinding.action.CSAction1;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.IExpandable;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;

public class ExpandViewModel extends BaseBindingViewModel<MultiItemEntity> {

    public ExpandViewModel() {
        super();
        setSpan(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int i) {
                if (items.get(i).getItemType() == 2) {
                    return 1;
                } else {
                    return 4;
                }
            }
        });
    }

    @Override
    protected Map<Integer, CSBravhItemBinding> getItemBinding() {
        Map<Integer, CSBravhItemBinding> mp = new HashMap<>();
        mp.put(0, new CSBravhItemBinding(BR.data, R.layout.item_ex_grandfa, BR.action, new GrandAct()));
        mp.put(1, new CSBravhItemBinding(BR.data, R.layout.item_ex_father, BR.action, new FatherAct()));
        mp.put(2, new CSBravhItemBinding(BR.data, R.layout.item_ex_child, BR.action, new ChildAct()));
        return mp;
    }

    @Override
    public void load() {
        load(getData());
    }

    @Override
    public void onDataLoadComplete() {
        bindingAdapter.expandAll();
    }

    private Flowable<List<MultiItemEntity>> getData() {
        return Flowable.create(new FlowableOnSubscribe<List<MultiItemEntity>>() {
            @Override
            public void subscribe(FlowableEmitter<List<MultiItemEntity>> emitter) throws Exception {
                ArrayList<MultiItemEntity> data = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    ExDataGrandFa exDataGrandFa = new ExDataGrandFa("点我有惊喜", "点我啊", R.mipmap.headerandfooter_img1);
                    for (int j = 0; j < 3; j++) {
                        ExDataFather exDataFather = new ExDataFather("点我啊", "来点我啊", R.mipmap.m_img1);
                        for (int k = 0; k < 6; k++) {
                            ExDataChild exDataChild = new ExDataChild("点我消失", R.mipmap.head_img1);
                            exDataFather.addSubItem(exDataChild);
                        }
                        exDataGrandFa.addSubItem(exDataFather);
                    }
                    data.add(exDataGrandFa);
                }
                emitter.onNext(data);
                emitter.onComplete();
            }
        }, BackpressureStrategy.BUFFER);
    }

    @Override
    public RecyclerView.ItemDecoration onitemDecoration() {
        return new NormaltemDecoration(10);
    }

    public class GrandAct implements CSAction1<ExDataGrandFa> {

        @Override
        public void call(ExDataGrandFa param) {
            if (param.isExpanded()) {
                bindingAdapter.collapse(items.indexOf(param));
            } else {
                bindingAdapter.expand(items.indexOf(param));
            }
        }
    }


    public class FatherAct implements CSAction1<ExDataFather> {

        @Override
        public void call(ExDataFather param) {
            if (param.isExpanded()) {
                bindingAdapter.collapse(items.indexOf(param));
            } else {
                bindingAdapter.expand(items.indexOf(param));
            }
        }
    }

    public class ChildAct implements CSAction1<ExDataChild> {
        @Override
        public void call(ExDataChild param) {
            //这一部分删除其实也不难,我是直接抄官方的,先获取当前item的父item,将集合中item去掉,再从父item中删掉当前item,要删2次
            int positionAtAll = getParentPositionInAll(items.indexOf(param));
            items.remove(param);
            if (positionAtAll != -1) {
                IExpandable multiItemEntity = (IExpandable) bindingAdapter.getData().get(positionAtAll);
                multiItemEntity.getSubItems().remove(param);
            }
        }
    }


    /**
     * 该方法用于 IExpandable 树形列表。
     * 如果不存在 Parent，则 return -1。
     *
     * @param position 所处列表的位置
     * @return 父 position 在数据列表中的位置
     */
    public int getParentPositionInAll(int position) {
        List<MultiItemEntity> data = bindingAdapter.getData();
        MultiItemEntity multiItemEntity = bindingAdapter.getItem(position);

        if (isExpandable(multiItemEntity)) {
            IExpandable IExpandable = (IExpandable) multiItemEntity;
            for (int i = position - 1; i >= 0; i--) {
                MultiItemEntity entity = data.get(i);
                if (isExpandable(entity) && IExpandable.getLevel() > ((IExpandable) entity).getLevel()) {
                    return i;
                }
            }
        } else {
            for (int i = position - 1; i >= 0; i--) {
                MultiItemEntity entity = data.get(i);
                if (isExpandable(entity)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public boolean isExpandable(MultiItemEntity item) {
        return item != null && item instanceof IExpandable;
    }

}
