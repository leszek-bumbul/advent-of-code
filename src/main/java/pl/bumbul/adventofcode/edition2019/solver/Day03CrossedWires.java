package pl.bumbul.adventofcode.edition2019.solver;

import lombok.Data;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import pl.bumbul.adventofcode.edition2019.ResourceLoader;
import pl.bumbul.adventofcode.edition2019.Task;

import java.util.*;
import java.util.function.Consumer;

@Component
@Log4j2
public class Day03CrossedWires implements Task {

    private static final int FIRST_PATH = 1;
    private static final int SECOND_PATH = 2;
    private ResourceLoader resourceLoader;

    HashMap<String, Consumer<List<Point>>> consumers;

    @Getter
    Map<Integer, List<String>> paths;

    public Day03CrossedWires(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void execute() {
        initPaths();
        log.info("--- Day 3: Crossed Wires ---");
        log.info("Stage 1 solution: {}", findMinimalDistance());
    }

    void initPaths() {
        paths = resourceLoader.loadFileWithInstructionsInEachRow("Day03CrossedWires.input");
    }

    public Integer findMinimalDistance() {
        Point currentPoint = new Point(0, 0, false);
        List<Point> pathPoints = new ArrayList<>();
        for (String direction : paths.get(FIRST_PATH)) {
            var length = direction.substring(1);
            switch (direction.charAt(0)) {
                case 'U':
                    for (int i = 0; i < Integer.parseInt(length); i++) {
                        currentPoint = new Point(currentPoint.getX(), currentPoint.getY() + 1, false);
                        pathPoints.add(currentPoint);
                    }
                    break;
                case 'D':
                    for (int i = 0; i < Integer.parseInt(length); i++) {
                        currentPoint = new Point(currentPoint.getX(), currentPoint.getY() -1, false);
                        pathPoints.add(currentPoint);
                    }
                    break;
                case 'R':
                    for (int i = 0; i < Integer.parseInt(length); i++) {
                        currentPoint = new Point(currentPoint.getX() +1, currentPoint.getY(), false);
                        pathPoints.add(currentPoint);
                    }
                    break;
                case 'L':
                    for (int i = 0; i < Integer.parseInt(length); i++) {
                        currentPoint = new Point(currentPoint.getX() -1, currentPoint.getY(), false);
                        pathPoints.add(currentPoint);
                    }
                    break;
                default:
                    break;
            }
        }
        currentPoint = new Point(0, 0, false);
        for(String direction : paths.get(SECOND_PATH)){
            var length = direction.substring(1);
            switch (direction.charAt(0)) {
                case 'U':
                    for (int i = 0; i < Integer.parseInt(length); i++) {
                        currentPoint = new Point(currentPoint.getX(), currentPoint.getY() + 1, false);
                        if(pathPoints.contains(currentPoint)){
                            pathPoints.get(pathPoints.indexOf(currentPoint)).setCrossed(true);
                        }
                    }
                    break;
                case 'D':
                    for (int i = 0; i < Integer.parseInt(length); i++) {
                        currentPoint = new Point(currentPoint.getX(), currentPoint.getY() -1, false);
                        if(pathPoints.contains(currentPoint)){
                            pathPoints.get(pathPoints.indexOf(currentPoint)).setCrossed(true);
                        }                    }
                    break;
                case 'R':
                    for (int i = 0; i < Integer.parseInt(length); i++) {
                        currentPoint = new Point(currentPoint.getX() +1, currentPoint.getY(), false);
                        if(pathPoints.contains(currentPoint)){
                            pathPoints.get(pathPoints.indexOf(currentPoint)).setCrossed(true);
                        }                    }
                    break;
                case 'L':
                    for (int i = 0; i < Integer.parseInt(length); i++) {
                        currentPoint = new Point(currentPoint.getX() -1, currentPoint.getY(), false);
                        if(pathPoints.contains(currentPoint)){
                            pathPoints.get(pathPoints.indexOf(currentPoint)).setCrossed(true);
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        return pathPoints.stream()
                .filter(Point::isCrossed)
                .mapToInt(point -> Math.abs(point.getX())+Math.abs(point.getY()))
                .min()
                .orElseThrow();
    }


    @Data
    private static class Point {
        private int x;
        private int y;
        private boolean crossed;

        Point(int aX, int aY, boolean isCrossed) {
            this.x = aX;
            this.y = aY;
            this.crossed = isCrossed;
        }
    }
}
