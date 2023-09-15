package com.example.leetcode

/**
// * 给你两个版本号 version1 和 version2 ，请你比较它们。
//版本号由一个或多个修订号组成，各修订号由一个 '.' 连接。每个修订号由 多位数字 组成，可能包含 前导零 。每个版本号至少包含一个字符。
//修订号从左到右编号，下标从 0 开始，最左边的修订号下标为 0 ，下一个修订号下标为 1 ，以此类推。例如，2.5.33 和 0.1 都是有效的版本号。
//
//比较版本号时，请按从左到右的顺序依次比较它们的修订号。比较修订号时，只需比较 忽略任何前导零后的整数值 。也就是说，修订号 1 和修订号 001 相等。
//如果版本号没有指定某个下标处的修订号，则该修订号视为 0 。例如，版本 1.0 小于版本 1.1 ，因为它们下标为 0 的修订号相同，而下标为 1 的修订号分别为 0 和 1 ，0 < 1 。
//
//返回规则如下：
//如果 version1 > version2 返回 1，
//如果 version1 < version2 返回 -1，
//除此之外返回 0。
//
//示例 1：
//输入：version1 = "1.01", version2 = "1.001"
//输出：0
//解释：忽略前导零，"01" 和 "001" 都表示相同的整数 "1"
//示例 2：
//
//输入：version1 = "1.0", version2 = "1.0.0"
//输出：0
//解释：version1 没有指定下标为 2 的修订号，即视为 "0"
//示例 3：
//
//输入：version1 = "0.1", version2 = "1.1"
//输出：-1
//解释：version1 中下标为 0 的修订号是 "0"，version2 中下标为 0 的修订号是 "1" 。0 < 1，所以 version1 < version2
 */
fun main() {
//    val version1 = "1.01"
//    val version2 = "1.001"
    val version1 = "0.1"
    val version2 = "1.1"
    val res = compareVersion(version1, version2)
    print("res:$res")

}

/**
 * 审题：输入两个版本号字符串，字符串由点好.分割，修订号全部由数字组成，现在要对两个版本号进行比较，相等返回0，其他返回1或者-1
 * 解题思路：
 * - 通过点好。进行分割成两个list集合
 * - while循环，不断从两个list集合中，获取单个字符串，没有的话用0补齐，转换成数字，然后进行比较
 * - 相同的话继续while循环，比较下一个分割号
 * - 不同的话直接比较
 */
fun compareVersion(version1: String, version2: String): Int {
    val list1 = version1.split(".")
    val list2 = version2.split(".")

    var i = 0
    var j = 0
    while (i < list1.size || j < list2.size) {
        val v1 = list1.getOrNull(i++)?.toInt() ?: 0
        val v2 = list2.getOrNull(j++)?.toInt() ?: 0
        println("v1=$v1, v2=$v2")

        if (v1 != v2) {
            return v1.compareTo(v2)
        }
    }

    return 0
}