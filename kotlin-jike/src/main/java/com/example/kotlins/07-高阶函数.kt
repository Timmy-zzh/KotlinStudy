package com.example.kotlins

import android.view.View
import android.widget.ImageView

fun main() {
    println("高阶函数")
//    setClickListener(::onClick)
//    setClickListener({ view -> })

    samFunc()

    // 研究apply方法源码
    val str = "aldjf"
    str.apply {
        println(this)
    }

}

// Lambda表达式写法
fun samFunc() {
    val image: ImageView? = null
    image?.setOnClickListener(object : View.OnClickListener {
        override fun onClick(p0: View?) {
            gotoNextPage()
        }
    })
    // 去掉object关键字
    image?.setOnClickListener(View.OnClickListener { v: View? ->
        gotoNextPage()
    })
    // sam简化
    image?.setOnClickListener({ v: View? ->
        gotoNextPage()
    })
    // 类型推导
    image?.setOnClickListener({ v -> gotoNextPage() })

    // 只有一个参数,用it
    image?.setOnClickListener({ it ->
        gotoNextPage()
    })
    // it省略
    image?.setOnClickListener({ gotoNextPage() })

    // Lambda挪到括号外面
    image?.setOnClickListener() {
        gotoNextPage()
    }
    // 括号也可以省略
    image?.setOnClickListener {
        gotoNextPage()
    }
}

fun gotoNextPage() {
    //
}

// 定义函数类型
//var clickListener: ((view: View) -> Unit)? = null
//
//// 使用
//fun setClickListener(l: (View) -> Unit) {
//    clickListener = l
//}

// 函数定义
fun add(a: Int, b: Int): Int {
    return a + b;
}

// 函数的引用
val funAdd: (Int, Int) -> Int = ::add

// Lambad表达式演化
fun onClick(view: View): Unit {}
// 高阶函数
//setOnCLickLiseter(::onClick)




























