package com.caesar.brvahbinding.loadmore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.caesar.brvahbinding.R;
import com.caesar.brvahbinding.databinding.ActivityLoadMoreChatBinding;

public class LoadMoreChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoadMoreChatBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_load_more_chat);
        binding.swp.setEnabled(false);
        LoadMoreChatViewModel loadMoreChatViewModel = new LoadMoreChatViewModel(binding.rvShow);
        binding.setVm(loadMoreChatViewModel);
        loadMoreChatViewModel.load();
    }
}
