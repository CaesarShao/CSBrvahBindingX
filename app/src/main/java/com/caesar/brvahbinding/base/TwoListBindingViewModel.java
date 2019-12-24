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

public abstract class TwoListBindingViewModel<A,B> extends BaseViewModel {



    //数据集
    public ObservableArrayList<A> itemsA;
    //数据绑定的布局及BR和data
    public Map<Integer, CSBravhItemBinding> itemBindingA;
    //适配器,可以用用户自己的,但是必须要继承CSItemBindingAdapter
    public CSItemBindingAdapter<A, BaseViewHolder> bindingAdapterA;
    //设置每个item的占的格数
    public BaseQuickAdapter.SpanSizeLookup spanSizeLookupA;
    //当多布局时,如果item不想继承MultiItemEntity,可以用这个来设置每个itemType
    public MultiTypeDelegate<A> multiTypeDelegatA;
    //空布局
    public ObservableInt emptyResIdA;
    //头部数据的绑定的布局及BR和data
    public ArrayList<CSBravhItemBinding> headBindingA;
    //脚部数据的绑定的布局及BR和data
    public ArrayList<CSBravhItemBinding> footBindingA;
    //是否处于刷新状态
    public ObservableBoolean isRefreshingA;
    //SwipeRefreshLayout的刷新回调,是AndroidX的SwipeRefreshLayout
    public SwipeRefreshLayout.OnRefreshListener refreshListenerA;
    //Empty View 点击回调
    public View.OnClickListener emptyOnClickListenerA = getEmptyOnClickListenerA();
    //加载的动画
    public ObservableInt animationTypeA;
    //加载的自定义动画
    public BaseAnimation customAnimationA = onCustomAnimation();

    public RecyclerView.ItemDecoration itemDecorationA = onitemDecorationA();

    public RecyclerView mRecyclerViewA;

    protected Disposable disposableA;





    //数据集
    public ObservableArrayList<B> itemsB;
    //数据绑定的布局及BR和data
    public Map<Integer, CSBravhItemBinding> itemBindingB;
    //适配器,可以用用户自己的,但是必须要继承CSItemBindingAdapter
    public CSItemBindingAdapter<B, BaseViewHolder> bindingAdapterB;
    //设置每个item的占的格数
    public BaseQuickAdapter.SpanSizeLookup spanSizeLookupB;
    //当多布局时,如果item不想继承MultiItemEntity,可以用这个来设置每个itemType
    public MultiTypeDelegate<B> multiTypeDelegatB;
    //空布局
    public ObservableInt emptyResIdB;
    //头部数据的绑定的布局及BR和data
    public ArrayList<CSBravhItemBinding> headBindingB;
    //脚部数据的绑定的布局及BR和data
    public ArrayList<CSBravhItemBinding> footBindingB;
    //是否处于刷新状态
    public ObservableBoolean isRefreshingB;
    //SwipeRefreshLayout的刷新回调,是AndroidX的SwipeRefreshLayout
    public SwipeRefreshLayout.OnRefreshListener refreshListenerB;
    //Empty View 点击回调
    public View.OnClickListener emptyOnClickListenerB = getEmptyOnClickListenerB();
    //加载的动画
    public ObservableInt animationTypeB;
    //加载的自定义动画
    public BaseAnimation customAnimationB = onCustomAnimation();

    public RecyclerView.ItemDecoration itemDecorationB = onitemDecorationB();

    public RecyclerView mRecyclerViewB;

    protected Disposable disposableB;

    public TwoListBindingViewModel() {



        itemsA = new ObservableArrayList<>();
        itemBindingA = getItemBindingA();
        bindingAdapterA = getBindingAdapterA();
        emptyResIdA = new ObservableInt(getEmptyViewRes(EmptyViewType.EMPTY));
        headBindingA = getHeadBindingA();
        footBindingA = getFootBindingA();
        isRefreshingA = new ObservableBoolean();
        refreshListenerA = getRefreshListenerA();
        animationTypeA = new ObservableInt(BaseQuickAdapter.SLIDEIN_BOTTOM);
        if (bindingAdapterA == null) {
            bindingAdapterA = new CSBindingAdapter<>(itemBindingA, itemsA);
        }
        bindingAdapterA.isFirstOnly(isAnimationFirstOnley());




        itemsB = new ObservableArrayList<>();
        itemBindingB = getItemBindingB();
        bindingAdapterB = getBindingAdapterB();
        emptyResIdB = new ObservableInt(getEmptyViewRes(EmptyViewType.EMPTY));
        headBindingB = getHeadBindingB();
        footBindingB = getFootBindingB();
        isRefreshingB = new ObservableBoolean();
        refreshListenerB = getRefreshListenerB();
        animationTypeB = new ObservableInt(BaseQuickAdapter.SLIDEIN_BOTTOM);
        if (bindingAdapterB == null) {
            bindingAdapterB = new CSBindingAdapter<>(itemBindingB, itemsB);
        }
        bindingAdapterB.isFirstOnly(isAnimationFirstOnley());
    }



