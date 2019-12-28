package pl.bumbul.adventofcode.edition2019;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import pl.bumbul.adventofcode.edition2019.solver.Task1;

import javax.annotation.PostConstruct;

@Component
@Log4j2
public class Edition2019Runner {

    private Task1 task1;

    public Edition2019Runner(Task1 task1) {
        this.task1 = task1;
    }

    @PostConstruct
    public void init() {
        log.info("Advent of code - edition 2019");
        task1.execute();
    }
}
