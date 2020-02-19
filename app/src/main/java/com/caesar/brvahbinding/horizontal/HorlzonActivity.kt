package com.caesar.brvahbinding.horizontal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.caesar.brvahbinding.R
import com.caesar.brvahbinding.databinding.ActivityHorlzonBinding

class HorlzonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityHorlzonBinding>(this, R.layout.activity_horlzon)
        val viewModel = HorlzonViewModel()
        binding.vm = viewModel
        viewModel.load()
    }
}
