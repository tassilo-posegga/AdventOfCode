/**
 * https://adventofcode.com/2022/day/2
 *
 * A = Rock
 * B = Paper
 * C = Scissor
 */
fun main(args: Array<String>) {
    val input = "https://pastebin.com/raw/2R8C2XHk".getTextFromUrl().splitOnLineBreak()
    println("Sum of priorities: ${PriorityCounter.getSummedPriorities(input)}")
}

data class Rucksack(
    val firstCompartment: Compartment,
    val secondCompartment: Compartment,
)

data class Compartment(
    val items: String,
)

object RucksackPacker {
    fun packRucksacks(rucksacks: List<String>): List<Rucksack> =
        rucksacks.map(::packRucksack)

    private fun packRucksack(rucksackString: String): Rucksack {
        val totalItems = rucksackString.length
        val middle = rucksackString.length / 2
        return Rucksack(
            firstCompartment = Compartment(rucksackString.substring(0, middle)),
            secondCompartment = Compartment(rucksackString.substring(middle, totalItems))
        )
    }
}

private fun Rucksack.getWrongItem(): Char? {
    firstCompartment.items.toCharArray().forEach { firstCompartmentItem ->
        val matchingItem = secondCompartment.items.toCharArray().find { firstCompartmentItem == it }
        if (matchingItem != null) return matchingItem
    }
    return null
}

object Priorities {

    val priorityMap: Map<Char, Int> = createItemTypes()

    private fun createItemTypes(): Map<Char, Int> {
        val priorityMap: MutableMap<Char, Int> = mutableMapOf()
        var priority = 1
        for (character: Char in 'a'..'z') {
            priorityMap[character] = priority++
        }
        for (character: Char in 'A'..'Z') {
            priorityMap[character] = priority++
        }
        return priorityMap
    }
}

object PriorityCounter {

    fun getSummedPriorities(input: List<String>): Int =
        RucksackPacker.packRucksacks(input).sumOf {
            Priorities.priorityMap[it.getWrongItem()]!!
        }
}