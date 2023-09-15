package com.example.kotlins

//class BasicType {
//}

/**
 * 基本数据类型
 */
fun main() {

    com.example.kotlins.variableDeclaration01()
//    basicType02()
    com.example.kotlins.array03()
    com.example.kotlins.funStudy04()
    com.example.kotlins.flowControl05()

//    var a: Int
//    a = 21
//    println("a=$a")

//    fun1()
}

/**
 * 流程控制语句：
 * - if
 * - when
 * - for
 * - while
 */
fun flowControl05() {
    val i = 1
    if (i > 0) {
        println("Big")
    } else {
        println("Small")
    }
    // 表达式使用
    val message = if (i > 0) "Big" else "Small"
    println("message:$message")

    com.example.kotlins.getLength("AAA")

}

fun getLength(text: String?): Int {
    return if (text != null) text.length else 0
}

// Elvis 表达式。
fun getLength1(text: String?): Int {
    return text?.length ?: 0
}

/**
 * 函数
 * - 函数声明
 * - 函数调用
 * - 参数默认值
 *
 */
fun funStudy04() {
    com.example.kotlins.funName("Ponh")
    com.example.kotlins.funName(name = "TTT")
    com.example.kotlins.funName1("1111")
    com.example.kotlins.createUser(name = "AAA", age = 20)
}

/**
 * 函数声明：
 * - fun关键字
 * - 函数名 funName
 * - 入参数 name
 * - 返回值 ：String
 */
fun funName(name: String): String {
    return "Hello,$name"
}

// 变型1：单一表达式函数
fun funName1(name: String): String = "Hello,$name"

// 返回值类型推导
fun funName2(name: String) = "Hello2,$name"

fun createUser(
    name: String,
    age: Int,
    gender: Int = 0,
    frientCount: Int = 0
) {

}

/**
 * 数组与集合
 * -获取数据长度和元素
 */
fun array03() {
    val arr = arrayOf("A", "B", "c")
    val numbs = arrayOf(1, 3, 4, 5)
    println("size is ${arr.size}")
    println("one element is ${numbs[2]}")
}

/**
 * 基本类型
 * - kotlin中，一切皆对象
 * - 对于可能为空的变量，声明时在变量类型后面加上问号？
 */
fun basicType02() {
    val d: Double = 1.toDouble()

//    val d: Double = null    // 编译报错
    val d1: Double? = null

    var v1: Double? = null   // 可能为空的变量
    val v2: Double = 10.0    // 不可为空变量
    v1 = v2
//    v2 = v1 // 报错，v2声明为不可为空，不能赋值可能为空的数据

    // 数字类型
    val int = 1
    val long = 1345L
    val double = 13.0
    val float = 1.4f

    // 类型转换，kotlin推崇显示转换
    val aa = 100
    val ab: Long = aa.toLong()

    // 布尔类型 Boolean
    var isTrue: Boolean = aa > ab

    // 字符类型 Char
    val c: Char = 'A'

    // 字符串 String
    val s = "Hello Kotlin"
    // 字符串模版
    val name = "Tim"
    println("Hello,$name")
    val arr = arrayOf("AAA", "BBB")
    println("Hello,${arr[1]}")
    // 原始字符串,按原来字符串格式输出
    val ss = """
        lakdjfal
        klasjf,
            lajkfd
    """.trimIndent()
    println(ss)

}

/**
 * 1、变量声明
 * - var 可变变量
 * - val 不可变量
 * - Kotlin没有基本数据类型，全部是对象
 */
fun variableDeclaration01() {
    var a: Int = 100
    // 类型推导
    var a1 = 100

    // 尽量多使用val
    val b: Int = 0
//    b = 10 // 报错
    val b1: Int?
//    a = b1  // 报错，b1是可空类型，a是不可空类型
}

fun fun1() {
    val a: Int = 1
    val boxedA: Int? = a
    val anotherBoxedA: Int? = a
    print(boxedA == anotherBoxedA)
    print(boxedA === anotherBoxedA)
    // 输出什么内容?
}

