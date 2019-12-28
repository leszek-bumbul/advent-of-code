package pl.bumbul.adventofcode.edition2019.solver;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import pl.bumbul.adventofcode.edition2019.ResourceLoader;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Optional;
import java.util.function.LongUnaryOperator;

@Component
@Log4j2
public class Task1 implements Task {

    private ResourceLoader resourceLoader;

    public Task1(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void execute() {
        resourceLoader.load("task1.txt");
        log.info("Task 1 stage 1 solution: {}", solve(calculateRequiredFuel));
        log.info("Task 1 stage 2 solution: {}", solve(this::calculateRequiredFuelWithItsMass));
    }

    private Long solve(LongUnaryOperator algorithm) {
        Optional<Long> result = Optional.empty();
        try {
            result = Files.readAllLines(resourceLoader.getPath(), Charset.defaultCharset()).stream()
                    .map(mass -> algorithm.applyAsLong(Long.parseLong(mass)))
                    .reduce(Long::sum);
        } catch (IOException e) {
            log.error("File not found", e);
        }
        return result.orElseThrow();
    }

    LongUnaryOperator calculateRequiredFuel = mass -> ((long) Math.floor(1.0 * mass / 3)) - 2;

    Long calculateRequiredFuelWithItsMass(long mass) {
        var requiredFuel = calculateRequiredFuel.applyAsLong(mass);
        if (requiredFuel <= 0L) {
            return 0L;
        }
        return requiredFuel + calculateRequiredFuelWithItsMass(requiredFuel);
    }
}
