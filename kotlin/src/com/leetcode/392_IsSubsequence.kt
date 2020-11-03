package com.leetcode

private class Solution392 {
    fun isSubsequence(s: String, t: String): Boolean {
        if (s.isEmpty()) return true
        if (t.isEmpty()) return false

        var tIndex = 0
        s.forEach { sChar ->
            while (tIndex < t.length && t[tIndex] != sChar) tIndex++
            if (tIndex >= t.length) return false
            tIndex++
        }

        return true
    }
}

fun main() {
    println(Solution392().isSubsequence("aaaa", "aaaba"))
}