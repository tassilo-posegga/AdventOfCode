package day11

import getTextFromUrl
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class Day11KtTest {

    private lateinit var game: MonkeyGame

    @Before
    fun setUp() {
        game = MonkeyGame(MonkeyParser.parse(MONKEY_TEXT))
    }

    @Test
    fun testParseMonkey() {
        val input = "https://pastebin.com/raw/EMkvCSq7".getTextFromUrl()
        val parsedGame = MonkeyGame(MonkeyParser.parse( input))
        val manualGame = MonkeyGame(MONKEY_REAL_INPUT)

        parsedGame.playRounds(20)
        manualGame.playRounds(20)

        assertEquals(
            expected = manualGame.inspectingMap,
            actual = parsedGame.inspectingMap,
        )
    }

    @Test
    fun testFirstRound() {
        game.playRound()

        assertEquals(
            expected = listOf(20, 23, 27, 26).map { Item(it) },
            actual = game.monkeys[0].items,
        )
        assertEquals(
            expected = listOf(2080, 25, 167, 207, 401, 1046).map { Item(it) },
            actual = game.monkeys[1].items,
        )
        assertEquals(
            expected = emptyList(),
            actual = game.monkeys[2].items,
        )
        assertEquals(
            expected = emptyList(),
            actual = game.monkeys[3].items,
        )

    }

    @Test
    fun testLastRound() {
        game.playRounds(20)

        assertEquals(
            expected = listOf(10, 12, 14, 26, 34).map { Item(it) },
            actual = game.monkeys[0].items,
        )
        assertEquals(
            expected = listOf(245, 93, 53, 199, 115).map { Item(it) },
            actual = game.monkeys[1].items,
        )
        assertEquals(
            expected = emptyList(),
            actual = game.monkeys[2].items,
        )
        assertEquals(
            expected = emptyList(),
            actual = game.monkeys[3].items,
        )
    }


    @Test
    fun testMonkeyBusiness() {
        game.playRounds(20)

        assertEquals(
            expected = 10605,
            actual = game.getMonkeyBusiness(),
        )
    }

    companion object {
        val MONKEY_REAL_INPUT = listOf(
            Monkey(
                items = listOf(66, 59, 64, 51).map { Item(it) }.toMutableList(),
                operation = { it * 3 },
                divisor = 2,
                targetMonkeyTrue = 1,
                targetMonkeyFalse = 4
            ),
            Monkey(
                items = listOf(67, 61).map { Item(it) }.toMutableList(),
                operation = { it * 19 },
                divisor = 7,
                targetMonkeyTrue = 3,
                targetMonkeyFalse = 5
            ),
            Monkey(
                items = listOf(86, 93, 80, 70, 71, 81, 56).map { Item(it) }.toMutableList(),
                operation = { it + 2 },
                divisor = 11,
                targetMonkeyTrue = 4,
                targetMonkeyFalse = 0
            ),
            Monkey(
                items = listOf(94).map { Item(it) }.toMutableList(),
                operation = { it * it },
                divisor = 19,
                targetMonkeyTrue = 7,
                targetMonkeyFalse = 6
            ),
            Monkey(
                items = listOf(71, 92, 64).map { Item(it) }.toMutableList(),
                operation = { it + 8 },
                divisor = 3,
                targetMonkeyTrue = 5,
                targetMonkeyFalse = 1
            ),
            Monkey(
                items = listOf(58, 81, 92, 75, 56).map { Item(it) }.toMutableList(),
                operation = { it + 6 },
                divisor = 5,
                targetMonkeyTrue = 3,
                targetMonkeyFalse = 6
            ),
            Monkey(
                items = listOf(82, 98, 77, 94, 86, 81).map { Item(it) }.toMutableList(),
                operation = { it + 7 },
                divisor = 17,
                targetMonkeyTrue = 7,
                targetMonkeyFalse = 2
            ),
            Monkey(
                items = listOf(54, 95, 70, 93, 88, 93, 63, 50).map { Item(it) }.toMutableList(),
                operation = { it + 4 },
                divisor = 13,
                targetMonkeyTrue = 2,
                targetMonkeyFalse = 0
            ),
        )
        val MONKEY_TEST_INPUT = listOf(
            Monkey(
                items = listOf(79, 98).map { Item(it) }.toMutableList(),
                divisor = 23,
                targetMonkeyTrue = 2,
                targetMonkeyFalse = 3,
                operation = { it * 19 }
            ),
            Monkey(
                items = listOf(54, 65, 75, 74).map { Item(it) }.toMutableList(),
                divisor = 19,
                targetMonkeyTrue = 2,
                targetMonkeyFalse = 0,
                operation = { it + 6 }
            ),
            Monkey(
                items = listOf(79, 60, 97).map { Item(it) }.toMutableList(),
                divisor = 13,
                targetMonkeyTrue = 1,
                targetMonkeyFalse = 3,
                operation = { it * it }
            ),
            Monkey(
                items = listOf(74).map { Item(it) }.toMutableList(),
                divisor = 17,
                targetMonkeyTrue = 0,
                targetMonkeyFalse = 1,
                operation = { it + 3 }
            ),
        )

        val MONKEY_TEXT = "Monkey 0:\r\n" +
                "  Starting items: 79, 98\r\n" +
                "  Operation: new = old * 19\r\n" +
                "  Test: divisible by 23\r\n" +
                "    If true: throw to monkey 2\r\n" +
                "    If false: throw to monkey 3\r\n\r\n" +
                "Monkey 1:\r\n" +
                "  Starting items: 54, 65, 75, 74\r\n" +
                "  Operation: new = old + 6\r\n" +
                "  Test: divisible by 19\r\n" +
                "    If true: throw to monkey 2\r\n" +
                "    If false: throw to monkey 0\r\n\r\n" +
                "Monkey 2:\r\n" +
                "  Starting items: 79, 60, 97\r\n" +
                "  Operation: new = old * old\r\n" +
                "  Test: divisible by 13\r\n" +
                "    If true: throw to monkey 1\r\n" +
                "    If false: throw to monkey 3\r\n\r\n" +
                "Monkey 3:\r\n" +
                "  Starting items: 74\r\n" +
                "  Operation: new = old + 3\r\n" +
                "  Test: divisible by 17\r\n" +
                "    If true: throw to monkey 0\r\n" +
                "    If false: throw to monkey 1\r\n"
    }
}