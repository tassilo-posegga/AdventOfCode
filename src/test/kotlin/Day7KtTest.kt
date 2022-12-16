import day7.CommandExecutor
import day7.FileSystem
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

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
        assertEquals(48381165, fileSystem.getFileSize(fileSystem.tree.root))
    }

    @Test
    fun testDirectoriesAbove100000() {
        assertEquals(24933642, fileSystem.getDirectoriesAboveSize(100000).sum())
    }

    @Test
    fun testDirectoryA() {
        assertEquals(94853, fileSystem.getFileSize(fileSystem.tree.root.childs.find { it.name == "a" }!!))
    }

    @Test
    fun testDirectoryD() {
        assertEquals(24933642, fileSystem.getFileSize(fileSystem.tree.root.childs.find { it.name == "d" }!!))
    }

    @Test
    fun testDirectoryE() {
        assertEquals(
            584,
            fileSystem.getFileSize(
                fileSystem.tree.root
                    .childs.find { it.name == "a" }!!
                    .childs.find { it.name == "e" }!!
            )
        )
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
    }
}