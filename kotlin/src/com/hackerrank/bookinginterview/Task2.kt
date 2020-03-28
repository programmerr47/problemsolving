package com.hackerrank.bookinginterview

//private val root = EmodjiNode(Char.MIN_VALUE)
//
//init {
//    prepare(
//        listOf(
//            ":)" to 1,
//            ":-)" to 1,
//            ":/" to 2,
//            ":(" to 3,
//            ":-(" to 3
//        )
//    )
//}

class EmoticonParser {

    /**
     * I will not implement the generic parsing function and instead with hardcode of parsing predefined emoji list.
     * This is done because in more generic case there is no specification and agreement on more hard emotions.
     * For instance: Given a string "::)" and two emotions "::" and ":)", which one should I prefer or prefer nothing?
     * Or other: Given a string ":():" and emotions ":():", ":(" and "):", which one should I prefer?
     *
     * The point is that in more complex way (want we have 100-200 emodjies and more) it will be better to create a
     * tree of trees and instantly search in it. Here we have only 5 emodjies. So if I will have additional time, I will add that.
     */
    fun parseEmoticons(text: String): List<InlinedEmoticon> {
        val result = ArrayList<InlinedEmoticon>()
        var i = 0
        while (i < text.length) {
            if (text[i] == ':') {
                val candidate = parseCandidate(text, i)
                if (candidate == null) {
                    i++
                } else {
                    result.add(candidate)
                    i += candidate.length
                }
            } else {
                i++
            }
        }

        return result
    }

    private fun parseCandidate(text: String, startIndex: Int): InlinedEmoticon? =
        if (startIndex + 1 < text.length) {
            when (text[startIndex + 1]) {
                '-' -> {
                    if (startIndex + 2 < text.length) {
                        when (text[startIndex + 2]) {
                            ')' -> InlinedEmoticon(1, startIndex, 3)
                            '(' -> InlinedEmoticon(1, startIndex, 3)
                            else -> null
                        }
                    } else null
                }
                '/' -> InlinedEmoticon(2, startIndex, 2)
                ')' -> InlinedEmoticon(1, startIndex, 2)
                '(' -> InlinedEmoticon(3, startIndex, 2)
                else -> null
            }
        } else null
}

data class InlinedEmoticon(val emoticonId: Int, val position: Int, val length: Int)

fun main() {
    println(EmoticonParser().parseEmoticons(":)::-:)"))
}