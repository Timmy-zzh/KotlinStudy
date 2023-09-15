package com.example.kotlins._12KtHttp.v3

interface Callback<T : Any> {
    fun onSuccess(data: T)
    fun onFail(throwable: Throwable)
}