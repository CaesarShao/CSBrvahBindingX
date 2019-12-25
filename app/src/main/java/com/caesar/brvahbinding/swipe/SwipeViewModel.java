package com.caesar.brvahbinding.swipe;

import android.graphics.Canvas;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.caesar.brvahbinding.R;
import com.caesar.brvahbinding.base.BaseBindingViewModel;
import com.caesar.brvahbinding.base.FramGroble;
import com.caesar.brvahbinding.other.CreateData;
import com.caesar.brvahbinding.usal.NormalLineDecoration;
import com.caesar.brvahbinding.usal.SimpleData;
import com.caesarlib.brvahbinding.CSBravhItemBinding;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;

import java.util.HashMap;
import java.util.Map;

public class SwipeViewModel extends BaseBindingViewModel<SimpleData> {
    private boolean isSwipe = true;

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

    @Override
    public OnItemSwipeListener getItemSwipeListener() {
        return new OnItemSwipeListener() {
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
                canvas.drawColor(ContextCompat.getColor(FramGroble.INSTANCE.getTopActivity(), R.color.colorAccent));
            }
        };
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

    @Override
    public int getOnSwipeMoveFrags() {
        return ItemTouchHelper.START | ItemTouchHelper.END;
    }
}
