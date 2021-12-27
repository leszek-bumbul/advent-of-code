package pl.bumbul.adventofcode.edition2021.solver;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day01SonarSweepTest {

    Day01SonarSweep tested = new Day01SonarSweep("2021/Day01SonarSweepTest1.input");

    @Test
    void testDepthIncreaseQuantity() {
        // given
        Integer expectedNumberOfIncreases = 7;

        // when
        Integer actualNumberOfIncreases = tested.countIncreases();

        // then
        Assertions.assertEquals(expectedNumberOfIncreases, actualNumberOfIncreases);

    }

    @Test
    void testThreeMeasurmentSlidingWindow(){
        // given
        Integer expectedNumberOfIncreases = 5;

        // when
        Integer actualNumberOfIncreases = tested.countIncreasesFromThreeMeasurmentSlidingWindow();

        // then
        Assertions.assertEquals(expectedNumberOfIncreases, actualNumberOfIncreases);
    }
}