package com.timmy.trending.data.entity

data class RepoList(
    val count: Int = 0,
    val items: List<Repo> = listOf(),
    val msg: String = "empty"
)

data class Repo(
    val added_starts: String = "",
    val avatars: List<String> = listOf(),
    val desc: String = "",
    val forks: String = "",
    val lang: String = "",
    val repo: String = "",
    val repo_link: String = "",
    val stars: String = ""
)