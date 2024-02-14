package de.havox_design.aoc.utils.java.model.data.partitions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlankLinePartitioner implements Partitioner<String> {
    @Override
    public List<List<String>> partition(List<String> lines) {
        List<List<String>> partitions = new ArrayList<>();
        List<String> currentPartition = new ArrayList<>();
        boolean lastLineBlank = false;

        for (String line : lines) {
            boolean isBlank = line.isBlank();
            if (isBlank) {
                if (!lastLineBlank) {
                    partitions.add(Collections.unmodifiableList(currentPartition));
                }
            } else {
                if (lastLineBlank) {
                    currentPartition = new ArrayList<>();
                }
                currentPartition.add(line);
            }
            lastLineBlank = isBlank;
        }

        if (!lastLineBlank) {
            partitions.add(Collections.unmodifiableList(currentPartition));
        }

        return Collections.unmodifiableList(partitions);
    }
}
