package com.leetcode

import kotlin.math.*

/**
 * We make two projection arrays. And after that combine then with subtraction of original h[i].
 * In more details:
 * 1. We put ray of lights from left to right and consider h[i] as an obstacle, so it will put shadow after itself
 * 2. We put ray of lights from right to left and consider h[i] as an obstacle, so it will put shadow before itself
 * 3. For each i we find minimum of two projections, that's the place filled with water
 * 4. For each i we subtract h[i] from that minimum, so we will have level of whater for the given i.
 * 5. Sum up all volumes.
 *
 * Here is more less visual representation, given the leetcode example.
 *
 * 1. Original:         [0,1,0,2,1,0,1,3,2,1,2,1]
 *    Visual:           |            |
 *                      |       ║    |
 *                      |   ║   ║║ ║ |
 *                      | ║ ║║ ║║║║║║|
 *
 * 2. Right Projection: [0,1,1,2,2,2,2,3,3,3,3,3]
 *    Visual:           |            |
 *                      |       ║****|
 *                      |   ║***║║*║*|
 *                      | ║*║║*║║║║║║|
 *
 * 3. Left Projection:  [3,3,3,3,3,3,3,3,2,2,2,1]
 *    Visual:           |            |
 *                      |*******║    |
 *                      |***║***║║*║ |
 *                      |*║*║║*║║║║║║|
 *
 * 4. Min Projection:  [0,1,1,2,2,2,2,3,2,2,2,1]
 *    Visual:           |            |
 *                      |       ║    |
 *                      |   ║***║║*║ |
 *                      | ║*║║*║║║║║║|
 *
 * Num of * = 6 <--- answer!
 *
 * Time: O(n), Space: O(n)
 *
 * P.S.: actually lProj is introduced for the readability and clarity, but it can be easily omitted
 */
private class Solution42 {
    fun trap(height: IntArray): Int {
        val rProj = IntArray(height.size)
        height.forEachIndexed { i, h ->
            rProj[i] = max(rProj[max(0, i - 1)], h)
        }

        val lProj = IntArray(height.size)
        for (i in height.lastIndex downTo 0) {
            lProj[i] = max(lProj[min(height.lastIndex, i + 1)], height[i])
        }

        var result = 0
        height.forEachIndexed { i, h ->
            result += min(lProj[i], rProj[i]) - h
        }

        return result
    }
}

fun main() {
    val solution = Solution42()

    println(solution.trap(intArrayOf(1, 10, 0, 0, 0, 0, 0, 10, 1)))
    println(solution.trap(intArrayOf(1, 10, 0, 5, 9, 6, 1, 10, 1)))
    println(solution.trap(intArrayOf(1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1)))
    println(solution.trap(intArrayOf(1, 2, 3, 4, 5, 6)))
    println(solution.trap(intArrayOf(6, 5, 4, 3, 2, 1)))
    println(solution.trap(intArrayOf(6, 5, 4, 3, 2, 1)))
    println(solution.trap(intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)))
}