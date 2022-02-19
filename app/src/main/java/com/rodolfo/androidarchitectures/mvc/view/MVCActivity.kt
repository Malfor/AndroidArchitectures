package com.rodolfo.androidarchitectures.mvc.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import com.rodolfo.androidarchitectures.databinding.ActivityMvcBinding
import com.rodolfo.androidarchitectures.mvc.adapter.CountryAdapter
import com.rodolfo.androidarchitectures.mvc.controller.CountriesController

class MVCActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMvcBinding

    private var adapter: CountryAdapter = CountryAdapter()
    private val controller = CountriesController(this)

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMvcBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "MVC Activity"

        setListener()
    }

    private fun setListener() {
        binding.retryButton.setOnClickListener { onRetry() }
    }

    fun setValues(values : List<String>) {
        binding.list.adapter = adapter
        adapter.setList(values)
        binding.list.isVisible = true
        binding.retryButton.isGone = true
        binding.progress.isGone = true
    }

    private fun onRetry() {
        controller.onRefresh()
        binding.list.isGone = true
        binding.retryButton.isVisible = true
        binding.progress.isVisible = true
    }

    fun onError(message: String?) {
        Snackbar.make(binding.root, message ?: "Algo salío mal", Snackbar.LENGTH_LONG).show()
        binding.list.isGone = true
        binding.retryButton.isVisible = true
        binding.progress.isGone = true
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, MVCActivity::class.java)
    }
}