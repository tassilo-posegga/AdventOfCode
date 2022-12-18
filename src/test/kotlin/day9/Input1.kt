package day9

import javax.naming.NameNotFoundException

object Input1 {
    val INPUT = listOf(
        "R 4",
        "U 4",
        "L 3",
        "D 1",
        "R 4",
        "D 1",
        "L 5",
        "R 2",
    )

    const val INITIAL_STATE = "......\n" +
            "......\n" +
            "......\n" +
            "......\n" +
            "H...."

    private const val AFTER_MOVE_1 = "......\n" +
            "......\n" +
            "......\n" +
            "......\n" +
            "4321H."

    private const val AFTER_MOVE_2 = "....H.\n" +
            "....1.\n" +
            "..432.\n" +
            ".5....\n" +
            "6....."

    private const val AFTER_MOVE_3 = ".H1...\n" +
            "...2..\n" +
            "..43..\n" +
            ".5....\n" +
            "6....."

    private const val AFTER_MOVE_4 = "..1...\n" +
            ".H.2..\n" +
            "..43..\n" +
            ".5....\n" +
            "6....."

    private const val AFTER_MOVE_5 = "......\n" +
            "...21H\n" +
            "..43..\n" +
            ".5....\n" +
            "6...."

    private const val AFTER_MOVE_6 = "......\n" +
            "...21.\n" +
            "..43.H\n" +
            ".5....\n" +
            "6....."

    private const val AFTER_MOVE_7 = "......\n" +
            "......\n" +
            "H123..\n" +
            ".5....\n" +
            "6....."

    private const val AFTER_MOVE_8 = "......\n" +
            "......\n" +
            ".1H3..\n" +
            ".5....\n" +
            "6....."

    val moveStates = listOf(
        AFTER_MOVE_1,
        AFTER_MOVE_2,
        AFTER_MOVE_3,
        AFTER_MOVE_4,
        AFTER_MOVE_5,
        AFTER_MOVE_6,
        AFTER_MOVE_7,
        AFTER_MOVE_8,
    )

    fun getField(char: String, string: String): Field? {
        val lines = string.split("\n").reversed()

        lines.forEachIndexed { y, line ->
            if (line.indexOf(char) != -1) {
                return Field(line.indexOf(char), y)
            }
        }

       return null
    }
}