package pl.bumbul.adventofcode.edition2019.solver;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day01TheTyrannyOfTheRocketEquationTest {

    private final Day01TheTyrannyOfTheRocketEquation tested = new Day01TheTyrannyOfTheRocketEquation("2019/Day01TheTyrannyOfTheRocketEquation.input");

    @Test
    void testFuelCounterUpper() {
        assertFuelCounterUpper(12, 2);
        assertFuelCounterUpper(14, 2);
        assertFuelCounterUpper(1969, 654);
        assertFuelCounterUpper(100756, 33583);
    }

    @Test
    void testRocketEquationDoubleChecker() {
        assertRocketEquationDoubleChecker(12, 2);
        assertRocketEquationDoubleChecker(14, 2);
        assertRocketEquationDoubleChecker(1969, 966);
        assertRocketEquationDoubleChecker(100756, 50346);
    }

    private void assertFuelCounterUpper(long actualMass, long expectedFuel) {
        Assertions.assertEquals(expectedFuel, tested.requiredFuel.applyAsLong(actualMass));
    }

    private void assertRocketEquationDoubleChecker(long actualMass, long expectedFuel) {
        Assertions.assertEquals(expectedFuel, tested.calculateRequiredFuelWithItsMass(actualMass).longValue());
    }
}
