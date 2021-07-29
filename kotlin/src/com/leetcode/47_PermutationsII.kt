package com.leetcode

import kotlin.math.pow

/**
 * This is the enhancement of 47 supporting recurrent numbers
 * The approach is similar except the creation of Set in each
 * depth level of recursive `permute` call.
 *
 * We need that Set to check that this number was already used
 * in all previous combinations, so we will not repeat ourselfs
 *
 * Time: O(n^n)
 * Space: O(n^2) - since we creating new Set on each level of recursion
 *                 and we have O(n) levels and each Set's size is O(n),
 *                 thus, we will have O(n)*O(n) = O(n^2)
 *
 * Variation II.
 * The other option rather than using new HashSet on each level of
 * `permute` method is to sort initial array first (either create
 * new one or sort in place) and then just check num[index] ? num[index - 1]
 *
 * This will make next things:
 * Time: O(n^n) + O(n*log(n)) = O(n^n) - the complexity will be the same
 *                                       still worth to mention that it
 *                                       will be slightly slower :)
 *
 * Space: O(n) - since we not creating new data structures inside `permute`
 *               call, we taking back our space complexity!
 */
private class Solution47 {
    fun permuteUnique(nums: IntArray): List<List<Int>> {
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

        val used = HashSet<Int>(nums.size)
        nums.forEachIndexed { i, num ->
            if (!bitMask[i] && num !in used) {
                bitMask[i] = true
                sample.add(num)
                permute(permutations, bitMask, nums, sample)
                sample.removeAt(sample.size - 1)
                bitMask[i] = false

                used += num
            }
        }
    }

    private fun Int.pow(other: Int): Int = this.toDouble().pow(other).toInt()
}

private class Solution47Version2 {
    fun permuteUnique(nums: IntArray): List<List<Int>> {
        val result = ArrayList<List<Int>>(nums.size.pow(nums.size))
        val bitMask = BooleanArray(nums.size)
        val sample = ArrayList<Int>(nums.size)

        nums.sort()
        permute(result, bitMask, nums, sample)

        return result
    }

    private fun permute(permutations: MutableList<List<Int>>, bitMask: BooleanArray, nums: IntArray, sample: MutableList<Int>) {
        if (sample.size == nums.size) {
            permutations.add(ArrayList(sample))
            return
        }

        nums.forEachIndexed { i, num ->
            if (!bitMask[i] && (i <= 0 || nums[i - 1] != num || bitMask[i - 1])) {
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
    val solution = Solution47Version2()
//    println(solution.permuteUnique(intArrayOf(1, 2, 3)))
    println(solution.permuteUnique(intArrayOf(1, 2, 1)))
    println(solution.permuteUnique(intArrayOf(1, 2, 3, 1)))
//    println(solution.permuteUnique(intArrayOf(3, 2, 1)))
//    println(solution.permuteUnique(intArrayOf(0, 1)))
//    println(solution.permuteUnique(intArrayOf(666)))
//    println(solution.permuteUnique(intArrayOf(1, 2, 4, 8)))
//    println(solution.permuteUnique(intArrayOf(1, 2, 4, 8, 9)))
}