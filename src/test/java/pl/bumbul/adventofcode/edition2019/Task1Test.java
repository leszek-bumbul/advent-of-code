package pl.bumbul.adventofcode.edition2019;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.bumbul.adventofcode.edition2019.task1.Task1Solver;

import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class Task1Test {

    @Mock
    private ResourceLoader resourceLoader;

    @InjectMocks
    private Task1Solver task1Solver;

    @Test
    public void testFuelCounterUpper() {
        given(resourceLoader.getPath()).willReturn(Paths.get(ClassLoader.getSystemResource("task1test.txt").getPath()));
        var total = task1Solver.solve();
        assertEquals( (2 + 2 + 654 + 33583), total.longValue());
    }
}
