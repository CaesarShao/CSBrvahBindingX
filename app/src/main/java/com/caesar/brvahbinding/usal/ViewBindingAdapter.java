package com.caesar.brvahbinding.usal;

import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.caesar.brvahbinding.R;


/**
 * 作者：kelingqiu on 17/10/12 16:03
 * 邮箱：42747487@qq.com
 */
public class ViewBindingAdapter {




    @BindingAdapter({"picbitmap"})
    public static void setImageBitmap(ImageView imageView, Bitmap bitmap) {
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
    }

    @BindingAdapter({"picResource"})
    public static void setImageBitmap(ImageView imageView, int resId) {
        imageView.setImageResource(resId);
    }

    @BindingAdapter({"picIsExpand"})
    public static void setImageExpandRes(ImageView imageView, Boolean resId) {
        if (resId){
            imageView.setImageResource(R.mipmap.arrow_b);
        }else {
            imageView.setImageResource(R.mipmap.arrow_r);

        }
    }

}
