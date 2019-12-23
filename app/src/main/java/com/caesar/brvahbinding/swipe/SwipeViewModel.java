package com.caesar.brvahbinding.swipe;

import android.graphics.Canvas;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.caesar.brvahbinding.R;
import com.caesar.brvahbinding.base.BaseBindingViewModel;
import com.caesar.brvahbinding.other.CreateData;
import com.caesar.brvahbinding.usal.NormalLineDecoration;
import com.caesar.brvahbinding.usal.SimpleData;
import com.caesarlib.brvahbinding.CSBravhItemBinding;
import com.caesarlib.brvahbinding.CSDragAndSwipeCallBack;
import com.chad.library.adapter.base.DraggableController;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;

import java.util.HashMap;
import java.util.Map;

public class SwipeViewModel extends BaseBindingViewModel<SimpleData> {

    public SwipeViewModel(final RecyclerView recyclerView) {
        super();

        OnItemSwipeListener onItemSwipeListener = new OnItemSwipeListener() {
            @Override
            public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int i) {

            }

            @Override
            public void clearView(RecyclerView.ViewHolder viewHolder, int i) {

            }

            @Override
            public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int i) {

            }

            @Override
            public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float v, float v1, boolean b) {
                canvas.drawColor(ContextCompat.getColor(recyclerView.getContext(), R.color.colorAccent));
            }
        };
        DraggableController draggableController = bindingAdapter.getDraggableController();
        draggableController.enableSwipeItem();
        draggableController.setOnItemSwipeListener(onItemSwipeListener);
        CSDragAndSwipeCallBack itemDragAndSwipeCallback = new CSDragAndSwipeCallBack(draggableController);
        //这边是滑动功能,可以用系统的ItemDragAndSwipeCallback,如果是多布局拖动功能,并且想要不同type之间也可以拖动,要用CSDragAndSwipeCallBack
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemDragAndSwipeCallback.setSwipeMoveFlags(ItemTouchHelper.START | ItemTouchHelper.END);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    @Override
    protected Map<Integer, CSBravhItemBinding> getItemBinding() {
        Map<Integer, CSBravhItemBinding> mp = new HashMap<>();
        mp.put(0, new CSBravhItemBinding(com.caesar.brvahbinding.BR.bean, R.layout.item_simple));
        return mp;
    }

    @Override
    public void load() {
        load(CreateData.getSimpleData());
    }

    @Override
    public RecyclerView.ItemDecoration onitemDecoration() {
        return new NormalLineDecoration(30, true);
    }

}
