package com.timmy.trending.data

import com.timmy.trending.bean.RepoList
import com.timmy.trending.bean.ResultX

/**
 * 数据层 - 仓库
 */
class MainRepository(
    private val dataSource: RepoDataSource = RemoteRepoDataSource,  // 远端数据层
    private val localDataSource: RepoDataSource? = null     // 本地数据层
) : IRepository {
    override suspend fun getRepoList(): ResultX<RepoList> {
        // 根据业务逻辑判断是从本地获取，还是请求云端数据
        return dataSource.getRepos()
    }
}