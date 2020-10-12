package com.leetcode

private class Solution119 {
    fun getRow(rowIndex: Int): List<Int> {
        val result = ArrayList<Int>(rowIndex + 1).apply {
            repeat(rowIndex + 1) { add(0) }
        }

        repeat(rowIndex + 1) { i ->
            if (i == 0) {
                result[i] = 1
            } else {
                for (j in i downTo 0) {
                    result[j] = result[j] + if (j == 0) 0 else result[j - 1]
                }
            }
        }

        return result
    }
}

fun main() {
    println(Solution119().getRow(5))
}