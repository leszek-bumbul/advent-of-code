package pl.bumbul.adventofcode.edition2019;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;

@Component
@Log4j2
public class Edition2019Runner {

    private ConfigrationLoader configrationLoader;

    public Edition2019Runner(ConfigrationLoader configrationLoader) {
        this.configrationLoader = configrationLoader;
    }

    @PostConstruct
    public void init() {
        log.info("Advent of code - edition 2019");
        configrationLoader.getTasks().forEach(task ->
                {
                    try {
                        ((Task) Class.forName("pl.bumbul.adventofcode.edition2019.solver." + task)
                                .getDeclaredConstructor()
                                .newInstance()).execute();
                    } catch (InstantiationException e) {
                        log.error("Cannot be instantiated.", e);
                    } catch (IllegalAccessException e) {
                        log.error("Illegal access to the selected class", e);
                    } catch (InvocationTargetException e) {
                        log.error("Error! invocation target ", e);
                    } catch (NoSuchMethodException e) {
                        log.error("Method cannot be found", e);
                    } catch (ClassNotFoundException e) {
                        log.error("Class not found", e);
                    }
                }
        );
    }
}
