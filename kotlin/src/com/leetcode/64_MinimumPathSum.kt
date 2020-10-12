package com.leetcode

import kotlin.math.min

//Partial sum vector. Time: O(n*m). Space: O(m)
private class Solution64 {
    fun minPathSum(grid: Array<IntArray>): Int {
        val waveGrid = IntArray(grid[0].size)

        for (i in grid.indices) {
            for (j in grid[i].indices) {
                waveGrid[j] = when {
                    j == 0 -> waveGrid[j]
                    i == 0 -> waveGrid[j - 1]
                    else -> min(waveGrid[j], waveGrid[j - 1])
                } + grid[i][j]
            }
        }

        return waveGrid.last()
    }
}

//Partial sum matrix. Time: O(n*m). Space: O(n*m)
private class Solution64old1 {
    fun minPathSum(grid: Array<IntArray>): Int {
        val waveGrid = Array(grid.size) { IntArray(grid[it].size) }

        for (i in grid.indices) {
            for (j in grid[i].indices) {
                waveGrid[i][j] = when {
                    i == 0 && j == 0 -> 0
                    i == 0 -> waveGrid[i][j - 1]
                    j == 0 -> waveGrid[i - 1][j]
                    else -> min(waveGrid[i - 1][j], waveGrid[i][j - 1])
                } + grid[i][j]
            }
        }

        return waveGrid.last().last()
    }
}

fun main() {
    println(Solution64().minPathSum(arrayOf(
        intArrayOf(1,3,1),
        intArrayOf(1,5,1),
        intArrayOf(4,2,1)
    )))
}