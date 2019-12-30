package pl.bumbul.adventofcode.edition2019.solver;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import pl.bumbul.adventofcode.edition2019.ResourceLoader;
import pl.bumbul.adventofcode.edition2019.Task;

import java.util.function.LongUnaryOperator;

@Component
@Log4j2
public class Day01TheTyrannyOfTheRocketEquation implements Task {

    private ResourceLoader resourceLoader;

    public Day01TheTyrannyOfTheRocketEquation(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void execute() {
        log.info("--- Day 1: The Tyranny of the Rocket Equation ---");
        log.info("Stage 1 solution: {}", solve(calculateRequiredFuel));
        log.info("Stage 2 solution: {}", solve(this::calculateRequiredFuelWithItsMass));
    }

    private Long solve(LongUnaryOperator algorithm) {
        return resourceLoader.loadFileWithOneEntryPerRow("Day01TheTyrannyOfTheRocketEquation.input")
                    .map(algorithm::applyAsLong)
                    .reduce(0L, Long::sum);
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
