package com.leetcode

import kotlin.math.max
import kotlin.math.min

/**
 * Building 2d matrix with products, where products[i][j] is a product from j to i
 *
 * Time complexity: O(n^2), Space complexity: O(n^2)
 */
private class Solution152 {
    fun maxProduct(nums: IntArray): Int {
        if (nums.isEmpty()) return 0

        var maxProduct: Int = nums[0]
        val products = Array(nums.size) { IntArray(nums.size) }

        for (i in products.indices) {
            for (j in products[i].indices) {
                if (j > i) break
                else if (j == i) {
                    products[i][j] = nums[i]
                    maxProduct = max(maxProduct, products[i][j])
                } else {
                    products[i][j] = products[i - 1][j] * nums[i]
                    maxProduct = max(maxProduct, products[i][j])
                }
            }
        }
        return maxProduct
    }
}

/**
 * Basically we just multiply everything with two traversals: forwards and backwards
 * If we will face 0 we reset our multiplicator.
 * The magic is hidden in multipling negative values. Let's consider we have no zeros.
 * Then if we will have even negative numbers we just need to multiply everything
 * If we will have odd negative numbers we need to multiply everything
 * before the last negative or after first negative number
 *
 * Now if we will have zeros then each of subarray with non zeroes value is a
 * subexample of the algorithm for non zero array
 *
 * Time complexity: O(n), Space complexity: O(1)
 */
private class Solution152Linear {
    fun maxProduct(nums: IntArray): Int {
        if (nums.isEmpty()) return 0

        var result: Int = Int.MIN_VALUE

        var product: Int = 1
        for (i in 0..nums.lastIndex) {
            product *= nums[i]
            result = max(result, product)

            if (product == 0) product = 1
        }

        product = 1
        for (i in nums.lastIndex downTo 0) {
            product *= nums[i]
            result = max(result, product)

            if (product == 0) product = 1
        }

        return result
    }
}

fun main() {
    println(Solution152Linear().maxProduct(intArrayOf(2, 3, -2, 4)))
}