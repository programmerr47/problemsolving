package com.leetcode.data

import java.lang.StringBuilder

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null

    override fun toString(): String = buildString {
        toString(this, this@TreeNode, 0)
    }

    private fun toString(builder: StringBuilder, node: TreeNode, depth: Int) {
        for (i in 0 until depth) {
            builder.append(' ').append(' ')
        }
        builder.append(node.`val`)

        node.left?.let {
            builder.append('\n')
            toString(builder, it, depth + 1)
        }

        node.right?.let {
            builder.append('\n')
            toString(builder, it, depth + 1)
        }
    }
}

fun treeNode(value: Int, init: TreeNode.() -> Unit = {}): TreeNode = TreeNode(value).apply(init)