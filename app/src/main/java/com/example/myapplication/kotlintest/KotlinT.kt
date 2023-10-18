package com.example.myapplication.kotlintest

import com.example.kotlins._12KtHttp.ApiServer
import com.example.kotlins._12KtHttp.bean.RepoList
import com.example.kotlins._12KtHttp.v3_suspend.Callback
import com.example.kotlins._12KtHttp.v3_suspend.KtHttpV3
import com.example.kotlins._12KtHttp.v3_suspend.await
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking


// 支持挂起函数
fun testKtHttpV32() = runBlocking {
    KtHttpV3.baseUrl = "https://api.github.com"

    val apiServer = KtHttpV3.create(ApiServer::class.java)

    val deferred = async {
        val ktCall = apiServer.reposSuspend(lang = "Kotlin", since = "weekly")
        ktCall.await()
    }
    println("----result111： $deferred")
    val repoList = deferred.await()

//        println("----result222：" + (repoList == null?"null":repoList.toString))
    println("----result222:${repoList.toString()}")
}

fun testKtHttpV3() {

    KtHttpV3.baseUrl = "https://api.github.com"

    val apiServer = KtHttpV3.create(ApiServer::class.java)

    val ktCall = apiServer.reposSuspend(lang = "Kotlin", since = "weekly")
    println("----testKtHttpV3-111 $ktCall")

    ktCall.request(object : Callback<RepoList> {
        override fun onSuccess(data: RepoList) {
            println("----testKtHttpV3-111 onSuccess: $data")
        }

        override fun onFail(throwable: Throwable) {
            println("----testKtHttpV3-111 onFail: $throwable")
        }
    })

}