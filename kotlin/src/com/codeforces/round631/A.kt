package com.codeforces.round631

import java.util.*

fun main() {
    // Creates an instance which takes input from standard input (keyboard)
    val reader = Scanner(System.`in`)
    task(reader)
}

private fun task(input: Scanner) {
    val boolPool = BooleanArray(101)
    repeat(input.nextInt()) {
        boolPool.indices.forEach { boolPool[it] = false }
        val n = input.nextInt()
        var x = input.nextInt()

        repeat(n) { i ->
            val ai = input.nextInt() - 1
            boolPool[ai] = true
        }

        var result = 0
        while (x >= 0) {
            if (!boolPool[result]) {
                x--

                if (x < 0) {
                    break
                }
            }
            result++

            if (result > boolPool.size - 1) {
                break
            }
        }

        if (x > 0) {
            result += x
        }

        println(result)
    }
}