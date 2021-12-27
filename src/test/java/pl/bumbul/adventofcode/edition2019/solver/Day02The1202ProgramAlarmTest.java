package pl.bumbul.adventofcode.edition2019.solver;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class Day02The1202ProgramAlarmTest {

    private Day02The1202ProgramAlarm tested;

    @Test
    void testAddition() {
        // given
        var expectedCodeStream = List.of(2, 0, 0, 0, 99);
        tested = new Day02The1202ProgramAlarm("Day02The1202ProgramAlarmTest1.input");

        // when
        var actualMemory = tested.runIntcodeProgram(0, 0);

        // then
        Assertions.assertEquals(expectedCodeStream, actualMemory);
    }

    @Test
    void testMultiplication() {
        // given
        var expectedCodeStream = List.of(2, 3, 0, 6, 99);
        tested = new Day02The1202ProgramAlarm("Day02The1202ProgramAlarmTest2.input");

        // when
        var actualMemory = tested.runIntcodeProgram(3, 0);

        // then
        Assertions.assertEquals(expectedCodeStream, actualMemory);
    }

    @Test
    void testAdditionWithMultiplication() {
        // given
        var expectedCodeStream = List.of(30, 1, 1, 4, 2, 5, 6, 0, 99);
        tested = new Day02The1202ProgramAlarm("Day02The1202ProgramAlarmTest3.input");

        // when
        var actualMemory = tested.runIntcodeProgram(1, 1);

        // then
        Assertions.assertEquals(expectedCodeStream, actualMemory);
    }

}
