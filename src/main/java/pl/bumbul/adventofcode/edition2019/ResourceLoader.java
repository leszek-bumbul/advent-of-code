package pl.bumbul.adventofcode.edition2019;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Component
@Getter
public class ResourceLoader {

    private Path path;

    public void load(String fileName){
        Optional<URL> file = Optional.ofNullable(ClassLoader.getSystemResource(fileName));
        file.ifPresent(url -> path = Paths.get(url.getPath()));
    }

}
