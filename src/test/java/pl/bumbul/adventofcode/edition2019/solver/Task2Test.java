package pl.bumbul.adventofcode.edition2019.solver;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.bumbul.adventofcode.edition2019.ResourceLoader;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class Task2Test {

    @Mock
    private ResourceLoader resourceLoader;

    @InjectMocks
    private Task2 tested;
    private List<Integer> codeStream;

    @After
    public void tearDown() {
        tested.resetTestCodeStream();
    }

    @Test
    public void testAddition() {
        // given
        codeStream = new ArrayList<>(List.of(1, 0, 0, 0, 99));

        var expectedCodeStream = List.of(2, 0, 0, 0, 99);

        // when
        tested.setTestCodeStream(codeStream);
        tested.codeSummator.accept(0);

        // then
        assertEquals(expectedCodeStream, tested.getCodeStream());
    }

    @Test
    public void testMultiplication() {
        // given
        codeStream = new ArrayList<>(List.of(2, 3, 0, 3, 99));
        var expectedCodeStream = List.of(2, 3, 0, 6, 99);

        // when
        tested.setTestCodeStream(codeStream);
        tested.codeMultiplicator.accept(0);

        // then
        assertEquals(expectedCodeStream, tested.getCodeStream());
    }

    @Test
    public void testAdditionWithMultiplication() {
        // given
        codeStream = new ArrayList<>(List.of(1,1,1,4,99,5,6,0,99));
        var expectedCodeStream = List.of(30,1,1,4,2,5,6,0,99);

        // when
        tested.setTestCodeStream(codeStream);
        tested.codeSummator.accept(0);
        tested.codeMultiplicator.accept(4);

        // then
        assertEquals(expectedCodeStream, tested.getCodeStream());
    }

}
