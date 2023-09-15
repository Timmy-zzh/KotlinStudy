package com.example.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

val fixedDis = Executors.newFixedThreadPool(2) {
    Thread(it, "MyThread").apply {
        isDaemon = false
    }
}.asCoroutineDispatcher()

fun main() = runBlocking {

    val parentJob = launch(fixedDis) {

        launch {
            var i = 0
            while (isActive) {
                Thread.sleep(500)
                i++
                println("first i=$i")
            }
        }

        launch {
            var i = 0
            while (isActive) {
                Thread.sleep(500)
                i++
                println("second i=$i")
            }
        }
    }
    delay(2000)

    parentJob.cancel()
    parentJob.join()

    println("End")
}

fun main141() {
    println("14 - 协程")

//    launch01()
//    runBlocking02()
    async03()

}

fun async03() = runBlocking {
    println("before:${Thread.currentThread().name}")

    val deferred = async {
        println("async start:${Thread.currentThread().name}")
        delay(1000L)
        println("async end")
        return@async "Async Res"
    }

    // 调用了await就会阻塞，直到挂起函数被唤醒，有返回值
    val await = deferred.await()
    println("async res:${await}")

    println("after:${Thread.currentThread().name}")
}

fun runBlocking02() {
    println("before:${Thread.currentThread().name}")
    // 会阻塞后面的 after方法执行
    runBlocking {
        println("runBlocking start:${Thread.currentThread().name}")
        delay(1000L)
        println("runBlocking end")
    }
    println("after:${Thread.currentThread().name}")

    // 可以去掉sleep，因为runBlocking会阻塞执行
//    Thread.sleep(2000)
}

fun launch01() {
    println("before:${Thread.currentThread().name}")
    GlobalScope.launch(Dispatchers.IO) {
        println("launch start:${Thread.currentThread().name}")
        delay(1000)
        println("launch end")
    }
    println("after:${Thread.currentThread().name}")
    Thread.sleep(2000)
    println("launch01 end")
}
