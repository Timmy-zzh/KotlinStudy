package com.example.kotlins

import com.example.kotlins._08textprocessor.TextProcessorV1

fun main() {
    println("统计单词频率")
    val text = "Kotlin is very good , I lick Kotlin so much"
    val textProcessorV1 = TextProcessorV1()
    val wordFreqList = textProcessorV1.processText(text)
    println(wordFreqList)
}