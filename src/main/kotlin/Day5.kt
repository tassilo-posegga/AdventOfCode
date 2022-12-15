import java.util.*

/**
 * https://adventofcode.com/2022/day/4
 */
fun main(args: Array<String>) {
    val input = "https://pastebin.com/raw/wM3m6c8E".getTextFromUrl().splitOnLineBreak()
    val cratePilesInput = input.subList(0, 8)

    Storage.initPiles(9)

    cratePilesInput.forEach { crateString ->
        for (iterator: Int in crateString.indices step 4) {
            val possibleCrate = crateString.substring(iterator, iterator + 3)
            val crate = possibleCrate.substringAfter("[").substringBefore("]")
            if (crate != possibleCrate)
                Storage.addCrateToPile(iterator / 4 + 1, crate.toCharArray()[0])
        }
    }

    Storage.reversePiles()
    Storage.print()

    val instructionsInput = input.subList(10, input.size)
    val commands = instructionsInput.map {
        it.parseCommand()
    }
    commands.forEach {
        executeCommand(it)
        Storage.print()
        println()
    }


    println(Storage.getTopCrates())
}

private fun executeCommand(command: Command) {
    for (i in 1..command.amount) {
        Storage.moveCrate(command.from, command.to)
    }
}

private fun String.parseCommand(): Command {
    val foo = split(" ")
    return Command(
        amount = foo[1].toInt(),
        from = foo[3].toInt(),
        to = foo[5].toInt(),
    )
}

data class Command(
    val from: Int,
    val to: Int,
    val amount: Int,
)

object Storage {
    private val cratePiles: MutableMap<Int, Stack<Char>> = mutableMapOf()

    fun print() {
        cratePiles.forEach { entry ->
            print("${entry.key}: ->")
            entry.value.forEach {
                print("[$it]")
            }
            println()
        }
    }

    fun moveCrate(fromPile: Int, toPile: Int) {
        cratePiles[toPile]!!.push(cratePiles[fromPile]!!.pop())
    }

    fun initPiles(numberOfPiles: Int) {
        for (id in 1..numberOfPiles) {
            cratePiles[id] = Stack()
        }
    }

    fun addCrateToPile(pileId: Int, crate: Char) {
        cratePiles[pileId]!!.push(crate)
    }

    /**
     * Call after parsing input for initial setup
     */
    fun reversePiles() {
        cratePiles.forEach {
            cratePiles[it.key]!!.reverse()
        }
    }

    fun getTopCrates(): List<Char> =
        cratePiles.map {
            it.value.peek()
        }
}