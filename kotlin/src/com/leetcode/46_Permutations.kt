package com.leetcode

import kotlin.math.pow

/**
 * We use straightforward approach with helping bit array.
 * Every time we go into the next level of recursion,
 * we mark the number as used.
 *
 * Tip:
 * This approach would not work in case of recurrent numbers!
 *
 * Time: O(n^n) - actually the total number of permutations is n!
 *                which is less than n^n, but strictly in recursive
 *                permute call we always going through the whole
 *                array of nums independently of depth of recursive
 *                call. Maybe that can be improved somehow to n!,
 *                but I think this will cost taking more space
 * Space: O(n)
 */
private class Solution46 {
    fun permute(nums: IntArray): List<List<Int>> {
        val result = ArrayList<List<Int>>(nums.size.pow(nums.size))
        val bitMask = BooleanArray(nums.size)
        val sample = ArrayList<Int>(nums.size)

        permute(result, bitMask, nums, sample)

        return result
    }

    private fun permute(permutations: MutableList<List<Int>>, bitMask: BooleanArray, nums: IntArray, sample: MutableList<Int>) {
        if (sample.size == nums.size) {
            permutations.add(ArrayList(sample))
            return
        }

        nums.forEachIndexed { i, num ->
            if (!bitMask[i]) {
                bitMask[i] = true
                sample.add(num)
                permute(permutations, bitMask, nums, sample)
                sample.removeAt(sample.size - 1)
                bitMask[i] = false
            }
        }
    }

    private fun Int.pow(other: Int): Int = this.toDouble().pow(other).toInt()
}

fun main() {
    val solution = Solution46()
    println(solution.permute(intArrayOf(1, 2, 3)))
    println(solution.permute(intArrayOf(3, 2, 1)))
    println(solution.permute(intArrayOf(0, 1)))
    println(solution.permute(intArrayOf(666)))
    println(solution.permute(intArrayOf(1, 2, 4, 8)))
    println(solution.permute(intArrayOf(1, 2, 4, 8, 9)))
}