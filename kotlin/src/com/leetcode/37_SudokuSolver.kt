package com.leetcode

/**
 * Sort of a bitset but inverse and only 9 bits
 */
class SudokuUnit {
    //possible value. Just bit mask of where i-th bit is either i value is possible in this cell or not
    var posValue: Int = INITIAL_MASK
        private set
    var possibilities: Int = 9
        private set

    val isEstablished: Boolean get() = possibilities <= 1

    val value: Int
        get() = if (posValue == 0) 1 else (Integer.numberOfTrailingZeros(posValue) + 1)

    constructor()

    private constructor(other: SudokuUnit) {
        posValue = other.posValue
        possibilities = other.possibilities
    }

    fun reset() {
        posValue = INITIAL_MASK
        possibilities = 9
    }

    fun set(index: Int) {
        posValue = 1 shl (index - 1)
        possibilities = 1
    }

    fun exclude(index: Int) {
        val exclMask = 1 shl (index - 1)
        if ((posValue and exclMask) shr (index - 1) == 1) {
            possibilities--
        }
        posValue = posValue and (exclMask xor INITIAL_MASK)
    }

    inline fun iterate(block: (Int) -> Unit) {
        var iter = posValue
        var value = 0
        while (iter > 0) {
            val rem = iter % 2
            iter = iter shr 1
            value++

            if (rem == 1) block(value)
        }
    }

    fun copy() = SudokuUnit(this)

    override fun toString(): String = posValue.toString()

    private companion object {
        const val INITIAL_MASK = 511 // 0b111111111
    }
}

class SudokuBoard(private val n: Int = 3) {
    private val board: Array<Array<SudokuUnit>>

    init {
        val size = n * n
        board = Array(size) {
            Array(size) { SudokuUnit() }
        }
    }

    fun fill(charBoard: Array<CharArray>) {
        charBoard.forEachIndexed { i, row ->
            row.forEachIndexed { j, c ->
                if (c != '.') {
                    set(board, c - '0', i, j)
                }
            }
        }
    }

    fun solveAny() {
        val dotCoords = ArrayList<Pair<Int, Int>>(n * n)
        board.forEachIndexed { i, row ->
            row.forEachIndexed { j, unit ->
                if (!board[i][j].isEstablished) {
                    dotCoords.add(i to j)
                }
            }
        }
        dotCoords.sortBy { (i, j) -> board[i][j].possibilities }

        if (!solve(board, dotCoords, 0)) {
            throw IllegalStateException("The sudoku is not solvable for the given configuration")
        }
    }

    private fun solve(board: Array<Array<SudokuUnit>>, dotCoords: ArrayList<Pair<Int, Int>>, k: Int): Boolean {
        if (k > dotCoords.lastIndex) return true

        val (i, j) = dotCoords[k]
        if (board[i][j].isEstablished) {
            return solve(board, dotCoords, k + 1)
        } else {
            board[i][j].iterate { posValue ->
                val snapShot = board.snapshot()
                val result = kotlin.runCatching {
                    set(snapShot, posValue, i, j)
                }

                if (result.isSuccess) {
                    if (solve(snapShot, dotCoords, k + 1)) {
                        snapShot.copyTo(board)
                        return true
                    }
                }
            }

            return false
        }
    }

    private fun Array<Array<SudokuUnit>>.snapshot() = Array(size) { i ->
        Array(this[i].size) { j ->
            this[i][j].copy()
        }
    }

    private fun Array<Array<SudokuUnit>>.copyTo(other: Array<Array<SudokuUnit>>) {
        forEachIndexed { i, row ->
            row.forEachIndexed { j, unit ->
                other[i][j] = this[i][j]
            }
        }
    }

