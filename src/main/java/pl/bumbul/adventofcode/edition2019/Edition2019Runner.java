package pl.bumbul.adventofcode.edition2019;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Log4j2
public class Edition2019Runner {

    private Task task1;
    private Task task2;

    public Edition2019Runner(Task task1, Task task2) {
        this.task1 = task1;
        this.task2 = task2;
    }

    @PostConstruct
    public void init() {
        log.info("Advent of code - edition 2019");
        task1.execute();
        task2.execute();
    }
}
