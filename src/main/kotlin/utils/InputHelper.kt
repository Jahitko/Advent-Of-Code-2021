package utils

import java.io.File

internal object InputHelper {

    fun getInputAsString(filename: String): String = File(filename).readText()

    fun getLinesFromFilePathAsListOfString(filename: String): List<String> = File(filename).bufferedReader().readLines()

    fun getLinesFromFilePathAsListInteger(filename: String): List<Int> = getLinesFromFilePathAsListOfString(filename).map { it.toInt() }

}