package com.hackerrank.bookinginterview

/*
 * Complete the 'findMissingLog' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts INTEGER_ARRAY sortedLogIds as parameter.
 */

fun findMissingLog(sortedLogIds: List<Int>): Int {
    if (sortedLogIds.isEmpty()) return 1

    var start = 0
    var end = sortedLogIds.size - 1

    while (start < end) {
        val mid = (start + end) shr 1

        //means that to left side we have no missing id
        if (sortedLogIds[mid] - 1 == mid) {
            start = mid + 1
        } else {
            end = mid
        }
    }

    return end + if (sortedLogIds[end] - 1 == end) 2 else 1
}

fun main() {
    println(findMissingLog(listOf()))
}