package com.example.kotlins._12KtHttp

import com.example.kotlins._12KtHttp.annotations.Field
import com.example.kotlins._12KtHttp.annotations.GET
import com.example.kotlins._12KtHttp.bean.RepoList
import com.example.kotlins._12KtHttp.v3.KtCall

interface ApiServer {

    @GET("/repo")
    fun repos(
        @Field("lang") lang: String,
        @Field("since") since: String
    ): KtCall<RepoList>

    @GET("/repo")
    fun repoSync(
        @Field("lang") lang: String,
        @Field("since") since: String
    ): RepoList

}