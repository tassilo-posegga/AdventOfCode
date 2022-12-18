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

class Mover(
    val rope: List<Field> = List(10) { Field(0, 0) },
) {
    private var visitedFields: MutableList<Field> = mutableListOf(rope.last().copy())

    fun getAmountOfVisitedFields() =
        visitedFields.distinct().size

    fun executeMoves(moves: List<Move>) {
        moves.forEach(::executeMove)
    }

    fun executeMove(move: Move) {
        for (i in 0 until move.amount) {
            // moving the head and each subsequent field if it needs to move
            moveField(rope.first(), move.direction)
            for (field in 0 until rope.size - 1) {
                val head = rope[field]
                val tail = rope[field + 1]
                moveTail(head, tail)
                savePositionOfTail(tail)
            }
        }
    }

    private fun savePositionOfTail(tail: Field) {
        if (tail == rope.last()) {
            visitedFields.add(tail.copy())
        }
    }

    private fun moveTail(head: Field, tail: Field) {
        val difference = head - tail // a vector indicating the directions to move
        if (!isInRangeOfOtherField(difference))
            getDirectionToMove(difference).forEach { moveField(tail, it) }
    }

    private fun getDirectionToMove(difference: Field): List<Direction> = when {
        difference.x > 0 && difference.y > 0 -> Direction.Up + Direction.Right
        difference.x < 0 && difference.y < 0 -> Direction.Down + Direction.Left
        difference.x < 0 && difference.y > 0 -> Direction.Up + Direction.Left
        difference.x > 0 && difference.y < 0 -> Direction.Down + Direction.Right
        difference.x > 1 -> listOf(Direction.Right)
        difference.x < -1 -> listOf(Direction.Left)
        difference.y > 1 -> listOf(Direction.Up)
        difference.y < -1 -> listOf(Direction.Down)
        else -> listOf(Direction.None)
    }

    /**
     * Is in range when tails coordinates are at max 1 away from head on each dimension
     */
    fun isInRangeOfOtherField(difference: Field): Boolean =
        abs(difference.x) <= 1 && abs(difference.y) <= 1

    private fun moveField(field: Field, direction: Direction) {
        when (direction) {
            Direction.Up -> field.y++
            Direction.Left -> field.x--
            Direction.Down -> field.y--
            Direction.Right -> field.x++
            Direction.None -> Unit
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

    operator fun minus(other: Field): Field =
        Field(
            x = this.x - other.x,
            y = this.y - other.y
        )
}

enum class Direction {
    Up,
    Left,
    Down,
    Right,
    None;

    operator fun plus(other: Direction): List<Direction> =
        listOf(this, other)
}