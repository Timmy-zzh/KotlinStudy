package com.example.leetcode.sort

import com.example.leetcode.print

fun main() {
    val array = intArrayOf(6, 4, 5, 1, 3, 2)
    array.print()
    val res = sortBobb(array)
    res.print()
}

/**
 * 冒泡排序:
 * 解题：两层for循环，
 * - 外层for循环，规定循环到的位置，每次循环后最后一个元素都要是最大值
 * - 内层for循环，用于比较相邻两个元素的大小，大的元素要在后面，并进行交换
 */
fun sortBobb(array: IntArray): IntArray {
    val size = array.size
    for (i in 0 until size) {
        for (j in 0 until size - i - 1) {
            if (array[j] > array[j + 1]) {
                val temp = array[j]
                array[j] = array[j + 1]
                array[j + 1] = temp
            }
        }
    }
    return array
}