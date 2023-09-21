package com.example.kotlins._12KtHttp.v5_flow2

import com.google.gson.Gson
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.channels.onSuccess
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.Call
import okhttp3.Response
import java.io.IOException
import java.lang.reflect.Type
import kotlin.Exception

/**
 * 让KtHttp支持Flow流
 * - 在返回KtCall 的时候使用callbackFlow转换成Flow流
 * - 扩展函数实现
 */
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

suspend fun <T : Any> KtCall<T>.asFlow() = callbackFlow<T> {
    val call = request(object : Callback<T> {
        override fun onSuccess(data: T) {
            trySendBlocking(data)
                .onSuccess { close() }
                .onFailure {
                    close(it)
                }
        }

        override fun onFail(throwable: Throwable) {
            close(throwable)
        }
    })

    awaitClose { call.cancel() }
}

// 支持挂起函数
//suspend fun <T : Any> KtCall<T>.await(): T =
//    suspendCancellableCoroutine { continuation ->
//        val call = request(object : Callback<T> {
//
//            override fun onSuccess(data: T) {
//                println("----await onSuceess")
//                continuation.resumeWith(Result.success(data))
////                continuation.resume(data)
//            }
//
//            override fun onFail(throwable: Throwable) {
//                println("----await onFail")
//                continuation.resumeWith(Result.failure(throwable))
////                continuation.resumeWithException(throwable)
//            }
//        })
//
//        continuation.invokeOnCancellation {
//            call.cancel()
//        }
//    }



