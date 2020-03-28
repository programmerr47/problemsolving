package com.codeforces.round628

import java.util.*

fun main() {
    // Creates an instance which takes input from standard input (keyboard)
    val reader = Scanner(System.`in`)
    task(reader)
}

private fun task(input: Scanner) {
    repeat(input.nextInt()) {
        val value = input.nextInt()
        print(1)
        print(" ")
        println(value - 1)
    }
}