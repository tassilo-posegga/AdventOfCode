import java.util.InputMismatchException

/**
 * https://adventofcode.com/2022/day/2
 *
 * A = Rock
 * B = Paper
 * C = Scissor
 */
fun main(args: Array<String>) {
    val rounds = "https://pastebin.com/raw/LzsxbPNt".getTextFromUrl().splitOnLineBreak()

    var totalScore = 0

    rounds.forEach { round ->
        val roundChoices = round.split(" ")
        val opponentChoice = roundChoices.first().asRps()
        val neededChoice = roundChoices.last().asResult()

        totalScore += when (neededChoice) {
            Result.Win -> opponentChoice.getWinner().value
            Result.Draw -> opponentChoice.value
            Result.Loss -> opponentChoice.getWinner().getWinner().value
        }

        totalScore += neededChoice.value
    }

    println("Total score: $totalScore")
}

private fun String.asRps(): RPS =
    when (this) {
        "A" -> RPS.Rock
        "B" -> RPS.Paper
        "C" -> RPS.Scissor
        else -> throw InputMismatchException()
    }

private fun String.asResult(): Result =
    when (this) {
        "X" -> Result.Loss
        "Y" -> Result.Draw
        "Z" -> Result.Win
        else -> throw InputMismatchException()
    }

private fun RPS.getWinner(): RPS =
    when (this) {
        RPS.Rock -> RPS.Paper
        RPS.Paper -> RPS.Scissor
        RPS.Scissor -> RPS.Rock
    }

enum class Result(val value: Int) {
    Loss(0),
    Draw(3),
    Win(6)
}

enum class RPS(val value: Int) {
    Rock(1),
    Paper(2),
    Scissor(3)
}