package com.codeforces.round626

import java.util.*
import kotlin.math.max
import kotlin.math.sqrt

fun main(args: Array<String>) {
    // Creates an instance which takes input from standard input (keyboard)
    val reader = Scanner(System.`in`)
    rectCount(reader)
}

private fun rectCount(input: Scanner) {
    val rowCount = input.nextInt()
    val columnCount = input.nextInt()
    val k = input.nextInt()
    val delMax = sqrt(k.toFloat()).toInt()

    val delimiters = mutableListOf<Int>()
    for (i in 1..delMax) {
        if (k % i == 0) {
            delimiters.add(i)

            if (k / i != i) {
                delimiters.add(k / i)
            }
        }
    }

    val conRowCounts = conRow(input, rowCount)
    val conColCounts = conRow(input, columnCount)

    var result = 0L
    for (i in delimiters.indices) {
        val del1 = delimiters[i]
        val del2 = k / del1

        var horizRects = 0L
        conRowCounts.forEach { conRowCount ->
            horizRects += max(conRowCount - del1 + 1, 0)
        }

        var vertRects = 0L
        conColCounts.forEach { conColCount ->
            vertRects += max(conColCount - del2 + 1, 0)
        }

        result += horizRects * vertRects
    }

    println(result)
}

private fun conRow(input: Scanner, rowCount: Int): List<Int> {
    val result = mutableListOf<Int>()
    var conRow = 0
    repeat(rowCount) {
        val row = input.nextInt()
        if (row == 1) {
            conRow++
        } else if (conRow > 0) {
            result.add(conRow)
            conRow = 0
        }
    }
    if (conRow > 0) {
        result.add(conRow)
    }
    return result
}
