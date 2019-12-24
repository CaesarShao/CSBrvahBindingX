package com.caesar.brvahbinding.emptyrefreshview;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.caesar.brvahbinding.R;
import com.caesar.brvahbinding.base.BaseBindingViewModel;
import com.caesar.brvahbinding.base.EmptyViewType;
import com.caesar.brvahbinding.other.CreateData;
import com.caesar.brvahbinding.usal.NormalLineDecoration;
import com.caesar.brvahbinding.usal.SimpleData;
import com.caesarlib.brvahbinding.CSBravhItemBinding;
import com.caesarlib.brvahbinding.CSLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;

public class EmptyRefrshViewModel extends BaseBindingViewModel<SimpleData> {

    private int countD;

    @Override
    protected Map<Integer, CSBravhItemBinding> getItemBinding() {
        Map<Integer, CSBravhItemBinding> mp = new HashMap<>();
        mp.put(0, new CSBravhItemBinding(com.caesar.brvahbinding.BR.bean, R.layout.item_simple));
        return mp;
    }
    @Override//里面模拟网络请求时,出现的情况,正常返回,数据为空,请求错误.
    public void load() {
        ++countD;
        if (countD % 3 == 0) {
            load(CreateData.getSimpleData());
        } else if (countD % 3 == 1) {
            load(getEmptyData());
        } else if (countD % 3 == 2) {
            load(getErrorData());
        }

    }
    //模拟数据返回为空
    public static Flowable<List<SimpleData>> getEmptyData() {
        CSLog.Print("调用加载空布局");
        return Flowable.create(new FlowableOnSubscribe<List<SimpleData>>() {
            @Override
            public void subscribe(FlowableEmitter<List<SimpleData>> emitter) throws Exception {
                ArrayList<SimpleData> data = new ArrayList<>();
                emitter.onNext(data);
                emitter.onComplete();
            }
        }, BackpressureStrategy.BUFFER).delay(3, TimeUnit.SECONDS);
    }
    //模拟网络请求出错
    public static Flowable<List<SimpleData>> getErrorData() {
        CSLog.Print("调用加载错误");
        return Flowable.create(new FlowableOnSubscribe<List<SimpleData>>() {
            @Override
            public void subscribe(FlowableEmitter<List<SimpleData>> emitter) throws Exception {
                emitter.onError(null);
            }
        }, BackpressureStrategy.BUFFER).delay(1, TimeUnit.SECONDS);
    }
    //清空数据的点击事件,这边因为我的baseAdapter里面,对items做过监听,可以看CSBindingListChangedCallBack这个类,所以,当items的数据发生
    //改变时,绑定的适配器会自动刷新,所以,如果你是使用自己的适配器的话(下面会讲),别忘记刷新适配器
    public void cliear(View view) {
        items.clear();
    }

    @Override
    public RecyclerView.ItemDecoration onitemDecoration() {
        return new NormalLineDecoration(30, true);
    }

    @Override//可以重写空布局的UI
    public int getEmptyViewRes(int type) {
        switch (type) {
            case EmptyViewType.ERROR:
                return R.layout.layout_frame_error_view;
            case EmptyViewType.LOADING:
                return R.layout.layout_frame_loading_view;
            case EmptyViewType.REFRESH:
                return R.layout.layout_frame_refresh_view;
            default:
                return R.layout.layout_frame_empty_view;
        }
    }
}
