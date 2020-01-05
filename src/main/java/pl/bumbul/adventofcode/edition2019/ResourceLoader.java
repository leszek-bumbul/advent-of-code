package pl.bumbul.adventofcode.edition2019;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log4j2
public class ResourceLoader {

    private ResourceLoader() {
        throw new AssertionError();
    }

    private static final String FILE_NOT_FOUND = "File not found";
    private static Path path;

    private static void loadFile(String fileName) {
        Optional<URL> file = Optional.ofNullable(ClassLoader.getSystemResource(fileName));
        file.ifPresent(url -> path = Paths.get(url.getPath()));
    }

    public static Stream<Long> loadFileWithOneEntryPerRow(String fileName) {
        loadFile(fileName);
        Stream<Long> result = Stream.empty();
        try {
            result = Files.readAllLines(path).stream().map(Long::parseLong);
        } catch (IOException e) {
            log.error(FILE_NOT_FOUND, e);
        }
        return result;
    }

    public static List<Integer> loadFileWithEntriesSeparatedByPeriod(String fileName) {
        loadFile(fileName);
        List<Integer> result = new ArrayList<>();
        try {
            result = Arrays.stream(String.join(",", Files.readAllLines(path)).split(",", 0))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            log.error(FILE_NOT_FOUND, e);
        }
        return result;
    }

    public static Map<Integer, List<String>> loadFileWithInstructionsInEachRow(String fileName) {
        loadFile(fileName);
        Map<Integer, List<String>> result = new HashMap<>();
        try {
            result.put(1, Arrays.asList(Files.readAllLines(path).get(0).split(",", 0)));
            result.put(2, Arrays.asList(Files.readAllLines(path).get(1).split(",", 0)));
        } catch (IOException e) {
            log.error(FILE_NOT_FOUND, e);
        }
        return result;
    }
}
