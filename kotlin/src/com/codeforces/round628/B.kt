package com.codeforces.round628

import java.util.*

fun main() {
    // Creates an instance which takes input from standard input (keyboard)
    val reader = Scanner(System.`in`)
    task(reader)
}

private fun task(input: Scanner) {
    repeat(input.nextInt()) {
        val n = input.nextInt()
        val a = IntArray(n) { input.nextInt() }.also { it.sort() }
        println(countOfNonRepeating(a))
    }
}

private fun countOfNonRepeating(sorted: IntArray): Int {
    var result = 0
    sorted.forEachIndexed { i, item ->
        if (i == 0 || sorted[i - 1] < item) {
            result++
        }
    }
    return result
}