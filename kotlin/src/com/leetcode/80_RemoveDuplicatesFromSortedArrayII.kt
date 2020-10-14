package com.leetcode

private class Solution80 {
    fun removeDuplicates(nums: IntArray): Int {
        if (nums.isEmpty()) return 0

        var newSize = 1
        var sameCount = 1
        var prevItem = nums[0]

        for (i in 1..nums.lastIndex) {
            if (nums[i] != prevItem) {
                nums[newSize] = nums[i]
                newSize++
                prevItem = nums[i]
                sameCount = 1
            } else if (sameCount < MAX_THRESHOLD) {
                nums[newSize] = nums[i]
                newSize++
                prevItem = nums[i]
                sameCount++
            }
        }

        return newSize
    }

    private companion object {
        const val MAX_THRESHOLD = 2
    }
}

fun main() {
    println(Solution80().removeDuplicates(intArrayOf(0, 0, 1, 1, 1, 1, 2, 3, 3)))
}