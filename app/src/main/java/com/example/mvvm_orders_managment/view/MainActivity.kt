package com.example.mvvm_orders_managment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_orders_managment.databinding.ActivityMainBinding
import com.example.mvvm_orders_managment.model.LoadingState
import com.example.mvvm_orders_managment.viewModel.MainActivityViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()
    private val adapter = OrderAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()

        initializeUI()
        initializeObservers()

        viewModel.onViewReady()
    }


    private fun initializeUI() {
        binding.ordersRecyclerView.adapter = adapter
        binding.ordersRecyclerView.layoutManager = LinearLayoutManager(this)

        binding.searchEditText.doOnTextChanged { text, start, before, count ->
            viewModel.onSearchQuery(text.toString())
        }
    }

    private fun initializeObservers() {
        viewModel.loadingStateLiveData.observe(this) {
            onLoadingStateChanged(it)
        }

        viewModel.ordersLiveData.observe(this) {
            adapter.updateOrders(it)
        }

    }

    private fun onLoadingStateChanged(state: LoadingState) {
        binding.searchEditText.visibility = if (state == LoadingState.LOADED) VISIBLE else GONE
        binding.ordersRecyclerView.visibility = if (state == LoadingState.LOADED) VISIBLE else GONE
        binding.loadingView.visibility = if (state == LoadingState.LOADING) VISIBLE else GONE
        if (state == LoadingState.ERROR) {
            Snackbar.make(binding.root, "", Snackbar.LENGTH_SHORT).show()
        }
    }


}