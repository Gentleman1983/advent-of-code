package de.havox_design.aoc2018.day16;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import de.havox_design.aoc.utils.java.model.data.partitions.BlankLinePartitioner;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ChronalClassification implements AoCFunctionality {
    private final List<Sample> samples;

    public ChronalClassification(String fileName) {
        BlankLinePartitioner partitioner = new BlankLinePartitioner();
        SamplesParser samplesParser = new SamplesParser();

        List<String> input = readData(fileName);
        Collection<List<String>> partitions = partitioner.partition(readData(fileName));
        samples = partitions
                .stream()
                .limit(partitions.size() - 1)
                .map(samplesParser::parse)
                .toList();
    }

    public static long processTask1(String fileName) {
        ChronalClassification instance = new ChronalClassification(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        ChronalClassification instance = new ChronalClassification(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        return samples.stream()
                .map(this::filterOptions)
                .map(Collection::size)
                .filter(count -> count >= 3L).count();
    }

    public long processTask2() {
        return 0;
    }

    private Set<Instruction> filterOptions(final Sample sample) {
        return Arrays.stream(Instructions.values())
                .filter(instruction -> instruction.apply(sample.instruction(), sample.before()).equals(sample.after()))
                .map(Instructions::getInstruction)
                .collect(Collectors.toSet());
    }
}
