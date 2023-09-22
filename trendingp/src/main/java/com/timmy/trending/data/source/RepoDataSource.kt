package com.timmy.trending.data.source

import com.timmy.trending.data.entity.RepoList
import com.timmy.trending.data.entity.ResultX

interface RepoDataSource {
    suspend fun getRepos(): ResultX<RepoList>
}
