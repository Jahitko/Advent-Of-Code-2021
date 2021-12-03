import day1.SonarSweep
import day2.Dive

fun main(args: Array<String>) {

    if (args.size == 1) { // Solution for day X
        val module = when (args[0]) {
            "1" -> SonarSweep
            "2" -> Dive
            /*"3" -> TobogganTrajectory
            "4" -> PassportProcessing
            "5" -> BinaryBoarding
            "6" -> CustomCustoms
            "7" -> HandyHaversacks
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