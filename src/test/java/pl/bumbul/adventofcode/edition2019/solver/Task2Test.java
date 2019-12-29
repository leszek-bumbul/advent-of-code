package pl.bumbul.adventofcode.edition2019.solver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.bumbul.adventofcode.edition2019.ResourceLoader;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class Task2Test {

    @Mock
    private ResourceLoader resourceLoader;

    @InjectMocks
    private Task2 tested;

    @Test
    public void testAddition() {
        // given
        given(resourceLoader.loadFileWithEntriesSeparatedByPeriod(any()))
                .willReturn(new ArrayList<>(List.of(1, 0, 0, 0, 99)));

        var expectedCodeStream = List.of(2, 0, 0, 0, 99);
        tested.initMemory();

        // when
        var actualMemory = tested.runIntcodeProgram(0, 0);

        // then
        assertEquals(expectedCodeStream, actualMemory);
    }

    @Test
    public void testMultiplication() {
        // given
        given(resourceLoader.loadFileWithEntriesSeparatedByPeriod(any()))
                .willReturn(new ArrayList<>(List.of(2, 3, 0, 3, 99)));
        var expectedCodeStream = List.of(2, 3, 0, 6, 99);
        tested.initMemory();

        // when
        var actualMemory = tested.runIntcodeProgram(3, 0);

        // then
        assertEquals(expectedCodeStream, actualMemory);
    }

    @Test
    public void testAdditionWithMultiplication() {
        // given
        given(resourceLoader.loadFileWithEntriesSeparatedByPeriod(any()))
                .willReturn(new ArrayList<>(List.of(1, 1, 1, 4, 99, 5, 6, 0, 99)));
        var expectedCodeStream = List.of(30, 1, 1, 4, 2, 5, 6, 0, 99);
        tested.initMemory();

        // when
        var actualMemory = tested.runIntcodeProgram(1, 1);

        // then
        assertEquals(expectedCodeStream, actualMemory);
    }

}
