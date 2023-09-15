package com.example.kotlins

fun main() {
    println("09-委托")

    Universal(SqlDB()).save()
    Universal(GreeDaoDB()).save()

    //
    println("委托属性")
    println(data)
    println(data)

}

/**
 * 委托属性
 */
val data: String by lazy {
    request()
}

fun request(): String {
    println("requets call")
    return "net data"
}

class Item() {
    var count: Int = 0
    // 使用count属性的引用
    var total: Int by ::count
}


/**
 * 委托类
 */
interface DB {
    fun save()
}

class SqlDB : DB {
    override fun save() {
        println("save Sql")
    }
}

class GreeDaoDB : DB {
    override fun save() {
        println("save GreeDao")
    }
}

/**
 * 委托类，使用by关键字，内部最终的实现都交给了入参db
 * - 再不用写接口的方法了，为简洁而生
 */
class Universal(db: DB) : DB by db

