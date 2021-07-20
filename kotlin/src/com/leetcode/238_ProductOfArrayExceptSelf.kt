package com.leetcode

//Time: O(n), Space: O(1)
private class Solution238 {

    fun productExceptSelf(nums: IntArray): IntArray {
        val result = IntArray(nums.size).apply { sort() }
        var mult = 1

        for (i in nums.indices) {
            result[i] = mult
            mult *= nums[i]
        }

        mult = 1

        for (i in nums.lastIndex downTo 0) {
            result[i] *= mult
            mult *= nums[i]
        }

        return result
    }

}

fun main() {
    println(Solution238().productExceptSelf(intArrayOf(1, 2, 3, 4)).joinToString())
}