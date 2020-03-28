package com.codecraft20

import java.util.*

fun main(args: Array<String>) {
    // Creates an instance which takes input from standard input (keyboard)
    val reader = Scanner(System.`in`)
    stringConversion(reader)
}

private fun stringConversion(input: Scanner) {
    repeat(input.nextInt()) {
        val length = input.nextInt()
        val str = input.next()

        var min = str
        var minK = 1
        for (k in 2..str.length) {
            val tailPart = str.substring(0, k - 1)
            var newStr = str.substring(k - 1)
            newStr = newStr + if (newStr.length % 2 == 0) tailPart else tailPart.reversed()

            if (newStr < min) {
                min = newStr
                minK = k
            }
        }

        println(min)
        println(minK)
    }
}