package com.timmy.trending.data

import com.timmy.trending.bean.RepoList
import retrofit2.http.GET
import retrofit2.http.Query

interface RepoService {

    @GET("repo")
    suspend fun repos(
        @Query("lang") lang: String = "Kotlin",
        @Query("since") since: String = "weekly"
    ): RepoList
}