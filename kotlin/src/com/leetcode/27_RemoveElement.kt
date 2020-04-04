package com.leetcode

class Solution27 {
    fun removeElement(nums: IntArray, `val`: Int): Int {
        var end = nums.lastIndex
        var cur = 0
        while (cur <= end) {
            if (nums[cur] != `val`) {
                cur++
            } else {
                val tmp = nums[cur]
                nums[cur] = nums[end]
                nums[end] = tmp
                end--
            }
        }

        return cur
    }
}

fun main() {
    val input = intArrayOf(0,1,2,2,3,0,4,2)
    val outLength = Solution27().removeElement(input, 2)

    println(outLength)
    println(input.slice(0 until outLength))
}