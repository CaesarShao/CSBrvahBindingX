package com.caesar.brvahbinding.base;

import android.view.View;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableInt;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.caesar.brvahbinding.R;
import com.caesarlib.brvahbinding.CSBindingAdapter;
import com.caesarlib.brvahbinding.CSBravhItemBinding;
import com.caesarlib.brvahbinding.CSItemBindingAdapter;
import com.caesarlib.brvahbinding.CSLog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.animation.BaseAnimation;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.chad.library.adapter.base.util.MultiTypeDelegate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseBindingViewModel<B> extends BaseViewModel {
    //数据集
    public ObservableArrayList<B> items;
    //数据绑定的布局及BR和data
    public Map<Integer, CSBravhItemBinding> itemBinding;
    //适配器,可以用用户自己的,但是必须要继承CSItemBindingAdapter
    public CSItemBindingAdapter<B, BaseViewHolder> bindingAdapter;
    //设置每个item的占的格数
    public BaseQuickAdapter.SpanSizeLookup spanSizeLookup;
    //当多布局时,如果item不想继承MultiItemEntity,可以用这个来设置每个itemType
    public MultiTypeDelegate<B> multiTypeDelegat;
    //空布局
    public ObservableInt emptyResId;
    //头部数据的绑定的布局及BR和data
    public ArrayList<CSBravhItemBinding> headBinding;
    //脚部数据的绑定的布局及BR和data
    public ArrayList<CSBravhItemBinding> footBinding;
    //是否处于刷新状态
    public ObservableBoolean isRefreshing;
    //SwipeRefreshLayout的刷新回调,是AndroidX的SwipeRefreshLayout
    public SwipeRefreshLayout.OnRefreshListener refreshListener;
    //Empty View 点击回调
    public View.OnClickListener emptyOnClickListener = getEmptyOnClickListener();
    //加载的动画
    public ObservableInt animationType;
    //加载的自定义动画
    public BaseAnimation customAnimation = onCustomAnimation();

    public RecyclerView.ItemDecoration itemDecoration = onitemDecoration();

    public RecyclerView mRecyclerView;

    protected Disposable disposable;

    //滑动删除监听
    public OnItemSwipeListener onItemSwipeListener;
    public ObservableInt swipeMoveFrags;
    //长按拖动监听
    public OnItemDragListener onItemDragListener;

    public BaseBindingViewModel() {
        items = new ObservableArrayList<>();
        itemBinding = getItemBinding();
        bindingAdapter = getBindingAdapter();
        emptyResId = new ObservableInt(getEmptyViewRes(EmptyViewType.EMPTY));
        headBinding = getHeadBinding();
        footBinding = getFootBinding();
        isRefreshing = new ObservableBoolean();
        refreshListener = getRefreshListener();
        animationType = new ObservableInt(BaseQuickAdapter.SLIDEIN_BOTTOM);
        onItemSwipeListener = getItemSwipeListener();
        swipeMoveFrags = new ObservableInt(getOnSwipeMoveFrags());
        onItemDragListener = getItemDragListener();
        if (bindingAdapter == null) {
            bindingAdapter = new CSBindingAdapter<>(itemBinding, items);
        }
        bindingAdapter.isFirstOnly(isAnimationFirstOnley());
    }

    protected void load(Flowable<List<B>> flowable) {
        if (isRefreshing.get()) {
            emptyResId.set(getEmptyViewRes(EmptyViewType.REFRESH));
        } else {
            CSLog.Print("调用了正在加载界面");
            emptyResId.set(getEmptyViewRes(EmptyViewType.LOADING));
        }
        disposable = flowable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<B>>() {
                    @Override
                    public void accept(List<B> bs) throws Exception {
                        CSLog.Print("收到数据了");
                        addItems(bs);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        CSLog.Print("出现异常");
                        emptyResId.set(getEmptyViewRes(EmptyViewType.ERROR));
                        isRefreshing.set(false);
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        CSLog.Print("完成加载了");
                        emptyResId.set(getEmptyViewRes(EmptyViewType.EMPTY));
                        isRefreshing.set(false);
                        onDataLoadComplete();
                    }
                });
    }

    //空布局的点击事件,里面做判断,如果当前空布局不是正在加载的状态,点击之后,就重新获取数据
    protected View.OnClickListener getEmptyOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CSLog.Print("点击了空布局按钮");
                if (emptyResId.get() != getEmptyViewRes(EmptyViewType.LOADING)) {
                    reload();
                    emptyResId.set(getEmptyViewRes(EmptyViewType.LOADING));
                }
            }
        };
    }

    /**
     * 重新加载
     */
    public void reload() {
        dispose();
        items.clear();
        load();
    }

    protected CSItemBindingAdapter<B, BaseViewHolder> getBindingAdapter() {
        return null;
    }


    public void addItems(List<B> newData) {
        if (newData != null && newData.size() > 0) {
            items.addAll(newData);
//            bindingAdapter.addData(newData);
        }
    }

    public void addItems(List<B> newData, int index) {
        if (newData != null && newData.size() > 0) {
            items.addAll(index, newData);
//            bindingAdapter.addData(index,newData);
        }
    }

    protected abstract Map<Integer, CSBravhItemBinding> getItemBinding();

    public abstract void load();

    public ArrayList<CSBravhItemBinding> getHeadBinding() {
        return null;
    }

    public ArrayList<CSBravhItemBinding> getFootBinding() {
        return null;
    }


    public void setSpan(BaseQuickAdapter.SpanSizeLookup spanSizeLookup) {
        this.spanSizeLookup = spanSizeLookup;
    }

    public void setMultiTypeDelegat(MultiTypeDelegate<B> multiTypeDelegat) {
        this.multiTypeDelegat = multiTypeDelegat;
    }

    public int getEmptyViewRes(int type) {
        switch (type) {
            case EmptyViewType.ERROR:
                return R.layout.layout_frame_error_view;
            case EmptyViewType.LOADING:
                return R.layout.layout_frame_loading_view;
            case EmptyViewType.REFRESH:
                return R.layout.layout_frame_refresh_view;
            default:
                return R.layout.layout_frame_empty_view;
        }
    }

    public BaseAnimation onCustomAnimation() {
        return null;
    }

    public RecyclerView.ItemDecoration onitemDecoration() {
        return null;
    }

    //下拉刷新控件的监听器,里面调用重新加载数据的方法
    public SwipeRefreshLayout.OnRefreshListener getRefreshListener() {
        return new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isRefreshing.set(true);
                reload();
            }
        };
    }

    private void dispose() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public void onBack(View view) {
        FramGroble.INSTANCE.getTopActivity().finish();
    }

    public Boolean isAnimationFirstOnley() {
        return false;
    }

    public void onDataLoadComplete() {

    }

    public OnItemSwipeListener getItemSwipeListener() {
        return null;
    }

    public int getOnSwipeMoveFrags() {
        return -1;
    }

    public OnItemDragListener getItemDragListener() {
        return null;
    }

}
