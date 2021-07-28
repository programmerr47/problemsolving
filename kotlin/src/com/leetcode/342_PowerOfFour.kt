package com.leetcode

/**
 * expression consists of two parts:
 *
 * 1. n & (n - 1) == 0
 * This is to prove that our integer have only one 1 bit.
 * Because only in case 2^x you will have next binary representation: 10000000
 * Which means 2^x - 1 will be: 01111111
 * Which means the logical AND will give 0 as result
 *
 * So by the end of this expression if it is true, we know for sure that the number is power of 2
 * And 4^x = 2^(2x)
 * Which means the even number of 0 bits after 1 bit.
 * Which leads to the second part of expression.
 *
 * 2. n & 0x55555555 == n
 * 0x55555555 is a hexadecimal representation of 1010101010101010101010101010101
 * In that case all 1 bits are placed on even positions.
 * If our number if power of 2 but not power of 4, then the logical AND will give 0
 * For instance: 8 -> 1000.  01000 & 10101 = 0
 *
 * But if number is power of 4, then the 1 bit will be placed always on even position
 * 1 -> 1
 * 4 -> 100
 * 16 -> 10000
 * 64 -> 1000000
 * 256 -> 100000000
 *
 * And the logical AND with 1010101010101010101010101010101 will always give the same number
 * which will prove that our number is 4^x not just 2^x
 *
 * P.S.: only exceptional case is 0, that's why we have separate check for it
 */
private class Solution342 {
    fun isPowerOfFour(n: Int): Boolean =
        n != 0 && n and (n - 1) == 0 && n and 0x55555555 == n
}

fun main() {
    val solution = Solution342()
    println(solution.isPowerOfFour(1))
    println(solution.isPowerOfFour(2))
    println(solution.isPowerOfFour(4))
    println(solution.isPowerOfFour(8))
    println(solution.isPowerOfFour(16))
    println(solution.isPowerOfFour(64))
    println(solution.isPowerOfFour(1024))
    println(solution.isPowerOfFour(1025))
    println(solution.isPowerOfFour(-4))
}