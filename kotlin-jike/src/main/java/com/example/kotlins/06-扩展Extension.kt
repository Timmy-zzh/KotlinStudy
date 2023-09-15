package com.example.kotlins

fun main() {
    println("Extension")
    val str = "ljklf"
    val lastE = str.lastElement()
    println(lastE)

    println(str.lastE1)

    str.trim()

}

// 扩展函数
fun String.lastElement(): Char? {
    if (this.isEmpty()) {
        return null
    }
    return this[this.length - 1]
}

// 扩展属性
val String.lastE1: Char?
    get() {
        if (this.isEmpty()) {
            return null
        }
        return this[length - 1]
    }