    protected void loadA(Flowable<List<A>> flowable) {
        if (isRefreshingA.get()) {
            emptyResIdA.set(getEmptyViewRes(EmptyViewType.REFRESH));
        } else {
            CSLog.Print("调用了正在加载界面");
            emptyResIdA.set(getEmptyViewRes(EmptyViewType.LOADING));
        }
        disposableA = flowable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<A>>() {
                    @Override
                    public void accept(List<A> bs) throws Exception {
                        CSLog.Print("收到数据了");
                        addItemsA(bs);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        CSLog.Print("出现异常");
                        emptyResIdA.set(getEmptyViewRes(EmptyViewType.ERROR));
                        isRefreshingA.set(false);
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        CSLog.Print("完成加载了");
                        emptyResIdA.set(getEmptyViewRes(EmptyViewType.EMPTY));
                        isRefreshingA.set(false);
                        onDataLoadCompleteA();
                    }
                });
    }






    protected void loadB(Flowable<List<B>> flowable) {
        if (isRefreshingB.get()) {
            emptyResIdB.set(getEmptyViewRes(EmptyViewType.REFRESH));
        } else {
            CSLog.Print("调用了正在加载界面");
            emptyResIdB.set(getEmptyViewRes(EmptyViewType.LOADING));
        }
        disposableB = flowable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<B>>() {
                    @Override
                    public void accept(List<B> bs) throws Exception {
                        CSLog.Print("收到数据了");
                        addItemsB(bs);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        CSLog.Print("出现异常");
                        emptyResIdB.set(getEmptyViewRes(EmptyViewType.ERROR));
                        isRefreshingB.set(false);
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        CSLog.Print("完成加载了");
                        emptyResIdB.set(getEmptyViewRes(EmptyViewType.EMPTY));
                        isRefreshingB.set(false);
                        onDataLoadCompleteB();
                    }
                });
    }


    protected View.OnClickListener getEmptyOnClickListenerA() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CSLog.Print("点击了空布局按钮");
                if (emptyResIdA.get() != getEmptyViewRes(EmptyViewType.LOADING)) {
                    reloadA();
                    emptyResIdA.set(getEmptyViewRes(EmptyViewType.LOADING));
                }
            }
        };
    }

    protected View.OnClickListener getEmptyOnClickListenerB() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CSLog.Print("点击了空布局按钮");
                if (emptyResIdB.get() != getEmptyViewRes(EmptyViewType.LOADING)) {
                    reloadB();
                    emptyResIdB.set(getEmptyViewRes(EmptyViewType.LOADING));
                }
            }
        };
    }


    /**
     * 重新加载
     */
    public void reloadA() {
        disposeA();
        itemsA.clear();
        loadA();
    }

    /**
     * 重新加载
     */
    public void reloadB() {
        disposeB();
        itemsB.clear();
        loadB();
    }



    protected CSItemBindingAdapter<A, BaseViewHolder> getBindingAdapterA() {
        return null;
    }

    protected CSItemBindingAdapter<B, BaseViewHolder> getBindingAdapterB() {
        return null;
    }





    public void addItemsA(List<A> newData) {
        if (newData != null && newData.size() > 0) {
            itemsA.addAll(newData);
//            bindingAdapter.addData(newData);
        }
    }

    public void addItemsA(List<A> newData, int index) {
        if (newData != null && newData.size() > 0) {
            itemsA.addAll(index, newData);
//            bindingAdapter.addData(index,newData);
        }
    }


    public void addItemsB(List<B> newData) {
        if (newData != null && newData.size() > 0) {
            itemsB.addAll(newData);
//            bindingAdapter.addData(newData);
        }
    }

    public void addItemsB(List<B> newData, int index) {
        if (newData != null && newData.size() > 0) {
            itemsB.addAll(index, newData);
//            bindingAdapter.addData(index,newData);
        }
    }

    protected abstract Map<Integer, CSBravhItemBinding> getItemBindingA();

    protected abstract Map<Integer, CSBravhItemBinding> getItemBindingB();

    public abstract void loadA();

    public abstract void loadB();

    public ArrayList<CSBravhItemBinding> getHeadBindingA() {
        return null;
    }

    public ArrayList<CSBravhItemBinding> getHeadBindingB() {
        return null;
    }

    public ArrayList<CSBravhItemBinding> getFootBindingA() {
        return null;
    }

    public ArrayList<CSBravhItemBinding> getFootBindingB() {
        return null;
    }

    public void setSpanA(BaseQuickAdapter.SpanSizeLookup spanSizeLookup) {
        this.spanSizeLookupA = spanSizeLookup;
    }

    public void setSpanB(BaseQuickAdapter.SpanSizeLookup spanSizeLookup) {
        this.spanSizeLookupB = spanSizeLookup;
    }


    public void setMultiTypeDelegatA(MultiTypeDelegate<A> multiTypeDelegat) {
        this.multiTypeDelegatA = multiTypeDelegat;
    }

    public void setMultiTypeDelegatB(MultiTypeDelegate<B> multiTypeDelegat) {
        this.multiTypeDelegatB = multiTypeDelegat;
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



    public RecyclerView.ItemDecoration onitemDecorationA() {
        return null;
    }

    public RecyclerView.ItemDecoration onitemDecorationB() {
        return null;
    }



    public SwipeRefreshLayout.OnRefreshListener getRefreshListenerA() {
        return new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isRefreshingA.set(true);
                reloadA();
            }
        };
    }

    public SwipeRefreshLayout.OnRefreshListener getRefreshListenerB() {
        return new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isRefreshingB.set(true);
                reloadB();
            }
        };
    }


    private void disposeA() {
        if (disposableA != null && !disposableA.isDisposed()) {
            disposableA.dispose();
        }
    }

    private void disposeB() {
        if (disposableB != null && !disposableB.isDisposed()) {
            disposableB.dispose();
        }
    }

    public void onBack(View view) {
        FramGroble.INSTANCE.getTopActivity().finish();
    }

    public Boolean isAnimationFirstOnley() {
        return false;
    }

    public void onDataLoadCompleteA() {

    }

    public void onDataLoadCompleteB() {

    }

}
