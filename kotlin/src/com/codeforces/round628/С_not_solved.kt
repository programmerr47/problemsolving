package com.codeforces.round628

import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.LinkedHashMap
import kotlin.collections.LinkedHashSet

fun main() {
    // Creates an instance which takes input from standard input (keyboard)
    val reader = Scanner(System.`in`)
    task(reader)
}

private fun task(input: Scanner) {
    val n = input.nextInt()
    val tree = Tree(n)

    repeat(n - 1) {
        val start = input.nextInt()
        val end = input.nextInt()
        insert(tree, start, end, it)
    }

    val weights = IntArray(n - 1)
    val queue = findLeafs(tree)

    var inc = 0
    while (queue.isNotEmpty()) {
        val cur = queue.first()
        queue.remove(cur)

        cur.parent?.let {
            if (!cur.visited) {
                cur.visited = true
                weights[cur.parentIndex] = inc
                queue.add(it)
                inc++
            }
        }
    }

    weights.forEach { println(it) }
}

private fun insert(tree: Tree, check: Int, value: Int, index: Int) {
    val node = tree.values.getOrPut(check) {
        val root = Node(check)
        tree.root = root
        tree.values[check] = root
        root
    }

    val preset = tree.values.getOrPut(value) { Node(value) }?.also {
        it.parent = node
        it.parentIndex = index
    }

    node.children.add(preset)
}

private fun findLeafs(tree: Tree): LinkedHashSet<Node> {
    val queue = LinkedList<Node>()
    queue.push(tree.root)

    var result = LinkedHashSet<Node>()
    while (queue.isNotEmpty()) {
        val current = queue.poll()
        if (current.isLeaf) {
            result.add(current)
        } else {
            queue.addAll(current.children)
        }
    }

    return result
}

private class Tree(
    n: Int
) {
    lateinit var root: Node
    var values: HashMap<Int, Node> = HashMap(n, 1f)
}

private data class Node(var value: Int) {
    var children: MutableList<Node> = mutableListOf()
    var parent: Node? = null
    var parentIndex: Int =  -1

    var visited: Boolean = false

    val isLeaf get() = children.isEmpty()
}