package day8

import getTextFromUrl
import splitOnLineBreak

/**
 * https://adventofcode.com/2022/day/8
 */
fun main(args: Array<String>) {
    val input = "https://pastebin.com/raw/jcgLjS4C".getTextFromUrl().splitOnLineBreak()
    val forest = ForestParser.parseForest(input)
    val treeObserver = TreeObserver(forest)
    println("Visible trees: ${treeObserver.getAmountOfVisibleTrees()}")
    println("Highest scenic score: ${treeObserver.getHighestScenicScore()}")
}

class TreeObserver(private val forest: Forest) {

    fun getHighestScenicScore(): Int =
        forest.trees.maxOf { it.maxOf { tree -> getTreesScenicScore(tree) } }

    fun getTreesScenicScore(tree: Tree): Int =
        Direction.values().map {
            getNumberTreesUntilBlocked(tree, getVisibleTreesInDirectionOf(tree, it))
        }.multipliesOf()

    private fun List<Int>.multipliesOf(): Int {
        var multiplies = this[0]
        for (i in 1 until size) {
            multiplies *= this[i]
        }
        return multiplies
    }

    fun getAmountOfVisibleTrees(): Int =
        forest.trees.sumOf { it.count { tree -> isTreeVisible(tree) } }

    fun isTreeVisible(tree: Tree): Boolean =
        isVisibleFromDirection(tree, getVisibleTreesInDirectionOf(tree, Direction.Up))
                || isVisibleFromDirection(tree, getVisibleTreesInDirectionOf(tree, Direction.Down))
                || isVisibleFromDirection(tree, getVisibleTreesInDirectionOf(tree, Direction.Left))
                || isVisibleFromDirection(tree, getVisibleTreesInDirectionOf(tree, Direction.Right))

    fun getVisibleTreesInDirectionOf(tree: Tree, direction: Direction): List<Tree> =
        when (direction) {
            Direction.Left -> forest.trees[tree.row].subList(0, tree.column).reversed()
            Direction.Right -> forest.trees[tree.row].subList(tree.column + 1, forest.trees[tree.row].size)
            Direction.Up -> forest.trees.map { it[tree.column] }.subList(0, tree.row).reversed()
            Direction.Down -> with(forest.trees.map { it[tree.column] }) { subList(tree.row + 1, size) }
        }

    private fun getNumberTreesUntilBlocked(tree: Tree, treesInDirection: List<Tree>): Int {
        val indexOfFirstBlockingTree = treesInDirection.indexOfFirst { it.height >= tree.height }
        //no blocking tree found, visible trees is all
        return if (indexOfFirstBlockingTree == -1) {
            treesInDirection.size
        } else {
            indexOfFirstBlockingTree + 1
        }
    }

    private fun isVisibleFromDirection(tree: Tree, treesInDirection: List<Tree>): Boolean =
        treesInDirection
            .map { it.height }
            .none { it >= tree.height }
}


object ForestParser {
    fun parseForest(input: List<String>): Forest =
        Forest(trees = input.mapIndexed { row, stringInput ->
            // single chars as items
            stringInput.split("")
                .filterNot { it.isEmpty() }
                .mapIndexed { column, height ->
                    Tree(
                        height = height.toInt(),
                        row = row,
                        column = column,
                    )
                }
        })
}

class Tree(
    val height: Int,
    val row: Int,
    val column: Int
)

class Forest(val trees: List<List<Tree>>)

enum class Direction {
    Up,
    Left,
    Down,
    Right,
}