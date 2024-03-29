package com.assistant.fronted.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
//    private const val BASE_URL="http://127.0.0.1:8080/"
    private const val BASE_URL="http://10.0.2.2:8080/"
//    private const val BASE_URL="http:192.168.2.218:8080/"
    private const val ConnectURL = "http://1.116.250.147:8282/"

    private val retrofit=Retrofit.Builder()
        .baseUrl(ConnectURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass:Class<T>):T= retrofit.create(serviceClass)

    inline fun <reified T> create():T= create(T::class.java)

}