package com.leetcode

/**
 * Possibly we can combine reverse method and fixSortedAsc
 * But I'm not sure about it since 2 cycles one operation in each
 * is equal to 1 cycle with two operations. So to gain some boost
 * we need to have less operations than sum of what we have. And
 * I'm not sure we can achieve that.
 *
 * Time: O(n)
 */
private class Solution31 {
    fun nextPermutation(nums: IntArray) {
        if (nums.size < 2) return

        var num = -1
        var i = nums.lastIndex
        while (i >= 0 && nums[i] >= num) {
            num = nums[i]
            i--
        }

        nums.reverse(i + 1, nums.lastIndex)
        if (i >= 0) nums.fixSortedAsc(i, nums.lastIndex)
    }

    private fun IntArray.reverse(from: Int = 0, to: Int = lastIndex) {
        val mid = (to + from) shr 1
        for (i in from..mid) {
            switch(i, to - (i - from))
        }
    }

    private fun IntArray.fixSortedAsc(from: Int, to: Int) {
        var j = from
        while (j < to && this[from] >= this[j + 1]) {
            j++
        }
        if (j < to) switch(from, j + 1)
    }

    private fun IntArray.switch(index1: Int, index2: Int) {
        val tmp = this[index1]
        this[index1] = this[index2]
        this[index2] = tmp
    }
}

fun main() {
    val solution = Solution31()
    println(solution.solve(1, 3, 2))
    println(solution.solve(1, 2, 2, 3))
    println(solution.solve(3, 2, 1))
    println(solution.solve(1, 2))
    println(solution.solve(2, 1))
    println(solution.solve(1))
    println(solution.solve(4, 5, 2, 9, 1))
    println(solution.solve(1, 10, 9, 8, 7, 5, 5, 2, 1, 1, 1))
}

private fun Solution31.solve(vararg nums: Int): List<Int> {
    nextPermutation(nums)
    return nums.toList()
}