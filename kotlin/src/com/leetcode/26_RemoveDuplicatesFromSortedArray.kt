package com.leetcode

private class Solution26 {
    fun removeDuplicates(nums: IntArray): Int {
        if (nums.isEmpty()) return 0

        var newSize = 1
        var prevItem = nums[0]

        for (i in 1..nums.lastIndex) {
            if (nums[i] != prevItem) {
                nums[newSize] = nums[i]
                newSize++
                prevItem = nums[i]
            }
        }

        return newSize
    }
}

fun main() {
    println(Solution26().removeDuplicates(intArrayOf(0, 0, 1, 1, 1, 2, 2, 3, 3, 4)))
}