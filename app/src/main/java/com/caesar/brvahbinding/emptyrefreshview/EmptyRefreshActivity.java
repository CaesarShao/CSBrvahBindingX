package com.caesar.brvahbinding.emptyrefreshview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.caesar.brvahbinding.R;
import com.caesar.brvahbinding.databinding.ActivityEmptyRefreshBinding;

public class EmptyRefreshActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_refresh);
        ActivityEmptyRefreshBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_empty_refresh);
        EmptyRefrshViewModel emptyRefrshViewModel = new EmptyRefrshViewModel();
        binding.setVm(emptyRefrshViewModel);
        emptyRefrshViewModel.load();
    }
}
