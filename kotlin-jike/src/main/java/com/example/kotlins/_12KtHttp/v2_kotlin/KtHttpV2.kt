package com.example.kotlins._12KtHttp.v2_kotlin

import com.example.kotlins._12KtHttp.annotations.Field
import com.example.kotlins._12KtHttp.annotations.GET
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.reflect.Method
import java.lang.reflect.Proxy

/**
 * Kotlin版本实现的OkHttp网络请求框架
 * - 函数式编程
 * -- 懒加载
 * -- 牛逼啊，这才是kotlin代码
 */
object KtHttpV2 {
    var baseUrl: String = "https://baseurl"
    private val okHttpClient by lazy { OkHttpClient() }
    private val gson by lazy { Gson() }

    /**
     * 创建服务接口
     * 类型实化
     */
    inline fun <reified T> create(): T {
        return Proxy.newProxyInstance(
            T::class.java.classLoader,
            arrayOf<Class<*>>(T::class.java)
        ) { proxy, method, args ->
//            val annotations = method.annotations
//            for (annotation in annotations) {
//                if (annotation is GET) {
//                    val url = KtHttpV1.baseUrl + annotation.path
//                    return KtHttpV1.invokeReal(url, method, args!!)
//                }
//            }
            return@newProxyInstance method.annotations
                .filterIsInstance<GET>()
                .takeIf { it.size == 1 }
                ?.let { invokeReal2("$baseUrl+${it[0].path}", method, args) }
        } as T
    }

    private fun parseUrl(url: String, pair: Pair<Array<Annotation>, Any>) =
        pair.first
            .filterIsInstance<Field>()
            .first()
            .let { field ->
                if (!url.contains("?")) {
                    "$url?${field.field}=${pair.second}"
                } else {
                    "$url&${field.field}=${pair.second}"
                }
            }

    /**
     * 1、参数处理，get请求需要将参数拼接到url后面
     * 2、使用OkHttp发起请求
     * - 同步请求了
     * 3、处理返回的数据，转换最后返回
     */
    fun invokeReal2(path: String, method: Method, args: Array<Any>): Any? =
        method.parameterAnnotations
            .takeIf { method.parameterAnnotations.size == args.size }
            ?.mapIndexed { index, annotations -> Pair(annotations, args[index]) }
            ?.fold(path, KtHttpV2::parseUrl)
            ?.let {
                println("url:$it")
                Request.Builder().url(it).build()
            }
            ?.let { okHttpClient.newCall(it).execute().body?.toString() }
            ?.let { gson.fromJson(it, method.genericReturnType) }

//    {
//        // 1、参数处理
//        if (method.parameterAnnotations.size != args.size) {
//            return null
//        }
//
//        var url = path
//        val parameterAnnotations = method.parameterAnnotations
//        for (i in parameterAnnotations.indices) {
//            for (paramsAnno in parameterAnnotations[i]) {
//                if (paramsAnno is Field) {
//                    val key = paramsAnno.field
//                    val value = args[i].toString()
//                    if (!url.contains("?")) {
//                        url += "?$key=$value"
//                    } else {
//                        url += "&$key=$value"
//                    }
//                }
//            }
//        }
//        println("url=$url")
//
//        //2、发起请求
//        val request: Request = Request.Builder()
//            .url(url)
//            .get()
//            .build()
//
//        val response = okHttpClient.newCall(request).execute()
//
//        println("code:${response.code}")
//        // 3、响应数据处理
//        val json = response.body.toString()
//        println("json:$json")
//        if (response.code == 200) {
//            // 返回值类型的T
//            val genericReturnType = method.genericReturnType
//            return gson.fromJson<Any?>(json, genericReturnType)
//        } else {
//            return null
//        }
//    }


}
