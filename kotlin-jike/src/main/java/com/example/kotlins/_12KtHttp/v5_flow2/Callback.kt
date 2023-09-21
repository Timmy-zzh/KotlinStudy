package com.example.kotlins._12KtHttp.v5_flow2

interface Callback<T : Any> {
    fun onSuccess(data: T)
    fun onFail(throwable: Throwable)
}