package com.leetcode

import com.leetcode.data.TreeNode
import com.leetcode.data.treeNode
import com.sun.source.tree.Tree
import java.util.*

/**
 * In leetcode there is much more concise and elegant solution.
 * So beware of my big, but still fast one :)
 *
 * The algorithm is next:
 *
 * 1. find the max possible depth of the tree. This is achievable
 * by iterating only through the left children (by definition of
 * complete tree)
 *
 * 2. Calculate max amount of children in the lowest level of the tree.
 * For example: for depth 2 it is 2, for depth 4 it is 8 and etc
 *
 * 3. This step is very similar to binary search. We have the last child
 * somewhere between 0 and max amount of children in the lowest level.
 * So we take the avg of 0 and max_amount and check if this child is presented or not
 *
 * 4. If the child is presented, then we need to take right window [avg + 1, max_amout]
 * if the child is not presented, then we need to take left window [0, avg - 1]
 *
 * 5. Repeat 3-4 until the window will be 1 or less values.
 *
 * Estimation consists of several parts:
 *
 * 1. finding max depth. Since we have complete binary tree,
 * it will never have more than [log(n)] + 1 depth. Means O(logN)
 *
 * 2. then we have a window [0 ... max_count_low_level]. The last variable
 * is no more than n/2 by definition of binary tree. And we will have
 * something like binary search around that window, which is log(n/2).
 * Means O(logN)
 *
 * 3. On each iteration of "binary search". We need to calculate depth of
 * respective path in the tree. Since the 1. is valid, the respective path
 * will have depth no more than max depth. Means O(logN)
 *
 * So, Time: O(logN) + O(logN) * O(logN) = O((logN)^2)
 */
private class Solution222 {
    fun countNodes(root: TreeNode?): Int {
        if (root == null) return 0

        val maxDepth = root.depth()
        val maxCount = (1 shl maxDepth) - 1

        var left = 0
        var right = maxCount / 2
        var maxDepthLast = 0
        while (left <= right) {
            val avg = (left + right) / 2
            if (root.depth(maxDepth, avg) == maxDepth) {
                maxDepthLast = avg
                left = avg + 1
            } else {
                right = avg - 1
            }
        }

        return maxCount - maxCount / 2 + maxDepthLast
    }

    private fun TreeNode.depth(max: Int = 0, shift: Int = 0): Int {
        var ptr: TreeNode? = this
        var depth = 0
        while (ptr != null) {
            depth++
            ptr = if (shift.bit(max - depth - 1) == 0) ptr.left else ptr.right
        }

        return depth
    }

    private fun Int.bit(i: Int): Int = ((1 shl i) and this) shr i
}

fun main() {
    val solution = Solution222()
    println(solution.countNodes(null))
    println(solution.countNodes(
        treeNode(1) {
            left = treeNode(2) {
                left = treeNode(4) {
                    left = treeNode(8)
                    right = treeNode(9)
                }
                right = treeNode(5) {
                    left = treeNode(10)
                }
            }
            right = treeNode(3) {
                left = treeNode(6)
                right = treeNode(7)
            }
        }
    ))
    println(solution.countNodes(
        treeNode(1) {
            left = treeNode(2) {
                left = treeNode(4) {
                    left = treeNode(8)
                    right = treeNode(9)
                }
                right = treeNode(5) {
                    left = treeNode(10)
                    right = treeNode(11)
                }
            }
            right = treeNode(3) {
                left = treeNode(6) {
                    left = treeNode(12)
                    right = treeNode(13)
                }
                right = treeNode(7) {
                    left = treeNode(14)
                    right = treeNode(15)
                }
            }
        }
    ))
    println(solution.countNodes(
        treeNode(1) {
            left = treeNode(2) {
                left = treeNode(4) {
                    left = treeNode(8) {
                        left = treeNode(16)
                    }
                    right = treeNode(9)
                }
                right = treeNode(5) {
                    left = treeNode(10)
                    right = treeNode(11)
                }
            }
            right = treeNode(3) {
                left = treeNode(6) {
                    left = treeNode(12)
                    right = treeNode(13)
                }
                right = treeNode(7) {
                    left = treeNode(14)
                    right = treeNode(15)
                }
            }
        }
    ))
}

private fun buildCompleteTree(count: Int): TreeNode? {
    if (count == 0) return null

    var value = 1
    val root = TreeNode(value)
    val queue = LinkedList<TreeNode>()
    queue.add(root)

    var remain = count - 1
    while (remain > 0) {
        val node = queue.pollFirst()

        value++
        node.left = TreeNode(value)
        queue.addLast(node.left)
        remain--

        if (remain <= 0) return root

        value++
        node.right = TreeNode(value)
        queue.addLast(node.right)
        remain--
    }

    return root
}