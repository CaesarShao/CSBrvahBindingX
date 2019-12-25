package com.caesar.brvahbinding.drag;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.caesar.brvahbinding.R;
import com.caesar.brvahbinding.databinding.ActivityDragAswipeBinding;

public class DragASwipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDragAswipeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_drag_aswipe);
        DragASwipeViewModel dragASwipeViewModel = new DragASwipeViewModel();
        binding.setVm(dragASwipeViewModel);
        dragASwipeViewModel.load();
    }
}
