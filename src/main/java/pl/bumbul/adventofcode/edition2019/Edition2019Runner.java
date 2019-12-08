package pl.bumbul.adventofcode.edition2019;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import pl.bumbul.adventofcode.edition2019.task1.Task1Solver;

import javax.annotation.PostConstruct;

@Component
@Log4j2
public class Edition2019Runner {

    private Task1Solver task1Solver;

    public Edition2019Runner(Task1Solver task1Solver) {
        this.task1Solver = task1Solver;
    }

    @PostConstruct
    public void init() {
        log.info("Advent of code - edition 2019");
        log.info("Task 1 solution: {}", task1Solver.solve());
    }
}
