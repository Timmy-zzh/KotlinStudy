package com.timmy.trending.data.repository

import com.timmy.trending.data.entity.RepoList
import com.timmy.trending.data.entity.ResultX

interface IRepository {
    suspend fun getRepoList(): ResultX<RepoList>
}