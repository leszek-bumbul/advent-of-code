package pl.bumbul.adventofcode.edition2021.solver;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day02DiveTest {

    private final Day02Dive tested = new Day02Dive("2021/Day02DiveTest1.input");

    @Test
    void testMultiplicationOfFinalHorizontalAndFinalDepth(){
        // given
        Integer expectedResult = 150;

        // when
        Integer actual = tested.calculateMultiplicationOfFinalHorizontalAndFinalDepth();

        // then
        Assertions.assertEquals(expectedResult, actual);

    }

    @Test
    void testMultiplicationOfFinalHorizontalAndFinalDepthWithAim(){
        // given
        Integer expectedResult = 900;

        // when
        Integer actual = tested.calculateMultiplicationOfFinalHorizontalAndFinalDepthWithAim();

        // then
        Assertions.assertEquals(expectedResult, actual);
    }

}
