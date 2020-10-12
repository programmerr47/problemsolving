package com.leetcode

private class Solution17 {

    private val letterDict = arrayOf(
            arrayOf('a', 'b', 'c'),
            arrayOf('d', 'e', 'f'),
            arrayOf('g', 'h', 'i'),
            arrayOf('j', 'k', 'l'),
            arrayOf('m', 'n', 'o'),
            arrayOf('p', 'q', 'r', 's'),
            arrayOf('t', 'u', 'v'),
            arrayOf('w', 'x', 'y', 'z')
    )

    fun letterCombinations(digits: String): List<String> {
        if (digits.isEmpty()) return emptyList()

        val bucketArr = IntArray(digits.length)
        digits.forEachIndexed { i, c ->
            val index: Int = c - '2'
            bucketArr[i] = index
        }

        val letterArr = CharArray(digits.length)
        val result = ArrayList<String>(bucketArr.reduce { acc, item -> acc * item })
        combine(result, letterArr, bucketArr, 0)
        return result
    }

    private fun combine(result: MutableList<String>, letterArr: CharArray, bucketArr: IntArray, bucketI: Int) {
        if (bucketI == bucketArr.size) {
            result += letterArr.joinToString(separator = "")
            return
        }

        letterDict[bucketArr[bucketI]].forEach { bucketLetter ->
            letterArr[bucketI] = bucketLetter
            combine(result, letterArr, bucketArr, bucketI + 1)
        }
    }
}

fun main() {
    println(Solution17().letterCombinations("23"))
}