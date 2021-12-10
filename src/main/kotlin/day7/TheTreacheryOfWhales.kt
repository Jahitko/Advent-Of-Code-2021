package day7

import DailyExercise
import utils.InputHelper
import kotlin.math.abs
import kotlin.math.roundToInt

object TheTreacheryOfWhales : DailyExercise {

    private val crabPositions =
        InputHelper.getInputAsString("./src/main/kotlin/day7/input/input.txt").split(",").map { it.toInt() }

    private fun calculateFuelCostPartI(position: Int): Int =
        crabPositions.fold(0) { acc, value ->
            acc + abs(value - position)
        }

    private fun fuelCostForXSteps(x: Int): Int {
        return if(x == 0) {
            0
        } else x + fuelCostForXSteps(x - 1)
    }

    private fun calculateFuelCostPartII(position: Int): Int {
        return crabPositions.fold(0) { acc, value ->
            acc + fuelCostForXSteps(abs(value - position))
        }
    }

    private fun getMinCost(actualMin: Int, actualMax: Int, calculateFuelCost: (Int) -> Int): Pair<Int, Int> {
        val index: Int = ((actualMax - actualMin) / 2f).roundToInt() + actualMin
        val actualFuelCost = calculateFuelCost(index)
        val leftIsHigher = if (index > 0) calculateFuelCost(index - 1) > actualFuelCost else false
        val rightIsHigher = if (index < crabPositions.size - 1) calculateFuelCost(index + 1) > actualFuelCost else false

        if (leftIsHigher && rightIsHigher) {
            return Pair(index, actualFuelCost)
        }

        val newMax = if (!leftIsHigher) index else actualMax
        val newMin = if (!rightIsHigher) index else actualMin

        return getMinCost(newMin, newMax, calculateFuelCost)

    }

    override fun partI() {
        val result = getMinCost(0, crabPositions.size - 1, ::calculateFuelCostPartI)
        println("pos ${result.first} fuelSpent ${result.second}")
    }


    override fun partII() {
        val result = getMinCost(0, crabPositions.size - 1, ::calculateFuelCostPartII)
        println("pos ${result.first} fuelSpent ${result.second}")
    }
}