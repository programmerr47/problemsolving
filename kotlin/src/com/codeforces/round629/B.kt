package com.codeforces.round629

import java.util.*

fun main() {
    // Creates an instance which takes input from standard input (keyboard)
    val reader = Scanner(System.`in`)
    task(reader)
}

//TODO improve
private fun task(input: Scanner) {
    repeat(input.nextInt()) {
        val n = input.nextInt()
        val k = input.nextInt()

        var b1i = 1
        var partialSum = 0
        while (partialSum < k) {
            partialSum += b1i
            b1i++
        }

        if (partialSum >= k) {
            b1i--
            partialSum -= b1i
        }

        val b2i = k - partialSum - 1

        println(buildString {
            repeat(n) {
                val bi = n - 1 - it
                append(if (bi == b2i || bi == b1i) 'b' else 'a')
            }
        })
    }
}