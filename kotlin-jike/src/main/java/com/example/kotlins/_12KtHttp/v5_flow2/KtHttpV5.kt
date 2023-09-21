package com.example.kotlins._12KtHttp.v5_flow2

import com.example.kotlins._12KtHttp.annotations.Field
import com.example.kotlins._12KtHttp.annotations.GET
import com.google.gson.Gson
import com.google.gson.internal.`$Gson$Types`
import com.google.gson.internal.`$Gson$Types`.getRawType
import kotlinx.coroutines.flow.flow
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Proxy
import java.util.concurrent.Flow

/**
 * 让invokeReal 方法最终返回的数据对象及时Flow
 */
object KtHttpV5 {
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

        return when {
            isFlowReturn(method) -> {

                flow<T> {
                    val response = okHttpClient.newCall(request).execute()
                    val genericReturnType = method.genericReturnType
                    val json = response.body.toString()
                    val result = gson.fromJson<T>(json, genericReturnType)

                    emit(result)
                }
            }

            isKtCallReturn(method) -> {
                val newCall = okHttpClient.newCall(request)
                val genericReturnType = getTypeArgument(method)

                KtCall<T>(newCall, gson, genericReturnType)
            }

            else -> {
                // 同步
                val response = okHttpClient.newCall(request).execute()

                val genericReturnType = method.genericReturnType
                val json = response.body.toString()
                gson.fromJson<Any?>(json, genericReturnType)
            }
        }
    }

    private fun isFlowReturn(method: Method): Boolean =
        getRawType(method.genericReturnType) == Flow::class.java

    private fun isKtCallReturn(method: Method): Boolean =
        getRawType(method.genericReturnType) == KtCall::class.java

    // 函数返回类型的泛型
    private fun getTypeArgument(method: Method) =
        (method.genericReturnType as ParameterizedType).actualTypeArguments[0]

}