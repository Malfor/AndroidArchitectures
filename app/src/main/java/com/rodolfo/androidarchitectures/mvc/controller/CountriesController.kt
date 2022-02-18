package com.rodolfo.androidarchitectures.mvc.controller

import android.annotation.SuppressLint
import com.rodolfo.androidarchitectures.model.CountriesService
import com.rodolfo.androidarchitectures.model.Country
import com.rodolfo.androidarchitectures.mvc.view.MVCActivity
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CountriesController(
    private val view: MVCActivity
) {

    private val service = CountriesService()

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
                    view.setValues(countryNames)
                }

                override fun onError(e: Throwable) {
                    view.onError(e.message)
                }
            })
    }

    fun onRefresh() {
        fetchCountries()
    }
}