package com.application.coincloneapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.coincloneapp.R
import com.application.coincloneapp.databinding.ActivitySelectBinding
import com.application.coincloneapp.view.adapter.SelectRVAdapter
import timber.log.Timber

class SelectActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySelectBinding

    private val viewModel: SelectViewModel by viewModels()

    private lateinit var selectRVAdapter: SelectRVAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getCurrentCoinList()
        viewModel.currentPriceResult.observe(this, Observer {

            selectRVAdapter = SelectRVAdapter(this, it)

            binding.coinListRV.adapter =selectRVAdapter
            binding.coinListRV.layoutManager = LinearLayoutManager(this)

            Timber.d(it.toString())
        })

    }
}