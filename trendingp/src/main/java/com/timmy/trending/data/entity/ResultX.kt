package com.timmy.trending.data.entity

import java.lang.Exception

/**
 * 实体层
 */
sealed class ResultX<out R : Any> {

    data class Success<out T : Any>(val data: T) : ResultX<T>()

    data class Error(val exception: Exception) : ResultX<Nothing>()

    object Loading : ResultX<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }

}
