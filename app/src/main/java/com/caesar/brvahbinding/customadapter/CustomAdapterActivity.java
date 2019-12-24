package com.caesar.brvahbinding.customadapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.caesar.brvahbinding.R;
import com.caesar.brvahbinding.databinding.ActivityCustomAdapterBinding;

public class CustomAdapterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCustomAdapterBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_custom_adapter);
        binding.rvShow.setLayoutManager(new LinearLayoutManager(this));
        CustomAdapterViewModel customAdapterViewModel = new CustomAdapterViewModel();
        binding.setVm(customAdapterViewModel);
        customAdapterViewModel.load();
    }
}
