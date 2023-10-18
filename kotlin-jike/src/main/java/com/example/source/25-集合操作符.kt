package com.example.source

import com.example.kotlins._12KtHttp.annotations.GET

fun main() {

    listOperator()

}


/**

 */
fun listOperator() {

    val students1 = listOf(
        Student("abc", 59),
        Student("kdfja", 85),
        Student("kdfja", 85),
        Student("kdfja", 55),
        Student("kdfja", 75),
        Student("kdfja", 96),
        Student("kdfja", 85),
        Student("kdfja", 35),
        Student("kdfja", 65),
        Student("kdfja", 66),
        Student("kdfja", 68),
        Student("kdfja", 78),
        Student("kdfja", 88),
        Student("kdfja", 93),
    )

    //  * 过滤 filter
    // 源码就是创建一个新的数组，过滤后将符合条件的元素添加到数组中
    val newS = students1.filter {
        it.score < 60
    }
    println(newS)

    students1.filterIndexed { index, student ->
        index == 2
    }

    // 相当于过滤 元素属于哪类类型 xxx isInstance GET
    students1.filterIsInstance<GET>()

    // 转换 map
    val map = students1.map {
        it.copy(name = "aaa")
    }
    println(students1)
    println(map)

    // flatten 合并两个集合
    listOf(students1, map)
        .flatten()

    // 分组
    val groupBy = students1.groupBy { "${it.score / 10}0分组" }
    println(groupBy)

    // 分割，找出前三名 take
    // 后三名    .takeLast()
    val take = students1
        .sortedByDescending { it.score }
        .take(3)
    println(take)

    // 剔除 drop

    // 求和
    val sumOf = students1.sumOf { it.score }
    println(sumOf)

    val reduce = students1
        .map { it.score }
        .reduce { acc, score ->
            acc + score
        }
    println(reduce)

    val fold = students1
        .map { it.score }
        .fold(0) { acc, i ->
            acc + i
        }
    println(fold)

}

data class Student(val name: String, val score: Int)