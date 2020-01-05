package pl.bumbul.adventofcode.edition2019.solver;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day03CrossedWiresTest {

    Day03CrossedWires tested = new Day03CrossedWires();

    @Test
    public void testFirstSetOfPaths() {
        // given
        Integer expectedDistance = 159;
        Integer expectedSteps = 610;
        tested.init("Day03CrossedWiresTest1.input");

        // when
        Integer actualDistance = tested.getMinimalManhattanDistance.getAsInt();
        Integer actualSteps = tested.getMinimalStepsToNearestIntersection.getAsInt();

        // then
        assertEquals(expectedDistance, actualDistance);
        assertEquals(expectedSteps, actualSteps);
    }

    @Test
    public void testSecondSetOfPaths() {
        // given
        Integer expectedDistance = 135;
        Integer expectedSteps = 410;
        tested.init("Day03CrossedWiresTest2.input");

        // when
        Integer actualDistance = tested.getMinimalManhattanDistance.getAsInt();
        Integer actualSteps = tested.getMinimalStepsToNearestIntersection.getAsInt();

        // then
        assertEquals(expectedDistance, actualDistance);
        assertEquals(expectedSteps, actualSteps);
    }
}
