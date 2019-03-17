package com.saledance.saledanceapp.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.saledance.saledanceapp.model.entities.PublishedPost
import com.saledance.saledanceapp.network.Api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class BusinessListViewModel: ViewModel() {

    var disposable: Disposable? = null

    private val errorMassage: MutableLiveData<Throwable> = MutableLiveData()

    private val business: MutableLiveData<ArrayList<PublishedPost>> by lazy {
        MutableLiveData<ArrayList<PublishedPost>>().also {
            loadBusinesses()
        }
    }

    fun getBusinesses(): LiveData<ArrayList<PublishedPost>> = business

    fun getError(): LiveData<Throwable> = errorMassage

    private val api by lazy {
        Api.create()
    }

    private fun loadBusinesses(){
        Log.d("aviv", "started")
        disposable =
            api.getPublishedPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->  business.value = result},
                    { error ->  errorMassage.value = error}
                )
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}