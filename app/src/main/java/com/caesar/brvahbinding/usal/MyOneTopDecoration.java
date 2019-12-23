package com.caesar.brvahbinding.usal;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;



public class MyOneTopDecoration extends RecyclerView.ItemDecoration {
    private int spacing;
    private int linSpanCount = 0;

    public MyOneTopDecoration(int spacing) {
        this.spacing = spacing;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        int itemType = parent.getAdapter().getItemViewType(position);
        if (itemType == 0) {
            if (position % 2 == linSpanCount % 2) {
                outRect.left = spacing;
                outRect.right = spacing / 2;
            } else {
                outRect.left = spacing / 2;
                outRect.right = spacing;
            }
        }
        outRect.bottom = spacing;
    }

    public void AddLinSpanCount() {
        linSpanCount++;
    }

    public void clearSpanCount() {
        linSpanCount = 0;
    }

}
