package com.example.leetcode.stack

import java.util.Stack

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
有效字符串需满足：
左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。
每个右括号都有一个对应的相同类型的左括号。

示例 1：
输入：s = "()"
输出：true

示例 2：
输入：s = "()[]{}"
输出：true

示例 3：
输入：s = "(]"
输出：false
 */
fun main() {

}

/**
 * 审题：输入一个字符串，字符串由括号的左右部分组成，括号左右部分如果能够匹配，说明是有效的括号，否则不是
 * 解题：
 * - 遍历字符串，使用栈作为数据结构保存
 * - 如果是左括号，则入栈
 * - 如果是右括号，则出栈，并与当前右括号匹配
 * - 最后看栈中是否还有括号
 * 总结：从栈中取元素使用前，需要先判断; 取出的括号不匹配直接返回false
 */
fun isValid(s: String): Boolean {
    val map = mapOf<Char, Char>(
        '(' to ')',
        '{' to '}',
        '[' to ']'
    )
    val stack = Stack<Char>()
    s.forEach {
        if (map.containsKey(it)) {
            stack.push(it)
        } else {
            if (stack.isEmpty()) {
                return false
            }
            val peek = stack.peek()
            if (map[peek] == it) {
                stack.pop()
            } else {
//                stack.push(it)
                return false
            }
        }
    }
    return stack.isEmpty()
}