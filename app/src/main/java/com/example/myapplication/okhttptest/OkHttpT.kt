package com.example.myapplication.okhttptest

import android.util.Log
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * OkHttp同步请求执行
 * https:www.baidu.com?username=admin&password=admin
 */
fun okhttpSync() {
    val client: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .build()

    client.writeTimeoutMillis

    val request = Request.Builder()
        .get()
        .url("https:www.baidu.com?username=admin&password=admin")
        .addHeader("header1", "123")
        .build()
    val newCall = client.newCall(request)
    val response = newCall.execute()

}

/**
 * 异步请求
 */
fun okhttpAsync() {
    val client: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .addInterceptor(LoggerInterceptor())
        .build()

    val formBody = FormBody.Builder()
        .add("username", "adimin")
        .add("password", "123123")
        .build()

    val request = Request.Builder()
        .post(formBody)
        .url("http://www.")
        .build()

    val newCall = client.newCall(request)
    newCall.enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            Log.e("Tim", "call:$call,e:$e")
        }

        override fun onResponse(call: Call, response: Response) {
            Log.e("Tim", "call:$call,response:${response.body?.string()}")
        }
    })
}

fun okhttpAsyncSetParams() {
    val client: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .build()

    val formBody = FormBody.Builder()
        .add("username", "adimin")
        .add("password", "123123")
        .build()

    // 设置入参数据格式
//    RequestBody.Companion.
    val requestBody = RequestBody.create(
        "text/plain;charset=utf-8".toMediaTypeOrNull(),
        "{username:admin;password:123123}"
    )

    val request = Request.Builder()
        .post(formBody)
        .post(requestBody)
        .url("http://www.")
        .build()

    val newCall = client.newCall(request)
    newCall.enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            Log.e("Tim", "call:$call,e:$e")
        }

        override fun onResponse(call: Call, response: Response) {
            Log.e("Tim", "call:$call,response:${response.body?.string()}")
        }
    })
}

fun okhttpUploadFile() {
    val client: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .build()

    val file = File("sdcard/0/123.txt")

    // 设置入参数据格式
//    RequestBody.Companion.
    val requestBody = RequestBody.create(
        "application/octet-stream".toMediaTypeOrNull(),
        file
    )

    val request = Request.Builder()
        .post(requestBody)
        .url("http://www.")
        .build()

    val newCall = client.newCall(request)
    newCall.enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            Log.e("Tim", "call:$call,e:$e")
        }

        override fun onResponse(call: Call, response: Response) {
            Log.e("Tim", "call:$call,response:${response.body?.string()}")
        }
    })
}