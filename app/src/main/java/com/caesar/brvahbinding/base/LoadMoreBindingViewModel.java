package com.caesar.brvahbinding.base;

import androidx.databinding.ObservableBoolean;

import com.caesar.brvahbinding.R;
import com.caesarlib.brvahbinding.CSLog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.LoadMoreView;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public abstract class LoadMoreBindingViewModel<B> extends BaseBindingViewModel<B> {

    //加载更多布局,可以自定义
    public LoadMoreView loadMoreView;
    //加载更多结束
    public ObservableBoolean loadMoreEnd;
    //是否能够加载更多
    public ObservableBoolean loadMoreEnable;
    //加载更多完成
    public ObservableBoolean loadMoreSuccess;
    //上拉加载的监听回调
    public BaseQuickAdapter.RequestLoadMoreListener loadMoreListener;
    //当前页数,默认从0开始,可以设置默认值
    public int mPage;
    //每页加载返回的个数,按照这个来判断是否已经加到头了
    public int PageSize = 15;
    //默认从第几页开始加载
    public int defaultStart;

    public LoadMoreBindingViewModel() {
        super();
        loadMoreView = getLoadMoreView();
        loadMoreListener = getLoadMoreListener();
        loadMoreEnd = new ObservableBoolean();
        loadMoreEnable = new ObservableBoolean();
        loadMoreSuccess = new ObservableBoolean();
    }


    protected LoadMoreView getLoadMoreView() {
        return new LoadMoreView() {
            @Override
            public int getLayoutId() {
                return R.layout.view_load_more;
            }

            @Override
            protected int getLoadingViewId() {
                return R.id.load_more_loading_view;
            }

            @Override
            protected int getLoadFailViewId() {
                return R.id.load_more_load_fail_view;
            }

            @Override
            protected int getLoadEndViewId() {
                return R.id.load_more_load_end_view;
            }
        };
    }

    protected BaseQuickAdapter.RequestLoadMoreListener getLoadMoreListener() {
        return new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        };
    }


    private void loadMore() {
        CSLog.Print("加载更多调用了");
        load();
    }


    @Override
    public void load() {
        load(mPage);
    }

    @Override
    protected void load(Flowable<List<B>> flowable) {
        if (isRefreshing.get()) {
            emptyResId.set(getEmptyViewRes(EmptyViewType.REFRESH));
        } else {
            CSLog.Print("调用了正在加载界面");
            emptyResId.set(getEmptyViewRes(EmptyViewType.LOADING));
        }
        disposable = flowable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<B>>() {
                    @Override
                    public void accept(List<B> result) throws Exception {
                        setData(result);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (isRefreshing.get() || mPage == defaultStart) {
                            emptyResId.set(getEmptyViewRes(EmptyViewType.ERROR));
                            loadMoreEnable.set(true);
                        } else {
                            loadMoreSuccess.set(false);
                            loadMoreSuccess.notifyChange();
                        }
                        isRefreshing.set(false);
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        loadMoreEnable.set(true);
                        emptyResId.set(getEmptyViewRes(EmptyViewType.EMPTY));
                        isRefreshing.set(false);
                        mPage++;
                    }
                });
    }

    //重新加载,页数清空
    @Override
    public void reload() {
        mPage = defaultStart;
        super.reload();
    }

    //设置默认从第几页开始加载
    public void setDefaultStart(int index) {
        defaultStart = index;
        mPage = index;
    }


    public void setData(List<B> dat) {
        addItems(dat);
        if (dat.size() < getPageSize()) {
            loadMoreEnd.set(mPage == defaultStart);
            loadMoreEnd.notifyChange();
        } else {
            loadMoreSuccess.set(true);
            loadMoreSuccess.notifyChange();
        }
    }

    public abstract void load(int mPage);

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int pageSize) {
        this.PageSize = pageSize;
    }
}
