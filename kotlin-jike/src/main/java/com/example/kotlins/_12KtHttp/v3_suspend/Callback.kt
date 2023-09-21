package com.example.kotlins._12KtHttp.v3_suspend

interface Callback<T : Any> {
    fun onSuccess(data: T)
    fun onFail(throwable: Throwable)
}