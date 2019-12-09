package pl.bumbul.adventofcode.edition2019.task1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.bumbul.adventofcode.edition2019.ResourceLoader;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class Task1Test {

    @Mock
    private ResourceLoader resourceLoader;

    @InjectMocks
    private Task1Solver task1Solver;

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
        assertEquals(expectedFuel, task1Solver.calculateRequiredFuel.applyAsLong(actualMass));
    }

    private void assertRocketEquationDoubleChecker(long actualMass, long expectedFuel) {
        assertEquals(expectedFuel, task1Solver.calculateRequiredFuelWithItsMass(actualMass).longValue());
    }
}
