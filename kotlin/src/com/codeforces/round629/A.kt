package com.codeforces.round629

import java.util.*

fun main() {
    // Creates an instance which takes input from standard input (keyboard)
    val reader = Scanner(System.`in`)
    task(reader)
}

private fun task(input: Scanner) {
    repeat(input.nextInt()) {
        val a = input.nextLong()
        val b = input.nextLong()
        val mod = a % b
        println(if (mod == 0L) mod else b - mod)
    }
}