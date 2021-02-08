package com.taein.filelistsendtest.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CampusRetrofitServiceFactory {

    val CAMPUS_TALK_BASE_URL = "http://52.79.106.253:8080"

    val interceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
//        val client = OkHttpClient.Builder().build()

    val retrofit = Retrofit.Builder()
        .client(client)
//        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(CAMPUS_TALK_BASE_URL)
        .build()

    fun create(): PostingService {
        return retrofit.create<PostingService>(PostingService::class.java)
    }
}