package com.caesar.brvahbinding.other;

import com.caesar.brvahbinding.R;
import com.caesar.brvahbinding.usal.SimpleData;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;

public class CreateData {
    public static Flowable<List<SimpleData>> getSimpleData() {
        return Flowable.create(new FlowableOnSubscribe<List<SimpleData>>() {
            @Override
            public void subscribe(FlowableEmitter<List<SimpleData>> emitter) throws Exception {
                ArrayList<SimpleData> data = new ArrayList<>();
                data.add(new SimpleData("这货是个标题", "这货是个内容加描述", R.mipmap.animation_img1));
                data.add(new SimpleData("这货是个标题", "这货是个内容加描述", R.mipmap.animation_img2));
                data.add(new SimpleData("这货是个标题", "这货是个内容加描述", R.mipmap.animation_img3));
                data.add(new SimpleData("这货是个标题", "这货是个内容加描述", R.mipmap.animation_img2));
                data.add(new SimpleData("这货是个标题", "这货是个内容加描述", R.mipmap.animation_img1));
                data.add(new SimpleData("这货是个标题", "这货是个内容加描述", R.mipmap.animation_img2));
                data.add(new SimpleData("这货是个标题", "这货是个内容加描述", R.mipmap.animation_img3));
                data.add(new SimpleData("这货是个标题", "这货是个内容加描述", R.mipmap.animation_img1));
                data.add(new SimpleData("这货是个标题", "这货是个内容加描述", R.mipmap.animation_img1));
                data.add(new SimpleData("这货是个标题", "这货是个内容加描述", R.mipmap.animation_img2));
                data.add(new SimpleData("这货是个标题", "这货是个内容加描述", R.mipmap.animation_img3));
                data.add(new SimpleData("这货是个标题", "这货是个内容加描述", R.mipmap.animation_img1));
                data.add(new SimpleData("这货是个标题", "这货是个内容加描述", R.mipmap.animation_img1));
                data.add(new SimpleData("这货是个标题", "这货是个内容加描述", R.mipmap.animation_img2));
                data.add(new SimpleData("这货是个标题", "这货是个内容加描述", R.mipmap.animation_img3));
                data.add(new SimpleData("这货是个标题", "这货是个内容加描述", R.mipmap.animation_img1));
                emitter.onNext(data);
                emitter.onComplete();
            }
        }, BackpressureStrategy.BUFFER);
    }
}
