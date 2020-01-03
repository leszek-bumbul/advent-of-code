package pl.bumbul.adventofcode.edition2019.solver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.bumbul.adventofcode.edition2019.ResourceLoader;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class Day03CrossedWiresTest {

    @Mock
    ResourceLoader resourceLoader;

    @InjectMocks
    Day03CrossedWires tested;

    @Test
    public void testFirstSetOfPaths() {
        // given
        Map<Integer, List<String>> instructions = Map.of(
                1, List.of("R75", "D30", "R83", "U83", "L12", "D49", "R71", "U7", "L72"),
                2, List.of("U62", "R66", "U55", "R34", "D71", "R55", "D58", "R83")
        );
        given(resourceLoader.loadFileWithInstructionsInEachRow(any())).willReturn(instructions);
        Integer expectedDistance = 159;
        Integer expectedSteps = 610;
        tested.loadResources();
        tested.initPathPoints();

        // when
        Integer actualDistance = tested.getMinimalManhattanDistance.getAsInt();
        Integer actualSteps = tested.getMinimalStepsToNearestIntersection.getAsInt();

        // then
        assertEquals(expectedDistance, actualDistance);
        assertEquals(expectedSteps, actualSteps);
    }

    @Test
    public void testSecondSetOfPaths() {
        Map<Integer, List<String>> instructions = Map.of(
                1, List.of("R98", "U47", "R26", "D63", "R33", "U87", "L62", "D20", "R33", "U53", "R51"),
                2, List.of("U98", "R91", "D20", "R16", "D67", "R40", "U7", "R15", "U6", "R7")
        );
        given(resourceLoader.loadFileWithInstructionsInEachRow(any())).willReturn(instructions);
        Integer expectedDistance = 135;
        Integer expectedSteps = 410;
        tested.loadResources();
        tested.initPathPoints();

        // when
        Integer actualDistance = tested.getMinimalManhattanDistance.getAsInt();
        Integer actualSteps = tested.getMinimalStepsToNearestIntersection.getAsInt();

        // then
        assertEquals(expectedDistance, actualDistance);
        assertEquals(expectedSteps, actualSteps);
    }
}
