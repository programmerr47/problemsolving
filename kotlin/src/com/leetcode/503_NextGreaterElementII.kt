package com.leetcode

import java.util.*

//Decreasing stack of indexes. Time: O(n), Space: O(n)
private class Solution503 {

    fun nextGreaterElements(nums: IntArray): IntArray {
        val result = IntArray(nums.size) { -1 }

        val indexStack = LinkedList<Int>()
        for (i in 0 until (nums.size * 2)) {
            val num = nums[i % nums.size]
            while (indexStack.isNotEmpty() && nums[indexStack.peekLast()] < num) {
                result[indexStack.pollLast()] = num
            }
            indexStack.add(i % nums.size)
        }
        return result
    }
}

fun main() {
    println(Solution503().nextGreaterElements(intArrayOf(1, 2, 1)).joinToString())
}