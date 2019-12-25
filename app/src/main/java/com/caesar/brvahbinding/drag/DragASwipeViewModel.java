package com.caesar.brvahbinding.drag;


import android.graphics.Canvas;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.caesar.brvahbinding.R;
import com.caesar.brvahbinding.base.FramGroble;
import com.caesar.brvahbinding.nonMultiple.NonMultiViewModel;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;

public class DragASwipeViewModel extends NonMultiViewModel {
    private boolean isSwipe = true;
    private boolean isDrag = true;

    @Override
    public OnItemDragListener getItemDragListener() {
        return new OnItemDragListener() {
            @Override
            public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int i) {

            }

            @Override
            public void onItemDragMoving(RecyclerView.ViewHolder viewHolder, int i, RecyclerView.ViewHolder viewHolder1, int i1) {

            }

            @Override
            public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int i) {

            }
        };
    }

    @Override
    public OnItemSwipeListener getItemSwipeListener() {
        return new OnItemSwipeListener() {
            @Override
            public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {

            }

            @Override
            public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {

            }

            @Override
            public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {

            }

            @Override
            public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {
                canvas.drawColor(ContextCompat.getColor(FramGroble.INSTANCE.getTopActivity(), R.color.colorAccent));
            }
        };
    }
    public void onDrag(View view) {
        if (isDrag) {
            isDrag = false;
            bindingAdapter.getDraggableController().disableDragItem();
        } else {
            isDrag = true;
            bindingAdapter.getDraggableController().enableDragItem(bindingAdapter.getItemTouchHelper(null));
        }
    }
    public void onSwi(View view) {
        if (isSwipe) {
            isSwipe = false;
            bindingAdapter.getDraggableController().disableSwipeItem();
        } else {
            isSwipe = true;
            bindingAdapter.getDraggableController().enableSwipeItem();
        }
    }
}
