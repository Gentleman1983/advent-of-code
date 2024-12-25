package de.havox_design.aoc2022.day07

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class NoSpaceLeftOnDeviceTest {
    @ParameterizedTest
    @MethodSource("getDataForTestReadFolder")
    fun testReadFolder(filename: String, expectedFileSystem: Directory) {
        val objectUnderTest = NoSpaceLeftOnDevice(filename)
        objectUnderTest.readData()

        objectUnderTest.getFs().toString().shouldBe(expectedFileSystem.toString())
    }

    @ParameterizedTest
    @CsvSource(
        "de/havox_design/aoc2022/day07/day07Sample.txt,48381165"
    )
    fun testSumAllFiles(filename: String, expectedSize: Int) {
        val data = NoSpaceLeftOnDevice(filename)
        data.readData()

        data.getFs().calculateSizeOfFilesInDirAndSubDirs().shouldBe(expectedSize)
    }

    @ParameterizedTest
    @CsvSource(
        "de/havox_design/aoc2022/day07/day07Sample.txt,24933642"
    )
    fun testFindSmallestDirLargerThanLimit(filename: String, expectedSize: Int) {
        val missingSpace = 8381165
        val data = NoSpaceLeftOnDevice(filename)
        data.readData()

        data.getFs().findSmallestDirLargerThanLimit(missingSpace).shouldBe(expectedSize)
    }

    @ParameterizedTest
    @CsvSource(
        "de/havox_design/aoc2022/day07/day07Sample.txt,95437"
    )
    fun testProcessPart1(filename: String, expectedSize: Int) =
        NoSpaceLeftOnDevice(filename).processPart1().shouldBe(expectedSize)

    @ParameterizedTest
    @CsvSource(
        "de/havox_design/aoc2022/day07/day07Sample.txt,24933642"
    )
    fun testProcessPart2(filename: String, expectedSize: Int) =
        NoSpaceLeftOnDevice(filename).processPart2().shouldBe(expectedSize)

    companion object {
        @JvmStatic
        private fun getDataForTestReadFolder(): Stream<Arguments> {
            val dirE = Directory(null, "e", emptySet(), setOf(File("i", 584)))
            val dirA = Directory(
                null,
                "a",
                setOf(dirE),
                setOf(
                    File("f", 29116),
                    File("g", 2557),
                    File("h.lst", 62596)
                )
            )
            dirE.parent = dirA
            val dirD = Directory(
                null,
                "d",
                emptySet(),
                setOf(
                    File("j", 4060174),
                    File("d.log", 8033020),
                    File("d.ext", 5626152),
                    File("k", 7214296)
                )
            )
            val rootDir = Directory(
                null,
                "/",
                setOf(dirA, dirD),
                setOf(
                    File("b.txt", 14848514),
                    File("c.dat", 8504156)
                )
            )
            dirA.parent = rootDir
            dirD.parent = rootDir

            return Stream.of(Arguments.of("de/havox_design/aoc2022/day07/day07Sample.txt", rootDir))
        }
    }
}
