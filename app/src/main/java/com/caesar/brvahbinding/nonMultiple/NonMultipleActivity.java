package com.caesar.brvahbinding.nonMultiple;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.caesar.brvahbinding.R;
import com.caesar.brvahbinding.databinding.ActivityNonMultipleBinding;

public class NonMultipleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityNonMultipleBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_non_multiple);
        NonMultiViewModel nonMultiViewModel = new NonMultiViewModel();
        binding.setVm(nonMultiViewModel);
        nonMultiViewModel.load();
    }
}
