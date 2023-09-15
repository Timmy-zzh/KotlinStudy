package com.example.kotlins._04calcuate

import kotlin.system.exitProcess

/**
 * 目标：实现一个计算器
 * - when与枚举类搭配使用
 */
fun main() {
    println("实战: 实现计算器")
    val help = """
        请输入标准的算式，并且按回车;
        比如: 1 + 1，注意符号与数字之间需要有空格。
        输入exit，退出程序。
    """.trimIndent()

    while (true) {
        println(help)

        // 获取控制台输入
        val input1 = readLine() ?: continue
        if (input1 == "exit") {
            exitProcess(0)
        }

        val inputArr = input1.split(" ")
        val result = calculate1(inputArr)

        if (result == null) {
            println("输入有误")
            continue
        } else {
            println("$input1 = $result")
        }
    }
}

/**
 * Exception in thread "main" java.lang.IllegalArgumentException: No enum constant com.example.mylibrary._01calcuate.Operation.+
at java.base/java.lang.Enum.valueOf(Enum.java:273)
at com.example.mylibrary._01calcuate.Operation.valueOf(CalcuatorV1.kt)
at com.example.mylibrary._01calcuate.CalcuatorV1Kt.calculate1(CalcuatorV1.kt:42)
at com.example.mylibrary._01calcuate.CalcuatorV1Kt.main(CalcuatorV1.kt:27)
at com.example.mylibrary._01calcuate.CalcuatorV1Kt.main(CalcuatorV1.kt)
 */
fun calculate1(inputArr: List<String>): Int? {
    if (inputArr.size != 3) return null

    val left = inputArr[0].toInt()
    // 报错
    val operation = Operation.valueOf(inputArr[1])
    val right = inputArr[2].toInt()

    return when (operation) {
        Operation.ADD -> left + right
        Operation.DELETE -> left - right
        Operation.CHENG -> left * right
        Operation.CHU -> left / right
    }
}

enum class Operation(val operate: String) {
    ADD("+"),
    DELETE("-"),
    CHENG("*"),
    CHU("/")
}

