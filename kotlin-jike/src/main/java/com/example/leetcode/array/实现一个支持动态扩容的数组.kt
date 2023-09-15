package com.example.leetcode.array

import com.example.leetcode.print

fun main() {
    println("实现一个支持动态扩容的数组")
    val myArray = MyArray()
    myArray.add(1)
    myArray.add(3)
    myArray.add(5)
    myArray.add(7)
    myArray.add(9)
    myArray.add(11)
    myArray.add(13)

    myArray.delete(2)
    myArray.delete(1)
    myArray.delete(0)

}

/**
 * 实现一个支持动态扩容的数组
 * - 增删改查
 * - 动态扩容，数据拷贝
 */
class MyArray {
    var count = 8
    var index = 0
    var array: IntArray

    constructor() {
        count = 2
        // 定义一个默认大小是8的数组
        array = IntArray(count)
    }

    /**
     * 插入
     */
    fun add(i: Int, num: Int) {

    }

    fun add(num: Int) {
        println("add $num")
        array.print()
        if (index >= array.size) {
            println("需要扩容---")
            // 扩容
            // 1、创建新的数组
            count = (count * 1.5).toInt()
//            val newArray: IntArray = IntArray(count)
            // 2、老数组数据复制到新数组
            // 1&2 直接copy过去
            val array2 = array.copyOf(count)
            array = array2
            array.print()
        }
        array[index] = num
        index++
        println("add after ---")
        array.print()
    }

    fun get(i: Int): Int {
        if (i >= this.index) {
            return -1
        }
        return array[i]
    }

    /**
     * 删除数组中某个位置的元素
     * 1、先找到这个元素，然后将元素前面的元素和后面的元素数据复制到新的数组中
     * i 表示删除的是元素下标
     */
    fun delete(i: Int): Boolean {
        println("delete $i")
        array.print()
        if (i >= index) {
            return false
        }
        // 头尾元素处理
        when (i) {
            0 -> {
                val newArr = IntArray(index)
                // 先copy前面部分，再遍历后面复制的元素，添加到数组中
                array.copyInto(newArr, 0, 1, index - 1)
                array = newArr

            }

            index - 1 -> {
                index--
                array[i] = 0
            }

            else -> {
                val newArr = IntArray(index)
                // 先copy前面部分，再遍历后面复制的元素，添加到数组中
                array.copyInto(newArr, 0, 0, i)
                var h = i
                for (j in i + 1 until index) {
                    newArr[h++] = array[j]
                }
                array = newArr
            }
        }

        println("delete after $i")
        array.print()
        return true
    }


}