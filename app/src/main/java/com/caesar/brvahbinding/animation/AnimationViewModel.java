package com.caesar.brvahbinding.animation;

import android.view.View;
import android.widget.AdapterView;

import androidx.recyclerview.widget.RecyclerView;

import com.caesar.brvahbinding.R;
import com.caesar.brvahbinding.base.BaseBindingViewModel;
import com.caesar.brvahbinding.other.CreateData;
import com.caesar.brvahbinding.usal.NormalLineDecoration;
import com.caesar.brvahbinding.usal.SimpleData;
import com.caesarlib.brvahbinding.CSBravhItemBinding;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.HashMap;
import java.util.Map;

public class AnimationViewModel extends BaseBindingViewModel<SimpleData> {

    public AdapterView.OnItemSelectedListener onItemClickListener = getOnItemCli();

    public AnimationViewModel() {
        super();
        //该viewmodel是演示效果,实际在构造方法中,直接调用即可,
        // animationType.set(BaseQuickAdapter.SLIDEIN_BOTTOM);
    }

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




    public AdapterView.OnItemSelectedListener getOnItemCli() {
        return new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        animationType.set(BaseQuickAdapter.SLIDEIN_BOTTOM);
                        break;
                    case 1:
                        animationType.set(BaseQuickAdapter.ALPHAIN);
                        break;
                    case 2:
                        animationType.set(BaseQuickAdapter.SCALEIN);
                        break;
                    case 3:
                        animationType.set(BaseQuickAdapter.SLIDEIN_LEFT);
                        break;
                    case 4:
                        animationType.set(BaseQuickAdapter.SLIDEIN_RIGHT);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }

    @Override
    public RecyclerView.ItemDecoration onitemDecoration() {
        return new NormalLineDecoration(30, true);
    }
}
