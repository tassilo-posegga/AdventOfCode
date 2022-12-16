package day7

import getTextFromUrl
import splitOnLineBreak

/**
 * https://adventofcode.com/2022/day/7
 */
fun main(args: Array<String>) {
    val input = "https://pastebin.com/raw/9RTpeXyz".getTextFromUrl()
    val commands = getCommands(input)
    val fileSystem = FileSystem()
    val commandExecutor = CommandExecutor(fileSystem)
    commands.forEach(commandExecutor::executeCommand)
    println(fileSystem.getDirectoriesBelowSize(100000).sumOf { fileSystem.getFileSize(it) })
}

fun getCommands(input: String): List<String> =
    input.splitOnLineBreak()

class CommandExecutor(
    private val fileSystem: FileSystem
) {
    fun executeCommand(command: String) {
        when {
            command.startsWith("$ cd") -> changeDirectory(command)
            command.startsWith("dir") -> createDirectory(command)
            //just ignoring ls command and create a file or directory when we do not match any $
            command.startsWith("$ ls") -> Unit
            else -> createFile(command)
        }
    }

    private fun createFile(command: String) {
        fileSystem.addFile(
            name = command.substringAfter(" "),
            size = command.substringBefore(" ").toInt(),
        )
    }

    private fun createDirectory(command: String) {
        fileSystem.addDirectory(command.substringAfter("dir "))
    }

    private fun changeDirectory(command: String) {
        when (val targetDirectory = command.substringAfter("cd ")) {
            "/" -> fileSystem.travelToRoot()
            ".." -> fileSystem.travelUpwards()
            else -> fileSystem.travelToNode(targetDirectory)
        }
    }
}

class FileSystem {

    val tree = Tree(
        Node(
            name = "/",
            type = NodeType.Directory,
            parent = null,
        )
    )

    private var currentNode: Node = tree.root

    fun addDirectory(name: String) {
        Node(
            name = name,
            type = NodeType.Directory,
            parent = currentNode
        ).addIfNotExists()
    }

    fun addFile(name: String, size: Int) {
        Node(
            name = name,
            type = NodeType.File,
            parent = currentNode,
            size = size,
        ).addIfNotExists()
    }

    private fun Node.addIfNotExists() {
        if (!currentNode.childs.contains(this))
            currentNode.childs.add(this)
    }

    fun travelToRoot() {
        currentNode = tree.root
    }

    fun travelToNode(name: String) {
        currentNode = currentNode.childs.find { it.name == name }!!
    }

    fun travelUpwards() {
        currentNode = currentNode.parent!!
    }

    fun getDirectoriesBelowSize(size: Int): List<Node> {
        val allDirectories = listAllDirectories(tree.root)
        return allDirectories.filter {
            getFileSize(it) <= size
        }
    }

    fun listAllDirectories(
        node: Node,
        list: MutableList<Node> = mutableListOf()
    ): List<Node> {
        list += node.listSubDirectories()
        node.childs.forEach { listAllDirectories(it, list) }
        return list
    }

    private fun Node.listSubDirectories(): List<Node> =
        childs.filter { it.type == NodeType.Directory }

    fun getFileSize(node: Node): Int {
        return node.size + node.childs.sumOf { getFileSize(it) }
    }

    data class Tree(
        val root: Node
    )

    data class Node(
        val name: String,
        val type: NodeType,
        val parent: Node?,
        val childs: MutableList<Node> = mutableListOf(),
        val size: Int = 0,
    ) {
        override fun toString(): String {
            return "$type: $name"
        }
    }

    enum class NodeType {
        Directory,
        File,
    }
}
