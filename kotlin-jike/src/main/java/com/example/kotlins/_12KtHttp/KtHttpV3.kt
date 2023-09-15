package com.example.kotlins._12KtHttp

import com.example.kotlins._12KtHttp.annotations.Field
import com.example.kotlins._12KtHttp.annotations.GET
import com.example.kotlins._12KtHttp.v3.KtCall
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Proxy

/**
 * v3版本，让接口支持异步请求
 * =设计思想：动态代理中，执行的方法最后的返回值，之前返回的是一个Bean对象，因为是同步请求，直接返回数据
 * ==异步请求，调用的是enqueue方法，需要传入okhttp的一个Callbac回调接口实例，
 * ==改为返回一个KtCall对象，该对象包含okhttp请求call,方法的返回类型，和json对象，
 * ==KtCall对象中并没有发起真正的网络接口请求，KtCall类中还有一个call方法，在该方法中传入自己定义的CallBack，
 * ==
 */
object KtHttpV3 {
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
                            return invokeReal<Any>(url, method, args!!)
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
    private fun <T : Any> invokeReal(path: String, method: Method, args: Array<Any>): Any? {
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
        println("----url=$url")

        //2、发起请求
        val request: Request = Request.Builder()
            .url(url)
            .get()
            .build()

//        val response = okHttpClient.newCall(request).execute()

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

        // todo 支持同步和异步请求，通过判断返回类型处理
        val newCall = okHttpClient.newCall(request)
        val genericReturnType = getTypeArgument(method)

        return KtCall<T>(newCall, gson, genericReturnType)
    }

    // 函数返回类型的泛型
    private fun getTypeArgument(method: Method) =
        (method.genericReturnType as ParameterizedType).actualTypeArguments[0]


}