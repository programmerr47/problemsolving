package com.leetcode

import java.lang.Integer.max
import java.util.*

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class TreeZigZagNode(var value: Int) {
    var left: TreeZigZagNode? = null
    var right: TreeZigZagNode? = null

    var leftZigZag: Boolean? = null
    var maxZigZag: Int = 0
}

//DFS. Time: O(n). Space: O(log(n))
class Solution1372 {
    fun longestZigZag(root: TreeNode?): Int = find(root, true, 0)

    private fun find(node: TreeNode?, isLeft: Boolean, count: Int): Int {
        return when {
            node == null -> count - 1
            isLeft -> max(find(node.left, true, 1), find(node.right, false, count + 1))
            else -> max(find(node.left, true, count + 1), find(node.right, false, 1))
        }
    }
}

//BFS in case if the tree is very deep. Time: O(n). Space: O(n)
class Solution1372Old1 {
    fun longestZigZag(root: TreeNode?): Int =
        root?.findMax() ?: 0

    private fun TreeNode.findMax(): Int {
        val childQ = LinkedList<TreeNode>()
        val parentQ = LinkedList<TreeNode?>()
        val zigZagQ = LinkedList<TreeZigZagNode?>()

        childQ.add(this)
        parentQ.add(null)
        zigZagQ.add(null)

        var max = 0

        while(childQ.isNotEmpty()) {
            val node = childQ.poll()
            val parent = parentQ.poll()
            val zigZagParent = zigZagQ.poll()

            val zigZag = TreeZigZagNode(node.`val`)
            if (parent != null) {
                val zzParent = zigZagParent!!

                if (node === parent.left) {
                    zzParent.left = zigZag
                } else {
                    zzParent.right = zigZag
                }

                val parentLeft = zzParent.leftZigZag
                if (parentLeft == null) {
                    zigZag.leftZigZag = zigZag == zzParent.left
                    zigZag.maxZigZag = 1
                } else if (parentLeft && zigZag == zzParent.right) {
                    zigZag.leftZigZag = false
                    zigZag.maxZigZag = zzParent.maxZigZag + 1
                } else if (!parentLeft && zigZag == zzParent.left) {
                    zigZag.leftZigZag = true
                    zigZag.maxZigZag = zzParent.maxZigZag + 1
                } else {
                    zigZag.leftZigZag = zigZag == zzParent.left
                    zigZag.maxZigZag = 1
                }
            }

            node.left?.let {
                childQ.push(it)
                parentQ.push(node)
                zigZagQ.push(zigZag)
            }

            node.right?.let {
                childQ.push(it)
                parentQ.push(node)
                zigZagQ.push(zigZag)
            }

            max = max(max, zigZag.maxZigZag)
        }

        return max
    }
}

fun main() {
    val solution = Solution1372()

    println(solution.longestZigZag(node(1) {
        right = node(1) {
            right = node(1) {
                right = node(1)
                left = node(1) {
                    right = node(1) {
                        right = node(1)
                    }
                }
            }
            left = node(1)
        }
    }))

    println(solution.longestZigZag(node(1) {
        right = node(1)
        left = node(1) {
            right = node(1) {
                right = node(1)
                left = node(1) {
                    right = node(1)
                }
            }
        }
    }))

    println(solution.longestZigZag(node(1)))
}

private inline fun node(value: Int, block: TreeNode.() -> Unit = {}) = TreeNode(value).apply(block)