package com.timmy.trending.case1

import com.timmy.trending.bean.RepoList
import com.timmy.trending.bean.ResultX
import com.timmy.trending.data.IRepository
import com.timmy.trending.data.MainRepository

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