package com.caesar.brvahbinding.usal;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;



public class NormalLineDecoration extends RecyclerView.ItemDecoration {
    private int spacing;
    private boolean incuideBeside ;

    public NormalLineDecoration(int spacing, boolean incuideBeside) {
        this.spacing = spacing;
        this.incuideBeside = incuideBeside;

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        int itemType = parent.getAdapter().getItemViewType(position);
        if (incuideBeside ) {
            outRect.left = spacing;
            outRect.right = spacing;
        }
        outRect.bottom = spacing;
        if (position == 0) {
            outRect.top = spacing;
        }
    }

}
