package com.example.mylibrary.kthttp

import com.example.kotlins._12KtHttp.ApiServer
import com.example.kotlins._12KtHttp.v1_java.KtHttpV1
import com.example.kotlins._12KtHttp.v3_suspend.KtHttpV3
import com.example.kotlins._12KtHttp.bean.RepoList
import com.example.kotlins._12KtHttp.v3_suspend.Callback
import kotlin.test.Test

class KtHttpTest {

    @Test
    fun ktHttpTestV1() {
        KtHttpV1.baseUrl = "https://api.github.com"

        val apiServer = KtHttpV1.create(ApiServer::class.java)

        val data = apiServer.repoSync(lang = "Kotlin", since = "weekly")

        println(data)

    }

    @Test
    fun ktHttpTestV3() {
        KtHttpV3.baseUrl = "https://api.github.com"

        val apiServer = KtHttpV3.create(ApiServer::class.java)

        val ktCall = apiServer.reposSuspend(lang = "Kotlin", since = "weekly")
        println("---- $ktCall")

        ktCall.request(object : Callback<RepoList> {
            override fun onSuccess(data: RepoList) {
                println("---- $data")
            }

            override fun onFail(throwable: Throwable) {
                println("---- $throwable")
            }
        })
    }
}