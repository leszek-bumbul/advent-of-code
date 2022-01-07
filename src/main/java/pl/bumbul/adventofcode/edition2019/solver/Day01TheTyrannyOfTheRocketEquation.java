package pl.bumbul.adventofcode.edition2019.solver;

import lombok.extern.log4j.Log4j2;
import pl.bumbul.adventofcode.commons.AdventDay;
import pl.bumbul.adventofcode.commons.ResourceLoader;

import java.util.List;
import java.util.function.LongUnaryOperator;
import java.util.stream.Collectors;

@Log4j2
public class Day01TheTyrannyOfTheRocketEquation implements AdventDay {

    private final List<Long> dataSamples;

    public Day01TheTyrannyOfTheRocketEquation(String taskInput) {
        dataSamples = ResourceLoader.loadFileWithOneEntryPerRow(taskInput, ResourceLoader.extractData)
                .map(Long::parseLong).collect(Collectors.toList());
    }

    @Override
    public void solve() {
        log.info("--- Day 1: The Tyranny of the Rocket Equation ---");
        log.info("Stage 1 solution: {}", calculate(requiredFuel));
        log.info("Stage 2 solution: {}", calculate(this::calculateRequiredFuelWithItsMass));
    }

    LongUnaryOperator requiredFuel = mass -> ((long) Math.floor(1.0 * mass / 3)) - 2;

    Long calculate(LongUnaryOperator algorithm) {
        return dataSamples.stream().mapToLong(Long::longValue).map(algorithm)
                .reduce(0L, Long::sum);
    }

    Long calculateRequiredFuelWithItsMass(long mass) {
        var fuel = requiredFuel.applyAsLong(mass);
        if (fuel <= 0L) {
            return 0L;
        }
        return fuel + calculateRequiredFuelWithItsMass(fuel);
    }
}
