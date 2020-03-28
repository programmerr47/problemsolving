package com.codeforces.educational84

import java.util.*

fun main() {
    val reader = Scanner(System.`in`)
    task(reader)
}

private fun task(input: Scanner) {
    repeat(input.nextInt()) {
        val n = input.nextLong()
        val k = input.nextLong()

        val answer = if (k * k <= n && n % 2 == k % 2) "YES" else "NO"
        println(answer)
    }
}