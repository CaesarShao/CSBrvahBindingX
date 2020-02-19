package com.caesarlib.brvahbinding;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableInt;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.DraggableController;
import com.chad.library.adapter.base.animation.BaseAnimation;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.chad.library.adapter.base.util.MultiTypeDelegate;

import java.util.ArrayList;

public class CSBrvahBindingAdapter {

    @BindingAdapter(
            value = {"cs_brvah_adapter", "cs_brvah_layoutManager", "cs_brvah_spansize", "cs_brvah_multiType", "cs_brvah_headBinding", "cs_brvah_footBinding", "cs_brvah_loadMoreListener", "cs_brvah_Decoration", "cs_brvah_animation_custom", "cs_brvah_loadMoreView", "cs_brvah_upFetchListener", "cs_brvah_animation", "cs_brvah_OnItemSwipeListener", "cs_brvah_OnItemDragListener","cs_brvah_SwipeMoveFrags"},
            requireAll = false
    )
    public static <T> void setCSBravhAdapter(RecyclerView recyclerView, BaseQuickAdapter adapter, CSBrvahLayoutManager.LayoutManagerFactory layoutManager, BaseQuickAdapter.SpanSizeLookup spanSizeLookup, MultiTypeDelegate<T> multiTypeDelegate, ArrayList<CSBravhItemBinding> headBinding, ArrayList<CSBravhItemBinding> footBinding, BaseQuickAdapter.RequestLoadMoreListener loadMoreListener, RecyclerView.ItemDecoration itemDecoration, BaseAnimation animationCustom, LoadMoreView loadMoreView, BaseQuickAdapter.UpFetchListener upFetchListener, ObservableInt animationType, OnItemSwipeListener onItemSwipeListener, OnItemDragListener onItemDragListener,ObservableInt SwipeMoveFrags) {

        Adapter oldAdapter = recyclerView.getAdapter();
        adapter = initAdapter(recyclerView, adapter, oldAdapter, spanSizeLookup, multiTypeDelegate, itemDecoration, loadMoreListener, loadMoreView, upFetchListener, animationType, animationCustom, onItemSwipeListener, onItemDragListener, SwipeMoveFrags);
        CSbrvahLog.Print("适配器是否为空:" + (adapter == null));
        Context context = recyclerView.getContext();
        if (layoutManager != null) {
            recyclerView.setLayoutManager(layoutManager.create(recyclerView));
        } else {
            recyclerView.setLayoutManager(CSBrvahLayoutManager.linear().create(recyclerView));
        }
        recyclerView.setAdapter(adapter);
        if (headBinding != null) {
            for (CSBravhItemBinding binding : headBinding) {
                ViewDataBinding viewBinding = DataBindingUtil.inflate(LayoutInflater.from(context), binding.getLayoutRes(), null, false);
                viewBinding.setVariable(binding.getVariableId(), binding.getHeadAFootData());
                if (binding.getAction() != null) {
                    viewBinding.setVariable(binding.getActionId(), binding.getAction());
                }
                adapter.addHeaderView(viewBinding.getRoot());
            }
        }

        if (footBinding != null) {
            for (CSBravhItemBinding binding : footBinding) {
                ViewDataBinding viewBinding = DataBindingUtil.inflate(LayoutInflater.from(context), binding.getLayoutRes(), null, false);
                viewBinding.setVariable(binding.getVariableId(), binding.getHeadAFootData());
                if (binding.getAction() != null) {
                    viewBinding.setVariable(binding.getActionId(), binding.getAction());
                }
                adapter.addFooterView(viewBinding.getRoot());
            }
        }
    }