    private fun set(board: Array<Array<SudokuUnit>>, num: Int, i: Int, j: Int) {
        if (board[i][j].isEstablished && board[i][j].value != num) {
            throw IllegalStateException("Board is corrupted. Number $num can not be placed on [$i, $j], since there is already number ${board[i][j]} there")
        } else {
            board[i][j].set(num)

            for (k in board.indices) {
                //excluding num from all places within same row
                if (k != j) {
                    if (!exclude(board, num, i, k)) {
                        throw IllegalStateException("Board is corrupted. Number $num can not be placed on [$i,$k], since it is already placed in the same row [$i,$j]")
                    }
                }

                //excluding num from all places within same col
                if (k != i) {
                    if (!exclude(board, num, k, j)) {
                        throw IllegalStateException("Board is corrupted. Number $num can not be placed on [$k,$j], since it is already placed in the same column [$i,$j]")
                    }
                }

                //excluding num from all places within same square
                val ki = (i / n) * n + k / n
                val kj = (j / n) * n + k % n
                if (ki != i && kj != j) {
                    if (!exclude(board, num, ki, kj)) {
                        throw IllegalStateException("Board is corrupted. Number $num can not be placed on [$ki,$kj], since it is already placed in the same square [${i/n},${j/n}]")
                    }
                }
            }
        }
    }

    private fun exclude(board: Array<Array<SudokuUnit>>, num: Int, i: Int, j: Int): Boolean {
        if (board[i][j].isEstablished && board[i][j].value == num) {
            return false
        } else if (!board[i][j].isEstablished) {
            board[i][j].exclude(num)
            if (board[i][j].isEstablished) {
                set(board, board[i][j].value, i, j)
            }
        }

        return true
    }

    fun toCharBoard(cBoard: Array<CharArray>) {
        board.forEachIndexed { i, row ->
            row.forEachIndexed { j, unit ->
                cBoard[i][j] = if (board[i][j].isEstablished) {
                    '0' + board[i][j].value
                } else {
                    '.'
                }
            }
        }
    }
}

private class Solution37 {
    fun solveSudoku(board: Array<CharArray>) {
        val sudoku = SudokuBoard()
        sudoku.fill(board)
        sudoku.solveAny()
        sudoku.toCharBoard(board)
    }
}

fun main() {
    val solution = Solution37()

    println(solution.solve(
        arrayOf(
            charArrayOf('5','3','.','.','7','.','.','.','.'),
            charArrayOf('6','.','.','1','9','5','.','.','.'),
            charArrayOf('.','9','8','.','.','.','.','6','.'),
            charArrayOf('8','.','.','.','6','.','.','.','3'),
            charArrayOf('4','.','.','8','.','3','.','.','1'),
            charArrayOf('7','.','.','.','2','.','.','.','6'),
            charArrayOf('.','6','.','.','.','.','2','8','.'),
            charArrayOf('.','.','.','4','1','9','.','.','5'),
            charArrayOf('.','.','.','.','8','.','.','7','9')
        )
    ).toStr())

    println(solution.solve(
        arrayOf(
            charArrayOf('.','3','.','.','7','.','.','.','.'),
            charArrayOf('6','.','.','1','9','5','.','.','.'),
            charArrayOf('.','9','.','.','.','.','.','6','.'),
            charArrayOf('8','.','.','.','6','.','.','.','.'),
            charArrayOf('4','.','.','8','.','3','.','.','1'),
            charArrayOf('7','.','.','.','2','.','.','.','6'),
            charArrayOf('.','6','.','.','.','.','2','8','.'),
            charArrayOf('.','.','.','4','.','9','.','.','5'),
            charArrayOf('.','.','.','.','8','.','.','7','9')
        )
    ).toStr())

    println(solution.solve(
        arrayOf(
            charArrayOf('.','.','.','.','.','.','.','.','.'),
            charArrayOf('.','.','.','.','.','.','.','.','.'),
            charArrayOf('.','.','.','.','.','.','.','.','.'),
            charArrayOf('.','.','.','.','.','.','.','.','.'),
            charArrayOf('.','.','.','.','.','.','.','.','.'),
            charArrayOf('.','.','.','.','.','.','.','.','.'),
            charArrayOf('.','.','.','.','.','.','.','.','.'),
            charArrayOf('.','.','.','.','.','.','.','.','.'),
            charArrayOf('.','.','.','.','.','.','.','.','.')
        )
    ).toStr())
}

private fun Array<CharArray>.toStr() = joinToString(separator = "\n") { it.joinToString() }

private fun Solution37.solve(board: Array<CharArray>): Array<CharArray> = board.also { solveSudoku(it) }