/**
 * https://adventofcode.com/2022/day/1
 */
fun main(args: Array<String>) {
    val input = "https://pastebin.com/raw/7cyA5jW5".getTextFromUrl()
    val caloriesPerMealPerElv = input.split("\r\n\r\n")
    val totalCaloriesPerElf = caloriesPerMealPerElv.map { elfInput ->
        elfInput.splitOnLineBreak()
            .map { it.toInt() }
            .sumOf { it }
    }.sorted()

    println(totalCaloriesPerElf.last())
    println(totalCaloriesPerElf.takeLast(3).sum())
}
