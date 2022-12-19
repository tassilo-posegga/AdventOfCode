package day10

import getTextFromUrl
import splitOnLineBreak
import java.lang.StringBuilder

/**
 * https://adventofcode.com/2022/day/10
 */
fun main(args: Array<String>) {
    val input = "https://pastebin.com/raw/8T5vsnaW".getTextFromUrl().splitOnLineBreak()
    val instructions = InstructionParser.parse(input)
    val executor = InstructionExecutor()
    executor.executeInstructions(instructions)
    println(executor.getPixels())
}

class InstructionExecutor {
    private val cycles = mutableListOf(1)

    fun getPixels(): String {
        val sb = StringBuilder()
        for (index in 0 until cycles.size - 1) {
            val registerX = index % 40
            val sprite = cycles[index] - 1..cycles[index] + 1
            if (registerX in sprite) sb.append("#")
            else sb.append(".")
            if (registerX == 39) sb.append("\n")
        }
        return sb.toString()
    }

    fun getTotalSignalStrength(): Int =
        getSignalStrengthAtPosition(20) +
                getSignalStrengthAtPosition(60) +
                getSignalStrengthAtPosition(100) +
                getSignalStrengthAtPosition(140) +
                getSignalStrengthAtPosition(180) +
                getSignalStrengthAtPosition(220)

    fun executeInstructions(instructions: List<Instruction>): MutableList<Int> {
        instructions.forEach {
            when (it) {
                is Add -> {
                    cycles.add(cycles.last())
                    cycles.add(cycles.last() + it.amount)
                }

                is NoOp -> {
                    cycles.add(cycles.last())
                }
            }
        }
        return cycles
    }

    private fun getSignalStrengthAtPosition(position: Int): Int =
        cycles[position - 1] * position

}

object InstructionParser {
    fun parse(input: List<String>): List<Instruction> =
        input.map {
            if (it.startsWith("addx")) Add(it.substringAfter(" ").toInt())
            else NoOp
        }
}

sealed class Instruction
data class Add(val amount: Int) : Instruction()
object NoOp : Instruction()