package pl.bumbul.adventofcode.edition2021.solver;

import lombok.*;
import lombok.extern.log4j.Log4j2;
import pl.bumbul.adventofcode.commons.AdventDay;
import pl.bumbul.adventofcode.commons.ResourceLoader;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class Day02Dive implements AdventDay {

    private final List<Command> commands;

    public Day02Dive(String taskInput) {
        commands = ResourceLoader.loadFileWithOneEntryPerRow(taskInput, ResourceLoader.extractData)
                .map(e -> {
                    var base = e.split(" ");
                    return new Command(Directive.valueOf(base[0].toUpperCase()), Integer.parseInt(base[1]));
                })
                .collect(Collectors.toList());
    }

    @Override
    public void solve() {
        log.info("--- Day 2: Dive ---");
        log.info("Stage 1 solution: {}", calculateMultiplicationOfFinalHorizontalAndFinalDepth());//1698735
        log.info("Stage 1 solution: {}", calculateMultiplicationOfFinalHorizontalAndFinalDepthWithAim());
    }

    public Integer calculateMultiplicationOfFinalHorizontalAndFinalDepth() {
        var horizontal = 0;
        var depth = 0;
        for (var command : commands) {
            switch (command.getDirective()) {
                case FORWARD: {
                    horizontal += command.getValue();
                    break;
                }
                case DOWN:{
                    depth += command.getValue();
                    break;
                }
                case UP:{
                    depth -= command.getValue();
                    break;
                }
                default:
                    throw new IllegalStateException("Unexpected value: " + command.getDirective());
            }
        }
        return horizontal * depth;
    }

    public Integer calculateMultiplicationOfFinalHorizontalAndFinalDepthWithAim() {
        var horizontal = 0;
        var depth = 0;
        var aim = 0;
        for (var command: commands){
            switch (command.getDirective()) {
                case FORWARD: {
                    horizontal += command.getValue();
                    depth += (aim * command.getValue());
                    break;
                }
                case DOWN:{
                    aim += command.getValue();
                    break;
                }
                case UP:{
                    aim -= command.getValue();
                    break;
                }
                default:
                    throw new IllegalStateException("Unexpected value: " + command.getDirective());
            }
        }
        return horizontal * depth;
    }

    @Getter
    @AllArgsConstructor
    private enum Directive {
        FORWARD("forward"), DOWN("down"), UP("up");
        private final String dir;

    }

    @Data
    @AllArgsConstructor
    private static class Command {
        private Directive directive;
        private Integer value;
    }

}
