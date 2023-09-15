package com.example.coroutine

import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    flow {
        emit(1)
        emit(2)
        emit(3)
        emit(4)
        emit(5)
    }.filter { it > 2 }
        .map { it * 2 }   //可以理解为遍历，并操作
        .take(2)   // 只取前两个数据
        .collect {
            println(it)
        }

}