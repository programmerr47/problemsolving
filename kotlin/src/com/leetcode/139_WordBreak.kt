package com.leetcode

private class Solution139 {
    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        val wordSet = wordDict.toHashSet()
        val possible = BooleanArray(s.length) //possible[i] = can split substring [0, i] on words
        repeat(s.length) { i ->
            possible[i] = checkPossible(s, wordSet, possible, i)
        }

        return possible.last()
    }

    private fun checkPossible(s: String, wordSet: Set<String>, possible: BooleanArray, index: Int): Boolean {
        for (j in index downTo 0) {
            val subStr = s.substring(j, index + 1)
            if (subStr in wordSet && (j == 0 || possible[j - 1])) {
                return true
            }
        }

        return false
    }
}

fun main() {
    println(Solution139().wordBreak("leetcode", listOf("leet", "code")))
}