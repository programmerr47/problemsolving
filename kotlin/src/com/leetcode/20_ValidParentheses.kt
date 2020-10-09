package com.leetcode

import java.util.*

class Solution20 {
    fun isValid(s: String): Boolean {
        val list = LinkedList<Char>()
        s.forEachIndexed { i, char ->
            when(char) {
                '(', '[', '{' -> list.add(char)
                ')' -> if (list.peekLast() == '(') list.removeLast() else return false
                ']' -> if (list.peekLast() == '[') list.removeLast() else return false
                '}' -> if (list.peekLast() == '{') list.removeLast() else return false
            }

            if (list.size > (s.length - (i + 1))) return false
        }

        return list.isEmpty()
    }
}

fun main() {
    val solution = Solution20()
    println(solution.isValid("()"))
    println(solution.isValid("()[]{}"))
    println(solution.isValid("(]"))
    println(solution.isValid("([)]"))
    println(solution.isValid("{[]}"))
    println(solution.isValid("{[[[]}"))
}