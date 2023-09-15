package com.example.kotlins._04calcuate

import kotlin.system.exitProcess

/**
 * 目标：实现一个计算器
 * - 在输出台，有一段提示文案
 * - 用户可以进行输入，且有固定格式，例如：1 + 1
 * - 按回车键后计算出结果，并将结果打印在控制台展示
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
//        val input = readLine()
//        if (input==null){
//            continue
//        }
        val input1 = readLine() ?: continue
        if (input1 == "exit") {
            exitProcess(0)
        }

        val inputArr = input1.split(" ")

        val result = calculate(inputArr)
        if (result == null) {
            println("输入有误")
            continue
        } else {
            println("$input1 = $result")
        }
    }
}

fun calculate(inputArr: List<String>): Int? {
    val left = inputArr[0].toInt()
    val operate = inputArr[1]
    val right = inputArr[2].toInt()

    return when (operate) {
        "+" -> left + right
        "-" -> left - right
        "*" -> left * right
        "/" -> left / right
        else -> null
    }
}

