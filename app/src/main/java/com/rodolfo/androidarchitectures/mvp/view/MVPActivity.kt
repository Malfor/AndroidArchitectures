package com.rodolfo.androidarchitectures.mvp.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import com.rodolfo.androidarchitectures.databinding.ActivityMvpBinding
import com.rodolfo.androidarchitectures.mvc.adapter.CountryAdapter
import com.rodolfo.androidarchitectures.mvp.presenter.CountriesPresenter

class MVPActivity : AppCompatActivity(), CountriesPresenter.CountriesView {

    private lateinit var binding: ActivityMvpBinding

    private val adapter: CountryAdapter = CountryAdapter()
    private val presenter: CountriesPresenter = CountriesPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMvpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "MVP Activity"

        setListener()
    }

    private fun setListener() {
        binding.retryButton.setOnClickListener { onRetry() }
    }

    private fun onRetry() {
        presenter.onRefresh()
        binding.list.isGone = true
        binding.retryButton.isVisible = true
        binding.progress.isVisible = true
    }

    override fun setValues(countries: List<String>) {
        binding.list.adapter = adapter
        adapter.setList(countries)
        binding.list.isVisible = true
        binding.retryButton.isGone = true
        binding.progress.isGone = true
    }

    override fun onError(message: String?) {
        Snackbar.make(binding.root, message ?: "Algo salío mal", Snackbar.LENGTH_LONG).show()
        binding.list.isGone = true
        binding.retryButton.isVisible = true
        binding.progress.isGone = true
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, MVPActivity::class.java)
    }
}