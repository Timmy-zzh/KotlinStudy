package com.example.leetcode

/**
 * 这是 LeetCode 的 1119 号题。题意大致是这样的：程序的输入是一个字符串 s。题目要求我们移除当中的所有元音字母 a、e、i、o、u，然后返回。
 * 题解：过滤掉这五个字母即可
 *
 */
fun main() {
    val res = removeVowels("aksjfkdsjfkioutt")
    println("res:$res")
}

fun removeVowels(s: String) = s.filter {
    it !in setOf('a', 'e', 'i', 'o', 'u')
}

fun removeVowels1(s: String): String {
    return s.filter {
        it !in setOf('a', 'e', 'i', 'o', 'u')
    }
}