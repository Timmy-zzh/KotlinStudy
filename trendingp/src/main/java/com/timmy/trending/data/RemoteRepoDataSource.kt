package com.timmy.trending.data

import android.util.Log
import com.timmy.trending.bean.RepoList
import com.timmy.trending.bean.ResultX
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

object RemoteRepoDataSource : RepoDataSource {

    /**
     * 表达式思维：
     * - 开启协程 withContext
     */
    override suspend fun getRepos(): ResultX<RepoList> =
        withContext(Dispatchers.IO) {
            try {
                val repos = RetrofitClient.service.repos()
                Log.d("Data", "data=$repos")
                ResultX.Success(repos)
            } catch (e: Exception) {
                Log.e("Data", e.message, e)
                ResultX.Error(e)
            }
        }

}