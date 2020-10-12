package com.leetcode

import java.lang.Integer.max

//DP, array of max subsequence ending by array[i]. Time: O(n^2). Space: O(n)
private class Solution300 {
    fun lengthOfLIS(nums: IntArray): Int {
        val counts = IntArray(nums.size)
        var countMax = 0

        nums.forEachIndexed { i, num ->
            var maxCount = 0
            repeat(i) { j ->
                if (nums[j] < nums[i]) {
                    maxCount = max(maxCount, counts[j])
                }
            }

            counts[i] = maxCount + 1
            countMax = max(countMax, counts[i])
        }

        return countMax
    }
}

fun main() {
    println(Solution300().lengthOfLIS(intArrayOf(10, 9, 2, 5, 3, 7, 101, 18)))
}