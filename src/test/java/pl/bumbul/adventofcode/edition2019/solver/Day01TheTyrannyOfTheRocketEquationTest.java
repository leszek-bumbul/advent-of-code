package pl.bumbul.adventofcode.edition2019.solver;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day01TheTyrannyOfTheRocketEquationTest {

    private Day01TheTyrannyOfTheRocketEquation tested = new Day01TheTyrannyOfTheRocketEquation();

    @Test
    public void testFuelCounterUpper() {
        assertFuelCounterUpper(12, 2);
        assertFuelCounterUpper(14, 2);
        assertFuelCounterUpper(1969, 654);
        assertFuelCounterUpper(100756, 33583);
    }

    @Test
    public void testRocketEquationDoubleChecker() {
        assertRocketEquationDoubleChecker(12, 2);
        assertRocketEquationDoubleChecker(14, 2);
        assertRocketEquationDoubleChecker(1969, 966);
        assertRocketEquationDoubleChecker(100756, 50346);
    }

    private void assertFuelCounterUpper(long actualMass, long expectedFuel) {
        assertEquals(expectedFuel, tested.calculateRequiredFuel.applyAsLong(actualMass));
    }

    private void assertRocketEquationDoubleChecker(long actualMass, long expectedFuel) {
        assertEquals(expectedFuel, tested.calculateRequiredFuelWithItsMass(actualMass).longValue());
    }
}
