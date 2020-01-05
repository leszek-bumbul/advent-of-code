package pl.bumbul.adventofcode.edition2019.solver;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class Day04SecureContainerTest {

    Day04SecureContainer tested = new Day04SecureContainer();

    @Test
    public void testWrongNumberNoDoubleFound() {
        // given
        long inputNumber = 123789;

        // when
        boolean result = tested.verifyNumber.test(inputNumber);

        //then
        assertFalse(result);
    }

    @Test
    public void testWrongNumberDecreasingPairOfDigitsFound() {
        // given
        long inputNumber = 223450;

        // when
        boolean result = tested.verifyNumber.test(inputNumber);

        // then
        assertFalse(result);
    }

}