package com.caesar.brvahbinding.loadmore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.caesar.brvahbinding.R;
import com.caesar.brvahbinding.databinding.ActivityLoadMoreLineBinding;

public class LoadMoreLineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_more_line);
        ActivityLoadMoreLineBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_load_more_line);
        LoadMoreLineViewModel loadMoreLineViewModel = new LoadMoreLineViewModel();
        binding.setVm(loadMoreLineViewModel);
        loadMoreLineViewModel.load();

    }
}
