package day9

import javax.naming.NameNotFoundException

object Input2 {
    val INPUT = listOf(
        "R 5",
        "U 8",
        "L 8",
        "D 3",
        "R 17",
        "D 10",
        "L 25",
        "U 20",
    )

    const val INITIAL_STATE = "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "...........H..............\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            ".........................."

    private const val AFTER_MOVE_1 = "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "...........54321H.........\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            ".........................."

    private const val AFTER_MOVE_2 = "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "................H.........\n" +
            "................1.........\n" +
            "................2.........\n" +
            "................3.........\n" +
            "...............54.........\n" +
            "..............6...........\n" +
            ".............7............\n" +
            "............8.............\n" +
            "...........9..............\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            ".........................."

    private const val AFTER_MOVE_3 = "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "........H1234.............\n" +
            "............5.............\n" +
            "............6.............\n" +
            "............7.............\n" +
            "............8.............\n" +
            "............9.............\n" +
            "..........................\n" +
            "..........................\n" +
            "...........s..............\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            ".........................."

    private const val AFTER_MOVE_4 = "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            ".........2345.............\n" +
            "........1...6.............\n" +
            "........H...7.............\n" +
            "............8.............\n" +
            "............9.............\n" +
            "..........................\n" +
            "..........................\n" +
            "...........s..............\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            ".........................."

    private const val AFTER_MOVE_5 = "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "................987654321H\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "...........s..............\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            ".........................."

    private const val AFTER_MOVE_6 = "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "...........s.........98765\n" +
            ".........................4\n" +
            ".........................3\n" +
            ".........................2\n" +
            ".........................1\n" +
            ".........................H"

    private const val AFTER_MOVE_7 = "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "...........s..............\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "H123456789................"

    private const val AFTER_MOVE_8 = "H.........................\n" +
            "1.........................\n" +
            "2.........................\n" +
            "3.........................\n" +
            "4.........................\n" +
            "5.........................\n" +
            "6.........................\n" +
            "7.........................\n" +
            "8.........................\n" +
            "9.........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "...........s..............\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            "..........................\n" +
            ".........................."

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
                return Field(line.indexOf(char) - 11, y - 5)
            }
        }

        return null
    }
}