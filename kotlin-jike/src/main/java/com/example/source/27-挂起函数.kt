package com.example.source

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 * 挂起函数：
 * - 以同步代码书写的方式，执行异步加载
 * - 挂起，恢复resume
 * - 状态变化，如何实现的
 * - 原理是内部做了转换 CallBack
 * -- CPS
 */

fun main() = runBlocking {
    println("挂起函数 suspend")
    testCoroutine()
}

suspend fun testCoroutine() {
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