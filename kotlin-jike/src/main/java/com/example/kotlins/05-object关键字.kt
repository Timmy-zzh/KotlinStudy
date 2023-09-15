package com.example.kotlins

import android.view.View

/**
 * object关键字学习
 *
 */
fun main() {
    // 匿名内部类
    setClick(null)
    anonymityInnerClass()
    // 单例模式
    User.login()
    // 伴生对象 componin
    User1.InnerClass.foo()
    User2.InnerClass2.foo()
    User3.foo()

    User4.creator("Tim")

    //
    UserManger1.user
    UserManger2.getInstance("aaa")
    PersonManager.getInstance("vvvv")

    // 抽象模板类
    UserManager3.crator("AAA")
    PersonManager3.crator("BBB")

}

/**
 * 四种方式实现单例模式
 */
// 1、懒汉式 -- by lazy
object UserManger1 {
    val user by lazy {
        loadUser()
    }

    private fun loadUser(): User2 {
        return User2()
    }
}

// 2 DCL
class UserManger2 private constructor(name: String) {

    companion object {

        @Volatile
        private var INSTANCE2: UserManger2? = null

        fun getInstance(name: String): UserManger2 =
            // 第一次判空  INSTANCE2 ?:
            INSTANCE2 ?: synchronized(this) {
                // 第二次判空，接着调用also方法进行赋值it
                INSTANCE2 ?: UserManger2(name).also {
                    INSTANCE2 = it
                }
            }
    }
}

// 3.抽象类模版
class PersonManager private constructor(name: String) {

    companion object {
        @Volatile
        private var INSTANCE2: PersonManager? = null
        fun getInstance(name: String): PersonManager =
            // 第一次判空  INSTANCE2 ?:
            INSTANCE2 ?: synchronized(this) {
                // 第二次判空，接着调用also方法进行赋值it
                INSTANCE2 ?: PersonManager(name).also {
                    INSTANCE2 = it
                }
            }
    }
}

abstract class BaseSingleton<in P, out T> {
    @Volatile
    private var instance: T? = null

    fun getInstance(param: P) {
        instance ?: synchronized(this) {
            instance ?: crator(param).also {
                instance = it
            }
        }
    }

    abstract fun crator(param: P): T
}

// 改造
class UserManager3 private constructor(name: String) {
    companion object : BaseSingleton<String, UserManager3>() {
        override fun crator(param: String): UserManager3 {
            return UserManager3(name = param)
        }
    }
}

class PersonManager3 private constructor(name: String) {
    companion object : BaseSingleton<String, PersonManager3>() {
        override fun crator(param: String): PersonManager3 = PersonManager3(param)
    }
}

/**
 *  伴生对象，工厂模式
 *  - 工厂只有一个User4
 *  - 通过传入不同的参数，可以制造不同的User4对象
 */
class User4 private constructor(name: String) {

    companion object {
        @JvmStatic
        fun creator(name: String): User4 {
            return User4(name = name)
        }
    }
}

// 单例

// companion object将foo方法提出到外部了
class User3 {
    companion object InnerClass3 {
        @JvmStatic
        fun foo() {
            //
        }
    }
}

// 注解 @JvmStatic，将foo设置为静态方法
class User2 {
    object InnerClass2 {
        @JvmStatic
        fun foo() {
            //
        }
    }
}

// 嵌套类，内部的类是单例饿汉式,foo方法是普通方法
class User1 {
    object InnerClass {
        fun foo() {
            //
        }
    }
}

// static代码块中初始化对象，饿汉式
object User {
    fun login() {
        // do something
    }
}

// 匿名内部类，同时实现了多个接口，这个实现类可能只用到了一次
fun anonymityInnerClass() {
    object : Abs5(), A5, B5 {
        override fun abs() {
            TODO("Not yet implemented")
        }

        override fun funA() {
            TODO("Not yet implemented")
        }

        override fun funB() {
            TODO("Not yet implemented")
        }
    }
}

fun setClick(view: View?) {
    view?.setOnClickListener(object : View.OnClickListener {
        override fun onClick(p0: View?) {
            // do something
        }
    })
}

interface A5 {
    fun funA()
}

interface B5 {
    fun funB()
}

abstract class Abs5 {
    abstract fun abs()
}