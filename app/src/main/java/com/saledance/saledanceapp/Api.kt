package com.saledance.saledanceapp

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.*

interface Api {


        @GET("api")
        fun getPublishedPosts() : Observable<ArrayList<PublishedPost>>

        companion object {
                fun create(): Api {

                        val retrofit = Retrofit.Builder()
                                .addCallAdapterFactory(
                                        RxJava2CallAdapterFactory.create())
                                .addConverterFactory(
                                        GsonConverterFactory.create())
                                .baseUrl("https://saledance.azurewebsites.net/")
                                .build()

                        return retrofit.create(Api::class.java)
                }
        }

}
