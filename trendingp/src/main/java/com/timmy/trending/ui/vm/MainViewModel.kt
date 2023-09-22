package com.timmy.trending.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timmy.trending.data.entity.Repo
import com.timmy.trending.data.entity.RepoList
import com.timmy.trending.data.entity.ResultX
import com.timmy.trending.domain.GetRepoListUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    val getRepoListUseCase: GetRepoListUseCase = GetRepoListUseCase()
) : ViewModel() {

    val repos: LiveData<RepoList>
        get() = _repos
    private val _repos = MutableLiveData<RepoList>()

    fun loadRepos() {
//        viewModelScope.launch{}

        viewModelScope.launch {
            val result = getRepoListUseCase()
            when (result) {
                is ResultX.Success -> {
                    _repos.value = result.data
                }

                is ResultX.Error -> {
                    _repos.value = RepoList(items = createItem(), count = 10)
                }

                ResultX.Loading -> {
                    // loading show
                }
            }
        }

    }

    private fun createItem(): List<Repo> =
        listOf<Repo>(
//            repeat(19) {
            Repo(repo = "lasjfdlkda"),
            Repo(repo = "lasjfdlkda"),
            Repo(repo = "lasjfdlkda"),
            Repo(repo = "lasjfdlkda"),
            Repo(repo = "lasjfdlkda"),
            Repo(repo = "lasjfdlkda"),
            Repo(repo = "lasjfdlkda"),
            Repo(repo = "lasjfdlkda"),
            Repo(repo = "lasjfdlkda"),
            Repo(repo = "lasjfdlkda"),
            Repo(repo = "lasjfdlkda"),
            Repo(repo = "lasjfdlkda"),
            Repo(repo = "lasjfdlkda"),
            Repo(repo = "lasjfdlkda"),
            Repo(repo = "lasjfdlkda"),
        )
}