package com.leetcode

import java.lang.IllegalStateException

private class Solution1093 {
    fun sampleStats(count: IntArray): DoubleArray {
        var minIndex = -1
        var maxIndex = -1
        var sum: Long = 0
        var allCount = 0
        var maxCount = -1
        var maxCountIndex = -1

        var mirroredI: Int
        count.forEachIndexed { i, c ->
            mirroredI = count.size - i - 1
            if (minIndex == -1 && c != 0) minIndex = i
            if (maxIndex == -1 && count[mirroredI] != 0) maxIndex = mirroredI
            if (maxCount < c) {
                maxCount = c
                maxCountIndex = i
            }

            sum += i.toLong() * c
            allCount += c
        }

        return doubleArrayOf(
            minIndex.toDouble(),
            maxIndex.toDouble(),
            sum / allCount.toDouble(),
            findMedian(count, allCount),
            maxCountIndex.toDouble()
        )
    }

    private fun findMedian(count: IntArray, allCount: Int): Double {
        val medianIndex = allCount / 2 - (1 - allCount % 2)
        val isEven = allCount % 2 == 0
        var tempI = -1
        count.forEachIndexed { i, c ->
            if (tempI + c < medianIndex) {
                tempI += c
            } else if (tempI + c == medianIndex) {
                if (isEven) {
                    for (j in i+1 until count.size) {
                        if (count[j] != 0) {
                            return (i + j) / 2.0
                        }
                    }

                    throw IllegalStateException("Impossible")
                } else {
                    return i.toDouble()
                }
            } else {
                return i.toDouble()
            }
        }

        throw IllegalStateException("Impossible")
    }
}

fun main() {
    val solution = Solution1093()
    p(solution.sampleStats(intArrayOf(0, 2, 0, 2, 0, 2))) //1,1,3,3,5,5
    p(solution.sampleStats(intArrayOf(0, 2, 0, 2, 0, 4))) //1,1,3,3,5,5,5,5
    p(solution.sampleStats(intArrayOf(0, 2, 0, 2, 0, 5))) //1,1,3,3,5,5,5,5,5
    p(solution.sampleStats(intArrayOf(0, 2, 0, 2, 0, 6))) //1,1,3,3,5,5,5,5,5,5
    p(solution.sampleStats(intArrayOf(0, 1, 0, 1, 0, 3))) //1,3,5,5,5
    p(solution.sampleStats(intArrayOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0)))
    p(solution.sampleStats(intArrayOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3510,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6718,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,27870,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,35,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,26256,0,0,0,0,9586565,0,0,0,0,0,0,0,2333,0,0,0,0)))
}

private fun p(arr: DoubleArray) = println(arr.joinToString())