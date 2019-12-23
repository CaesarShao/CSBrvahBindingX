package com.caesar.brvahbinding.emptyrefreshview;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.caesar.brvahbinding.R;
import com.caesar.brvahbinding.base.BaseBindingViewModel;
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

    @Override
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

    public static Flowable<List<SimpleData>> getErrorData() {
        CSLog.Print("调用加载错误");
        return Flowable.create(new FlowableOnSubscribe<List<SimpleData>>() {
            @Override
            public void subscribe(FlowableEmitter<List<SimpleData>> emitter) throws Exception {
                emitter.onError(null);
            }
        }, BackpressureStrategy.BUFFER).delay(1, TimeUnit.SECONDS);
    }

    public void cliear(View view) {
        items.clear();
    }

    @Override
    public RecyclerView.ItemDecoration onitemDecoration() {
        return new NormalLineDecoration(30, true);
    }
}
