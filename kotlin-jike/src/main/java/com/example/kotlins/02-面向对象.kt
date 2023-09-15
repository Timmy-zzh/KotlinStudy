package com.example.kotlins

import java.lang.Exception

fun main() {

}

// 类的声明
class Person(val name: String, var age: Int) {

    val isAdult: Boolean
        get() {
            return age >= 18
        }

    val isAdult1 = age >= 18
}

// 抽象类
abstract class Animal {
    abstract fun walk()
}

class Brid : Animal() {
    override fun walk() {

    }
}

/**
 * 类的继承
 * - 类和方法都需要使用open关键字进行修饰
 */
open class Person1(val name: String) {
    open fun eat() {
        println()
    }
}

class Body(name: String) : Person1(name) {
    override fun eat() {

    }
}

/**
 * 接口与实现
 * - 接口中的方法可以有默认实现
 */
interface Behavior {
    val canWalk: Boolean

    fun walk() {
        if (canWalk) {
            println("Behavior: walk fun...")
        }
    }
}

class Person2(val name: String) : Behavior {
    override val canWalk: Boolean
        get() = true
}

/**
 * 类的嵌套
 * - kotlin嵌套类默认是静态内部类
 * -- 防止出现内存泄漏
 */
class A {
    val name: String = "AA"
    fun foo() = 1
    class B {
        // 报错
//        val a = name
//        val b = foo()
    }
}

// 内部类使用inner关键字进行标识
class A1 {
    val name: String = "AA"
    fun foo() = 1
    inner class B1 {
        // 报错
        val a = name
        val b = foo()
    }
}

// 数据类 data
data class Student(val name: String, val classRoom: Int) {

}

/**
 * 密封类 sealed
 * - 枚举的超类
 * - 与when配合使用，穷尽所有的可能
 */
sealed class Result<out R> {

    data class Success<out T>(val data: T, val message: String = "") : Result<T>()

    data class Error(val exception: Exception) : Result<Nothing>()

    data class Loading(val time: Long = System.currentTimeMillis()) : Result<Nothing>()

}

fun display(data: Result<*>) = when (data) {
    is Result.Success -> println("show success view")
    is Result.Error -> println("show error view")
    is Result.Loading -> println("show loading")
}