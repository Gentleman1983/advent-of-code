package de.havox_design.aoc2018.day16;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import de.havox_design.aoc.utils.java.model.data.partitions.BlankLinePartitioner;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ChronalClassification implements AoCFunctionality {
    private final List<Sample> samples;
    private final List<NumberedInstruction> program;

    public ChronalClassification(String fileName) {
        BlankLinePartitioner partitioner = new BlankLinePartitioner();
        InstructionParser instructionParser = new InstructionParser();
        SamplesParser samplesParser = new SamplesParser();

        List<String> input = readData(fileName);
        List<List<String>> partitions = partitioner.partition(input);
        samples = partitions
                .stream()
                .limit(partitions.size() - 1L)
                .map(samplesParser::parse)
                .toList();
        program = partitions
                .getLast()
                .stream()
                .map(instructionParser::parse).toList();
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
        Map<Integer, Instruction> map = instructionsByOpcode(samples);
        Registers registers = new Registers(List.of(0, 0, 0, 0));

        for (NumberedInstruction instruction : program) {
            registers = map
                    .get(instruction.opcode())
                    .apply(instruction, registers);
        }

        return registers.getRegister(0);
    }

    private Set<Instruction> filterOptions(final Sample sample) {
        return Arrays.stream(Instructions.values())
                .filter(instruction -> instruction.apply(sample.instruction(), sample.before()).equals(sample.after()))
                .map(Instructions::getInstruction)
                .collect(Collectors.toSet());
    }

    private Map<Integer, Instruction> instructionsByOpcode(final List<Sample> samples) {
        Map<Integer, Set<Instruction>> map = new HashMap<>();
        IntStream
                .range(0, 16)
                .forEach(i -> map
                        .put(
                                i,
                                Arrays
                                        .stream(Instructions.values())
                                        .map(Instructions::getInstruction)
                                        .collect(Collectors.toSet())
                        )
                );

        samples
                .forEach(sample -> map
                        .compute(
                                sample.instruction().opcode(),
                                (opcode, instructions) -> filterOptions(sample)
                        )
                );

        Map<Integer, Instruction> result = new HashMap<>();

        while (!map.isEmpty()) {
            Map.Entry<Integer, Set<Instruction>> oneLeft = map
                    .entrySet()
                    .stream()
                    .filter(entry -> entry.getValue().size() == 1)
                    .findAny()
                    .orElseThrow();
            Instruction value = oneLeft
                    .getValue()
                    .stream()
                    .findAny()
                    .orElseThrow();

            map.remove(oneLeft.getKey());
            map.replaceAll(remove(value));
            result.put(oneLeft.getKey(), value);
        }

        return Collections.unmodifiableMap(result);
    }

    private BiFunction<Integer, Set<Instruction>, Set<Instruction>> remove(Instruction instruction) {
        return (integer, instructions) -> {
            Set<Instruction> set = new HashSet<>(instructions);

            set.remove(instruction);

            return Collections.unmodifiableSet(set);
        };
    }
}
