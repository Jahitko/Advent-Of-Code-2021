package day6

import DailyExercise
import utils.InputHelper

object Lanternfish : DailyExercise {

    private const val numIterPartI = 80
    private const val numIterPartII = 256

    private val fishInput =
        InputHelper.getInputAsString("./src/main/kotlin/day6/input/input.txt").split(",").map { it.toInt() }

    private fun modelLanternfishGrowth(numIter: Int): Long {
        val array = LongArray(9) { index ->
            fishInput.count { it == index }.toLong()
        }

        var i = 0
        while (i < numIter) {
            val tmpFishArr = array.toList()
            array[6] = tmpFishArr[0] + tmpFishArr[7]
            array[8] = tmpFishArr[0]
            array[7] = tmpFishArr[8]
            for (j in 0 until 6) {
                array[j] = tmpFishArr[j + 1]
            }

            i++
        }

        return array.sum()
    }

    override fun partI() =
        println(modelLanternfishGrowth(numIterPartI))

    override fun partII() =
        println(modelLanternfishGrowth(numIterPartII))

}