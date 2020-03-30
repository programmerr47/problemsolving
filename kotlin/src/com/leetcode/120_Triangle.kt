package com.leetcode

import java.lang.Integer.min

//Recursion. Time: O(n), Space:  O(n^(1/2)), where n - is a total amount of numbers
class Solution120 {
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