    private static <T> BaseQuickAdapter initAdapter(RecyclerView recyclerView, BaseQuickAdapter adapter, Adapter oldAdapter, BaseQuickAdapter.SpanSizeLookup spanSizeLookup, MultiTypeDelegate<T> multiTypeDelegate, RecyclerView.ItemDecoration itemDecoration, BaseQuickAdapter.RequestLoadMoreListener loadMoreListener, LoadMoreView loadMoreView, BaseQuickAdapter.UpFetchListener upFetchListener, ObservableInt animationType, BaseAnimation animationCustom, OnItemSwipeListener onItemSwipeListener, OnItemDragListener onItemDragListener,ObservableInt SwipeMoveFrags) {


        if (adapter == null) {
            if (oldAdapter != null) {
                CSbrvahLog.Print("oldAdapter为空");
                adapter = (BaseQuickAdapter) oldAdapter;
            } else {
                CSbrvahLog.Print("oldAdapter不为空");
            }
        } else {
            CSbrvahLog.Print("Adapter不为空");
        }
        if (adapter != null) {
            if (spanSizeLookup != null) {
                CSbrvahLog.Print("设置了spanSizeLookup");
                adapter.setSpanSizeLookup(spanSizeLookup);
            }
            if (loadMoreListener != null) {
                adapter.setOnLoadMoreListener(loadMoreListener, recyclerView);
            }
            if (loadMoreView != null) {
                adapter.setLoadMoreView(loadMoreView);
            }
            if (upFetchListener != null) {
                adapter.setUpFetchListener(upFetchListener);
            }
            if (animationType != null) {
                adapter.openLoadAnimation(animationType.get());
            }
            if (recyclerView.getItemDecorationCount() == 0 && itemDecoration != null) {
                recyclerView.addItemDecoration(itemDecoration);
            }
            if (animationCustom != null) {
                adapter.openLoadAnimation(animationCustom);
            }
            if (adapter instanceof CSItemBindingAdapter) {
                if (multiTypeDelegate != null) {
                    CSbrvahLog.Print("设置了multiTypeDelegate");
                    ((CSItemBindingAdapter) adapter).setMultiTypeDelegate(multiTypeDelegate);
                }

                if (onItemSwipeListener != null || onItemDragListener != null) {
                    DraggableController draggableController = ((CSItemBindingAdapter) adapter).getDraggableController();
                    CSDragAndSwipeCallBack itemDragAndSwipeCallback = new CSDragAndSwipeCallBack(draggableController);
                    //这边是滑动功能,可以用系统的ItemDragAndSwipeCallback,如果是多布局拖动功能,并且想要不同type之间也可以拖动,要用CSDragAndSwipeCallBack
//                    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
                    ItemTouchHelper itemTouchHelper = ((CSItemBindingAdapter) adapter).getItemTouchHelper(itemDragAndSwipeCallback);
                    itemTouchHelper.attachToRecyclerView(recyclerView);
                    if (onItemSwipeListener != null) {
                        CSbrvahLog.Print("设置了滑动删除监听");
                        draggableController.enableSwipeItem();
                        draggableController.setOnItemSwipeListener(onItemSwipeListener);
                        if (SwipeMoveFrags!=null){
                            CSbrvahLog.Print("设置了侧滑方向");
                            itemDragAndSwipeCallback.setSwipeMoveFlags(SwipeMoveFrags.get());
                        }
                    }
                    if (onItemDragListener != null) {
                        CSbrvahLog.Print("设置了拖动监听");
                        draggableController.setOnItemDragListener(onItemDragListener);
                        draggableController.enableDragItem(itemTouchHelper);
                    }
                }

            }
        }


        return adapter;
    }


    @BindingAdapter(value = {"cs_brvah_loadMoreEnd"})
    public static void onLoadMoreEnd(RecyclerView recyclerView, ObservableBoolean loadMoreEnd) {
        CSbrvahLog.Print("加载结束调用:" + loadMoreEnd.get());
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter instanceof BaseQuickAdapter) {
            CSbrvahLog.Print("进入加载结束调用:" + loadMoreEnd.get());
            ((BaseQuickAdapter) adapter).loadMoreEnd(loadMoreEnd.get());
        }
    }

    @BindingAdapter(value = {"cs_brvah_loadMoreEnable"})
    public static void onLoadMoreEnable(RecyclerView recyclerView, ObservableBoolean loadMoreEnable) {
        CSbrvahLog.Print("是否加载更多:" + loadMoreEnable.get());
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter instanceof BaseQuickAdapter) {
            CSbrvahLog.Print("进入是否加载更多:" + loadMoreEnable.get());
            ((BaseQuickAdapter) adapter).setEnableLoadMore(loadMoreEnable.get());
        }
    }

    @BindingAdapter(value = {"cs_brvah_loadMoreSuccess"})
    public static void onLoadMoreSuccess(RecyclerView recyclerView, ObservableBoolean loadMoreSuccess) {
        CSbrvahLog.Print("是否加载成功:" + loadMoreSuccess.get());
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter instanceof BaseQuickAdapter) {
            CSbrvahLog.Print("进入是否加载成功:" + loadMoreSuccess.get());
            if (loadMoreSuccess.get()) {
                ((BaseQuickAdapter) adapter).loadMoreComplete();
            } else {
                ((BaseQuickAdapter) adapter).loadMoreFail();
            }
        }
    }


    @BindingAdapter(value = {"cs_brvah_emptyResId", "cs_brvah_emptyClickListener"})
    public static void onEmptyView(RecyclerView recyclerView, ObservableInt emptyResId, View.OnClickListener clickListener) {
        CSbrvahLog.Print("加载空布局调用");
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter instanceof BaseQuickAdapter) {
            if (emptyResId != null) {
                CSbrvahLog.Print("进入加载空布局");
                ((BaseQuickAdapter) adapter).setEmptyView(emptyResId.get(), (ViewGroup) recyclerView.getParent());
            }
            if (clickListener != null && ((BaseQuickAdapter) adapter).getEmptyView() != null) {
                CSbrvahLog.Print("进入加载空布局监听事件");
                ((BaseQuickAdapter) adapter).getEmptyView().setOnClickListener(clickListener);
            }

        }
    }


}
