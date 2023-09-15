package com.example.kotlins._12KtHttp

import com.example.kotlins._12KtHttp.annotations.Field
import com.example.kotlins._12KtHttp.annotations.GET
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

/**
 * Kotlin版本实现的OkHttp网络请求框架
 * - 底层使用okhttp进行网络请求框架的封装
 * - 第一步创建网络请求接口实现类，
 * - 网络服务接口，定义请求方法（请求方法类型-path，参数-filed）
 * - 发起请求
 */
object KtHttpV1 {
    var baseUrl: String = "https://baseurl"
    val okHttpClient = OkHttpClient.Builder().build()
    val gson = Gson()

    // 创建服务接口
    fun <T> create(service: Class<T>): T {
        return Proxy.newProxyInstance(
            service.classLoader,
            arrayOf<Class<*>>(service),
            object : InvocationHandler {
                override fun invoke(proxy: Any, method: Method, args: Array<Any>): Any? {
                    /**
                     * 判断方法是否GET注解，
                     * - 是的话，则获取注解的value值，和baseUrl拼接成完整请求路径
                     * - 接着处理参数，参数也是注解，需要获取注解的值，并拼接到url后面，采用占位符?和&
                     * - 最后通过retrofit发起请求，并获取最后返回的数据，经过Gson格式化后返回需要的数据格式
                     */

                    // 获取方法的所有注解
                    val annotations = method.annotations
                    for (annotation in annotations) {
                        if (annotation is GET) {
                            val url = baseUrl + annotation.path
                            return invokeReal(url, method, args!!)
                        }
                    }
                    return null
                }
            }
        ) as T
    }

    /**
     * 1、参数处理，get请求需要将参数拼接到url后面
     * 2、使用OkHttp发起请求
     * - 同步请求了
     * 3、处理返回的数据，转换最后返回
     */
    private fun invokeReal(path: String, method: Method, args: Array<Any>): Any? {
        // 1、参数处理
        if (method.parameterAnnotations.size != args.size) {
            return null
        }

        var url = path
        val parameterAnnotations = method.parameterAnnotations
        for (i in parameterAnnotations.indices) {
            for (paramsAnno in parameterAnnotations[i]) {
                if (paramsAnno is Field) {
                    val key = paramsAnno.field
                    val value = args[i].toString()
                    if (!url.contains("?")) {
                        url += "?$key=$value"
                    } else {
                        url += "&$key=$value"
                    }
                }
            }
        }
        println("url=$url")

        //2、发起请求
        val request: Request = Request.Builder()
            .url(url)
            .get()
            .build()

        val response = okHttpClient.newCall(request).execute()

        println("code:${response.code}")
        // 3、响应数据处理
        val json = response.body.toString()
        println("json:$json")
        if (response.code == 200) {
            // 返回值类型的T
            val genericReturnType = method.genericReturnType
            return gson.fromJson<Any?>(json, genericReturnType)
        } else {
            return null
        }
    }


}