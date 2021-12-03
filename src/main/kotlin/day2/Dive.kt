package day2

import DailyExercise
import utils.InputHelper
import kotlin.math.abs

object Dive : DailyExercise {

    private val directionsList =
        InputHelper.getLinesFromFilePathAsListOfString("./src/main/kotlin/day2/input/input.txt")

    private fun processCommands(callback: (directionCommand: String, amount: Int) -> Unit) {
        directionsList.forEach { direction ->
            val (directionCommand, amountStr) = direction.split(" ")
            val amount = amountStr.toInt()
            callback(directionCommand, amount)
        }
    }

    override fun partI() {

        val startPos = IntArray(2) { 0 }

        processCommands { directionCommand, amount ->
            when (directionCommand) {
                "forward" -> {
                    startPos[0] = startPos[0] + amount
                }
                "down" -> {
                    startPos[1] = startPos[1] + amount
                }
                else -> {
                    startPos[1] = startPos[1] - amount
                }
            }
        }

        val result = abs(startPos.first().times(startPos.last()))
        println(result)
    }

    override fun partII() {
        val startPos = IntArray(3) { 0 }

        processCommands { directionCommand, amount ->
            when (directionCommand) {
                "forward" -> {
                    startPos[0] += amount
                    startPos[1] += startPos[2].times(amount)
                }
                "down" -> {
                    startPos[2] = startPos[2] + amount
                }
                else -> {
                    startPos[2] = startPos[2] - amount
                }
            }
        }


        val result = abs(startPos.first().times(startPos[1]))
        println(result)
    }
}