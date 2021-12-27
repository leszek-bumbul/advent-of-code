package pl.bumbul.adventofcode.edition2019;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import pl.bumbul.adventofcode.commons.AdventDay;
import pl.bumbul.adventofcode.commons.EditionRunner;
import pl.bumbul.adventofcode.edition2019.solver.Day01TheTyrannyOfTheRocketEquation;
import pl.bumbul.adventofcode.edition2019.solver.Day02The1202ProgramAlarm;
import pl.bumbul.adventofcode.edition2019.solver.Day03CrossedWires;
import pl.bumbul.adventofcode.edition2019.solver.Day04SecureContainer;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@Log4j2
@NoArgsConstructor
public class Edition2019Runner implements EditionRunner {

    private final List<AdventDay> tasks = List.of(
            new Day01TheTyrannyOfTheRocketEquation("edition/2019/Day01TheTyrannyOfTheRocketEquation.input"),
            new Day02The1202ProgramAlarm("edition/2019/Day02The1202ProgramAlarm.input"),
            new Day03CrossedWires("edition/2019/Day03CrossedWires.input"),
            new Day04SecureContainer("edition/2019/Day04SecureContainer.input")
    );

    @Override
    public void run() {
        log.info("Advent of code - edition 2019");
        tasks.forEach(AdventDay::solve);
    }
}
