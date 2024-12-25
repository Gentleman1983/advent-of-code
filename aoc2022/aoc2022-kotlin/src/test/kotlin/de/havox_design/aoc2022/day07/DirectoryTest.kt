package de.havox_design.aoc2022.day07

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class DirectoryTest {
    @ParameterizedTest
    @MethodSource("getDataForTestDirectoryCalculateSizeOfFilesInDir")
    fun testDirectoryCalculateSizeOfFilesInDir(fileSizes: List<Int>, expectedSize: Int) {
        val dir = Directory(null, "/", emptySet(), emptySet())

        for (size in fileSizes) {
            dir.files += File("file$size", size)
        }

        dir.calculateSizeOfFilesInDir().shouldBe(expectedSize)
    }

    @ParameterizedTest
    @MethodSource("getDataForTestDirectoryCalculateSizeOfFilesInDirAndSubdirs")
    fun testDirectoryCalculateSizeOfFilesInDirAndSubdirs(
        fileSizes: List<Int>,
        subDirFileSizes: List<Int>,
        expectedSize: Int
    ) {
        val dir = Directory(null, "/", emptySet(), emptySet())
        val subDir = Directory(dir, "subDir", emptySet(), emptySet())

        dir.dirs += subDir

        for (size in subDirFileSizes) {
            subDir.files += File("file$size", size)
        }

        for (size in fileSizes) {
            dir.files += File("file$size", size)
        }

        dir.calculateSizeOfFilesInDirAndSubDirs().shouldBe(expectedSize)
    }

    companion object {
        @JvmStatic
        private fun getDataForTestDirectoryCalculateSizeOfFilesInDir(): Stream<Arguments> =
            Stream.of(
                Arguments.of(listOf(1), 1),
                Arguments.of(listOf(1, 2), 3),
                Arguments.of(listOf(1, 2, 3, 5, 7), 18),
                Arguments.of(listOf(13, 23, 42), 78),
                Arguments.of(listOf(53, 666), 719),
                Arguments.of(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9), 45)
            )

        @JvmStatic
        private fun getDataForTestDirectoryCalculateSizeOfFilesInDirAndSubdirs(): Stream<Arguments> =
            Stream.of(
                Arguments.of(listOf(99, 98, 97, 6), listOf(1), 301),
                Arguments.of(listOf(99, 98, 97, 6), listOf(1, 2), 303),
                Arguments.of(listOf(99, 98, 97, 6), listOf(1, 2, 3, 5, 7), 318),
                Arguments.of(listOf(99, 98, 97, 6), listOf(13, 23, 42), 378),
                Arguments.of(listOf(99, 98, 97, 6), listOf(53, 666), 1019),
                Arguments.of(listOf(99, 98, 97, 6), listOf(1, 2, 3, 4, 5, 6, 7, 8, 9), 345)
            )
    }
}
