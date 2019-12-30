package pl.bumbul.adventofcode.edition2019.solver;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import pl.bumbul.adventofcode.edition2019.ResourceLoader;
import pl.bumbul.adventofcode.edition2019.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.ObjIntConsumer;

@Component
@Log4j2
public class Day02The1202ProgramAlarm implements Task {

    private static final Integer OPCODE_STOP_PROGRAM = 99;
    private static final int NOUN = 1;
    private static final int VERB = 2;
    private static final int PROGRAM_RESULT_ADDRESS = 0;
    private Map<Integer, ObjIntConsumer<ArrayList<Integer>>> instructions;
    private List<Integer> memory;
    private ResourceLoader resourceLoader;

    public Day02The1202ProgramAlarm(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        setUpInstructions();
    }

    @Override
    public void execute() {
        initMemory();
        log.info("--- Day 2: 1202 Program Alarm ---");
        log.info("Stage 1 solution: {}", runIntcodeProgram(12, 2).get(PROGRAM_RESULT_ADDRESS));
        log.info("Stage 2 solution: {}", findNounAndVerb(19690720));
    }

    void initMemory() {
        memory = resourceLoader.loadFileWithEntriesSeparatedByPeriod("Day02The1202ProgramAlarm.input");
    }

    private Integer findNounAndVerb(Integer expected) {
        var matchingPairIsFound = false;
        int aNoun = 0;
        int aVerb = 0;
        for (int noun = 0; noun < 100 && !matchingPairIsFound; noun++) {
            for (int verb = 0; verb < 100 && !matchingPairIsFound; verb++) {
                if (expected.equals(runIntcodeProgram(noun, verb).get(0))) {
                    matchingPairIsFound = true;
                    aNoun = noun;
                    aVerb = verb;
                }

            }
        }
        return opcode(aNoun, aVerb);
    }

    List<Integer> runIntcodeProgram(int noun, int verb) {
        ArrayList<Integer> localMemory = new ArrayList<>(memory);
        localMemory.set(NOUN, noun);
        localMemory.set(VERB, verb);
        for (int index = 0; !OPCODE_STOP_PROGRAM.equals(localMemory.get(index)); index += 4) {
            instructions.get(localMemory.get(index)).accept(localMemory, index);
        }
        return localMemory;
    }

    private Integer opcode(int noun, int verb) {
        return 100 * noun + verb;
    }

    private void setUpInstructions() {
        ObjIntConsumer<ArrayList<Integer>> codeSummator = (instruction, instructionPointer) -> {
            var addressOfFirstElement = instruction.get(instructionPointer + 1);
            var addressOfSecondElement = instruction.get(instructionPointer + 2);
            var addressOfResult = instruction.get(instructionPointer + 3);
            instruction.set(addressOfResult, instruction.get(addressOfFirstElement) + instruction.get(addressOfSecondElement));
        };
        ObjIntConsumer<ArrayList<Integer>> codeMultiplicator = (instruction, instructionPointer) -> {
            var addressFirstElement = instruction.get(instructionPointer + 1);
            var addressSecondElement = instruction.get(instructionPointer + 2);
            var addressResult = instruction.get(instructionPointer + 3);
            instruction.set(addressResult, instruction.get(addressFirstElement) * instruction.get(addressSecondElement));
        };
        instructions = new HashMap<>(Map.of(1, codeSummator, 2, codeMultiplicator));
    }
}
