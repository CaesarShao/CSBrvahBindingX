package com.caesar.brvahbinding.animation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.caesar.brvahbinding.R;
import com.caesar.brvahbinding.databinding.ActivityAnimationBinding;

public class AnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAnimationBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_animation);
        AnimationViewModel animationViewModel = new AnimationViewModel();
        binding.setVm(animationViewModel);
        animationViewModel.load();
    }
}
