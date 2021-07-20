package com.leetcode

private class Solution560 {
    fun subarraySum(nums: IntArray, k: Int): Int {
        val subSums = IntArray(nums.size + 1)
        nums.forEachIndexed { i, num ->
            subSums[i + 1] = subSums[i] + num
        }

        val curCounts = HashMap<Int, Int>(nums.size + 1, 1f)
        var result = 0
        subSums.forEach { subSum ->
            result += curCounts[subSum - k] ?: 0
            curCounts[subSum] = (curCounts[subSum] ?: 0) + 1
        }

        return result
    }
}

fun main() {
    val solution = Solution560()
    println(solution.subarraySum(intArrayOf(1, 2, 3), 3))
    println(solution.subarraySum(intArrayOf(1, 1, 1), 2))
    println(solution.subarraySum(intArrayOf(1, 2, 3, -3), 3))
    println(solution.subarraySum(intArrayOf(1, 2, 3, -3, 3), 3))
    println(solution.subarraySum(intArrayOf(1, 2, 3, -3, 3, 2, 1), 3))
    println(solution.subarraySum(intArrayOf(3, -3, 3, 3, -3), 3))
}