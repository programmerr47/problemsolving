package com.leetcode.data

class ListNode(var `val`: Int) {
    var next: ListNode? = null

    fun joinToString(): String = buildString {
        append('[')
        var current: ListNode? = this@ListNode
        while (current != null) {
            if (current !== this@ListNode) append(',').append(' ')

            append(current.`val`)
            current = current.next
        }
        append(']')
    }
}

fun listNodesOf(vararg values: Int): ListNode? {
    val result = ListNode(-1)
    var current = result
    for (element in values) {
        val next = ListNode(element)
        current.next = next
        current = next
    }

    return result.next
}