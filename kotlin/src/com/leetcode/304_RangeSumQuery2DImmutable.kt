package com.leetcode

class NumMatrix(matrix: Array<IntArray>) {
    private val partialSums = Array(matrix.size) { IntArray(matrix[it].size) }

    init {
        for (i in matrix.indices) {
            for (j in matrix[i].indices) {
                partialSums[i][j] = when {
                    i == 0 && j == 0 -> matrix[i][j]
                    j == 0 -> partialSums[i - 1][j] + matrix[i][j]
                    i == 0 -> partialSums[i][j - 1] + matrix[i][j]
                    else -> partialSums[i - 1][j] + partialSums[i][j - 1] - partialSums[i - 1][j - 1] + matrix[i][j]
                }
            }
        }
    }

    fun sumRegion(row1: Int, col1: Int, row2: Int, col2: Int): Int {
        return partialSums[row2][col2] +
                (if (row1 == 0) 0 else -partialSums[row1 - 1][col2]) +
                (if (col1 == 0) 0 else -partialSums[row2][col1 - 1]) +
                (if (row1 == 0 || col1 == 0) 0 else partialSums[row1 - 1][col1 - 1])
    }

}

fun main() {
    val numMatrix = NumMatrix(arrayOf(
        intArrayOf(3, 0, 1, 4, 2),
        intArrayOf(5, 6, 3, 2, 1),
        intArrayOf(1, 2, 0, 1, 5),
        intArrayOf(4, 1, 0, 1, 7),
        intArrayOf(1, 0, 3, 0, 5)
    ))

    println(numMatrix.sumRegion(2, 1, 4, 3))
    println(numMatrix.sumRegion(1, 1, 2, 2))
    println(numMatrix.sumRegion(1, 2, 2, 4))
}