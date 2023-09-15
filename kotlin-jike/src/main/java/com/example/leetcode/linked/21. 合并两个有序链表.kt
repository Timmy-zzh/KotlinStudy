package com.example.leetcode.linked

import com.example.leetcode.bean.ListNode
import com.example.leetcode.print

/**
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。

示例 1：
输入：l1 = [1,2,4], l2 = [1,3,4]
输出：[1,1,2,3,4,4]

示例 2：
输入：l1 = [], l2 = []
输出：[]

示例 3：
输入：l1 = [], l2 = [0]
输出：[0]

提示：
两个链表的节点数目范围是 [0, 50]
-100 <= Node.val <= 100
l1 和 l2 均按 非递减顺序 排列
 */
fun main() {
    println("21. 合并两个有序链表")
    val list1 = ListNode(1)
    val list12 = ListNode(2)
    val list13 = ListNode(4)
    list1.next = list12
    list12.next = list13

    val list2 = ListNode(1)
    val list22 = ListNode(3)
    val list23 = ListNode(4)
    list2.next = list22
    list22.next = list23

    val res = mergeTwoLists(list1, list2)
    res.print()

}

fun mergeTwoLists2(list1: ListNode?, list2: ListNode?): ListNode? {
    val hailNode: ListNode = ListNode(-1)
    var currNode: ListNode = hailNode

    while (list1 != null && list2 != null) {
        if (list1.`val` < list2.`val`) {
            currNode.next = list1
//            list1 = list1.next    // list1是val修饰的，不能赋值
        } else {
            currNode.next = list2
//            list2 = list2.next
        }
        currNode = currNode.next!!
    }
    currNode.next = list1 ?: list2

    return hailNode.next
}

/**
 * 遍历两个链表，都不为空，则判断大小，有一个为空，则直接接上后面的界面即可
 * 头部空节点
 */
fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
    val hailNode: ListNode = ListNode(-1)
    var currNode: ListNode = hailNode
    var node1 = list1
    var node2 = list2

    while (node1 != null && node2 != null) {
        if (node1.`val` < node2.`val`) {
            currNode.next = node1
            node1 = node1.next
        } else {
            currNode.next = node2
            node2 = node2.next
        }
        currNode = currNode.next!!
    }
    currNode.next = node1 ?: node2

    return hailNode.next
}

/**
 * 审题：输入两个有序链表，合并为一个有序链表
 * 解题：
 * 1、遍历两个链表，比较两个链表的节点的大小，小的先拿来保存到新链表的后面
 * 2、采用链表空节点
 */
fun mergeTwoLists1(list1: ListNode?, list2: ListNode?): ListNode? {
    val empthNode: ListNode = ListNode(0)
    var node1 = list1
    var node2 = list2
    var currNode = empthNode

    while (node1 != null || node2 != null) {
        // 比较
        var node: ListNode?
        if (node1 != null && node2 != null) {
            if (node1.`val` < node2.`val`) {
                node = node1.next   // 保存下一个节点

                currNode.next = node1   // 节点插入
                currNode = node1
                currNode.next = null

                node1 = node
            } else {
                node = node2.next   // 保存下一个节点

                currNode.next = node2   // 节点插入
                currNode = node2
                currNode.next = null

                node2 = node
            }
        } else if (node1 != null) {
            node = node1.next   // 保存下一个节点

            currNode.next = node1   // 节点插入
            currNode = node1
            currNode.next = null

            node1 = node
        } else {
            node = node2!!.next   // 保存下一个节点

            currNode.next = node2   // 节点插入
            currNode = node2
            currNode.next = null

            node2 = node
        }

        // 插入到新链表中

        // node1,node2赋值
    }

    return empthNode.next
}