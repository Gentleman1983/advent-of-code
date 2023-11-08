package de.havox_design.aoc2015.utils;

import com.opensymphony.xwork2.util.ClassLoaderUtil;
import de.havox_design.aoc2015.utils.exceptions.ReadDataException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DataReader {
    public static List<String> readData(String fileName, Class callingClass) {
        try {
            URL url = ClassLoaderUtil.getResource(fileName, callingClass);
            Path path = Paths.get(url.toURI());
            return Files.readAllLines(path, StandardCharsets.UTF_8);
        }
        catch (IOException|URISyntaxException e) {
            throw new ReadDataException(e);
        }
    }
}
