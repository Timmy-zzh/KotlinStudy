package com.timmy.trending.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.timmy.trending.network.service.RepoService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Retrofit 封装
 * - 单例
 */
object RetrofitClient {

    private const val TAG = "OKHttp"
    private const val BASE_URL = "https://trendings.herokuapp.com/"
    private const val TIME_OUT = 10

    val moshi: Moshi by lazy {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    val service by lazy { getService(RepoService::class.java, BASE_URL) }

    /**
     * 创建 OkHttpClient 实例
     * 委托&懒加载&单例
     */
    private val okHttpClient: OkHttpClient by lazy {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
        builder.build()
    }

    private fun <S> getService(
        service: Class<S>,
        baseUrl: String,
        client: OkHttpClient = okHttpClient
    ): S {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(baseUrl)
            .build()
            .create(service)
    }

}