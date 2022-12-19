package day11

import getTextFromUrl
import splitOnLineBreak
import kotlin.math.floor

/**
 * https://adventofcode.com/2022/day/11
 */
fun main(args: Array<String>) {
    val input = "https://pastebin.com/raw/EMkvCSq7".getTextFromUrl()
    val game = MonkeyGame(MonkeyParser.parse(input))
    game.playRounds(20)
    println("Monkey business: ${game.getMonkeyBusiness()}")
}

object MonkeyParser {
    fun parse(input: String): List<Monkey> {
        val monkeys = input.split("\r\n\r\n")
        return monkeys.map { monkey ->
            val lines = monkey.splitOnLineBreak()
            Monkey(
                items = parseItems(lines[1]),
                operation = parseOperation(lines[2]),
                divisor = lines[3].substringAfter("Test: divisible by ").toInt(),
                targetMonkeyTrue = lines[4].substringAfter("If true: throw to monkey ").toInt(),
                targetMonkeyFalse = lines[5].substringAfter("If false: throw to monkey ").toInt(),
            )
        }
    }

    private fun parseOperation(line: String): (Int) -> Int = {
        val operation = line.substringAfter("Operation: new = old ").split(" ")
        var value = operation[1]
        if (value == "old") value = it.toString()
        when {
            operation[0] == "*" -> it * value.toInt()
            operation[0] == "+" -> it + value.toInt()
            else -> it
        }
    }

    private fun parseItems(input: String): MutableList<Item> {
        val itemsString = input.substringAfter("Starting items: ")
        return when {
            itemsString.contains(",") -> itemsString.split(", ").map { Item(it.toInt()) }.toMutableList()
            itemsString.isBlank() -> mutableListOf()
            else -> mutableListOf(Item(itemsString.toInt()))
        }
    }
}

class MonkeyGame(
    val monkeys: List<Monkey>
) {
    val inspectingMap = mutableMapOf<Int, Int>()

    fun getMonkeyBusiness() =
        inspectingMap.values.sorted().reversed()[0] * inspectingMap.values.sorted().reversed()[1]

    fun playRounds(rounds: Int) {
        for (i in 0 until rounds) {
            playRound()
        }
    }

    fun playRound() {
        monkeys.forEachIndexed { index, monkey ->
            if (inspectingMap[index] == null) inspectingMap[index] = 0
            inspectingMap[index] = inspectingMap[index]!! + monkey.items.size
            monkey.inspectItems().forEach {
                monkey.throwItem(it.key)
                monkeys[it.value].catchItem(it.key)
            }
        }
    }
}

data class Monkey(
    val items: MutableList<Item>,
    val divisor: Int,
    val targetMonkeyTrue: Int,
    val targetMonkeyFalse: Int,
    val operation: (Int) -> Int
) {

    //Due to concurrency issues we are returning a map
    fun inspectItems(): Map<Item, Int> {
        val itemTargetMap = mutableMapOf<Item, Int>()
        items.forEach {
            println(it)
            it.value = operation.invoke(it.value)
            println(it)
            it.value = floor(it.value.toFloat() / 3f).toInt()
            println(it)
            itemTargetMap[it] = getTargetMonkey(it)
            println("${getTargetMonkey(it)}")
            println()
        }
        return itemTargetMap
    }

    private fun getTargetMonkey(item: Item): Int =
        if (item.value % divisor == 0) targetMonkeyTrue
        else targetMonkeyFalse

    fun catchItem(item: Item) {
        items.add(item)
    }

    fun throwItem(item: Item) {
        items.remove(item)
    }
}

data class Item(
    var value: Int
)