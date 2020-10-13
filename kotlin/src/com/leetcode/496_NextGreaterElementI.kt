package com.leetcode

import java.util.*
import kotlin.collections.HashMap

//Straightforward solution time: O(n*m), space: O(1), where n = nums1.size, m = nums2.size
private class Solution496 {

    fun nextGreaterElement(nums1: IntArray, nums2: IntArray): IntArray {
        return IntArray(nums1.size) { i ->
            val num1 = nums1[i]

            var found = false
            var result = -1
            for (j in nums2.indices) {
                val num2 = nums2[j]
                if (!found && num2 == num1) {
                    found = true
                } else if (found && num2 > num1) {
                    result = num2
                    break
                }
            }

            result
        }
    }
}

//Decreasing stack time: O(m), space: O(n + m), where n = nums1.size, m = nums2.size
private class Solution496Stack {
    fun nextGreaterElement(nums1: IntArray, nums2: IntArray): IntArray {
        val map = HashMap<Int, Int>()
        nums1.forEachIndexed { i, num1 -> map[num1] = i }

        val result = IntArray(nums1.size) { -1 }
        val stack = LinkedList<Int>()
        nums2.forEach { nums2 ->
            while (stack.isNotEmpty() && stack.peekLast() < nums2) {
                val stackNum = stack.pollLast()
                map[stackNum]?.let { index -> result[index] = nums2 }
            }
            stack.add(nums2)
        }
        return result
    }
}

fun main() {
    println(Solution496Stack().nextGreaterElement(intArrayOf(4, 1, 2), intArrayOf(1, 3, 4, 2)).joinToString())
}