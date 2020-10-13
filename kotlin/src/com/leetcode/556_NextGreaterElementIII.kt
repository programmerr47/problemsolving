package com.leetcode

private class Solution556 {

    fun nextGreaterElement(n: Int): Int {
        var result: Long = 0

        val counts = IntArray(10)
        var tempN = n
        var prevDigit = -1
        while (tempN > 0) {
            val nextDigit = tempN % 10
            tempN /= 10
            counts[nextDigit] = counts[nextDigit] + 1

            if (prevDigit.also { prevDigit = nextDigit } > nextDigit) break
        }

        var closestGreater = -1
        for (i in prevDigit + 1 until counts.size) {
            if (counts[i] > 0) {
                counts[i] = counts[i] - 1
                closestGreater = i
                break
            }
        }
        if (closestGreater == -1) return -1

        result = tempN * 10L + closestGreater
        counts.forEachIndexed { digit, count ->
            repeat(count) { result = result * 10 + digit }
        }

        val resultInt = result.toInt()
        return if (resultInt.toLong() == result && resultInt != n) resultInt else -1
    }
}

fun main() {
    println(Solution556().nextGreaterElement(12))
}