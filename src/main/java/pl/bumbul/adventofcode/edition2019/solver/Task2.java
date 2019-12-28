package pl.bumbul.adventofcode.edition2019.solver;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import pl.bumbul.adventofcode.edition2019.ResourceLoader;

@Component
@Log4j2
public class Task2 implements Task {

    private final ResourceLoader resourceLoader;

    public Task2(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void execute() {
        resourceLoader.load("task2.txt");
        log.info("Task 2 stage 1 solution: {}","");
    }


}
