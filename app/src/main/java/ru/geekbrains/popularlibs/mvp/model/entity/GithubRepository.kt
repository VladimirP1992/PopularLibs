package ru.geekbrains.popularlibs.mvp.model.entity

//https://api.github.com/users/googlesamples/repos
data class GithubRepository(
    val id: String,
    val name: String,
    val forksCount: Int
)