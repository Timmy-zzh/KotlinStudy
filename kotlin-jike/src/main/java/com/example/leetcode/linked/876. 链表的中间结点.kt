package com.example.leetcode.linked

import com.example.leetcode.bean.ListNode
import com.example.leetcode.print

/**
 * 给你单链表的头结点 head ，请你找出并返回链表的中间结点。
如果有两个中间结点，则返回第二个中间结点。

示例 1：
输入：head = [1,2,3,4,5]
输出：[3,4,5]
解释：链表只有一个中间结点，值为 3 。

示例 2：
输入：head = [1,2,3,4,5,6]
输出：[4,5,6]

解释：该链表有两个中间结点，值分别为 3 和 4 ，返回第二个结点。
提示：
链表的结点数范围是 [1, 100]
1 <= Node.val <= 100
 */
fun main() {
    println("876. 链表的中间结点")
    val head = ListNode(1)
    val node2 = ListNode(2)
    val node3 = ListNode(3)
    val node4 = ListNode(4)
    val node5 = ListNode(5)
    val node6 = ListNode(6)
    head.next = node2
    node2.next = node3
    node3.next = node4
    node4.next = node5
//    node5.next = node6

    head.print()
    val res = middleNode(head)
    res.print()
}

fun middleNode(head: ListNode?): ListNode? {
    var fast = head
    var slow = head
    while (fast?.next != null) {
        fast = fast.next!!.next
        slow = slow?.next
    }
    return slow
}

/**
 * 审题：输入一个链表，找到链表的中间节点，并返回
 * 解题：双指针解法，快慢指针，快指针每次走两步，慢指针每次走一步，快指针判断next，和next。next是否为null
 * 总结：链表一定要画图
 */
fun middleNode1(head: ListNode?): ListNode? {
    var fast = head
    var slow = head
    while (fast != null) {
        if (fast.next == null) {
            return slow
        }
        if (fast.next!!.next == null) {
            return slow?.next
        }

        fast = fast.next!!.next
        slow = slow?.next
    }
    return null
}