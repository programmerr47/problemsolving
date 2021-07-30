package com.leetcode

import com.leetcode.data.ListNode

private class Solution24 {
    fun swapPairs(head: ListNode?): ListNode? {
        val fakeHeader = ListNode(-1).also { it.next = head }
        var current: ListNode? = fakeHeader
        while (current != null) {
            swapNext(current)
            current = current.next?.next
        }

        return fakeHeader.next
    }

    private fun swapNext(node: ListNode) {
        node.next?.let { next ->
            val newNext = swap(next)
            node.next = newNext
        }
    }

    private fun swap(node: ListNode): ListNode {
        val next = node.next ?: return node

        val nextNext = next.next
        next.next = node
        node.next = nextNext

        return next
    }
}

fun main() {
    println(Solution24().swapPairs(buildNotes(1, 2, 3, 4))?.toStrList())
}

private fun buildNotes(vararg values: Int): ListNode? {
    if (values.isEmpty()) return null

    val head = ListNode(values[0])
    var current = head
    for (i in 1..values.lastIndex) {
        val next = ListNode(values[i])
        current.next = next
        current = next
    }
    return head
}

private fun ListNode.toStrList(): String {
    var current: ListNode? = this
    val list = ArrayList<Int>().apply {
        while (current != null) {
            add(current!!.`val`)
            current = current!!.next
        }
    }
    return list.toString()
}