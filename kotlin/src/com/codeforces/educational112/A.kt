package com.codeforces.educational112

import java.util.*
import kotlin.math.max

fun main() {
    val reader = Scanner(System.`in`)
    task(reader)
}

private fun task(input: Scanner) {
    repeat(input.nextInt()) {
        val friendCount = input.nextLong()
        println(15 + (max(0, friendCount - 6) + 1) / 2 * 5)
    }
}