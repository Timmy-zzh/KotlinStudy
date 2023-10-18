package com.example.myapplication.okhttptest

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class LoggerInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val response = chain.proceed(request)
        Log.d("Logger", request.url.toString())
        Log.d("Logger", response.message)
        return response
    }
}