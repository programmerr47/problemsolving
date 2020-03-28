package com.leetcode

class NumArray(nums: IntArray) {
    private val partialSums = IntArray(nums.size)

    init {
        nums.forEachIndexed { i, num ->
            partialSums[i] = num + if (i == 0) 0 else partialSums[i - 1]
        }
    }

    fun sumRange(i: Int, j: Int): Int {
        return partialSums[j] - if (i == 0) 0 else partialSums[i - 1]
    }
}

fun main() {
    val numArray = NumArray(intArrayOf(-2, 0, 3, -5, 2, -1))
    println(numArray.sumRange(0, 2))
    println(numArray.sumRange(2, 5))
    println(numArray.sumRange(0, 5))
}