package pl.bumbul.adventofcode.edition2019.solver;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import pl.bumbul.adventofcode.edition2019.ResourceLoader;
import pl.bumbul.adventofcode.edition2019.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Component
@Log4j2
public class Task2 implements Task {

    private static final Integer CODE_STREAM_STOP = 99;
    private final ResourceLoader resourceLoader;
    private Map<Integer, Consumer<Integer>> codeProcessors;

    @Getter
    private List<Integer> codeStream;

    public Task2(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        this.codeProcessors = new HashMap<>(Map.of(1, this.codeSummator, 2, this.codeMultiplicator));
    }

    @Override
    public void execute() {
        resourceLoader.load("task2.txt");
        log.info("--- Day 2: 1202 Program Alarm ---");
        log.info("Task 2 stage 1 solution: {}", solve());
    }

    private Integer solve() {
        readCodeSource();
        inputDebugValues();
        int index = 0;
        while (!CODE_STREAM_STOP.equals(codeStream.get(index))) {
            codeProcessors.get(codeStream.get(index)).accept(index);
            index += 4;
        }
        return codeStream.get(0);
    }

    private void inputDebugValues() {
        codeStream.set(1, 12);
        codeStream.set(2, 2);
    }

    private void readCodeSource() {
        try {
            codeStream =
                    Arrays.stream(String.join(",", Files.readAllLines(resourceLoader.getPath())).split(",", 0))
                            .map(Integer::parseInt)
                            .collect(Collectors.toList());

        } catch (IOException e) {
            log.error("File not found", e);
        }
    }

    Consumer<Integer> codeSummator = index -> {
        var indexFirstElement = codeStream.get(index + 1);
        var indexSecondElement = codeStream.get(index + 2);
        var indexResult = codeStream.get(index + 3);
        codeStream.set(indexResult, codeStream.get(indexFirstElement) + codeStream.get(indexSecondElement));
    };

    Consumer<Integer> codeMultiplicator = index -> {
        var indexFirstElement = codeStream.get(index + 1);
        var indexSecondElement = codeStream.get(index + 2);
        var indexResult = codeStream.get(index + 3);
        codeStream.set(indexResult, codeStream.get(indexFirstElement) * codeStream.get(indexSecondElement));
    };


    void setTestCodeStream(List<Integer> testCodeStream) {
        codeStream = new ArrayList<>(testCodeStream);
    }

    void resetTestCodeStream() {
        codeStream = new ArrayList<>();
    }
}
