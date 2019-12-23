package com.caesar.brvahbinding.expand;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.caesar.brvahbinding.R;
import com.caesar.brvahbinding.databinding.ActivityExpandBinding;

public class ExpandActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand);
        ActivityExpandBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_expand);
        ExpandViewModel expandViewModel = new ExpandViewModel();
        binding.setVm(expandViewModel);
        expandViewModel.load();

    }
}
