package com.caesar.brvahbinding.usal;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;


public class NormaltemDecoration extends RecyclerView.ItemDecoration {

    private int spacing;

    public NormaltemDecoration(int spacing) {
        this.spacing = spacing;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = spacing;
        outRect.right = spacing;
        outRect.bottom = spacing;
        outRect.top = spacing;
    }
}