package day8

import org.junit.Test

import org.junit.Before
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Day8KtTest {

    private val forest = ForestParser.parseForest(input)
    private val trees = forest.trees

    private lateinit var treeObserver: TreeObserver

    @Before
    fun setup() {
        treeObserver = TreeObserver(forest)
    }

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
            assertTrue(treeObserver.isTreeVisible(trees[row][0]))
            assertTrue(treeObserver.isTreeVisible(trees[row][4]))
        }

        for (column in 0..4) {
            assertTrue(treeObserver.isTreeVisible(trees[0][column]))
            assertTrue(treeObserver.isTreeVisible(trees[4][column]))
        }
    }

    @Test
    fun testIsTreeNotVisible() {
        assertFalse(treeObserver.isTreeVisible(trees[2][2]))
        assertFalse(treeObserver.isTreeVisible(trees[1][3]))
        assertFalse(treeObserver.isTreeVisible(trees[3][1]))
        assertFalse(treeObserver.isTreeVisible(trees[3][3]))
    }

    @Test
    fun testAmountOfVisibleTrees() {
        assertEquals(
            expected = 21,
            actual = treeObserver.getAmountOfVisibleTrees()
        )
    }

    @Test
    fun testGetScenicScoreForTree() {
        assertEquals(
            expected = 8,
            actual = treeObserver.getTreesScenicScore(trees[3][2])
        )

        assertEquals(
            expected = 0,
            actual = treeObserver.getTreesScenicScore(trees[0][0])
        )

        assertEquals(
            expected = 1,
            actual = treeObserver.getTreesScenicScore(trees[2][2])
        )
    }

    @Test
    fun testGetHighestScenicScore() {
        assertEquals(
            expected = 8,
            actual = treeObserver.getHighestScenicScore()
        )
    }

    @Test
    fun testGetVisibleTreesInDirection() {
        assertEquals(
            expected = listOf(trees[2][2], trees[1][2], trees[0][2]),
            actual = treeObserver.getVisibleTreesInDirectionOf(trees[3][2], Direction.Up)
        )

        assertEquals(
            expected = listOf(trees[3][1], trees[3][0]),
            actual = treeObserver.getVisibleTreesInDirectionOf(trees[3][2], Direction.Left)
        )

        assertEquals(
            expected = listOf(trees[4][2]),
            actual = treeObserver.getVisibleTreesInDirectionOf(trees[3][2], Direction.Down)
        )

        assertEquals(
            expected = listOf(trees[3][3], trees[3][4]),
            actual = treeObserver.getVisibleTreesInDirectionOf(trees[3][2], Direction.Right)
        )
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