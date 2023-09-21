package com.example.kotlins._12KtHttp

import com.example.kotlins._12KtHttp.annotations.Field
import com.example.kotlins._12KtHttp.annotations.GET
import com.example.kotlins._12KtHttp.bean.RepoList
import com.example.kotlins._12KtHttp.v3_suspend.KtCall
import kotlinx.coroutines.flow.Flow

interface ApiServer {

    @GET("/repo")
    fun repoSync(
        @Field("lang") lang: String,
        @Field("since") since: String
    ): RepoList

    @GET("/repo")
    fun reposSuspend(
        @Field("lang") lang: String,
        @Field("since") since: String
    ): KtCall<RepoList>


    @GET("/repo")
    fun reposFlow(
        @Field("lang") lang: String,
        @Field("since") since: String
    ): com.example.kotlins._12KtHttp.v4_flow.KtCall<RepoList>


    @GET("/repo")
    fun reposFlow2(
        @Field("lang") lang: String,
        @Field("since") since: String
    ): Flow<RepoList>
}