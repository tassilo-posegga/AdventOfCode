package day9

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Day9KtTest {

    private lateinit var mover: Mover

    @Before
    fun setUp() {
        mover = Mover()
    }


    @Test
    fun testParseMoves() {
        assertEquals(
            expected = listOf(
                Move(Direction.Right, 4),
                Move(Direction.Up, 4),
                Move(Direction.Left, 3),
                Move(Direction.Down, 1),
                Move(Direction.Right, 4),
                Move(Direction.Down, 1),
                Move(Direction.Left, 5),
                Move(Direction.Right, 2),
            ),
            actual = MoveParser.parse(Input1.INPUT)
        )
    }

    @Test
    fun testHeadMoves() {
        assertEquals(Field(0, 0), mover.rope.first())
        mover.executeMove(Move(Direction.Down, 1))
        assertEquals(Field(0, -1), mover.rope.first())
    }

    @Test
    fun testIsTailInRangeInSurroundingFields() {
        val head = Field(0, 0)

        assertTrue(mover.isInRangeOfOtherField(head - Field(0, 0)))

        for (x in -1..1) {
            for (y in -1..1) {
                val tail = Field(1, 0)
                val isInRange = mover.isInRangeOfOtherField(head - tail)
                assertTrue(isInRange)
            }
        }
    }

    @Test
    fun testIsTailNotInRangeWhen2Away() {
        val head = Field(0, 0)

        assertFalse(mover.isInRangeOfOtherField(head - Field(0, 2)))
        assertFalse(mover.isInRangeOfOtherField(head - Field(2, 0)))
        assertFalse(mover.isInRangeOfOtherField(head - Field(-2, 0)))
        assertFalse(mover.isInRangeOfOtherField(head - Field(0, -2)))


        assertFalse(mover.isInRangeOfOtherField(head - Field(-2, -2)))
        assertFalse(mover.isInRangeOfOtherField(head - Field(-2, 2)))
        assertFalse(mover.isInRangeOfOtherField(head - Field(2, -2)))
        assertFalse(mover.isInRangeOfOtherField(head - Field(2, 2)))
    }

    @Test
    fun testIsTailInRangeWhenDiagonal() {
        val head = Field(0, 0)

        assertTrue(mover.isInRangeOfOtherField(head - Field(1, 1)))
        assertTrue(mover.isInRangeOfOtherField(head - Field(1, -1)))
        assertTrue(mover.isInRangeOfOtherField(head - Field(-1, 1)))
        assertTrue(mover.isInRangeOfOtherField(head - Field(-1, -1)))
    }

    @Test
    fun testAmount() {
        mover.executeMoves(MoveParser.parse(Input1.INPUT))
        assertEquals(
            expected = 1,
            actual = mover.getAmountOfVisitedFields()
        )
    }

    @Test
    fun testAmount2() {
        mover.executeMoves(MoveParser.parse(Input2.INPUT))
        assertEquals(
            expected = 36,
            actual = mover.getAmountOfVisitedFields()
        )
    }

    @Test
    fun testMoves() {
        assertEquals(
            expected = Input1.getField("H", Input1.INITIAL_STATE),
            actual = mover.rope.first()
        )

        for (move in MoveParser.parse(Input1.INPUT).indices) {

            mover.executeMove(MoveParser.parse(Input1.INPUT)[move])

            for (i in 0 until mover.rope.distinct().size) {
                val field = if (i == 0) Input1.getField("H", Input1.moveStates[move])
                else Input1.getField("$i", Input1.moveStates[move])

                println("Move: ${move + 1}, char: $i, field: $field")
                if (field != null) {
                    assertEquals(
                        expected = field,
                        actual = mover.rope[i]
                    )
                }
            }
        }
    }

    @Test
    fun testMoves2() {
        assertEquals(
            expected = Input2.getField("H", Input2.INITIAL_STATE),
            actual = mover.rope.first()
        )

        for (move in MoveParser.parse(Input2.INPUT).indices) {

            mover.executeMove(MoveParser.parse(Input2.INPUT)[move])

            for (i in 0 until mover.rope.distinct().size) {
                val field = if (i == 0) Input2.getField("H", Input2.moveStates[move])
                else Input2.getField("$i", Input2.moveStates[move])

                println("Move: ${move + 1}, char: $i, field: $field")
                if (field != null) {
                    assertEquals(
                        expected = field,
                        actual = mover.rope[i]
                    )
                }
            }
        }
    }
}