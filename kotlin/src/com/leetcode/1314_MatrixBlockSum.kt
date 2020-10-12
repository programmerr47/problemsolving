package com.leetcode

import java.lang.Integer.min
import kotlin.math.max

//Partial sum matrix O(n * m)
private class Solution1314 {

    fun matrixBlockSum(mat: Array<IntArray>, k: Int): Array<IntArray> {
        val partCols = fillPartColumns(mat, k)
        val result = Array(mat.size) { IntArray(mat[it].size) }

        val prev = Prev()
        mat.forEachIndexed { i, row ->
            val indexes = if (i % 2 == 0) row.indices else row.indices.reversed()
            indexes.forEach { j ->
                prev.result = calculateWindow(mat, partCols, k, i, j, prev)
                prev.i = i
                prev.j = j
                result[i][j] = prev.result
            }
        }

        return result
    }

    private fun fillPartColumns(mat: Array<IntArray>, k: Int): Array<IntArray> {
        val result = Array(mat.size) { IntArray(mat[it].size) }
        val colCount = mat[0].size

        var window = 0
        for (j in 0 until colCount) {
            mat.indices.forEach { i ->
                if (i == 0) {
                    val kMax = min(k, mat.lastIndex)
                    for (ki in 0..kMax) {
                        window += mat[ki][j]
                    }
                } else {
                    val removeI = i - k - 1
                    if (removeI >= 0) {
                        window -= mat[removeI][j]
                    }

                    val addI = i + k
                    if (addI < mat.size) {
                        window += mat[addI][j]
                    }
                }

                result[i][j] = window
            }
            window = 0
        }

        return result
    }

    private fun calculateWindow(mat: Array<IntArray>, partCols: Array<IntArray>, k: Int, i: Int, j: Int, prev: Prev): Int {
        var result = 0

        if (prev.result == 0) {
            val jMin = max(j - k, 0)
            val jMax = min(j + k, mat[i].lastIndex)

            for (kj in jMin..jMax) {
                result += partCols[i][kj]
            }
        } else {
            result = prev.result
            if (prev.i < i) {
                if (prev.i - k >= 0) {
                    result -= rowSum(mat, k, prev.i - k, j)
                }

                if (i + k <= mat.lastIndex) {
                    result += rowSum(mat, k, i + k, j)
                }
            } else if (prev.j < j) {
                if (prev.j - k >= 0) {
                    result -= partCols[i][prev.j - k]
                }

                if (j + k <= mat[i].lastIndex) {
                    result += partCols[i][j + k]
                }
            } else if (prev.j > j) {
                if (prev.j + k <= mat[i].lastIndex) {
                    result -= partCols[i][prev.j + k]
                }

                if (j - k >= 0) {
                    result += partCols[i][j - k]
                }
            }
        }

        return result
    }

    private fun rowSum(mat: Array<IntArray>, k: Int, i: Int, j: Int): Int {
        var result = 0
        val jMin = max(j - k, 0)
        val jMax = min(j + k, mat[i].lastIndex)

        for (kj in jMin..jMax) {
            result += mat[i][kj]
        }

        return result
    }

    private data class Prev(
        var result: Int = 0,
        var i: Int = -1,
        var j: Int = -1
    )
}

//Sliding window O(n * m * k)
private class Solution1314old2 {
    fun matrixBlockSum(mat: Array<IntArray>, k: Int): Array<IntArray> {
        val result = Array(mat.size) { IntArray(mat[it].size) }
        var prevRes = 0
        var prevI = -1
        var prevJ = -1

        mat.forEachIndexed { i, row ->
            val indexes = if (i % 2 == 0) row.indices else row.indices.reversed()
            indexes.forEach { j ->
                prevRes = calculateWindow(mat, k, i, j, prevI, prevJ, prevRes)
                prevI = i
                prevJ = j
                result[i][j] = prevRes
            }
        }

        return result
    }

    private fun calculateWindow(mat: Array<IntArray>, k: Int, i: Int, j: Int, prevI: Int, prevJ: Int, prevRes: Int): Int {
        var result = 0

        if (prevRes == 0) {
            val iMin = max(i - k, 0)
            val iMax = min(i + k, mat.lastIndex)

            for (ki in iMin..iMax) {
                result += rowSum(mat, k, ki, j)
            }
        } else {
            result = prevRes
            if (prevI < i) {
                if (prevI - k >= 0) {
                    result -= rowSum(mat, k, prevI - k, j)
                }

                if (i + k <= mat.lastIndex) {
                    result += rowSum(mat, k, i + k, j)
                }
            } else if (prevJ < j) {
                if (prevJ - k >= 0) {
                    result -= colSum(mat, k, i, prevJ - k)
                }

                if (j + k <= mat[i].lastIndex) {
                    result += colSum(mat, k, i, j + k)
                }
            } else if (prevJ > j) {
                if (prevJ + k <= mat[i].lastIndex) {
                    result -= colSum(mat, k, i, prevJ + k)
                }

                if (j - k >= 0) {
                    result += colSum(mat, k, i, j - k)
                }
            }
        }

        return result
    }

    private fun rowSum(mat: Array<IntArray>, k: Int, i: Int, j: Int): Int {
        var result = 0
        val jMin = max(j - k, 0)
        val jMax = min(j + k, mat[i].lastIndex)

        for (kj in jMin..jMax) {
            result += mat[i][kj]
        }

        return result
    }

    private fun colSum(mat: Array<IntArray>, k: Int, i: Int, j: Int): Int {
        var result = 0
        val iMin = max(i - k, 0)
        val iMax = min(i + k, mat.lastIndex)

        for (ki in iMin..iMax) {
            result += mat[ki][j]
        }

        return result
    }
}

//Straight solution: O(n * m * (k^2))
private class Solution1314old1 {
    fun matrixBlockSum(mat: Array<IntArray>, k: Int): Array<IntArray> {
        val result = Array(mat.size) { IntArray(mat[it].size) }

        mat.forEachIndexed { i, row ->
            row.forEachIndexed { j, _ ->
                result[i][j] = calculateWindow(mat, k, i, j)
            }
        }

        return result
    }

    private fun calculateWindow(mat: Array<IntArray>, k: Int, i: Int, j: Int): Int {
        val iMin = max(i - k, 0)
        val iMax = min(i + k, mat.lastIndex)
        var result = 0

        for (ki in iMin..iMax) {
            val jMin = max(j - k, 0)
            val jMax = min(j + k, mat[ki].lastIndex)

            for (kj in jMin..jMax) {
                result += mat[ki][kj]
            }
        }

        return result
    }
}

fun main() {
    println(Solution1314().matrixBlockSum(arrayOf(
        intArrayOf(1,2,3),
        intArrayOf(4,5,6),
        intArrayOf(7,8,9)
    ), 1).joinToString(separator = "\n") { it.joinToString(prefix = "[", postfix = "]") })
}