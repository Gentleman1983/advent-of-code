package de.havox_design.aoc2017.day21;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rule {
    private static final Pattern ELEMENT_DELIMITER = Pattern.compile("/");
    private static final Pattern RULE_PARSER = Pattern.compile("(?<input>[.#/]+)\\s+=>\\s+(?<output>[.#/]+)");

    private final List<Area> permutations;
    private final Supplier<Area> output;

    Rule(Area input, Supplier<Area> output) {
        this.permutations = Arrays
                .asList(
                        input,
                        input.flipHorizontal(),
                        input.flipVertical(),
                        input.flipHorizontal().flipVertical(),
                        input.rotate(),
                        input.rotate().flipHorizontal().flipVertical()
                );
        this.output = output;
    }

    Optional<Area> getConvertedOutput(Area input) {
        return permutations.contains(input) ? Optional.of(output.get()) : Optional.empty();
    }

    static Rule from(String rule) {
        Matcher matcher = RULE_PARSER.matcher(rule);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Expected rule but got: " + rule);
        }

        return new Rule(convert(matcher.group("input")), () -> convert(matcher.group("output")));
    }

    private static Area convert(String area) {
        return new Area(
                ELEMENT_DELIMITER
                        .splitAsStream(area)
                        .toList()
        );
    }
}
