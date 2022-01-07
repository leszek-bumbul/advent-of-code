package pl.bumbul.adventofcode.commons;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

@Log4j2
public class ResourceLoader {

    private ResourceLoader() {
        throw new AssertionError();
    }

    public static final String FILE_NOT_FOUND = "File not found";

    public static Stream<String> loadFileWithOneEntryPerRow(String fileName, Function<Path, Stream<String>> pathConverter) {
        Optional<URL> systemResource = Optional.ofNullable(ClassLoader.getSystemResource(fileName));
        if (systemResource.isEmpty()) {
            return Stream.empty();
        }
        return pathConverter.apply(Paths.get(systemResource.get().getPath()));
    }

    public static final Function<Path, Stream<String>> extractData = path -> {
        try {
            return Files.readAllLines(path).stream();
        } catch (IOException e) {
            log.error(FILE_NOT_FOUND, e);
        }
        return Stream.empty();
    };

    public static final Function<Path, Stream<String>> extractDataSeparatedByPeriod = path -> {
        try {
            return Arrays.stream(String.join(",", Files.readAllLines(path)).split(",", 0));
        } catch (IOException e) {
            log.error(FILE_NOT_FOUND, e);
        }
        return Stream.empty();
    };

}
