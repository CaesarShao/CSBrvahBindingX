package com.caesar.brvahbinding.headfoot

import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.caesar.brvahbinding.BR
import com.caesar.brvahbinding.R
import com.caesar.brvahbinding.base.BaseBindingViewModel
import com.caesar.brvahbinding.other.CreateData
import com.caesar.brvahbinding.usal.NormalLineTopHeadDecoration
import com.caesar.brvahbinding.usal.SimpleData
import com.caesarlib.brvahbinding.CSBravhItemBinding
import com.caesarlib.brvahbinding.action.CSAction0
import java.util.*

class HeadFootViewModelKot : BaseBindingViewModel<SimpleData?>() {
    override fun getItemBinding(): Map<Int, CSBravhItemBinding> {
        val mp: MutableMap<Int, CSBravhItemBinding> = HashMap()
        mp[0] = CSBravhItemBinding(BR.bean, R.layout.item_simple)
        return mp
    }

    //这个回调是头部的item的绑定数据,也支持绑定多个数据事件
    override fun getHeadBinding(): ArrayList<CSBravhItemBinding> {
        val heads = ArrayList<CSBravhItemBinding>()
        heads.add(
            CSBravhItemBinding(
                BR.data,
                R.layout.layout_head_one,
                HeadOneData(),
                BR.action,
                brvah()
            )
        )
        heads.add(CSBravhItemBinding(BR.data, R.layout.layout_head_two, HeadTwoData()))
        return heads
    }

    //这个回调是脚部的item的绑定数据,也支持绑定多个数据事件
    override fun getFootBinding(): ArrayList<CSBravhItemBinding> {
        val foots = ArrayList<CSBravhItemBinding>()
        foots.add(CSBravhItemBinding(BR.data, R.layout.layout_foot_one, FootOneData()))
        foots.add(CSBravhItemBinding(BR.data, R.layout.layout_foot_two, FootTwoData()))
        return foots
    }

    override fun load() {
        load(CreateData.getSimpleData())
    }

    override fun onitemDecoration(): ItemDecoration {
        return NormalLineTopHeadDecoration(30, true)
    }

    //某个头部绑定的事件,告诉大家可以这样调用
    inner class brvah : CSAction0 {
        override fun call() {
            bindingAdapter.removeAllHeaderView()
        }
    }
}