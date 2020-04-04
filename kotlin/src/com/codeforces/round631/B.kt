package com.codeforces.round631

import java.util.*
import kotlin.collections.HashSet

fun main() {
    // Creates an instance which takes input from standard input (keyboard)
    val reader = Scanner(System.`in`)
    task(reader)
}

private fun task(input: Scanner) {
    val a = IntArray(200_001)
    val pCheck = BooleanArray(200_001)
    val result = arrayOfNulls<String?>(200_001)
    repeat(input.nextInt()) {
        val n = input.nextInt()
        repeat(n) { i -> a[i] = input.nextInt() }

        var min2 = -1
        val p2All = HashSet<Int>()
        for (i in n - 1 downTo 0) {
            val j = a[i] - 1

            if (pCheck[j]) {
                break
            } else {
                pCheck[j] = true

                if (j == min2 + 1) {
                    while (pCheck[min2 + 1]) {
                        min2++
                    }
                }
            }

            if (min2 == n - 1 - i) {
                p2All.add(n - i)
            }
        }

        repeat(n + 1) { pCheck[it] = false }

        var count = 0
        var min = -1
        for (i in 0 until n) {
            val l1 = 1 + i

            val j = a[i] - 1
            if (pCheck[j]) {
                break
            } else {
                pCheck[j] = true

                if (j == min + 1) {
                    while (pCheck[min + 1]) {
                        min++
                    }
                }
            }

            if (min == l1 - 1 && (n - l1) in p2All) {
                result[count] = "$l1 ${n - l1}"
                count++
            }
        }

        println(count)
        repeat(count) {
            println(result[it])
        }

        repeat(n + 1) { i ->
            a[i] = 0
            result[i] = null
            pCheck[i] = false
        }
    }
}