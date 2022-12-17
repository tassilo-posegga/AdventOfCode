package day8

import org.junit.Test

import org.junit.Assert.*

class Day8KtTest {

    private val forest = ForestParser.parseForest(input)
    private val trees = forest.trees

    @Test
    fun testParsedCorrectly() {
        assertEquals(3, trees[0][0].height)
        assertEquals(0, trees[0][1].height)
        assertEquals(3, trees[0][4].height)
        assertEquals(2, trees[1][0].height)
        assertEquals(2, trees[1][4].height)
        assertEquals(3, trees[4][0].height)
        assertEquals(0, trees[4][4].height)
    }

    @Test
    fun testAllEdgeTreesVisible() {
        for (row in 0..4) {
            assertTrue(TreeObserver.isTreeVisible(row, 0, forest))
            assertTrue(TreeObserver.isTreeVisible(row, 4, forest))
        }

        for (column in 0..4) {
            assertTrue(TreeObserver.isTreeVisible(0, column, forest))
            assertTrue(TreeObserver.isTreeVisible(4, column, forest))
        }
    }

    @Test
    fun testIsTreeNotVisible() {
        assertFalse(TreeObserver.isTreeVisible(2, 2, forest))
        assertFalse(TreeObserver.isTreeVisible(1, 3, forest))
        assertFalse(TreeObserver.isTreeVisible(3, 1, forest))
        assertFalse(TreeObserver.isTreeVisible(3, 3, forest))
    }

    @Test
    fun testAmountOfVisibleTrees() {
        assertEquals(21, TreeObserver.getAmountOfVisibleTrees(forest))
    }

    companion object {
        val input = listOf(
            "30373",
            "25512",
            "65332",
            "33549",
            "35390",
        )
    }
}