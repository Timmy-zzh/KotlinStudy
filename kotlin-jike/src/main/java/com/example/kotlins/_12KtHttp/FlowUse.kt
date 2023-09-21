package com.example.kotlins._12KtHttp

import com.example.kotlins._12KtHttp.v4_flow.KtHttpV4
import com.example.kotlins._12KtHttp.v4_flow.asFlow
import com.example.kotlins._12KtHttp.v5_flow2.KtHttpV5
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking
import kotlin.math.sin

fun main() = runBlocking {
    testFlow()
}


suspend fun testFlow() {
    KtHttpV4.create(ApiServer::class.java)
        .reposFlow(lang = "Kotlin", since = "Weekly")
        .asFlow()
        .catch {
            println("Catch:$it")
        }
        .collect {
            println("collect:$it")
        }

    KtHttpV5.create(ApiServer::class.java)
        .reposFlow2(lang = "11", since = "222")
        .flowOn(Dispatchers.IO)
        .catch {
            println("Catch:$it")
        }
        .collect {
            println("collect:$it")
        }

}
