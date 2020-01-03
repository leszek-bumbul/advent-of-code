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
import java.util.Objects;
import java.util.function.IntSupplier;
import java.util.function.ObjIntConsumer;
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
    private final ObjIntConsumer<Point> createPoint = (point, step) ->
    {
        point.setSteps(step);
        pathPoints.add(point);
    };
    private final ObjIntConsumer<Point> crossOutPoint = (point, step) -> {
        if (pathPoints.contains(point)) {
            Point existingPoint = pathPoints.get(pathPoints.indexOf(point));
            existingPoint.setCrossed(true);
            existingPoint.addSteps(step);
        }
    };

    final IntSupplier getMinimalManhattanDistance = () -> pathPoints.stream()
            .filter(Point::isCrossed)
            .mapToInt(point -> Math.abs(point.getX()) + Math.abs(point.getY()))
            .min()
            .orElseThrow();

    final IntSupplier getMinimalStepsToNearestIntersection = () -> pathPoints.stream()
            .filter(Point::isCrossed)
            .mapToInt(Point::getSteps)
            .min()
            .orElseThrow();

    @Getter
    Map<Integer, List<String>> paths;

    public Day03CrossedWires(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void execute() {
        loadResources();
        log.info("--- Day 3: Crossed Wires ---");
        initPathPoints();
        log.info("Stage 1 solution: {}", getMinimalManhattanDistance.getAsInt());
        log.info("Stage 2 solution: {}", getMinimalStepsToNearestIntersection.getAsInt());
    }

    void loadResources() {
        paths = resourceLoader.loadFileWithInstructionsInEachRow("Day03CrossedWires.input");
    }

    void initPathPoints() {
        handlePath(FIRST_PATH, createPoint);
        handlePath(SECOND_PATH, crossOutPoint);
    }

    private void handlePath(int pathNumber, ObjIntConsumer<Point> pointConsumer) {
        Point currentPoint = STARTING_POINT;
        int length;
        int step = 1;
        UnaryOperator<Point> nextPoint;
        for (String direction : paths.get(pathNumber)) {
            length = Integer.parseInt(direction.substring(1));
            nextPoint = nextPointConstructors.get(direction.charAt(0));
            for (int i = 0; i < length; i++, step++) {
                currentPoint = nextPoint.apply(currentPoint);
                pointConsumer.accept(currentPoint, step);
            }
        }
    }

    @Data
    private static class Point {
        private int x;
        private int y;
        private boolean crossed;
        private int steps;

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

        public void addSteps(int steps) {
            this.steps += steps;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
