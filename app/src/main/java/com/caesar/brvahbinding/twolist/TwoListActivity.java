package com.caesar.brvahbinding.twolist;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.caesar.brvahbinding.R;
import com.caesar.brvahbinding.databinding.ActivityTwoListBinding;

public class TwoListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_list);
        ActivityTwoListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_two_list);
        TwoListViewModel twoListViewModel = new TwoListViewModel(binding.rvShow);
        binding.setVm(twoListViewModel);
        twoListViewModel.loadA();
        twoListViewModel.loadB();
    }
}
