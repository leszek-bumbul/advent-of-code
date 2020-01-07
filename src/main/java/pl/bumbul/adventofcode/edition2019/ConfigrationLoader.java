package pl.bumbul.adventofcode.edition2019;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties
public class ConfigrationLoader {

    private List<String> tasks;
}
