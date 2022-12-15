import java.net.URL

/**
 * https://adventofcode.com/2022/day/1
 */
fun main(args: Array<String>) {
    val input = URL("https://pastebin.com/raw/7cyA5jW5").readText()
    val inputPerElf = input.split("\r\n\r\n")
    val clsPerElf = inputPerElf.map { elfInput ->
        val ints = elfInput.split("\r\n")
        val foo = ints.map { it.toInt() }
        foo.sumOf { it }
    }

    println(clsPerElf.max())
}