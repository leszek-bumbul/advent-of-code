package pl.bumbul.adventofcode.edition2019.solver;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import pl.bumbul.adventofcode.edition2019.ResourceLoader;
import pl.bumbul.adventofcode.edition2019.Task;

import java.util.List;
import java.util.Objects;
import java.util.function.LongPredicate;
import java.util.function.LongSupplier;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Component
@Log4j2
public class Day04SecureContainer implements Task {

    private LongStream range;

    @Override
    public void execute() {
        initiateRange();
        log.info("--- Day 4: Secure Container ---");
        log.info("Stage 1 solution: {}", numberOfPasswords.getAsLong());
        log.info("Stage 2 solution: {}", "");
    }

    void initiateRange() {
        Long[] resource = ResourceLoader.loadFileWithOneEntryPerRow("Day04SecureContainer.input").toArray(Long[]::new);
        range = LongStream.rangeClosed(resource[0], resource[1]);
    }

    Predicate<List<Integer>> increasingDigits = numbers -> {
        for (int i = 0; i < numbers.size() - 1; i++) {
            if (numbers.get(i) > numbers.get(i + 1)) {
                return false;
            }
        }
        return true;
    };

    Predicate<List<Integer>> doubleFound = numbers -> {
        for (int i = 0; i < numbers.size() - 1; i++) {
            if (Objects.equals(numbers.get(i), numbers.get(i + 1))) {
                return true;
            }
        }
        return false;
    };

    LongPredicate verifyNumber = number -> {
        List<Integer> list = String.valueOf(number).chars().boxed().collect(Collectors.toList());
        return doubleFound.test(list) && increasingDigits.test(list);
    };

    private LongSupplier numberOfPasswords = () -> range.filter(verifyNumber).count();

}
