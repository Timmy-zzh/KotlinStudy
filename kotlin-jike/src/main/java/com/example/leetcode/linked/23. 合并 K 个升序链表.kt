package com.example.leetcode.linked

import com.example.leetcode.bean.ListNode

/**
 * 给你一个链表数组，每个链表都已经按升序排列。
请你将所有链表合并到一个升序链表中，返回合并后的链表。

示例 1：
输入：lists = [[1,4,5],[1,3,4],[2,6]]
输出：[1,1,2,3,4,4,5,6]
解释：链表数组如下：
[
1->4->5,
1->3->4,
2->6
]
将它们合并到一个有序链表中得到。
1->1->2->3->4->4->5->6

示例 2：
输入：lists = []
输出：[]

示例 3：
输入：lists = [[]]
输出：[]
 */
fun main() {

}

/**
 * 审题：输入一个链表集合，其中每个链表都是升序的，现在需要将所有链表合并为一个升序的链表，并返回头结点
 * 解题：遍历所有链表，新建一个空链表，并与所有链表升序合并
 * - 升序合并：两条链表不断遍历合并
 * - 虚拟头结点
 * - 升序遍历，合并
 */
fun mergeKLists(lists: Array<ListNode?>): ListNode? {
    if (lists.isNullOrEmpty()) {
        return null
    }
    val head = ListNode(-1)
    lists.forEach {
        head.next = mergeLink(head.next, it)
    }
    return head.next
}

fun mergeLink(node11: ListNode?, node12: ListNode?): ListNode? {
    var node = ListNode(0)
    val emptyHead = node
    var node1 = node11
    var node2 = node12

    while (node1 != null || node2 != null) {
        val num1 = node1?.`val` ?: Int.MAX_VALUE
        val num2 = node2?.`val` ?: Int.MAX_VALUE
        if (num1 < num2) {
            node.next = node1
            node1 = node1?.next
        } else {
            node.next = node2
            node2 = node2?.next
        }
        node = node.next!!
    }

    return emptyHead.next
}
