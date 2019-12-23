package com.caesar.brvahbinding.animation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.caesar.brvahbinding.R;
import com.caesar.brvahbinding.databinding.ActivityAnimationCustomBinding;

public class AnimationCustomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAnimationCustomBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_animation_custom);
        AnimationCustomViewModel animationCustomViewModel = new AnimationCustomViewModel();
        binding.setVm(animationCustomViewModel);
        animationCustomViewModel.load();
    }
}
