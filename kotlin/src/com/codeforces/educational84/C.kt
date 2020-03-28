package com.codeforces.educational84

import java.util.*

fun main() {
    val reader = Scanner(System.`in`)
    task(reader)
}

private fun task(input: Scanner) {
    val n = input.nextInt()
    val m = input.nextInt()
    val k = input.nextInt()

    val path = buildString {
        repeat(m - 1) { append("L") }
        repeat(n - 1) { append("U") }

        repeat(n) {
            val direction = if (it % 2 == 0) "R" else "L"
            repeat(m - 1) { append(direction) }
            if (it < n - 1) append("D")
        }
    }

    println(path.length)
    println(path)
}