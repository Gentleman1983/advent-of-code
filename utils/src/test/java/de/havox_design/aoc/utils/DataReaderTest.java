package de.havox_design.aoc.utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import de.havox_design.aoc.utils.exceptions.ReadDataException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class DataReaderTest {
    @ParameterizedTest
    @MethodSource("getDataForReadFile")
    void testReadFile(String fileName, List<String> expectedEntries) {
        List<String> testData = DataReader.readData(fileName, DataReaderTest.class);

        assertAll(
                () -> assertThat(testData.size(), is(expectedEntries.size())),
                () -> assertThat(testData, contains(expectedEntries.toArray())),
                () -> assertThat(testData, equalTo(expectedEntries))
        );
    }

    private static Stream<Arguments> getDataForReadFile() {
        return Stream.of(
                Arguments.of(
                        "DataReader-sample1.txt",
                        List.of(
                                "This is a single row text!"
                        )
                ),
                Arguments.of(
                        "DataReader-sample2.txt",
                        List.of(
                                "This is",
                                "a",
                                "multi row",
                                "text!"
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForReadFileException")
    void testReadFileException(String fileName) {
        assertThrows(ReadDataException.class, () -> DataReader.readData(fileName, DataReaderTest.class));
    }

    private static Stream<Arguments> getDataForReadFileException() {
        return Stream.of(
                Arguments.of("http://foo.bar/test.txt"),
                Arguments.of("amnb;//;_:DSAWR-t"),
                Arguments.of("non-existing-file.txt"),
                Arguments.of("A:\\this\\is\\a\\non\\existing\\file.txt"),
                Arguments.of("/this/is/a/non/existing/file.txt")
        );
    }
}
