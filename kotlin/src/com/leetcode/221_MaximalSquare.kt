package com.leetcode

import kotlin.math.max

/**
 * We use the additional matrix of sides b.
 * The b[i][j] representes the max side that is possible for the square,
 * which right-down corner is placed in (i,j) coordinate.
 *
 * For instance, with given:
 * 1 1 1 0 0
 * 1 1 1 1 0
 * 1 1 1 1 1
 * 0 1 1 1 0
 *
 * we will have next b:
 * 1 1 1 0 0
 * 1 2 2 1 0
 * 1 2 3 2 1
 * 0 1 2 3 0
 *
 * While we building b array we can remember the max side we will face,
 * which is 3 in the example above.
 *
 * Time: O(n*m). Space: O(n*m)
 */
private class Solution221 {
    fun maximalSquare(matrix: Array<CharArray>): Int {
        val maxSides = Array<IntArray>(matrix.size) { IntArray(matrix[it].size) }
        var result = 0

        matrix.forEachIndexed { i, row ->
            row.forEachIndexed { j, c ->
                if (c == '1') {
                    if (i == 0 || j == 0) {
                        maxSides[i][j] = 1
                        result = max(result, maxSides[i][j])
                    } else {
                        maxSides[i][j] = minOf(maxSides[i - 1][j - 1], maxSides[i - 1][j], maxSides[i][j - 1]) + 1
                        result = max(result, maxSides[i][j])
                    }
                }
            }
        }

        return result * result
    }
}

fun main() {
    println(Solution221().maximalSquare(arrayOf(
            charArrayOf('1', '0', '1', '0', '0'),
            charArrayOf('1', '0', '1', '1', '1'),
            charArrayOf('1', '1', '1', '1', '1'),
            charArrayOf('1', '0', '0', '1', '0')
    )))
}
