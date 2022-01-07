package pl.bumbul.adventofcode.edition2021.solver;

import lombok.extern.log4j.Log4j2;
import pl.bumbul.adventofcode.commons.AdventDay;
import pl.bumbul.adventofcode.commons.ResourceLoader;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class Day01SonarSweep implements AdventDay {

    private final List<Integer> depthSamples;

    public Day01SonarSweep(String taskInput){
        depthSamples = ResourceLoader.loadFileWithOneEntryPerRow(taskInput, ResourceLoader.extractData)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    @Override
    public void solve() {
        log.info("--- Day 1: Sonar Sweep ---");
        log.info("Stage 1 solution: {}", countIncreases());
        log.info("Stage 2 solution: {}", countIncreasesFromThreeMeasurmentSlidingWindow());
    }

    Integer countIncreasesFromThreeMeasurmentSlidingWindow() {
        Integer result = 0;
        for (int i = 0; i < depthSamples.size()-3; i++){
            if (depthSamples.get(i+3) - depthSamples.get(i) > 0) {result++;}
        }
        return result;
    }


    Integer countIncreases() {
        Integer count = 0;
        for (int i = 1; i < depthSamples.size(); i++){
            if (depthSamples.get(i) - depthSamples.get(i-1) > 0){ count++; }
        }
        return count;
    }
}
