package com.example.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

// 单个协程中处理独有的变量，等到协程操作完后，再统一返回

// 解决4：Actor

/**
 * 解决3：非阻塞式锁 Mutex
 */
fun main() = runBlocking {
    println("并发")
    var i = 0
    val mutex = Mutex()
    val jobs = mutableListOf<Job>()

    suspend fun prepare() {}

    repeat(10) {
        val job = launch(Dispatchers.Default) {
            repeat(1000) {
//                mutex.lock()  // 不推荐写法
//                i++
//                mutex.unlock()

                // 保证一定解锁的方法
                mutex.withLock {
                    i++
                }
            }
        }
        jobs.add(job)
    }

//    delay(1000)
    jobs.joinAll()

    println("res:$i")
}

// 处理:2：单线程操作，async

/**
 * 处理1：synchronized
 * 缺点：无法调用挂起函数
 */
fun main222() = runBlocking {
    println("并发")
    var i = 0
    val any = Any()
    val jobs = mutableListOf<Job>()

    suspend fun prepare() {}

    repeat(10) {
        val job = launch(Dispatchers.Default) {
            repeat(1000) {
                synchronized(any) {
//                    prepare()
                    i++
                }
            }
        }
        jobs.add(job)
    }

//    delay(1000)
    jobs.joinAll()

    println("res:$i")
}

/**
 * 开启10个协程处理共享数据
 */
fun main221() = runBlocking {
    println("并发")
    var i = 0
    val jobs = mutableListOf<Job>()

    repeat(10) {
        val job = launch(Dispatchers.Default) {
            repeat(1000) {
                i++
            }
        }
        jobs.add(job)
    }

//    delay(1000)
    jobs.joinAll()

    println("res:$i")
}