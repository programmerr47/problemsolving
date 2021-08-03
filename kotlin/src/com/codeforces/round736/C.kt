package com.codeforces.round736

import java.util.*

fun main() {
    val reader = Scanner(System.`in`)
    task(reader)
}

private fun task(input: Scanner) {
    val friendMap = FriendMap(input.nextInt())

    //friends connections
    repeat(input.nextInt()) {
        friendMap.link(input.nextInt(), input.nextInt())
    }

    //commands
    repeat(input.nextInt()) {
        when (input.nextInt()) {
            1 -> friendMap.link(input.nextInt(), input.nextInt())
            2 -> friendMap.unlink(input.nextInt(), input.nextInt())
            3 -> friendMap.process()
        }
    }
}

class FriendMap(n: Int) {
    private val friends = IntArray(n + 1)
    private var remain = n

    fun link(fA: Int, fB: Int) {
        if (fA < fB) {
            linkMin(fA, fB)
        } else {
            linkMin(fB, fA)
        }
    }

    private fun linkMin(fMin: Int, fMax: Int) {
        friends[fMin] = friends[fMin] + 1
        if (friends[fMin] - 1 == 0) {
            remain--
        }
    }

    fun unlink(fA: Int, fB: Int) {
        if (fA < fB) {
            unlinkMin(fA, fB)
        } else {
            unlinkMin(fB, fA)
        }
    }

    private fun unlinkMin(fMin: Int, fMax: Int) {
        friends[fMin] = friends[fMin] - 1
        if (friends[fMin] == 0) {
            remain++
        }
    }

    fun process() {
        println(remain)
    }
}