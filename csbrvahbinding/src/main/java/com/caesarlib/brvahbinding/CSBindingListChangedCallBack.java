package com.caesarlib.brvahbinding;

import androidx.databinding.ObservableList;

import com.chad.library.adapter.base.BaseQuickAdapter;

public class CSBindingListChangedCallBack extends ObservableList.OnListChangedCallback {
    private BaseQuickAdapter adapter;

    public CSBindingListChangedCallBack(BaseQuickAdapter adapter) {
        CSLog.Print("Z-BindingListChangedCallBack");
        this.adapter = adapter;
    }

    public void onChanged(ObservableList sender) {
        CSLog.Print("Z-onChanged");
        this.adapter.notifyDataSetChanged();
    }

    public void onItemRangeChanged(ObservableList sender, int positionStart, int itemCount) {
        CSLog.Print("Z-onItemRangeChanged" + sender.size() + " sender.hashCode:" + sender.hashCode() + " positionStart:" + positionStart + " itemCount:" + itemCount + "  real itemCount:" + this.adapter.getItemCount());
        this.adapter.notifyItemRangeChanged(positionStart, itemCount);
    }

    public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount) {
        CSLog.Print("Z-onItemRangeInserted:" + sender.size() + " sender.hashCode:" + sender.hashCode() + " positionStart:" + positionStart + " itemCount:" + itemCount + "  real itemCount:" + this.adapter.getItemCount());
        this.compatibilityDataSizeChanged(itemCount);
//        if (positionStart != 0) {
        this.adapter.notifyItemRangeInserted(positionStart + this.adapter.getHeaderLayoutCount(), itemCount);
//        }

    }

    public void onItemRangeMoved(ObservableList sender, int fromPosition, int toPosition, int itemCount) {
        CSLog.Print("Z-onItemRangeMoved" + sender.size() + " sender.hashCode:" + sender.hashCode() + " itemCount:" + itemCount + "  real itemCount:" + this.adapter.getItemCount());
        for (int i = 0; i < itemCount; ++i) {
            this.adapter.notifyItemMoved(fromPosition + i, toPosition + i);
        }

    }

    public void onItemRangeRemoved(ObservableList sender, int positionStart, int itemCount) {
        CSLog.Print("Z-onItemRangeRemoved positionStart:" + positionStart + " itemCount:" + itemCount + "  real itemCount:" + this.adapter.getItemCount() + " headerCount:" + this.adapter.getHeaderLayoutCount());
        int internalPosition = positionStart + this.adapter.getHeaderLayoutCount();
        this.compatibilityDataSizeChanged(0);
        if (this.adapter.getItemCount() == internalPosition) {
            this.adapter.notifyItemRangeRemoved(internalPosition, 1);
        } else if (this.adapter.getHeaderLayoutCount() > 0) {
            this.adapter.notifyItemRangeRemoved(internalPosition, itemCount);
        } else {
            this.adapter.notifyItemRangeRemoved(internalPosition, itemCount);
        }

    }

    private void compatibilityDataSizeChanged(int size) {
        int dataSize = this.adapter.getData() == null ? 0 : this.adapter.getData().size();
        if (dataSize == size) {
            this.adapter.notifyDataSetChanged();
        }

    }
}
