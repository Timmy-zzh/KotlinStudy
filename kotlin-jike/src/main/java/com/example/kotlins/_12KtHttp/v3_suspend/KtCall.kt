package com.example.kotlins._12KtHttp.v3_suspend

import com.google.gson.Gson
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.Call
import okhttp3.Response
import java.io.IOException
import java.lang.reflect.Type
import kotlin.Exception

class KtCall<T : Any>(
    private val call: Call,
    private val gson: Gson,
    private val type: Type
) {
    fun request(callback: Callback<T>): Call {
        // todo
        call.enqueue(object : okhttp3.Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("----onFailure,code= $e")
                callback.onFail(e)
            }

            override fun onResponse(call: Call, response: Response) {
                println("----onResponse,code= ${response.code}")
                try {
                    val data = gson.fromJson<T>(response.body?.string(), type)
                    callback.onSuccess(data)
                } catch (e: Exception) {
                    callback.onFail(e)
                }
            }
        })
        return call
    }
}

// 支持挂起函数
suspend fun <T : Any> KtCall<T>.await(): T =
    suspendCancellableCoroutine { continuation ->
        val call = request(object : Callback<T> {

            override fun onSuccess(data: T) {
                println("----await onSuceess")
                continuation.resumeWith(Result.success(data))
//                continuation.resume(data)
            }

            override fun onFail(throwable: Throwable) {
                println("----await onFail")
                continuation.resumeWith(Result.failure(throwable))
//                continuation.resumeWithException(throwable)
            }
        })

        continuation.invokeOnCancellation {
            call.cancel()
        }
    }



