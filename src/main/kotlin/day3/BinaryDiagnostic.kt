package day3

import DailyExercise
import utils.InputHelper

object BinaryDiagnostic : DailyExercise {

    private fun findCommonBitForBitX(bitX: Int, charArray: CharArray, numBits: Int, isMostCommonMethod: Boolean = true): Char {

        var numZero = 0
        var numOne = 0

        for (i in bitX until charArray.size step numBits) {
            if (charArray[i] == '0')
                numZero++ else numOne++
        }

        return if(isMostCommonMethod) {
            if (numZero > numOne) '0' else '1'
        } else if (numZero <= numOne) '0' else '1'

    }

    private fun negBit(bitChar: Char): Char =
        if(bitChar == '1') '0' else '1'

    private fun getLeastCommonBitStringFromMostCommonString(mostCommonBitString: String): String =
        mostCommonBitString.map { negBit(it) }.joinToString("")

    override fun partI() {
        val diagnosticReport =
            InputHelper.getInputAsString("./src/main/kotlin/day3/input/input.txt").trim()

        val numBits = diagnosticReport.split("\n").first().length

        diagnosticReport.replace("\n", "")
            .toCharArray().let { diagnosticReportSingleLine ->

                var mostCommonBinaryString = ""

                for (i in 0 until numBits) {
                    val mostCommonBit = findCommonBitForBitX(i, diagnosticReportSingleLine, numBits)
                    mostCommonBinaryString += mostCommonBit
                }

                val leastCommonBinaryString = getLeastCommonBitStringFromMostCommonString(mostCommonBinaryString)

                val powerConsumption = mostCommonBinaryString.toInt(2).times(leastCommonBinaryString.toInt(2))
                println(powerConsumption)
            }
    }

    private fun findMostCommonBitForBitXOxGen(bitX: Int, charArray: CharArray, numBits: Int) =
        findCommonBitForBitX(bitX, charArray, numBits, true)

    private fun findMostCommonBitForC02Supp(bitX: Int, charArray: CharArray, numBits: Int) =
        findCommonBitForBitX(bitX, charArray, numBits, false)

    override fun partII() {
        val diagnosticReport  =
            InputHelper.getLinesFromFilePathAsListOfString("./src/main/kotlin/day3/input/input.txt")

        val numBits = diagnosticReport.first().length

        val mostCommonBitString = filterDiagnosticsBasedOnMethod(diagnosticReport.toMutableList(), numBits, ::findMostCommonBitForBitXOxGen)
        val leastCommonBitString = filterDiagnosticsBasedOnMethod(diagnosticReport.toMutableList(), numBits, ::findMostCommonBitForC02Supp)

        val lifeSupportRating = mostCommonBitString.toInt(2).times(leastCommonBitString.toInt(2))
        println(lifeSupportRating)
    }

    private fun filterDiagnosticsBasedOnMethod(
        diagnosticReport: List<String>,
        numBits: Int,
        method: (bitX: Int, charArray: CharArray, numBits: Int) -> Char
    ): String {
        var diagnosticReport1 = diagnosticReport
        var bitIndex = 0
        var commonBitString = ""
        while (diagnosticReport1.size != 1) {
            val concat = diagnosticReport1.joinToString("")
            val mostCommonBit = method(bitIndex, concat.toCharArray(), numBits)
            commonBitString += mostCommonBit
            diagnosticReport1 = diagnosticReport1.filter { el -> el[bitIndex] == mostCommonBit }
            bitIndex++
        }
        return diagnosticReport1.first()
    }
}