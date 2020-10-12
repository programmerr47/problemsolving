package com.leetcode

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract
import kotlin.system.measureNanoTime

private class Solution22 {
    fun generateParenthesis(n: Int): List<String> =
            ArrayList<String>().also { generate(it, "", 0, 0, n) }

    private fun generate(result: MutableList<String>, generatedSeq: String, openCount: Int, closeCount: Int, n: Int) {
        if (generatedSeq.length == 2 * n) {
            result.add(generatedSeq)
        }

        if (openCount < n) generate(result, "$generatedSeq(", openCount + 1, closeCount, n)
        if (closeCount < openCount) generate(result, "$generatedSeq)", openCount, closeCount + 1, n)
    }
}

//Works worse than original solution O_o
object Solution22WithoutStringRebuild {
    private const val CHAR_EMPTY = ' '
    private const val CHAR_OPEN = '('
    private const val CHAR_CLOSE = ')'

    fun generateParenthesis(n: Int): List<String> =
            ArrayList<String>().also { generate(it, Array<Char>(2 * n) { CHAR_EMPTY },0, 0, 0) }

    private fun generate(result: MutableList<String>, generatedSeq: Array<Char>, index: Int, openCount: Int, closeCount: Int) {
        if (generatedSeq.size == index) {
            result.add(generatedSeq.joinToString(separator = ""))
        }

        if (openCount < generatedSeq.size shr 1) {
            generate(result, generatedSeq.also { it[index] = CHAR_OPEN }, index + 1, openCount + 1, closeCount)
        }
        if (closeCount < openCount) {
            generate(result, generatedSeq.also { it[index] = CHAR_CLOSE }, index + 1, openCount, closeCount + 1)
        }
    }
}

fun main() {
    val n = 5

    val result1 = withMeasureNs({ Solution22().generateParenthesis(n) }) { _, time -> println("Solution22 took ${time / 1_000} micros")}
    val result2 = withMeasureNs({ Solution22WithoutStringRebuild.generateParenthesis(n) }) { _, time -> println("Solution22WithoutStringRebuild took ${time / 1_000} micros")}

    repeat(result1.size) { i -> println("${result1[i]} - ${result2[i]}") }
}

private inline fun <T> withMeasureNs(block: () -> T, log: (res: T, time: Long) -> Unit): T {
    val result: T
    val time = measureNs { result = block() }
    log(result, time)
    return result
}

@OptIn(ExperimentalContracts::class)
private inline fun measureNs(block: () -> Unit): Long {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return measureNanoTime(block)
}