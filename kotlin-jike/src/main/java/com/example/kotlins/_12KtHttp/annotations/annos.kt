package com.example.kotlins._12KtHttp.annotations

@Target(AnnotationTarget.FUNCTION)  // 注解在方法上使用
@Retention(AnnotationRetention.RUNTIME) // 运行时注解
annotation class GET(
    val path: String = ""
)

@Target(AnnotationTarget.VALUE_PARAMETER)  // 注解在属性上使用
@Retention(AnnotationRetention.RUNTIME) // 运行时注解
annotation class Field(
    val field: String = ""
)