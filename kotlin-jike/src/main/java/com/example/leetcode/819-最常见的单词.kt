package com.example.leetcode

import java.lang.IllegalArgumentException

/**
//给定一个段落 (paragraph) 和一个禁用单词列表 (banned)。返回出现次数最多，同时不在禁用列表中的单词。
//题目保证至少有一个词不在禁用列表中，而且答案唯一。
//
//禁用列表中的单词用小写字母表示，不含标点符号。段落中的单词不区分大小写。答案都是小写字母。
//
//示例：
//输入:
//paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
//banned = ["hit"]
//输出: "ball"
//解释:
//"hit" 出现了3次，但它是一个禁用的单词。
//"ball" 出现了2次 (同时没有其他单词出现2次)，所以它是段落里出现次数最多的，且不在禁用列表中的单词。
//注意，所有这些单词在段落里不区分大小写，标点符号需要忽略（即使是紧挨着单词也忽略， 比如 "ball,"），
//"hit"不是最终的答案，虽然它出现次数更多，但它在禁用单词列表中。
 */
fun main() {
//    val paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
//    val banned = arrayOf("hit")
    val paragraph = "Bob. hIt, baLl"
    val banned = arrayOf("bob", "hit")
    val res = mostCommonWord(paragraph, banned)
    println(res)

}

/**
 * 审题：输入一个段落（包含多个单词和标点符号），和一个禁用单词列表，要求从段落中找出使用最多的一个单词，并且不能在禁用列表中出现，并返回
 * 解题思路：
 * - 段落中的单词，需要进行清理，替换标点符号，并将所有大写字母转成小写字母
 * - 接着统计单词出现的次数，使用hashmap存储
 * - 接着过滤掉在禁用列表中的单词，
 * - 接着找出出现次数最多的单词，并返回
 * 解题步骤：
 *
 * 有问题：怎么过滤了，还是把空字符算进来了
 */
fun mostCommonWord(paragraph: String, banned: Array<String>): String {
    return paragraph.lowercase()
        .replace("[^A-Za-z]".toRegex(), " ").trim()
        .split("\\s".toRegex())
        .filter { it !in banned.toSet() && it.isNotEmpty() }
        .groupBy { it }
        .mapValues { it.value.size }
        .apply { this.print() }
        .maxBy { it.value }
        ?.key ?: throw IllegalArgumentException()

}