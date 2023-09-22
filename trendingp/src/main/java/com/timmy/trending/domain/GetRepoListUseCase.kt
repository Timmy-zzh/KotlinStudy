package com.timmy.trending.domain

import com.timmy.trending.data.entity.ResultX
import com.timmy.trending.data.entity.RepoList
import com.timmy.trending.data.repository.IRepository
import com.timmy.trending.data.repository.MainRepository

/**
 * 领域层
 * - 复杂业务逻辑时使用
 * - 平常的使用mvvm架构即可
 */
class GetRepoListUseCase(private val repository: IRepository = MainRepository()) {
    suspend operator fun invoke(): ResultX<RepoList> {
        return repository.getRepoList()
    }
}