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
            actual = MoveParser.parse(INPUT)
        )
    }

    @Test
    fun testCompareFields() {
        val field1 = Field(0, 0)
        val field2 = Field(0, 0)
        assertEquals(field1, field2)
    }

    @Test
    fun testHeadMoves() {
        assertEquals(Field(0, 0), mover.head)
        mover.executeMove(Move(Direction.Down, 1))
        assertEquals(Field(0, -1), mover.head)
    }

    @Test
    fun testIsTailInRangeInSurroundingFields() {
        val head = Field(0, 0)

        assertTrue(mover.isTailInRange(head, Field(0, 0)))

        for (x in -1..1) {
            for (y in -1..1) {
                val tail = Field(1, 0)
                val isInRange = mover.isTailInRange(head, tail)
                assertTrue(isInRange)
            }
        }
    }

    @Test
    fun testIsTailInRangeWhen2Away() {
        val head = Field(0, 0)

        assertFalse(mover.isTailInRange(head, Field(0, 2)))
        assertFalse(mover.isTailInRange(head, Field(2, 0)))
        assertFalse(mover.isTailInRange(head, Field(4, 0)))
    }

    @Test
    fun testIsTailInRangeWhenDiagonal() {
        val head = Field(0, 0)

        assertTrue(mover.isTailInRange(head, Field(1, 1)))
        assertTrue(mover.isTailInRange(head, Field(1, -1)))
        assertTrue(mover.isTailInRange(head, Field(-1, 1)))
        assertTrue(mover.isTailInRange(head, Field(-1, -1)))
    }

    @Test
    fun testAmount() {
        mover.executeMoves(MoveParser.parse(INPUT))
        assertEquals(
            expected = 13,
            actual = mover.getAmountOfVisitedFields()
        )
    }

    companion object {
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
    }
}