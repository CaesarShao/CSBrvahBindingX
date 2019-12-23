package com.caesar.brvahbinding.headfoot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.caesar.brvahbinding.R;
import com.caesar.brvahbinding.databinding.ActivityHeadFootBinding;

public class HeadFootActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_foot);
        ActivityHeadFootBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_head_foot);
        HeadFootViewModel headFootViewModel =new HeadFootViewModel();
        binding.setVm(headFootViewModel);
        headFootViewModel.load();
    }
}
