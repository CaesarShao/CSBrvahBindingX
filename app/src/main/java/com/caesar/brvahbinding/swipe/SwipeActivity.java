package com.caesar.brvahbinding.swipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.caesar.brvahbinding.R;
import com.caesar.brvahbinding.databinding.ActivitySwipeBinding;

public class SwipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);
        ActivitySwipeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_swipe);
        SwipeViewModel swipeViewModel = new SwipeViewModel();
        binding.setVm(swipeViewModel);
        swipeViewModel.load();
    }
}
