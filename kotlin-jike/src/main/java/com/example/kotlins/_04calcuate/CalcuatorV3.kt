package com.example.kotlins._04calcuate

import java.lang.StringBuilder
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
    val calcuate = Calcuate3()
    calcuate.start()
}

class Calcuate3 {
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
        if (shouldExit(input)) exitProcess(0)

        val expr = parseExpression(input) ?: return null
        val left = expr.left
        val operate = expr.operate
        val right = expr.right
//        println("222 left=$left , opeare=${operate.value} , right=$right")
        return when (operate) {
            Operation3.ADD -> addString(left, right)
            Operation3.MINUS -> minusString(left, right)
            Operation3.MULTI -> multiString(left, right)
            Operation3.DIVI -> diviString(left, right)
        }
    }

    private fun diviString(left: String, right: String): String {
        val res = left.toInt() / right.toInt()
        return res.toString()
    }

    private fun multiString(left: String, right: String): String {
        val res = left.toInt() * right.toInt()
        return res.toString()
    }

    private fun minusString(left: String, right: String): String {
        val res = left.toInt() - right.toInt()
        return res.toString()
    }

    /**
     * 连个数字相加
     * -单独抽取出来，后期如果要修改，单独该这一个方法就可以
     * - 兼容大数相加：
     * --1、两个数字从个位数，开始获取数字，不够的用0补齐
     * --2、将两个数字，相同位置的数字相加，注意处理满10的情况，需要进位 carry
     * --3、循环处理，使用stringbuild保存相加后的数字
     * --4、循环结束后，需要判断carry是否有值
     * --5、通过stringbuild保存下来的数字，个位数在第一位，需要进行翻转处理
     */
    private fun addString(left: String, right: String): String {
        var leftNum = left.length - 1
        var rightNum = right.length - 1
        var carry = 0
        val sb = StringBuilder()

        while (leftNum >= 0 || rightNum >= 0) {
            val leftVal = if (leftNum >= 0) left[leftNum].digitToInt() else 0
            val rightVal = if (rightNum >= 0) right[rightNum].digitToInt() else 0

            val sum = leftVal + rightVal + carry
            carry = sum / 10

            sb.append(sum % 10)
            leftNum--
            rightNum--
        }

        if (carry > 0) {
            sb.append(carry)
        }

        return sb.reversed().toString()
    }

    /**
     * 解析输入的文本：
     * - 先判断是否有运算符，有的话进行切割，然后组成Expression对象返回
     */
    private fun parseExpression(input: String): Expression3? {
        val operate = parseOperation1(input) ?: return null
        val inputList = input.split(operate.value)
        if (inputList.size != 2) return null

        return Expression3(
            left = inputList[0].trim(),
            operate = operate,
            right = inputList[1].trim()
        )
    }

    private fun parseOperation(input: String): Operation3? {
        return when {
            input.contains(Operation3.ADD.value) -> Operation3.ADD
            input.contains(Operation3.MINUS.value) -> Operation3.MINUS
            input.contains(Operation3.MULTI.value) -> Operation3.MULTI
            input.contains(Operation3.DIVI.value) -> Operation3.DIVI
            else -> null
        }
    }

    private fun parseOperation1(input: String): Operation3? {
        Operation3.values().forEach {
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

data class Expression3(
    val left: String,
    val operate: Operation3,
    val right: String
)

enum class Operation3(val value: String) {
    ADD("+"),
    MINUS("-"),
    MULTI("*"),
    DIVI("/")
}

