package day9

import getTextFromUrl
import splitOnLineBreak

/**
 * https://adventofcode.com/2022/day/9
 */
fun main(args: Array<String>) {
    val input = "https://pastebin.com/raw/psZsPyJN".getTextFromUrl().splitOnLineBreak()
}

class Mover {
    var head = Field(0, 0)
    var tail = Field(0, 0)
    var visitedFields: MutableList<Field> = mutableListOf(tail)

    fun getAmountOfVisitedFields() =
        visitedFields.distinct().size

    fun executeMoves(moves: List<Move>) {
        moves.forEach(::executeMove)
    }

    fun executeMove(move: Move) {
        for (i in 0 until move.amount) {
            moveHead(move)
            moveTail()
        }
    }

    private fun moveTail() {
        if (!isTailInRange()) {
            if ((head.x + 1 == tail.x && head.y + 1 > tail.y)
                || (head.x + 1 > tail.x && head.y + 1 == tail.y)
            ) {
                tail.x += 1
                tail.y += 1
                return
            }
            if ((head.x - 1 == tail.x && head.y - 1 < tail.y)
                || (head.x - 1 < tail.x && head.y - 1 == tail.y)
            ) {
                tail.x -= 1
                tail.y -= 1
                return
            }
            if ((head.x - 1 == tail.x && head.y + 1 > tail.y)
                || (head.x - 1 < tail.x && head.y + 1 == tail.y)
            ) {
                tail.x += 1
                tail.y -= 1
                return
            }
            if ((head.x + 1 == tail.x && head.y - 1 < tail.y)
                || (head.x + 1 > tail.x && head.y - 1 == tail.y)
            ) {
                tail.x -= 1
                tail.y += 1
                return
            }

            if (head.x - 1 > tail.x) tail.x++
            if (head.x + 1 < tail.x) tail.x--
            if (head.y - 1 > tail.y) tail.y++
            if (head.y + 1 < tail.y) tail.y--
        }
        println(tail)
        visitedFields.add(tail.copy())
    }

    fun isTailInRange(): Boolean =
        head == tail
                || ((head.x - 1 == tail.x) && ((head.y - 1 == tail.y) || (head.y == tail.y) || (head.y + 1 == tail.y)))
                || ((head.x == tail.x) && ((head.y - 1 == tail.y) || (head.y == tail.y) || (head.y + 1 == tail.y)))
                || ((head.x + 1 == tail.x) && ((head.y - 1 == tail.y) || (head.y == tail.y) || (head.y + 1 == tail.y)))

    private fun moveHead(move: Move) {
        when (move.direction) {
            Direction.Up -> head.y++
            Direction.Left -> head.x--
            Direction.Down -> head.y--
            Direction.Right -> head.x++
        }
    }



}

object MoveParser {
    fun parse(input: List<String>): List<Move> =
        input.map {
            Move(
                direction = it.mapDirection(),
                amount = it.substringAfter(" ").toInt()
            )
        }

    private fun String.mapDirection(): Direction =
        when (this.substringBefore(" ")) {
            "U" -> Direction.Up
            "D" -> Direction.Down
            "R" -> Direction.Right
            "L" -> Direction.Left
            else -> throw NullPointerException()
        }
}

data class Move(
    val direction: Direction,
    val amount: Int,
)

data class Field(
    var x: Int,
    var y: Int,
) {
    override fun toString(): String = "$x | $y"
}

enum class Direction {
    Up,
    Left,
    Down,
    Right,
}
