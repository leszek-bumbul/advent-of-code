package pl.bumbul.adventofcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class AdventofcodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdventofcodeApplication.class, args);
    }

}
