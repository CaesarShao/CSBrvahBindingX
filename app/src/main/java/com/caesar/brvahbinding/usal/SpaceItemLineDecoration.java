package com.caesar.brvahbinding.usal;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;


public class SpaceItemLineDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpaceItemLineDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getChildLayoutPosition(view) != 0) {
            outRect.top = space;
            outRect.left = space;
        }
    }

}