package com.example.kotlins

import kotlin.reflect.KMutableProperty1
import kotlin.reflect.full.memberProperties

/**
 * 反射的三大作用：
 * - 感知程序的状态，获取对象的所有属性和属性值
 * - 修改程序的状态
 * - 改变程序的运行状态
 */
fun main() {
    println("10-反射")
    /**
     * 声明两个对象，并获取对象中所有属性和对应属性值的内容
     */
    val student1 = Student1("TTT", 98.5, 130)
    val school1 = School1("PKU", "AUS")

    readParams(student1)
    readParams(school1)

    // 修改对象属性的内容
    modifyAddressVal(school1)

    readParams(student1)
    readParams(school1)
}

fun modifyAddressVal(obj: Any) {
    println("modifyAddressVal fun call ...")
    obj::class.memberProperties.forEach {
//        println("${obj::class.simpleName}.${it.name} = ${it.getter.call(obj)}")
        if (it.name == "address" &&     // 属性名称
            it is KMutableProperty1 &&  // 是var修饰的变量
            it.setter.parameters.size == 2 &&   // set方法参数个数，一个是obj，一个是内容
            it.getter.returnType.classifier == String::class    // 属性返回值类型是String
        ) {
            it.setter.call(obj, "China")
            println("modify address params")
        } else {
            println("other parama: ${it.name}")
        }
    }
}

/**
 * 获取所有属性
 * - obj::class KClass类型
 */
fun readParams(obj: Any) {
//    obj::class.members.forEach {
//        println("${obj::class.simpleName}.${it.name} = ${it.parameters}")
//    }

    obj::class.memberProperties.forEach {
        println("${obj::class.simpleName}.${it.name} = ${it.getter.call(obj)}")
    }
}


data class Student1(
    val name: String,
    val score: Double,
    val height: Int
)

data class School1(
    val name: String,
    var address: String
)