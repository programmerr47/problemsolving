package com.leetcode

private class Solution416 {
    fun canPartition(nums: IntArray): Boolean {
        if (nums.isEmpty()) return true
        val sum = nums.sum()
        if (sum % 2 != 0) return false
        val maxJ = sum shr 1

        val possibility = Array<BooleanArray>(nums.size + 1) { BooleanArray(maxJ + 1) { false } }
        for (i in possibility.indices) {
            for (j in possibility[i].indices) {
                possibility[i][j] = when {
                    i == 0 && j == 0 -> true
                    i == 0 -> false
                    possibility[i - 1][j] -> true
                    j - nums[i - 1] < 0 -> false
                    possibility[i - 1][j - nums[i - 1]] -> true
                    else -> false
                }
            }
        }

        return possibility[nums.size][maxJ]
    }
}

fun main() {
    println(Solution416().canPartition(intArrayOf(1, 5, 11, 5)))
}