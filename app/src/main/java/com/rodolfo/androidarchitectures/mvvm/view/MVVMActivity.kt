package com.rodolfo.androidarchitectures.mvvm.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.rodolfo.androidarchitectures.databinding.ActivityMvvmBinding
import com.rodolfo.androidarchitectures.mvc.adapter.CountryAdapter
import com.rodolfo.androidarchitectures.mvvm.viewmodel.CountriesViewModel

class MVVMActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMvvmBinding

    private val adapter = CountryAdapter()
    private lateinit var viewModel: CountriesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMvvmBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "MVVM Activity"

        viewModel = ViewModelProvider(
            this,
            CountriesViewModel.CountriesViewModelFactory()
        ).get(CountriesViewModel::class.java)

        observeViewModel()

        setListener()
    }

    private fun observeViewModel() {
        viewModel.getCountries().observe(this) { list ->
            setValues(list)
        }

        viewModel.getCountryError().observe(this) { message ->
            onError(message)
        }
    }

    private fun setListener() {
        binding.retryButton.setOnClickListener { onRetry() }
    }

    private fun onRetry() {
        viewModel.onRefresh()
        binding.list.isGone = true
        binding.retryButton.isVisible = true
        binding.progress.isVisible = true
    }

     private fun setValues(countries: List<String>) {
        binding.list.adapter = adapter
        adapter.setList(countries)
        binding.list.isVisible = true
        binding.retryButton.isGone = true
        binding.progress.isGone = true
    }

    private fun onError(message: String?) {
        Snackbar.make(binding.root, message ?: "Algo salío mal", Snackbar.LENGTH_LONG).show()
        binding.list.isGone = true
        binding.retryButton.isVisible = true
        binding.progress.isGone = true
    }
    companion object {
        fun getIntent(context: Context) = Intent(context, MVVMActivity::class.java)
    }
}