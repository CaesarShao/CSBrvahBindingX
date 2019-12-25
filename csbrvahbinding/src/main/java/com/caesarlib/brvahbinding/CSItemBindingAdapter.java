package com.caesarlib.brvahbinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableList;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.ItemTouchHelper;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.DraggableController;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chad.library.adapter.base.util.MultiTypeDelegate;

import java.util.List;
import java.util.Map;

public abstract class CSItemBindingAdapter<T, V extends BaseViewHolder> extends BaseQuickAdapter<T, V> {
    private Map<Integer, CSBravhItemBinding> bravhItemBinding;
    private MultiTypeDelegate multiTypeDelegate;
    private DraggableController mDraggableController;
    private ItemTouchHelper itemTouchHelper;

    private CSBindingListChangedCallBack bindingListChangedCallBack;

    public CSItemBindingAdapter(Map<Integer, CSBravhItemBinding> itemBinding, List<T> data) {
        super(data);
        this.bravhItemBinding = itemBinding;
        this.bindingListChangedCallBack = new CSBindingListChangedCallBack(this);
        if (mData instanceof ObservableList) {
            ((ObservableList) mData).addOnListChangedCallback(bindingListChangedCallBack);
        }
    }


    @Override
    protected void convert(V helper, T item) {
        if (mDraggableController != null) {
            mDraggableController.initView(helper);
        }
        ViewDataBinding binding = DataBindingUtil.getBinding(helper.itemView);

        if (bravhItemBinding.size() > 1) {
            binding.setVariable(bravhItemBinding.get(helper.getItemViewType()).getVariableId(), item);
            if (bravhItemBinding.get(helper.getItemViewType()).getAction() != null) {
                binding.setVariable(bravhItemBinding.get(helper.getItemViewType()).getActionId(), bravhItemBinding.get(helper.getItemViewType()).getAction());
            }

        } else {
            binding.setVariable(bravhItemBinding.get(0).getVariableId(), item);
            if (bravhItemBinding.get(0).getAction() != null) {
                binding.setVariable(bravhItemBinding.get(0).getActionId(), bravhItemBinding.get(0).getAction());
            }
        }
        binding.executePendingBindings();

    }

    @Override
    protected int getDefItemViewType(int position) {
        if (bravhItemBinding != null && bravhItemBinding.size() > 1 && getItem(position) instanceof MultiItemEntity) {
            return ((MultiItemEntity) getItem(position)).getItemType();
        } else if (multiTypeDelegate != null) {
            return multiTypeDelegate.getDefItemViewType(mData, position);
        }
        return super.getDefItemViewType(position);
    }

    protected void setMultiTypeDelegate(MultiTypeDelegate multiTypeDelegate) {
        this.multiTypeDelegate = multiTypeDelegate;
    }

    @Override
    protected V onCreateDefViewHolder(ViewGroup parent, int viewType) {
        return createBaseViewHolder(parent, bravhItemBinding.get(viewType).getLayoutRes());
    }

    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layoutResId, parent, false);
        if (binding != null) {
            return binding.getRoot();
        } else {
            //在使用加载更多布局的时候,如果不是databinding的布局,binding会空,所以直接判断
            View view = LayoutInflater.from(parent.getContext()).inflate(layoutResId, parent, false);
            return view;

        }
    }

    public DraggableController getDraggableController() {
        if (mData instanceof ObservableList) {
            ((ObservableList) mData).removeOnListChangedCallback(bindingListChangedCallBack);
        }
        if (mDraggableController == null) {
            mDraggableController = new DraggableController(this);
        }
        return mDraggableController;
    }

    public ItemTouchHelper getItemTouchHelper(ItemTouchHelper.Callback callback) {
        if (itemTouchHelper == null) {
            itemTouchHelper = new ItemTouchHelper(callback);
        }
        return itemTouchHelper;
    }
}
