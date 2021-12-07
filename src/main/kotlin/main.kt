import day1.SonarSweep
import day2.Dive
import day3.BinaryDiagnostic
import day4.GiantSquid
import day5.HydrothermalVenture
import day6.Lanternfish

fun main(args: Array<String>) {

    if (args.size == 1) { // Solution for day X
        val module = when (args[0]) {
            "1" -> SonarSweep
            "2" -> Dive
            "3" -> BinaryDiagnostic
            "4" -> GiantSquid
            "5" -> HydrothermalVenture
            "6" -> Lanternfish
            /*"7" -> HandyHaversacks
            "8" -> HandheldHalting
            "9" -> EncodingError
            "10" -> AdapterArray
            "11" -> SeatingSystem
            "12" -> RainRisk
            "13" -> ShuttleSearch
            "14" -> DockingData
            "15" -> RambunctiousRecitation
            "16" -> TicketTranslation
            "17" -> ConwayCubes
            "18" -> OperationOrder
            "19" -> MonsterMessages
            "20" -> JurassicJigsaw
            "21" -> AllergenAssessment
            "22" -> CrabCombat
            "23" -> CrabCups*/
            else -> SonarSweep
        }

        module.printSolution()
    }

}