package com.timmy.trending.data

import com.timmy.trending.bean.RepoList
import com.timmy.trending.bean.ResultX

// 数据层

interface RepoDataSource {
    suspend fun getRepos(): ResultX<RepoList>
}

interface IRepository {
    suspend fun getRepoList(): ResultX<RepoList>
}