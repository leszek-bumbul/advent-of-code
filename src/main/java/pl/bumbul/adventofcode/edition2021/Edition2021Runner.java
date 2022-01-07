package pl.bumbul.adventofcode.edition2021;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import pl.bumbul.adventofcode.commons.AdventDay;
import pl.bumbul.adventofcode.commons.EditionRunner;
import pl.bumbul.adventofcode.edition2021.solver.Day01SonarSweep;
import pl.bumbul.adventofcode.edition2021.solver.Day02Dive;

import java.util.List;

@Component
@NoArgsConstructor
@Log4j2
public class Edition2021Runner implements EditionRunner {

    private final List<AdventDay> tasks = List.of(
            new Day01SonarSweep("edition/2021/Day01SonarSweep.input"),
            new Day02Dive("edition/2021/Day02Dive.input")
    );


    @Override
    public void run() {
        log.info("Advent of code - edition 2021");
        tasks.forEach(AdventDay::solve);
    }
}
