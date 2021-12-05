package day4

import DailyExercise
import utils.InputHelper

object GiantSquid : DailyExercise {

    private var inputAsString: List<String> = InputHelper.getInputAsString("./src/main/kotlin/day4/input/input.txt").split("\n\n")
    private val chosenNumbers = inputAsString.first().split(",").map { it.toInt() }
    private val boards = arrayListOf<List<List<Int>>>()

    init {

        for (i in 1 until inputAsString.size) {
            boards.add(
                inputAsString[i].split("\n").map { it ->
                    val regExp = Regex("([0-9]+)")
                    return@map regExp.findAll(it).map { it.value.toInt() }.toList()
                }
            )
        }
    }

    private fun isBoardCompleted(markedPositions: Set<BoardCoord>): Boolean {
        val xMarked = IntArray(5) { 0 }
        val yMarked = IntArray(5) { 0 }

        markedPositions.forEach { markedPosition ->
            xMarked[markedPosition.x] += 1
            yMarked[markedPosition.y] += 1
        }

        return xMarked.contains(5) || yMarked.contains(5)
    }

    private fun calculateFinalScore(
        chosenNumber: Int,
        selectedBoardIndex: Int,
        markedPositions: MutableSet<BoardCoord>
    ): Int {
        var accumulator = 0
        for (i in boards[selectedBoardIndex].indices) {
            for (j in boards[selectedBoardIndex][i].indices) {
                val pos = BoardCoord(i, j)
                if (markedPositions.contains(pos)) {
                    continue
                }
                accumulator += boards[selectedBoardIndex][i][j]
            }
        }

        return accumulator.times(chosenNumber)
    }

    private fun bingoMethod(isPartOne: Boolean = false) {

        val markedPositions = hashMapOf<Int, MutableSet<BoardCoord>>()
        var winningBoard = 0
        var lastChosenNumber = 0
        val boardsCompleted = BooleanArray(boards.size){false}
        var markedPositionsOfLast = mutableSetOf<BoardCoord>()

        loop1@ for (chosenNumber in chosenNumbers) {
            for (k in boards.indices) {
                for (i in boards[k].indices) {
                    for (j in boards[k][i].indices) {
                        if (boards[k][i][j] == chosenNumber) {
                            val pos = BoardCoord(i, j)
                            if (markedPositions.containsKey(k)) {
                                markedPositions[k]?.add(pos)
                            } else markedPositions[k] = mutableSetOf(pos)
                        }
                    }
                }

                if(!boardsCompleted[k]) {
                    if (markedPositions[k]?.let { isBoardCompleted(markedPositions = it) } == true) {
                        winningBoard = k
                        lastChosenNumber = chosenNumber
                        boardsCompleted[k] = true
                        markedPositionsOfLast = markedPositions[k]!!.toMutableSet()
                        if (isPartOne) {
                            break@loop1
                        }
                    }
                }
            }

        }

        println(calculateFinalScore(lastChosenNumber, winningBoard, markedPositions = markedPositionsOfLast))
        return

    }

    override fun partI() {
        bingoMethod(isPartOne = true)
    }

    override fun partII() {
        bingoMethod()
    }
}