package com.example.kotlins._12KtHttp.v4_flow

interface Callback<T : Any> {
    fun onSuccess(data: T)
    fun onFail(throwable: Throwable)
}