package com.codeforces.round736

import java.util.*

fun main() {
    val reader = Scanner(System.`in`)
    task(reader)
}

private fun task(input: Scanner) {
    repeat(input.nextInt()) {
        val columnCount = input.nextInt()
        val enemyRow = input.next()
        val gregoryRow = input.next()

        val resultRow = CharArray(columnCount) {
            enemyRow[it]
        }

        gregoryRow.forEachIndexed { i, gPawn ->
            if (gPawn == '1') {
                if (resultRow[i] == '0') resultRow[i] = '2'
                else if (i > 0 && resultRow[i - 1] == '1') resultRow[i - 1] = '2'
                else if (i < gregoryRow.lastIndex && resultRow[i + 1] == '1') resultRow[i + 1] = '2'
            }
        }

        println(resultRow.count { it == '2' })
    }
}