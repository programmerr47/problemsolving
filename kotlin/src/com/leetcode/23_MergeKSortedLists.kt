package com.leetcode

import com.leetcode.data.ListNode
import com.leetcode.data.listNodesOf
import kotlin.collections.ArrayList

/**
 * Time: O(n^2 * k) - k - max size of lists. We have N lists with size at max K.
 *                    We create a new list of sorted heads:
 *                    To create we go through N heads of original lists +
 *                    put them in respective positions of the result list.
 *                    We have binary search inside which is good optimization, but
 *                    still adding element by index is O(n).
 *                    Thus, creating new sorted heads = O(n) * (O(log(n)) * O(n)) = O(n^2)
 *
 *                    Next we go throught the sorted heads until its empty and on each step
 *                    we remove head from sorted list and add it's next pointer to the list back
 *                    Which means `while sortedNew.isNotEmpty` will be false when we will go
 *                    through all elements of all lists. Thus, the cycle itself is O(n*k)
 *
 *                    Next in each part of the cycle we:
 *                    1. Remove item from the sorted head list. Thus, O(n)
 *                    2. Add next pointer to that list. Thus, O(log(n)) + O(n) = O(n)
 *
 *                    Thus, the total cycle is O(n*k) * O(n) = O(n^2 * k)
 *
 *                    Thus, overall O(n^2) + O(n^2 * k) = O(n^2 * k)
 */
private class Solution23 {
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        val sortedNew = ArrayList<ListNode>(lists.size)

        lists.forEach { head ->
            head?.let {
                put(sortedNew, it)
            }
        }

        var result: ListNode? = null
        var current: ListNode? = null
        while (sortedNew.isNotEmpty()) {
            val next = sortedNew.removeAt(0)

            current?.let {
                it.next = next
            } ?: run {
                result = next
                current = next
            }

            current = next

            next.next?.let {
                put(sortedNew, it)
            }
        }

        return result
    }

    private fun put(sorted: MutableList<ListNode>, node: ListNode) {
        if (sorted.isEmpty()) {
            sorted.add(node)
            return
        }

        sorted.add(findIndex(sorted, node), node)
    }

    private fun findIndex(sorted: MutableList<ListNode>, node: ListNode): Int {
        var head = 0
        var tail = sorted.lastIndex

        while (head <= tail) {
            val mid = (head + tail).ushr(1) // safe from overflows
            val midVal = sorted[mid]
            val cmp = midVal.`val` - node.`val`

            if (cmp < 0)
                head = mid + 1
            else if (cmp > 0)
                tail = mid - 1
            else
                return mid // key found
        }
        return head
    }
}

fun main() {
    val solution = Solution23()
    println(solution.mergeKLists(arrayOf(
        listNodesOf(3, 5, 10, 20),
        listNodesOf(1, 8, 10, 20),
        listNodesOf(15, 19, 20, 20),
        listNodesOf(25, 26, 30, 100),
    ))?.joinToString())
}