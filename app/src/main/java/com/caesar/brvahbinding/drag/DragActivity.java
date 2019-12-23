package com.caesar.brvahbinding.drag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.caesar.brvahbinding.R;
import com.caesar.brvahbinding.databinding.ActivityDragBinding;

public class DragActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag);
        ActivityDragBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_drag);
        DragViewModel dragViewModel = new DragViewModel(binding.rvShow);
        binding.setVm(dragViewModel);
        dragViewModel.load();
    }
}
