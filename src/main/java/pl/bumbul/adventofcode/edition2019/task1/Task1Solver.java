package pl.bumbul.adventofcode.edition2019.task1;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import pl.bumbul.adventofcode.edition2019.ResourceLoader;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Optional;

@Component
@Log4j2
public class Task1Solver {

    private ResourceLoader resourceLoader;

    public Task1Solver(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public Long solve() {
        Optional<Long> result = Optional.empty();
        resourceLoader.load("task1.txt");
        try {
            result = Files.readAllLines(resourceLoader.getPath(), Charset.defaultCharset()).stream()
                    .map(mass -> this.calculateRequiredFuel(Long.parseLong(mass)))
                    .reduce(Long::sum);
        } catch (IOException e) {
            log.error("Something went wrong with the modules file", e);
        }
        return result.orElseThrow();
    }

    private long calculateRequiredFuel(long mass) {
        return ((long) Math.floor(1.0 * mass / 3)) - 2;
    }

}
