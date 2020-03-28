package com.codecraft20

import java.util.*
import kotlin.math.min

fun main(args: Array<String>) {
    // Creates an instance which takes input from standard input (keyboard)
    val reader = Scanner(System.`in`)
    ratingSpreading(reader)
}

private fun ratingSpreading(input: Scanner) {
    repeat(input.nextInt()) {
        val count = input.nextInt()
        val max = input.nextInt()

        var firstDesired = 0
        repeat(count) {
            val rating = input.nextInt()
            firstDesired = min(firstDesired + rating, max)
        }

        println(firstDesired)
    }
}
