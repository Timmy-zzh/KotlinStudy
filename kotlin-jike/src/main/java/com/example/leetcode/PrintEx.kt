package com.example.leetcode

import com.example.leetcode.bean.ListNode

fun List<Int>.print() {
    this.forEach {
        print("$it ,")
    }
    println()
}

fun IntArray.print() {
    this.forEach {
        print("$it ,")
    }
    println()
}

fun Map<String, Int>.print() {
    this.forEach {
        println("${it.key}:${it.value},")
    }
    println()
}

fun ListNode?.print() {
    var node: ListNode? = this
    while (node != null) {
        print("${node.`val`} ,")
        node = node.next
    }
    println()
}