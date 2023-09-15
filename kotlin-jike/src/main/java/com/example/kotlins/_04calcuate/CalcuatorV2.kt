package com.example.kotlins._04calcuate

import kotlin.system.exitProcess

/**
 * 目标：实现一个计算器
 * - when与枚举类搭配使用
 * - 第二个版本：使用面向对象思想进行封装
 * - 创建类Calcuate,在main方法中调用start属性方法
 * - 根据输入的input数据，需要做好输入数据的兼容，不管什么规格文案都需要能处理，
 * - 创建对input文本进行解析，得到Expression类，该类中包含数字和对应的操作符
 */
fun main() {
    println("实战: 实现计算器")
    val calcuate = Calcuate()
    calcuate.start()
}

class Calcuate {
    val help = """
        请输入标准的算式，并且按回车;
        比如: 1 + 1，注意符号与数字之间需要有空格。
        输入exit，退出程序。
    """.trimIndent()

    fun start() {
        while (true) {
            println(help)
            // 获取控制台输入
            val input = readLine() ?: continue

            val result = calcuate(input)

            if (result == null) {
                println("输入有误")
                continue
            } else {
                println("$input = $result")
            }
        }
    }

    fun calcuate(input: String): String? {
        if (shouldExit(input)) {
            exitProcess(0)
        }
        val expr = parseExpression(input) ?: return null
        val left = expr.left
        val operate = expr.operate
        val right = expr.right
//        println("222 left=$left , opeare=${operate.value} , right=$right")
        return when (operate) {
            Operation1.ADD -> addString(left, right)
            Operation1.MINUS -> (left.toInt() - right.toInt()).toString()
            Operation1.MULTI -> (left.toInt() * right.toInt()).toString()
            Operation1.DIVI -> (left.toInt() / right.toInt()).toString()
        }
    }

    private fun addString(left: String, right: String): String {
        return (left.toInt() + right.toInt()).toString()
    }

    /**
     * 解析输入的文本：
     * - 先判断是否有运算符，有的话进行切割，然后组成Expression对象返回
     */
    private fun parseExpression(input: String): Expression? {
        val operate = parseOperation1(input) ?: return null
        val inputList = input.split(operate.value)
        if (inputList.size != 2) return null

        return Expression(
            left = inputList[0].trim(),
            operate = operate,
            right = inputList[1].trim()
        )
    }

    private fun parseOperation(input: String): Operation1? {
        return when {
            input.contains(Operation1.ADD.value) -> Operation1.ADD
            input.contains(Operation1.MINUS.value) -> Operation1.MINUS
            input.contains(Operation1.MULTI.value) -> Operation1.MULTI
            input.contains(Operation1.DIVI.value) -> Operation1.DIVI
            else -> null
        }
    }

    private fun parseOperation1(input: String): Operation1? {
        Operation1.values().forEach {
            if (input.contains(it.value)) {
                return it
            }
        }
        return null
    }

    private fun shouldExit(input: String): Boolean {
        return input == "exit"
    }
}

data class Expression(
    val left: String,
    val operate: Operation1,
    val right: String
)

enum class Operation1(val value: String) {
    ADD("+"),
    MINUS("-"),
    MULTI("*"),
    DIVI("/")
}

