package com.codeforces.round736

import java.util.*
import kotlin.math.abs
import kotlin.math.max

fun main() {
    val reader = Scanner(System.`in`)
    task(reader)
}

private fun task(input: Scanner) {
    repeat(input.nextInt()) {
        val a = LongArray(input.nextInt()) { input.nextLong() }
        val diffs = LongArray(a.size - 1) { abs(a[it] - a[it + 1]) }
        println(findGcdCount(diffs) + 1)
    }
}

private fun findGcdCount(diffs: LongArray): Int {
    val divisors = LongArray(diffs.size)
    var maxSequence = 0
    for (i in divisors.indices) {
        var gcdCount = 1
        for (j in i..divisors.lastIndex) {
            if (j == i) {
                divisors[j] = diffs[j]
                maxSequence = max(maxSequence, gcdCount)
            } else {
                val gcd = divisors[j - 1].gcd(diffs[j])
                divisors[j] = gcd

                if (gcd <= 1) {
                    maxSequence = max(maxSequence, gcdCount)
                    break
                } else {
                    gcdCount += 1
                    if (j == divisors.lastIndex) {
//
//                        println(divisors.joinToString { it.joinToString() + "\n" })
                        return max(maxSequence, gcdCount)
                    }
                }
            }
        }
    }
//
//    println(divisors.joinToString { it.joinToString() + "\n" })

    return maxSequence
}

private fun Long.gcd(with: Long): Long {
    var a = this
    var b = with
    while (b != 0L) {
        val temp = a
        a = b
        b = temp % b
    }
    return a
}