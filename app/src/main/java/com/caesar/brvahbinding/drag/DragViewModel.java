package com.caesar.brvahbinding.drag;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.caesar.brvahbinding.nonMultiple.NonMultiViewModel;
import com.caesarlib.brvahbinding.CSDragAndSwipeCallBack;
import com.chad.library.adapter.base.DraggableController;
import com.chad.library.adapter.base.listener.OnItemDragListener;

public class DragViewModel extends NonMultiViewModel {
    public DragViewModel(RecyclerView recyclerView) {
        super();
        DraggableController draggableController = bindingAdapter.getDraggableController();
        draggableController.setOnItemDragListener(new OnItemDragListener() {
            @Override
            public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int i) {

            }

            @Override
            public void onItemDragMoving(RecyclerView.ViewHolder viewHolder, int i, RecyclerView.ViewHolder viewHolder1, int i1) {

            }

            @Override
            public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int i) {

            }
        });
        CSDragAndSwipeCallBack itemDragAndSwipeCallback = new CSDragAndSwipeCallBack(draggableController);
        //如果是单布局的拖动,可以用系统的ItemDragAndSwipeCallback,如果是多布局拖动功能,并且想要不同type之间也可以拖动,要用CSDragAndSwipeCallBack
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        draggableController.enableDragItem(itemTouchHelper);
    }
}
