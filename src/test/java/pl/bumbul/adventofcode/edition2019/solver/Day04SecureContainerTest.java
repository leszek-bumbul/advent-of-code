package pl.bumbul.adventofcode.edition2019.solver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.bumbul.adventofcode.edition2019.ResourceLoader;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class Day04SecureContainerTest {

    @Mock
    ResourceLoader resourceLoader;

    @InjectMocks
    Day04SecureContainer tested;

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