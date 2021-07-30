package com.leetcode

/**
 * Time: O(n^2), since on each iteration we remove item from the array of items, which takes O(n)
 */
private class Solution60 {
    fun getPermutation(n: Int, k: Int): String {
        var total = n.factorial()
        var remain = k - 1
        val nums = ArrayList<Int>(n).apply {
            repeat(n) { add(it + 1) }
        }

        val result = StringBuilder(n)
        for (i in 0 until n) {
            if (i == n - 1) {
                result.append(nums.first())
                break
            }

            total /= (n - i)
            val whole = remain / total
            remain %= total
            val num = nums.removeAt(whole)
            result.append(num)
        }

        return result.toString()
    }

    private fun Int.factorial() = (1..this).reduce { acc, i -> acc * i }
}

fun main() {
    val solution = Solution60()
    println(solution.getPermutation(3, 3))
    println(solution.getPermutation(4, 9))
    println(solution.getPermutation(4, 1))
    println(solution.getPermutation(4, 24))
    println(solution.getPermutation(3, 1))
}