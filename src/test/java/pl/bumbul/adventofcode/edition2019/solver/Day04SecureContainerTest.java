package pl.bumbul.adventofcode.edition2019.solver;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day04SecureContainerTest {

    private final Day04SecureContainer tested = new Day04SecureContainer("2019/Day04SecureContainer.input");

    @Test
    void testWrongNumberNoDoubleFound() {
        // given
        long inputNumber = 123789;

        // when
        boolean result = tested.verifyNumber.test(inputNumber);

        //then
        Assertions.assertFalse(result);
    }

    @Test
    void testWrongNumberDecreasingPairOfDigitsFound() {
        // given
        long inputNumber = 223450;

        // when
        boolean result = tested.verifyNumber.test(inputNumber);

        // then
        Assertions.assertFalse(result);
    }

    @Test
    void testWrongNumberWithGroupOfThreeDigits() {
        Assertions.assertFalse(tested.filterOutPasswordsForSecondStage.test(123444));
        Assertions.assertTrue(tested.filterOutPasswordsForSecondStage.test(112233));
        Assertions.assertTrue(tested.filterOutPasswordsForSecondStage.test(111122));

    }
}