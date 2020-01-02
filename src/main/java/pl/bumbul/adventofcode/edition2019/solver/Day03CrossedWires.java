package pl.bumbul.adventofcode.edition2019.solver;

import lombok.Data;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import pl.bumbul.adventofcode.edition2019.ResourceLoader;
import pl.bumbul.adventofcode.edition2019.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.IntSupplier;
import java.util.function.UnaryOperator;

@Component
@Log4j2
public class Day03CrossedWires implements Task {

    private static final int FIRST_PATH = 1;
    private static final int SECOND_PATH = 2;
    private static final Point STARTING_POINT = new Point(0, 0);

    private final ResourceLoader resourceLoader;
    private final Map<Character, UnaryOperator<Point>> nextPointConstructors = Map.of(
            'U', Point::upperPoint,
            'D', Point::lowerPoint,
            'R', Point::rightPoint,
            'L', Point::leftPoint
    );
    private List<Point> pathPoints = new ArrayList<>();
    private final Consumer<Point> createPoint = point -> pathPoints.add(point);
    private final Consumer<Point> crossOutPoint = point -> {
        if (pathPoints.contains(point)) {
            pathPoints.get(pathPoints.indexOf(point)).setCrossed(true);
        }
    };
    private final IntSupplier getMinimalManhattanDistance = () -> pathPoints.stream()
            .filter(Point::isCrossed)
            .mapToInt(point -> Math.abs(point.getX()) + Math.abs(point.getY()))
            .min()
            .orElseThrow();

    @Getter
    Map<Integer, List<String>> paths;

    public Day03CrossedWires(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void execute() {
        initPaths();
        log.info("--- Day 3: Crossed Wires ---");
        log.info("Stage 1 solution: {}", findMinimalManhattanDistance());
    }

    void initPaths() {
        paths = resourceLoader.loadFileWithInstructionsInEachRow("Day03CrossedWires.input");
    }

    public Integer findMinimalManhattanDistance() {
        solutionStage(FIRST_PATH, createPoint);
        solutionStage(SECOND_PATH, crossOutPoint);
        return getMinimalManhattanDistance.getAsInt();
    }

    private void solutionStage(int pathNumber, Consumer<Point> pointConsumer) {
        Point currentPoint = STARTING_POINT;
        int length;
        UnaryOperator<Point> nextPoint;
        for (String direction : paths.get(pathNumber)) {
            length = Integer.parseInt(direction.substring(1));
            nextPoint = nextPointConstructors.get(direction.charAt(0));
            for (int i = 0; i < length; i++) {
                currentPoint = nextPoint.apply(currentPoint);
                pointConsumer.accept(currentPoint);
            }
        }
    }

    @Data
    private static class Point {
        private int x;
        private int y;
        private boolean crossed;

        Point(int aX, int aY) {
            this.x = aX;
            this.y = aY;
            this.crossed = false;
        }

        Point rightPoint() {
            return new Point(x + 1, y);
        }

        Point leftPoint() {
            return new Point(x - 1, y);
        }

        Point upperPoint() {
            return new Point(x, y + 1);
        }

        Point lowerPoint() {
            return new Point(x, y - 1);
        }
    }
}
