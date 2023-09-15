package com.example.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() = runBlocking {
    println("15 挂起函数")
    val user = getUserInfo()
    println(user)
    val list = getFirendList(user)
    println(list)
    val feedList = getFeedList(list)
    println(feedList)
}

suspend fun getUserInfo(): String {
    withContext(Dispatchers.IO) {
        delay(1000)
    }
    return "BoyCoder"
}

suspend fun getFirendList(user: String): String {
    withContext(Dispatchers.IO) {
        delay(1000)
    }
    return "Tom,Jack"
}

suspend fun getFeedList(list: String): String {
    withContext(Dispatchers.IO) {
        delay(1000)
    }
    return "{FeedList ...}"
}