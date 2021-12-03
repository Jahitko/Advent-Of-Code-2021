package day1

import DailyExercise
import utils.InputHelper

object SonarSweep : DailyExercise {

    private val listOfDepths = InputHelper.getLinesFromFilePathAsListInteger("./src/main/kotlin/day1/input/input.txt")

    private const val windowSizePartI = 2
    private const val windowSizePartII = 4

    override fun partI() {
        val numIncreased = listOfDepths.windowed(windowSizePartI).count { (e1, e2) -> e1 < e2 }
        println(numIncreased)
    }

    override fun partII() {
        val numIncreased = listOfDepths.windowed(windowSizePartII).count { list -> list.first() < list.last()}
        println(numIncreased)
    }
}