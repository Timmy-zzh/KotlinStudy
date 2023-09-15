package com.example.coroutine

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select

/**
 * 使用select
 */
fun main() = runBlocking {
    println("Select ")
    val start = System.currentTimeMillis()

    // 挂起函数
    suspend fun getCacheData(id: String): Produce {
        delay(200)
        return Produce(id, 9.6)
    }

    suspend fun getNetInfo(id: String): Produce {
        delay(3000)
        return Produce(id, 9.8)
    }

    val produce = select<Produce> {
        async { getCacheData("111") }
            .onAwait {
                it
            }
        async { getNetInfo("111") }
            .onAwait {
                it
            }
    }

    logX(produce)
    println("produceNet time:${System.currentTimeMillis() - start}")

    println("main fun end")
}

/**
 * 第一个版本：模拟从缓存和网络获取数据
 */
fun main1() {
    runBlocking {
        println("Select ")
        val start = System.currentTimeMillis()

        // 挂起函数
        suspend fun getCacheData(id: String): Produce {
            delay(200)
            return Produce(id, 9.6)
        }

        suspend fun getNetInfo(id: String): Produce {
            delay(300)
            return Produce(id, 9.8)
        }

        val deferredNet = async {
            getNetInfo("111")
        }
        val deferredCache = async {
            getCacheData("111")
        }

        val produceCache = deferredCache.await()
        logX(produceCache)
        println("produceCache time:${System.currentTimeMillis() - start}")

        val produceNet = deferredNet.await()
        logX(produceNet)
        println("produceNet time:${System.currentTimeMillis() - start}")
    }
}


data class Produce(
    val id: String,
    val price: Double
)

