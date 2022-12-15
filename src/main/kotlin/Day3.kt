/**
 * https://adventofcode.com/2022/day/2
 *
 * A = Rock
 * B = Paper
 * C = Scissor
 */
fun main(args: Array<String>) {
    val input = "https://pastebin.com/raw/2R8C2XHk".getTextFromUrl().splitOnLineBreak()

    val groups = mutableListOf<Group>()
    for (i: Int in input.indices step 3) {
        groups.add(
            Group(
                firstRucksack = input[i],
                secondRucksack = input[i + 1],
                thirdRucksack = input[i + 2],
            )
        )
    }

    val priorities = groups.sumOf { Priorities.priorityMap[it.findBadge()!!]!! }

    println("Sum of priorities: $priorities")
}

fun Group.findBadge(): Char? {
    firstRucksack.forEach {
        if (secondRucksack.contains(it) && thirdRucksack.contains(it)) {
            return it
        }
    }
    return null
}

data class Group(
    val firstRucksack: String,
    val secondRucksack: String,
    val thirdRucksack: String,
)

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