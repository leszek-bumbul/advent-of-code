package pl.bumbul.adventofcode.edition2019;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@Log4j2
public class Edition2019Runner {

    private List<Task> taskList;

    public Edition2019Runner(Task day01TheTyrannyOfTheRocketEquation, Task day02The1202ProgramAlarm, Task day03CrossedWires) {
        this.taskList = List.of(
                day01TheTyrannyOfTheRocketEquation,
                day02The1202ProgramAlarm,
                day03CrossedWires);
    }

    @PostConstruct
    public void init() {
        log.info("Advent of code - edition 2019");
        taskList.forEach(Task::execute);
    }
}
