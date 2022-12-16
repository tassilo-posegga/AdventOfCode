import day7.CommandExecutor
import day7.FileSystem
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class Day7KtTest {

    private lateinit var fileSystem: FileSystem
    private lateinit var commandExecutor: CommandExecutor

    @Before
    fun setup() {
        fileSystem = FileSystem()
        commandExecutor = CommandExecutor(fileSystem)
        commands.forEach(commandExecutor::executeCommand)
    }

    @Test
    fun testTotalSize() {
        assertEquals(
            expected = 48381165,
            actual = fileSystem.getFileSize(fileSystem.tree.root)
        )
    }

    @Test
    fun testDirectoriesBelow100000() {
        assertEquals(
            expected = 95437,
            actual = fileSystem.getDirectoriesBelowSize(100000).sumOf { fileSystem.getFileSize(it) }
        )
    }

    @Test
    fun testDirectoryA() {
        assertEquals(
            expected = 94853,
            actual = fileSystem.getFileSize(fileSystem.tree.root.childs.find { it.name == "a" }!!)
        )
    }

    @Test
    fun testDirectoryD() {
        assertEquals(
            expected = 24933642,
            actual = fileSystem.getFileSize(fileSystem.tree.root.childs.find { it.name == "d" }!!)
        )
    }

    @Test
    fun testDirectoryDExtended() {
        commandExecutor = CommandExecutor(fileSystem)
        extendedCommands.forEach(commandExecutor::executeCommand)

        assertEquals(
            expected = 24933642 + 3000,
            actual = fileSystem.getFileSize(fileSystem.tree.root.childs.find { it.name == "d" }!!)
        )
    }

    @Test
    fun testDirectoryE() {
        assertEquals(
            expected = 584,
            actual = fileSystem.getFileSize(
                fileSystem.tree.root
                    .childs.find { it.name == "a" }!!
                    .childs.find { it.name == "e" }!!
            )
        )
    }

    @Test
    fun testFindAllDirectories() {
        assertEquals(
            expected = listOf("a", "d", "e"),
            actual = fileSystem.listAllDirectories(fileSystem.tree.root).map { it.name }
        )
    }

    @Test
    fun doesNotIncludeSingleFiles() {
        assertFalse(fileSystem.getDirectoriesBelowSize(100000).map { it.size }.contains(14848514))
        assertFalse(fileSystem.getDirectoriesBelowSize(100000).map { it.size }.contains(8504156))
        assertFalse(fileSystem.getDirectoriesBelowSize(100000).map { it.size }.contains(4060174))
        assertFalse(fileSystem.getDirectoriesBelowSize(100000).map { it.size }.contains(8033020))
        assertFalse(fileSystem.getDirectoriesBelowSize(100000).map { it.size }.contains(5626152))
        assertFalse(fileSystem.getDirectoriesBelowSize(100000).map { it.size }.contains(7214296))
    }

    companion object {
        val commands = listOf(
            "\$ cd /",
            "\$ ls",
            "dir a",
            "14848514 b.txt",
            "8504156 c.dat",
            "dir d",
            "\$ cd a",
            "\$ ls",
            "dir e",
            "29116 f",
            "2557 g",
            "62596 h.lst",
            "\$ cd e",
            "\$ ls",
            "584 i",
            "\$ cd ..",
            "\$ cd ..",
            "\$ cd d",
            "\$ ls",
            "4060174 j",
            "8033020 d.log",
            "5626152 d.ext",
            "7214296 k",
        )

        val extendedCommands = commands +
                listOf(
                    "dir x",
                    "\$ cd x",
                    "\$ ls",
                    "1000 x.log",
                    "dir y",
                    "\$ cd y",
                    "\$ ls",
                    "1000 y.log",
                    "dir z",
                    "\$ cd z",
                    "\$ ls",
                    "1000 z.log",
                )
    }
}