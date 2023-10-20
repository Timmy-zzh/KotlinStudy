package com.example.leetcode._03_stack

import java.util.Stack

/**
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。

示例 1：
输入：s = "(()"
输出：2
解释：最长有效括号子串是 "()"

示例 2：
输入：s = ")()())"
输出：4
解释：最长有效括号子串是 "()()"

示例 3：
输入：s = ""
输出：0
 */
fun main() {
    // "()(())" 报错
    longestValidParentheses("()(())")

}

/**
 * 看来得用动态规划算法
 */
fun longestValidParentheses(s: String): Int {
    return 0
}

/**
 * 审题：输入一串字符串，由左右括号组成，找出最长的有效子串，并返回长度
 * 解题：
 * -1、先遍历字符串，将遍历到的左右半括号放入栈中，
 * --如果是左括号直接入栈，栈顶元素必须为空，或是右括号
 * --如果是右括号，则判断栈顶原始是否是左括号，是则入栈，不是则全部出栈，并计算当前栈中已存在的元素数量
 */
fun longestValidParentheses1Err(s: String): Int {
    if (s.isNullOrEmpty()) {
        return 0
    }
    val stack = Stack<Char>()
    var res = 0

    s.forEach {
        if (it == '(') {
            if (stack.isEmpty() || stack.peek() == ')') {
                stack.push(it)
            } else {
                res = Math.max(stack.size - 1, res)
                stack.clear()
                stack.push(it)
            }
        } else {
            // 右括号
            if (stack.isEmpty()) {

            } else if (stack.peek() == '(') {
                stack.push(it)
            } else {
                res = Math.max(stack.size, res)
                stack.clear()
            }
        }
    }
    if (stack.isEmpty()){
        return res
    }

    res = if (stack.peek() == ')'){
        Math.max(stack.size, res)
    }else{
        Math.max(stack.size - 1, res)
    }

    return res
}

