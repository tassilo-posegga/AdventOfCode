package day8

import getTextFromUrl
import splitOnLineBreak

/**
 * https://adventofcode.com/2022/day/8
 */
fun main(args: Array<String>) {
    val input = "https://pastebin.com/raw/jcgLjS4C".getTextFromUrl().splitOnLineBreak()
    val forest = ForestParser.parseForest(input)
    println(TreeObserver.getAmountOfVisibleTrees(forest))
}

object TreeObserver {

    fun getAmountOfVisibleTrees(forest: Forest): Int {
        var visibleTrees = 0
        forest.trees.forEachIndexed { row, trees ->
            trees.forEachIndexed { column, _ ->
                if (isTreeVisible(row, column, forest))
                    visibleTrees++
            }
        }
        return visibleTrees
    }

    fun isTreeVisible(row: Int, column: Int, forest: Forest): Boolean {
        val currentHeight = forest.trees[row][column].height
        val isVisibleFromLeft = getIsVisibleFromLeft(forest, row, column, currentHeight)
        val isVisibleFromRight = getIsVisibleFromRight(forest, row, column, currentHeight)
        val isVisibleFromTop = getIsVisibleFromTop(forest, row, column, currentHeight)
        val isVisibleFromBottom = getIsVisibleFromBottom(forest, row, column, currentHeight)
        return isVisibleFromLeft || isVisibleFromRight || isVisibleFromTop || isVisibleFromBottom
    }

    private fun getIsVisibleFromTop(forest: Forest, row: Int, column: Int, currentHeight: Int) =
        forest.trees
            .map { it[column] }
            .subList(0, row)
            .map { it.height }
            .none { it >= currentHeight }

    private fun getIsVisibleFromBottom(forest: Forest, row: Int, column: Int, currentHeight: Int): Boolean {
        val columns = forest.trees.map { it[column] }
        return columns.subList(row + 1, columns.size)
            .map { it.height }
            .none { it >= currentHeight }
    }

    private fun getIsVisibleFromLeft(forest: Forest, row: Int, column: Int, currentHeight: Int) =
        forest.trees[row].subList(0, column)
            .map { it.height }
            .none { it >= currentHeight }

    private fun getIsVisibleFromRight(forest: Forest, row: Int, column: Int, currentHeight: Int) =
        forest.trees[row].subList(column + 1, forest.trees[row].size)
            .map { it.height }
            .none { it >= currentHeight }
}

object ForestParser {
    fun parseForest(input: List<String>): Forest =
        Forest(trees = input.map { stringInput ->
            stringInput.split("")
                .filterNot { it.isEmpty() }
                .map { Tree(height = it.toInt()) }
        })
}

class Tree(val height: Int)
class Forest(val trees: List<List<Tree>>)
