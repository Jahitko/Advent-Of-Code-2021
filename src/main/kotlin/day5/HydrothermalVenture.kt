package day5

import DailyExercise
import utils.InputHelper
import kotlin.math.abs

object HydrothermalVenture : DailyExercise {

    private val inputStringToBeFiltered =
        InputHelper.getInputAsString("./src/main/kotlin/day5/input/input.txt").split("\n").map { it ->
            return@map Regex("[0-9]+").findAll(it).map { it.value.toInt() }.toList()
        }

    private val hashMap = hashMapOf<String, Int>()

    private fun incrementNumOverlappingInHashmap(x: Int, y: Int) {
        val key = "($x, $y)"
        if (hashMap.containsKey(key)) {
            hashMap[key] = hashMap[key]!!.plus(1)
        } else {
            hashMap[key] = 1
        }
    }

    private fun getNumOverlappingHV(): Int {
        val inputString = inputStringToBeFiltered.filter { list ->
            list[0] == list[2] || list[1] == list[3]
        }

        inputString.forEach { line ->
            val (x1, y1, x2, y2) = line

            var xMin = x1
            var xMax = x2
            var yMin = y1
            var yMax = y2

            if (x1 > x2) {
                xMin = x2
                xMax = x1
            }

            if (y1 > y2) {
                yMin = y2
                yMax = y1
            }

            for (x in xMin..xMax) {
                for (y in yMin..yMax) {
                    incrementNumOverlappingInHashmap(x, y)
                }
            }
        }

        return hashMap.values.count { it >= 2 }
    }

    override fun partI() {
        println(getNumOverlappingHV())
    }

    private fun isDiagonal(x1: Int, y1: Int, x2: Int, y2: Int): Boolean {
        return (abs(x1 - x2) == abs(y1 - y2))
    }

    private fun getNumOverlappingDiagonal(): Int {
        val inputString = inputStringToBeFiltered.filter { list ->
            isDiagonal(
                x1 = list[0],
                y1 = list[1],
                x2 = list[2],
                y2 = list[3]
            )
        }

        inputString.forEach { line ->
            val (x1, y1, x2, y2) = line

            val numIter = abs(x1 - x2)
            val x1IsLower = x1 < x2
            val y1IsLower = y1 < y2

            var x = x1
            var y = y1

            val xStep = if(x1IsLower) 1 else -1
            val yStep = if(y1IsLower) 1 else -1

            incrementNumOverlappingInHashmap(x, y)
            for(i in 1..numIter) {
                x += xStep
                y += yStep

                incrementNumOverlappingInHashmap(x, y)
            }
        }

        return hashMap.values.count { it >= 2 }
    }

    override fun partII() {
        println(getNumOverlappingDiagonal())
    }
}