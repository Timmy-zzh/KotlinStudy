package com.example.kotlins

/**
 * 泛型：泛型是对程序的抽象
 * - 型变：
 * - 逆变 in：万能遥控器
 * -- 传入in，作为参数
 * - 协变：外卖
 */
fun main() {
    println("10-泛型")

    // buy(Controller<TV>()) // 报错
    buy(Controller<TV>()) // 需要加上in关键字，逆变

    //
    val kfcStore = Restaurant<KFC>()
    orderFood(kfcStore)

}

/**
 * 协变
 */
open class Food {}

// KFC
class KFC : Food() {}

// 饭店，提供食物
class Restaurant<out T> {
    fun orderFood(): T? {
        return null
    }
}

// 从这家商店点一份外卖
fun orderFood(restaurant: Restaurant<Food>) {
    val food = restaurant.orderFood()
}

/**
 * 电视机
 */
open class TV {
    open fun turnOn() {

    }
}

class XiaomiTV1 : TV() {
    override fun turnOn() {
        super.turnOn()
    }
}

/**
 * 电视机遥控器
 * - in 逆变
 */
class Controller<in T> {
    fun turnOn(tv: T) {

    }
}

// 买遥控器
fun buy(controller: Controller<XiaomiTV1>) {
    val tv = XiaomiTV1()
    controller.turnOn(tv)
}