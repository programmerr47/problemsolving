package com.leetcode

private class Solution48 {
    fun rotate(matrix: Array<IntArray>) {
        for (i in 0..(matrix.lastIndex / 2)) {
            for (j in 0 until (matrix.lastIndex - 2 * i)) {
                val tmp = matrix[i][i + j]
                matrix[i][i + j] = matrix[matrix.lastIndex - (i + j)][i]
                matrix[matrix.lastIndex - (i + j)][i] = matrix[matrix.lastIndex - i][matrix.lastIndex - (i + j)]
                matrix[matrix.lastIndex - i][matrix.lastIndex - (i + j)] = matrix[i + j][matrix.lastIndex - i]
                matrix[i + j][matrix.lastIndex - i] = tmp
            }
        }
    }
}

fun main() {
    val array = arrayOf(
            intArrayOf(5,1,9,11),
            intArrayOf(2,4,8,10),
            intArrayOf(13,3,6,7),
            intArrayOf(15,14,12,16)
    )
    Solution48().rotate(array)
    println(array.joinToString(separator = "\n") { it.joinToString() })
}