package com.example.kotlins

import com.example.kotlins._12KtHttp.ApiServer
import com.example.kotlins._12KtHttp.KtHttpV1
import com.example.kotlins._12KtHttp.KtHttpV2

/**
 * 注解
 * -是什么：程序的补充说明
 * -元注解
 * -自定义注解
 * -主机使用
 */
fun main() {
    println("10-注解")

    val c = Test()

    KtHttpV1.baseUrl = "https://api.github.com"
    val apiServer = KtHttpV1.create(ApiServer::class.java)
    val data = apiServer.repos(lang = "Kotlin", since = "weekly")
    println(data)


    KtHttpV2.baseUrl = "https://api.github.com"
    val apiServer2: ApiServer = KtHttpV2.create()
    val data2 = apiServer2.repos(lang = "Kotlin", since = "weekly")
    println(data2)

}

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
public annotation class MyAnnotation(
    val message: String,
    val replaceWith: ReplaceWith = ReplaceWith(""),
    val level: DeprecationLevel = DeprecationLevel.WARNING
)

@MyAnnotation(
    message = "a123",
    replaceWith = ReplaceWith("CalalcV3"),
    level = DeprecationLevel.ERROR
)
class Test {

}