package de.havox_design.aoc2016.day07;

import de.havox_design.aoc.utils.java.input.DataReader;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day07 {
    private final List<String> input;

    public Day07(String fileName) {
        input = readData(fileName);
    }

    public static long solvePart1(String fileName) {
        Day07 instance = new Day07(fileName);
        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        Day07 instance = new Day07(fileName);
        return instance.solvePart2();
    }

    public long solvePart1() {
        return calculateTlsIps();
    }

    public long solvePart2() {
        return calculateSslIps();
    }

    private Predicate<Address> getTlsPredicate() {
        return address ->
                address.supernetStream().anyMatch(isAbba()) &&
                        address.hypernetStream().noneMatch(isAbba());
    }

    private Predicate<Address> getSslPredicate() {
        return address -> {
            var reversedPatterns = address
                    .supernetStream()
                    .flatMap(filterAba())
                    .map(v -> v.substring(1) + v.charAt(1))
                    .collect(Collectors.toUnmodifiableSet());
            return address
                    .hypernetStream()
                    .flatMap(filterAba())
                    .anyMatch(reversedPatterns::contains);
        };
    }

    private long calculateTlsIps() {
        return calculateProtocolIps(getTlsPredicate());
    }

    private long calculateSslIps() {
        return calculateProtocolIps(getSslPredicate());
    }

    private long calculateProtocolIps(Predicate<Address> predicate) {
        return calculateProtocolIps(input, predicate);
    }

    private long calculateProtocolIps(List<String> input, Predicate<Address> predicate) {
        return input
                .stream()
                .filter(
                        value -> {
                            var supernet = new HashSet<String>();
                            var hypernet = new HashSet<String>();
                            var isHypernet = isHypernet(value);
                            for (String part : getSubAddresses(value)) {
                                (isHypernet ? hypernet : supernet).add(part);
                                isHypernet = !isHypernet;
                            }
                            return predicate
                                    .test(new Address(Set.copyOf(supernet), Set.copyOf(hypernet)));
                        })
                .count();
    }

    private Predicate<String> isAbba() {
        return value -> getDynamicScrollWindow(4, value)
                .anyMatch(v -> v[0] == v[3] && v[1] == v[2] && v[0] != v[1]);
    }

    private boolean isHypernet(String value) {
        return value
                .startsWith("[");
    }

    private Function<String, Stream<String>> filterAba() {
        return value -> getDynamicScrollWindow(3, value)
                .filter(v -> v[0] == v[2] && v[0] != v[1])
                .map(String::new);
    }

    private String[] getSubAddresses(String addressString) {
        return addressString
                .split("[^a-z]");
    }

    private Stream<char[]> getDynamicScrollWindow(int size, String value) {
        return IntStream
                .range(0, value.length() - size + 1)
                .mapToObj(i -> value.substring(i, i + size)
                        .toCharArray());
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
