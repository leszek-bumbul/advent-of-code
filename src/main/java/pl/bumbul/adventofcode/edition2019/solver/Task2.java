package pl.bumbul.adventofcode.edition2019.solver;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import pl.bumbul.adventofcode.edition2019.ResourceLoader;
import pl.bumbul.adventofcode.edition2019.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

@Component
@Log4j2
public class Task2 implements Task {

    private static final Integer OPCODE_STOP_PROGRAM = 99;
    private static final int NOUN = 1;
    private static final int VERB = 2;
    private Map<Integer, BiConsumer<List<Integer>, Integer>> instructions;

    @Getter
    private List<Integer> memory;
    private ResourceLoader resourceLoader;

    public Task2(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        setUpInstructions();
    }

    @Override
    public void execute() {
        initMemory();
        log.info("--- Day 2: 1202 Program Alarm ---");
        log.info("Stage 1 solution: {}", runIntcodeProgram(12, 2));
        log.info("Stage 2 solution: {}", findNounAndVerb(19690720));
    }

    void initMemory() {
        memory = resourceLoader.loadFileWithEntriesSeparatedByPeriod("task2.txt");
    }

    private Integer findNounAndVerb(Integer expected) {
        for (int noun = 0; noun < 100; noun++) {
            for (int verb = 0; verb < 100; verb++) {
                if (expected.equals(runIntcodeProgram(noun, verb))) {
                    return opcode(noun, verb);
                }
            }
        }
        return -1;
    }

    Integer runIntcodeProgram(int noun, int verb) {
        List<Integer> localMemory = new ArrayList<>(memory);
        localMemory.set(NOUN, noun);
        localMemory.set(VERB, verb);
        for (int index = 0; !OPCODE_STOP_PROGRAM.equals(localMemory.get(index)) ; index+=4) {
            instructions.get(localMemory.get(index)).accept(localMemory, index);
        }
        return localMemory.get(0);
    }

    private Integer opcode(int noun, int verb) {
        return 100 * noun + verb;
    }

    private void setUpInstructions() {
        BiConsumer<List<Integer>, Integer> codeSummator = (memory, instructionPointer) -> {
            var addressOfFirstElement = memory.get(instructionPointer + 1);
            var addressOfSecondElement = memory.get(instructionPointer + 2);
            var addressOfResult = memory.get(instructionPointer + 3);
            memory.set(addressOfResult, memory.get(addressOfFirstElement) + memory.get(addressOfSecondElement));
        };
        BiConsumer<List<Integer>, Integer> codeMultiplicator = (memory, instructionPointer) -> {
            var indexFirstElement = memory.get(instructionPointer + 1);
            var indexSecondElement = memory.get(instructionPointer + 2);
            var indexResult = memory.get(instructionPointer + 3);
            memory.set(indexResult, memory.get(indexFirstElement) * memory.get(indexSecondElement));
        };
        instructions = new HashMap<>(Map.of(1, codeSummator, 2, codeMultiplicator));
    }
}
