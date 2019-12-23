package com.caesarlib.brvahbinding;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.DraggableController;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;

public class CSDragAndSwipeCallBack extends ItemDragAndSwipeCallback {
    public CSDragAndSwipeCallBack(DraggableController draggableController) {
        super(draggableController);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder source, @NonNull RecyclerView.ViewHolder target) {
        return true;
    }
}
