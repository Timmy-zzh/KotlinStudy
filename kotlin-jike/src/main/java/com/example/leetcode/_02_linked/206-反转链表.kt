package com.example.leetcode._02_linked

import com.example.leetcode.bean.ListNode
import com.example.leetcode.print

/**
 *给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。

示例 1：
输入：head = [1,2,3,4,5]
输出：[5,4,3,2,1]

示例 2：
输入：head = [1,2]
输出：[2,1]

示例 3：
输入：head = []
输出：[]

提示：
链表中节点的数目范围是 [0, 5000]
-5000 <= Node.val <= 5000

进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
 */
fun main() {
    println("实现单链表反转")
    val node1 = ListNode(1)
    val node2 = ListNode(2)
    val node3 = ListNode(3)
    val node4 = ListNode(4)
    val node5 = ListNode(5)
    node1.next = node2
    node2.next = node3
    node3.next = node4
    node4.next = node5

    node1.print()
    val res = reverseList(node1)
    res.print()

}

/**
 * 双指针解法
 * - 核心是保存节点的下一个节点，这样可以防止赋值后的节点对原始链表发生影响
 */
fun reverseList(head: ListNode?): ListNode? {
    var prev: ListNode? = null
    var curr: ListNode? = head

    while (curr != null) {
        val next = curr.next
        // 节点插入到prev链表中
        curr.next = prev    // curr节点插入到prev前面
        prev = curr        // prev节点，移动到前面，等待下一个新节点插入

        curr = next
    }
    return prev
}

/**
 * 审题：输入一个链表头，有可能是空的，链表是一个接着一个的节点，现在需要将这个链表反转并返回反转后的头结点
 * 解题：
 * - 不断循环原始链表，拿到链表的节点，插入到新的链表中
 * - 新链表创建一个空的头结点
 * 总结：每次遍历，都创建了一个新的节点，能不能不新建对象呢？
 */
fun reverseList1(head: ListNode?): ListNode? {
    if (head == null) {
        return null
    }
    val hailNode = ListNode(0)
    var srcNode: ListNode? = head
    var dstNode: ListNode?

    while (srcNode != null) {
        println("node:${srcNode.`val`}")

        dstNode = ListNode(srcNode.`val`)
        if (hailNode.next == null) {
            hailNode.next = dstNode
        } else {
            dstNode.next = hailNode.next
            hailNode.next = dstNode
        }

        srcNode = srcNode.next
    }

    return hailNode.next
}