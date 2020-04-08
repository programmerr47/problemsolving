package com.leetcode

class Solution14 {
    fun longestCommonPrefix(strs: Array<String>): String {
        return buildString {
            strs.firstOrNull()?.forEachIndexed { i, char ->
                strs.forEach { str ->
                    if (i > str.lastIndex || str[i] != char) {
                        return@buildString
                    }
                }

                append(char)
            }
        }
    }
}

fun main() {
    println(Solution14().longestCommonPrefix(arrayOf("flower","flow","flight")))
}