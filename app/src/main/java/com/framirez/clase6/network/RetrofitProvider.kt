package com.framirez.clase6.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProvider {

    private val baseUrl = "https://gateway.marvel.com:443/"

    val retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getMarvelService() : MarvelService {
        return retrofit.create(MarvelService::class.java)
    }

}