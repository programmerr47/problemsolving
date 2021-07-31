package com.codeforces.educational112

import java.lang.Integer.max
import java.lang.Integer.min
import java.util.*

fun main() {
    val reader = Scanner(System.`in`)
    task(reader)
}

private fun task(input: Scanner) {
    repeat(input.nextInt()) {
        val w = input.nextInt()
        val h = input.nextInt()
        val x1 = input.nextInt()
        val y1 = input.nextInt()
        val x2 = input.nextInt()
        val y2 = input.nextInt()
        val w2 = input.nextInt()
        val h2 = input.nextInt()

        val w1 = x2 - x1
        val h1 = y2 - y1

        if (w < max(w1, w2) && h < max(h1, h2)) {
            println((-1).toDouble())
        } else {
            println(calculateMove(w, h, x1, y1, x2, y2, w1, h1, w2, h2))
        }
    }
}

private fun calculateMove(w: Int, h: Int, x1: Int, y1: Int, x2: Int, y2: Int, w1: Int, h1: Int, w2: Int, h2: Int): Double {
    if (x1 > w2 || y1 > h2 || (w - x2) > w2 || (h - y2) > h2) return 0.0

    return if (w >= w1 + w2 && h >= h1 + h2) {
        min(
            min(w2 - x1, w2 - (w - x2)),
            min(h2 - y1, h2 - (h - y2))
        ).toDouble()
    } else if (w >= w1 + w2) {
        min(w2 - x1, w2 - (w - x2)).toDouble()
    } else if (h >= h1 + h2) {
        min(h2 - y1, h2 - (h - y2)).toDouble()
    } else {
        -1.0
    }
}