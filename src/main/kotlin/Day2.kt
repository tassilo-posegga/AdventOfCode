import java.net.URL
import java.util.InputMismatchException

/**
 * https://adventofcode.com/2022/day/2
 *
 * A = Rock
 * B = Paper
 * C = Scissor
 */
fun main(args: Array<String>) {
    val rounds = splitStringOnLineBreak(getTextFromUrl("https://pastebin.com/raw/LzsxbPNt"))

    var totalScore = 0

    rounds.forEach { round ->
        val roundChoices = round.split(" ").map { it.asRps() }
        val opponentChoice = roundChoices.first()
        val myChoice = roundChoices.last()

        totalScore += myChoice.value

        if (myChoice.isWinAgainst(opponentChoice)) totalScore += 6
        if (myChoice.isDrawAgainst(opponentChoice)) totalScore += 3
    }

    println("Total score: $totalScore")
}

private fun String.asRps(): RPS =
    when (this) {
        "A" -> RPS.Rock
        "B" -> RPS.Paper
        "C" -> RPS.Scissor
        "X" -> RPS.Rock
        "Y" -> RPS.Paper
        "Z" -> RPS.Scissor
        else -> throw InputMismatchException()
    }

private fun RPS.isWinAgainst(rps: RPS): Boolean =
    when (this) {
        RPS.Rock -> rps == RPS.Scissor
        RPS.Paper -> rps == RPS.Rock
        RPS.Scissor -> rps == RPS.Paper
    }

private fun RPS.isDrawAgainst(rps: RPS): Boolean =
    when (this) {
        RPS.Rock -> rps == RPS.Rock
        RPS.Paper -> rps == RPS.Paper
        RPS.Scissor -> rps == RPS.Scissor
    }

enum class RPS(val value: Int) {
    Rock(1),
    Paper(2),
    Scissor(3)
}