import java.net.URL

/**
 * https://adventofcode.com/2022/day/1
 */
fun main(args: Array<String>) {
    val input = URL("https://pastebin.com/raw/7cyA5jW5").readText()
    val caloriesPerMealPerElv = input.split("\r\n\r\n")
    val totalCaloriesPerElf = caloriesPerMealPerElv.map { elfInput ->
        elfInput.split("\r\n")
            .map { it.toInt() }
            .sumOf { it }
    }.sorted()

    println(totalCaloriesPerElf.last())
    println(totalCaloriesPerElf.takeLast(3).sum())
}
