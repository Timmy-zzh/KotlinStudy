package com.example.coroutine

import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() {
    println("16 job lifecycle")
//    jobLifecycle01()
//    deferred02()
//    structConcurr03()
    calcTime04()
}

/**
 * 计算多个协程执行耗时
 */
fun calcTime04() = runBlocking {

    suspend fun getRes1(): String {
        delay(1000)
        return "result1"
    }

    suspend fun getRes2(): String {
        delay(1000)
        return "result2"
    }

    suspend fun getRes3(): String {
        delay(1000)
        return "result3"
    }

//    val results = arrayListOf<String>()
//    val time = measureTimeMillis {
//        results.add(getRes1())
//        results.add(getRes2())
//        results.add(getRes3())
//    }

    var results: List<String>
    val time = measureTimeMillis {
        val res1 = async { getRes1() }
        val res2 = async { getRes2() }
        val res3 = async { getRes3() }

        results = listOf(
            res1.await(),
            res2.await(),
            res3.await()
        )
    }
    println("time:$time")
    println(results)
    delay(2000)
    logX("structConcurr03 end")
}

/**
 * 结构化并发
 * 带有结构和层级的并发
 */
fun structConcurr03() = runBlocking {
    val parentJob: Job
    var job1: Job? = null
    var job2: Job? = null
    var job3: Job? = null

    parentJob = launch {
        job1 = launch {
            println("job1 start")
            delay(1000)
            println("job1 end")
        }
        job2 = launch {
            println("job2 start")
            delay(3000)
            println("job2 end")
        }
        job3 = launch {
            println("job3 start")
            delay(3000)
            println("job3 end")
        }
    }

    delay(500)

    parentJob.children.forEachIndexed { index, job ->
        when (index) {
            0 -> println("index=$index, is job1=${job === job1}")
            1 -> println("index=$index, is job2=${job === job2}")
            2 -> println("index=$index, is job3=${job === job3}")
        }
    }
    delay(6000)
    logX("structConcurr03 end")

}

fun deferred02() = runBlocking {
    val deferred1 = async {
        println("deferred1 start")
        delay(1000)
        println("deferred1 end")
        "deferred02 Result"
    }
    deferred1.log()
    val result = deferred1.await()
    println("reslut:$result")
    deferred1.log()
    delay(1100)

    logX("deferred02 end")
}

fun jobLifecycle01() = runBlocking {
    val job1 = launch {
        println("Job1 start")
        delay(1000)
        println("Job1 end")
    }
    job1.log()
    job1.start()
    delay(1100)
    job1.log()
//    job1.cancel()
//    job1.log()
    job1.invokeOnCompletion {
        job1.log()
    }

    delay(500)
    logX("jobLifecycle01 end")
}

fun Job.log() {
    logX(
        """ 
        isActive=$isActive,
        isCancelled=$isCancelled,
        isCompleted=$isCompleted
    """.trimIndent()
    )
}

fun logX(any: Any?) {
    println(
        """
===========================
$any
Thread:${Thread.currentThread().name}
===========================
    """.trimIndent()
    )
}
