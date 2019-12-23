package com.caesarlib.brvahbinding;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class CSBrvahLayoutManager {
    protected CSBrvahLayoutManager() {
    }

    public interface LayoutManagerFactory {
        RecyclerView.LayoutManager create(RecyclerView recyclerView);
    }

    /**
     * A {@link LinearLayoutManager}.
     */
    public static LayoutManagerFactory linear() {
        return new LayoutManagerFactory() {
            @Override
            public RecyclerView.LayoutManager create(RecyclerView recyclerView) {
                return new LinearLayoutManager(recyclerView.getContext());
            }
        };
    }

    /**
     * A {@link LinearLayoutManager} with the given orientation and reverseLayout.
     */
    public static LayoutManagerFactory linear(@RecyclerView.Orientation final int orientation, final boolean reverseLayout) {
        return new LayoutManagerFactory() {
            @Override
            public RecyclerView.LayoutManager create(RecyclerView recyclerView) {
                return new LinearLayoutManager(recyclerView.getContext(), orientation, reverseLayout);
            }
        };
    }

    /**
     * A {@link LinearLayoutManager}.
     */
    public static LayoutManagerFactory fetLinear() {
        return new LayoutManagerFactory() {
            @Override
            public RecyclerView.LayoutManager create(RecyclerView recyclerView) {

                LinearLayoutManager linearLayoutManager=  new LinearLayoutManager(recyclerView.getContext());
                linearLayoutManager.setStackFromEnd(true);
                return linearLayoutManager;
            }
        };
    }

    /**
     * A {@link GridLayoutManager} with the given spanCount.
     */
    public static LayoutManagerFactory grid(final int spanCount) {
        return new LayoutManagerFactory() {
            @Override
            public RecyclerView.LayoutManager create(RecyclerView recyclerView) {
                return new GridLayoutManager(recyclerView.getContext(), spanCount){
                };
            }
        };
    }

    /**
     * A {@link GridLayoutManager} with the given spanCount, orientation and reverseLayout.
     **/
    public static LayoutManagerFactory grid(final int spanCount, @RecyclerView.Orientation final int orientation, final boolean reverseLayout) {
        return new LayoutManagerFactory() {
            @Override
            public RecyclerView.LayoutManager create(RecyclerView recyclerView) {
                return new GridLayoutManager(recyclerView.getContext(), spanCount, orientation, reverseLayout);
            }
        };
    }

    /**
     * A {@link StaggeredGridLayoutManager} with the given spanCount and orientation.
     */
    public static LayoutManagerFactory staggeredGrid(final int spanCount, @RecyclerView.Orientation final int orientation) {
        return new LayoutManagerFactory() {
            @Override
            public RecyclerView.LayoutManager create(RecyclerView recyclerView) {
                return new StaggeredGridLayoutManager(spanCount, orientation);
            }
        };
    }
}
