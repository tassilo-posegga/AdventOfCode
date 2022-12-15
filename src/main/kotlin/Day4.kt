/**
 * https://adventofcode.com/2022/day/4
 */
fun main(args: Array<String>) {
    val input = "https://pastebin.com/raw/gjfNWJny".getTextFromUrl().splitOnLineBreak()
    println("Overlaps: ${getOverlaps(input)}")
}

fun getOverlaps(strings: List<String>): Int {
    var fullInterSectsCount = 0
    strings.forEach {
        val pair = it.getPair()
        val firstRange: IntRange = pair.first.getRange()
        val secondRange: IntRange = pair.second.getRange()
        if (secondRange.intersect(firstRange).isNotEmpty())
            fullInterSectsCount++
    }
    return fullInterSectsCount
}

private fun String.getPair(): Pair<String, String> {
    val split = this.split(",")
    return Pair(split[0], split[1])
}

private fun String.getRange(): IntRange {
    val split = split("-")
    return split[0].toInt()..split[1].toInt()
}
