package com.leetcode

import kotlin.math.abs

class Solution16 {
    fun threeSumClosest(nums: IntArray, target: Int): Int {
        val sorted = nums.sorted()
        var result = sorted[0] + sorted[1] + sorted[2]

        for (i1 in sorted.indices) {
            for (i2 in i1 + 1 until sorted.size) {
                val remain = target - sorted[i1] - sorted[i2]
                val i3 = findClosest(sorted, i2 + 1, sorted.lastIndex, remain)

                if (i3 < sorted.size) {
                    val newResult = sorted[i1] + sorted[i2] + sorted[i3]
                    if (abs(newResult - target) < abs(result - target)) {
                        result = newResult
                    }
                }
            }
        }

        return result
    }

    private fun findClosest(sorted: List<Int>, start: Int, end: Int, value: Int): Int {
        var a = start
        var b = end
        while (a < b) {
            val c = (a + b) shr 1
            when {
                sorted[c] == value -> return c
                sorted[c] < value -> a = c + 1
                sorted[c] > value -> b = c
            }
        }

        return a
    }

    //[2, 3, 4], 1
    //c = 1 s[c] = 3 > 1 -> b = 0
    //c = 0 s[c] = 2 > 1 -> b = -1
    //return 0

    //[1, 4], 2
    //c = 0 s[c] = 1 < 2 -> a = 1
    //c = 1 s[c] = 4 > 2 -> b = 0
    //return 1

    //[1, 4], 10
    //c = 0 s[c] = 1 < 10 -> a = 1
    //c = 1 s[c] = 4 < 10 -> a = 2
    //return 2

    //return a - 1
    private fun find(sorted: IntArray, value: Int): Int {
        var a = 0
        var b = sorted.lastIndex

        while (a <= b) {
            val c = (a + b) shr 1

            when {
                sorted[c] == value -> return c
                sorted[c] < value -> a = c + 1
                sorted[c] > value -> b = c - 1
            }
        }

        return a
    }
}

fun main() {
    println(Solution16().threeSumClosest(intArrayOf(-1, 0, 1, 1, 55), 3))
}