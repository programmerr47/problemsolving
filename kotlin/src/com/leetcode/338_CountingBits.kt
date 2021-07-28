package com.leetcode

private class Solution338 {
    fun countBits(n: Int): IntArray {
        val result = IntArray(n + 1)
        for (i in 1..n) {
            result[i] = result[i shr 1] + (i % 2)
        }
        return result
    }
}

fun main() {
    val solution = Solution338()
    repeat(33) {
        println("${solution.countBits(it).joinToString()}")
    }
}