package com.leetcode

/**
 * Took base from Leetcode and just rephrase for myself:
 * for given n we have a sum f(1,n) + ... + f(n,n)
 * Where is f(i,n) -> the number of BST with a root i.
 *
 * Since it is bst, we know, that for root i to the left we will have
 * [1, i-1] items and to the right we will have [i+1, n] items
 * [1, i-1] can be treated like a task for (i-1) items, and
 * [i+1, n] has no difference from [1, n-i] -> task for (n-i)
 *
 * which means task f(i,n) = task(i-1) * task(n-i)
 */
private class Solution96 {
    fun numTrees(n: Int): Int {
        if (n == 0 || n == 1) return 1

        var result = 0
        for (i in 1..n) {
            result += numTrees(i-1) * numTrees(n-i)
        }
        return result
    }
}

fun main() {
    println(Solution96().numTrees(2))
}