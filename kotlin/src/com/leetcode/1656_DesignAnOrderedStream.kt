package com.leetcode

class OrderedStream(n: Int) {

    private val arr = Array<String?>(n) { null }
    private var currentId = 0

    fun insert(idKey: Int, value: String): List<String> {
        arr[idKey - 1] = value
        return ArrayList<String>(arr.size).apply {
            currentId = fillSequence(arr, currentId)
        }
    }

    private fun MutableList<String>.fillSequence(arr: Array<String?>, id: Int): Int {
        var nextId = id
        while (nextId < arr.size) {
            val value = arr[nextId] ?: return nextId
            add(value)
            nextId++
        }

        return nextId
    }
}

fun main() {
    val stream = OrderedStream(5)
    println(stream.insert(3, "c"))
    println(stream.insert(1, "a"))
    println(stream.insert(2, "b"))
    println(stream.insert(5, "e"))
    println(stream.insert(4, "d"))
}