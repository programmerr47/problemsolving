package com.codeforces.round626

import java.util.*

fun main(args: Array<String>) {
    // Creates an instance which takes input from standard input (keyboard)
    val reader = Scanner(System.`in`)
    rectCount(reader)
}

private fun rectCount(input: Scanner) {
    repeat(input.nextInt()) {
        val arrSize = input.nextInt()

        var resultIndex1: Int = -1
        var ignore = false
        repeat(arrSize) {
            val item = input.nextInt()
            if (!ignore) {
                if (item % 2 == 0) {
                    println(1)
                    println(it + 1)
                    ignore = true
                } else {
                    if (resultIndex1 == -1) {
                        resultIndex1 = it
                    } else {
                        println(2)
                        print(it)
                        print(" ")
                        println(it + 1)
                        ignore = true
                    }
                }
            }
        }

        if (!ignore) {
            println(-1)
        }
    }
}
