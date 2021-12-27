package pl.bumbul.adventofcode.edition2019.solver;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day03CrossedWiresTest {

    private Day03CrossedWires tested;

    @Test
    void testFirstSetOfPaths() {
        // given
        Integer expectedDistance = 159;
        Integer expectedSteps = 610;
        tested = new Day03CrossedWires("Day03CrossedWiresTest1.input");

        // when
        Integer actualDistance = tested.minimalManhattanDistanceSupplier.getAsInt();
        Integer actualSteps = tested.minimalStepsToNearestIntersectionSupplier.getAsInt();

        // then
        Assertions.assertEquals(expectedDistance, actualDistance);
        Assertions.assertEquals(expectedSteps, actualSteps);
    }

    @Test
    void testSecondSetOfPaths() {
        // given
        Integer expectedDistance = 135;
        Integer expectedSteps = 410;
        tested = new Day03CrossedWires("Day03CrossedWiresTest2.input");

        // when
        Integer actualDistance = tested.minimalManhattanDistanceSupplier.getAsInt();
        Integer actualSteps = tested.minimalStepsToNearestIntersectionSupplier.getAsInt();

        // then
        Assertions.assertEquals(expectedDistance, actualDistance);
        Assertions.assertEquals(expectedSteps, actualSteps);
    }
}
