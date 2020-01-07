package pl.bumbul.adventofcode.edition2019.solver;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import pl.bumbul.adventofcode.edition2019.ResourceLoader;
import pl.bumbul.adventofcode.edition2019.Task;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;

@Log4j2
public class Day03CrossedWires implements Task {

    private static final int FIRST_WIRE = 1;
    private static final int SECOND_WIRE = 2;
    private static final Point STARTING_POINT = new Point(0, 0);
    private static final String HORIZONTAL = "horizontal";
    private static final String VERTICAL = "vertical";

    private final Map<Character, BiFunction<Point, List<Integer>, Point>> endingPointConstructors = Map.of(
            'U', Point::upperPoint,
            'D', Point::lowerPoint,
            'R', Point::rightPoint,
            'L', Point::leftPoint
    );

    private Map<Integer, List<Section>> wires = new HashMap<>();
    private List<Point> crossingPoints = new LinkedList<>();


    final IntSupplier getMinimalManhattanDistance = () -> crossingPoints.stream()
            .mapToInt(point -> Math.abs(point.getX()) + Math.abs(point.getY()))
            .min()
            .orElseThrow();

    final IntSupplier getMinimalStepsToNearestIntersection = () -> crossingPoints.stream()
            .mapToInt(Point::getSteps)
            .min()
            .orElseThrow();

    @Override
    public void execute() {
        init("Day03CrossedWires.input");
        log.info("--- Day 3: Crossed Wires ---");
        log.info("Stage 1 solution: {}", getMinimalManhattanDistance.getAsInt());
        log.info("Stage 2 solution: {}", getMinimalStepsToNearestIntersection.getAsInt());
    }

    void init(String fileName) {
        Map<Integer, List<String>> wiresInput = ResourceLoader.loadFileWithInstructionsInEachRow(fileName);
        wiresInput.forEach(this::convertWireDirectionsToSections);
        findCrossingPoints();
    }

    private void findCrossingPoints() {
        var firstWireHorizontals = wires.get(FIRST_WIRE).stream().filter(section -> HORIZONTAL.equals(section.getType())).collect(Collectors.toList());
        var firstWireVerticals = wires.get(FIRST_WIRE).stream().filter(section -> VERTICAL.equals(section.getType())).collect(Collectors.toList());
        var secondWireHorizontals = wires.get(SECOND_WIRE).stream().filter(section -> HORIZONTAL.equals(section.getType())).collect(Collectors.toList());
        var secondWireVerticals = wires.get(SECOND_WIRE).stream().filter(section -> VERTICAL.equals(section.getType())).collect(Collectors.toList());
        compareHorizontalVsVerticalSections(firstWireHorizontals, secondWireVerticals);
        compareHorizontalVsVerticalSections(secondWireHorizontals, firstWireVerticals);
    }

    private void convertWireDirectionsToSections(int wireNumber, List<String> wireDirections) {
        int length;
        int steps = 0;
        Point firstEndingOfASection = STARTING_POINT;
        BiFunction<Point, List<Integer>, Point> secondEndingOfASection;
        List<Section> wireSections = new LinkedList<>();

        for (String wireDirection : wireDirections) {
            length = Integer.parseInt(wireDirection.substring(1));
            steps += length;
            secondEndingOfASection = endingPointConstructors.get(wireDirection.charAt(0));
            Section section = new Section(firstEndingOfASection, secondEndingOfASection.apply(firstEndingOfASection, List.of(length, steps)));
            wireSections.add(section);
            firstEndingOfASection = section.getEndings().get(1);
        }
        wires.put(wireNumber, wireSections);
    }

    private void compareHorizontalVsVerticalSections(List<Section> horizontals, List<Section> verticals) {
        for (var horizontal : horizontals) {
            for (var vertical : verticals) {
                provideCrossingPoint(horizontal, vertical).ifPresent(crossingPoints::add);
            }
        }
    }

    private Optional<Point> provideCrossingPoint(Section horizontalSection, Section verticalSection) {
        List<Point> horizontalSectionEndings = horizontalSection.getEndings();
        List<Point> verticalSectionEndings = verticalSection.getEndings();
        return (verticalSectionEndings.get(0).getX() > Math.min(horizontalSectionEndings.get(0).getX(), horizontalSectionEndings.get(1).getX()) &&
                verticalSectionEndings.get(0).getX() < Math.max(horizontalSectionEndings.get(0).getX(), horizontalSectionEndings.get(1).getX()) &&
                horizontalSectionEndings.get(0).getY() > Math.min(verticalSectionEndings.get(0).getY(), verticalSectionEndings.get(1).getY()) &&
                horizontalSectionEndings.get(0).getY() < Math.max(verticalSectionEndings.get(0).getY(), verticalSectionEndings.get(1).getY()))
                ? Optional.of(new Point(verticalSectionEndings.get(0).getX(), horizontalSectionEndings.get(0).getY(),
                horizontalSectionEndings.get(0).getSteps() + Math.abs(verticalSectionEndings.get(0).getX() - horizontalSectionEndings.get(0).getX())
                        + verticalSectionEndings.get(0).getSteps() + Math.abs(horizontalSectionEndings.get(0).getY() - verticalSectionEndings.get(0).getY())))
                : Optional.empty();
    }

    @Data
    private static class Section {
        private final List<Point> endings;
        private final String type;

        Section(Point firstEnding, Point secondEnding) {
            endings = List.of(firstEnding, secondEnding);
            type = (Objects.equals(firstEnding.getX(), secondEnding.getX()) ? VERTICAL : HORIZONTAL);
        }
    }

    @Data
    private static class Point {
        private int x;
        private int y;
        private int steps;

        Point(int aX, int aY) {
            this.x = aX;
            this.y = aY;
            this.steps = 0;
        }

        Point(int aX, int aY, int aSteps) {
            this.x = aX;
            this.y = aY;
            this.steps = aSteps;
        }

        Point rightPoint(List<Integer> params) {
            return new Point(x + params.get(0), y, params.get(1));
        }

        Point leftPoint(List<Integer> params) {
            return new Point(x - params.get(0), y, params.get(1));
        }

        Point upperPoint(List<Integer> params) {
            return new Point(x, y + params.get(0), params.get(1));
        }

        Point lowerPoint(List<Integer> params) {
            return new Point(x, y - params.get(0), params.get(1));
        }
    }
}
