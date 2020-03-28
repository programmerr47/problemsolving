package com.hackerrank.bookinginterview

import kotlin.math.abs
import kotlin.math.sign

/*
 * Complete the 'roundPrice' function below.
 *
 * The function is expected to return a LONG_INTEGER.
 * The function accepts following parameters:
 *  1. INTEGER price
 *  2. INTEGER n
 */

fun roundPrice(price: Int, n: Int): Long {
    //we could you price.sign, but it is not specified in task what we should do in case `price == 0`
    val sign = price.sign

    val priceL = abs(price.toLong())
    val numOrder = ten(n)
    val nearestB = priceL / numOrder * numOrder
    val nearestT = nearestB + numOrder

    val result = when {
        sign >= 0 -> if (priceL - nearestB < nearestT - priceL) nearestB else nearestT
        else -> if (priceL - nearestB <= nearestT - priceL) nearestB else nearestT
    }

    return sign * if (result == 0L) nearestT else result
}

private fun ten(n: Int): Long {
    var result = 1L
    repeat(n) { result *= 10 }
    return result
}

fun main() {
    println(roundPrice(0, 2))
}