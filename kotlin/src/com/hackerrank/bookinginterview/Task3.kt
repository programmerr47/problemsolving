package com.hackerrank.bookinginterview

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileWriter
import java.io.InputStreamReader
import kotlin.math.max

//Using Dynamic Programming
fun maxLogs(logsCount: IntArray): Int {
    val partialMaxes = IntArray(logsCount.size)

    repeat(logsCount.size) { i ->
        partialMaxes[i] = when (i) {
            0 -> logsCount[i]
            1 -> max(partialMaxes[i - 1], logsCount[i])
            else -> max(partialMaxes[i - 1], partialMaxes[i - 2] + logsCount[i])
        }
    }

    return partialMaxes.last()
}

//fun main(args: Array<String>) {
//    val fileName = System.getenv("OUTPUT_PATH")
//    val `in` = BufferedReader(InputStreamReader(System.`in`))
//    val res: Int
//
//    var _sortedLogIds_size = 0
//    _sortedLogIds_size = Integer.parseInt(`in`.readLine().trim { it <= ' ' })
//    val _sortedLogIds = IntArray(_sortedLogIds_size)
//    var line: String?
//    for (_sortedLogIds_i in 0 until _sortedLogIds_size) {
//        line = `in`.readLine()
//        if (line == null) {
//            break
//        }
//        _sortedLogIds[_sortedLogIds_i] = Integer.parseInt(line)
//    }
//
//    res = maxLogs(_sortedLogIds)
//    print(res)
//}

fun main() {
    println(maxLogs(intArrayOf(4, 10, 7, 1, 100)))
}