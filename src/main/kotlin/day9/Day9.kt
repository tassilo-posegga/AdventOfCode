package day9

import getTextFromUrl
import splitOnLineBreak
import kotlin.math.abs

/**
 * https://adventofcode.com/2022/day/9
 */
fun main(args: Array<String>) {
    val input = "https://pastebin.com/raw/psZsPyJN".getTextFromUrl().splitOnLineBreak()
    val mover = Mover()
    mover.executeMoves(MoveParser.parse(input))
    println("Visited fields: ${mover.getAmountOfVisitedFields()}")
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
            moveField(head, move)
            moveTail(move)
            println(tail)
        }
    }

    private fun moveTail(move: Move) {
        if (!isTailInRange(head, tail)
            // not in same row and column
            && head.y != tail.y && head.x != tail.x
        ) {
            moveDiagonal(tail, move)
        } else if (!isTailInRange(head, tail)) {
            moveField(tail, move)
        }
        visitedFields.add(tail.copy())
    }

    private fun moveDiagonal(field: Field, move: Move) {
        when {
            head.x - tail.x == 1 -> moveField(field, Move(Direction.Right, 1))
            head.x - tail.x == -1 -> moveField(field, Move(Direction.Left, 1))
            head.y - tail.y == 1 -> moveField(field, Move(Direction.Up, 1))
            head.y - tail.y == -1 -> moveField(field, Move(Direction.Down, 1))
        }
        moveField(field, move)
    }

    /**
     * Is in range when tails coordinates are at max 1 away from head on each dimension
     */
    fun isTailInRange(head: Field, tail: Field): Boolean =
        abs(head.x - tail.x) <= 1 && abs(head.y - tail.y) <= 1

    private fun moveField(field: Field, move: Move) {
        when (move.direction) {
            Direction.Up -> field.y++
            Direction.Left -> field.x--
            Direction.Down -> field.y--
            Direction.Right -> field.x++
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
