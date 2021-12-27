package pl.bumbul.adventofcode;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import pl.bumbul.adventofcode.commons.EditionRunner;
import pl.bumbul.adventofcode.edition2019.Edition2019Runner;
import pl.bumbul.adventofcode.edition2021.Edition2021Runner;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties
public class AdventofcodeApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AdventofcodeApplication.class, args);
    }

    @Override
    public void run(String... args) {
        List.of(
                new Edition2019Runner(),
                new Edition2021Runner()
        ).forEach(EditionRunner::run);
    }
}
