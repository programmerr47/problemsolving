package com.leetcode

import java.lang.Integer.min

//DP, but we assume the we have natural numbers. Time: O(n), Space:  O(n), where n - is a total amount of numbers
class Solution120 {
    fun minimumTotal(triangle: List<List<Int>>): Int {
        val buffer = IntArray(triangle.size)

        triangle.forEachIndexed { i, row ->
            if (i != 0) {
                for (j in i downTo 0) {
                    if (j != 0) {
                        buffer[j] = if (j == i) buffer[j - 1] else min(buffer[j], buffer[j - 1])
                    }
                }
            }

            row.forEachIndexed { j, num ->
                buffer[j] = buffer[j] + num
            }
        }

        return buffer.min()!!
    }
}

//Recursion. Will not good for big data. Time: O(2^n), Space:  O(n), where n - is a total amount of rows
class Solution120old {
    fun minimumTotal(triangle: List<List<Int>>): Int = find(triangle, 0, 0, 0)

    private fun find(triangle: List<List<Int>>, i: Int, j: Int, sum: Int): Int {
        if (i >= triangle.size) return sum

        return min(
            find(triangle, i + 1, j, sum + triangle[i][j]),
            find(triangle, i + 1, j + 1, sum + triangle[i][j])
        )
    }
}

fun main() {
    println(Solution120().minimumTotal(listOf(
        listOf(2),
        listOf(3, 4),
        listOf(6, 5, 7),
        listOf(4, 1, 8, 3)
    )))
}