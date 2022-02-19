package com.rodolfo.androidarchitectures.mvvm.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rodolfo.androidarchitectures.model.CountriesService
import com.rodolfo.androidarchitectures.model.Country
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CountriesViewModel(
    private val service: CountriesService
) : ViewModel() {

    private val countries = MutableLiveData<List<String>>()
    private val countryError = MutableLiveData<String?>()

    fun getCountries() : LiveData<List<String>> {
        return countries
    }

    fun getCountryError() : LiveData<String?> {
        return countryError
    }

    init {
        fetchCountries()
    }

    @SuppressLint("CheckResult")
    private fun fetchCountries() {
        service.getCountries()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread() as Scheduler)
            .subscribeWith(object : DisposableSingleObserver<List<Country>>() {
                override fun onSuccess(list: List<Country>) {
                    val countryNames = list.map { it.countryName }
                    countries.value = countryNames
                }

                override fun onError(e: Throwable) {
                    countryError.value = e.message
                }
            })
    }

    fun onRefresh() {
        fetchCountries()
    }

    class CountriesViewModelFactory : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CountriesViewModel(CountriesService()) as T
        }
    }
}