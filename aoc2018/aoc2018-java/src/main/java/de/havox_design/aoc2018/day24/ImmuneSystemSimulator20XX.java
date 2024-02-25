package de.havox_design.aoc2018.day24;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import de.havox_design.aoc.utils.java.model.data.partitions.BlankLinePartitioner;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

public class ImmuneSystemSimulator20XX implements AoCFunctionality {
    private final List<Army> armies;

    public ImmuneSystemSimulator20XX(String fileName) {
        GroupParser groupParser = new GroupParser();
        ArmyParser armyParser = new ArmyParser(groupParser);
        BlankLinePartitioner partitioner = new BlankLinePartitioner();
        List<String> input = readData(fileName);
        List<List<String>> partitions = partitioner.partition(input);

        this.armies = partitions
                .stream()
                .map(armyParser::parse)
                .toList();
    }

    public static long processTask1(String fileName) {
        ImmuneSystemSimulator20XX instance = new ImmuneSystemSimulator20XX(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        ImmuneSystemSimulator20XX instance = new ImmuneSystemSimulator20XX(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        return calculateRemainingUnits(fight().orElseThrow());
    }

    public long processTask2() {
        return 0;
    }

    private long calculateRemainingUnits(final MutableArmy winner) {
        return winner
                .getGroups()
                .stream()
                .mapToLong(MutableGroup::getUnits)
                .sum();
    }

    private Optional<MutableArmy> fight() {
        List<MutableArmy> mutableArmies = mutableArmies();

        do {
            if (fight(mutableArmies) == 0) {
                return Optional.empty();
            }
        } while (
                mutableArmies
                        .stream()
                        .filter(army -> !army.getGroups().isEmpty())
                        .count() > 1
        );

        return Optional.of(
                mutableArmies
                        .stream()
                        .filter(army -> !army.getGroups().isEmpty())
                        .findFirst()
                        .orElseThrow()
        );
    }

    private List<MutableArmy> mutableArmies() {
        return armies
                .stream()
                .map(Army::asMutableArmy)
                .toList();
    }

    private static int fight(final List<MutableArmy> armies) {
        final var attacks = target(armies);
        return attack(armies, attacks);
    }

    private static List<Attack> target(final List<MutableArmy> armies) {
        final var attacks = new ArrayList<Attack>();
        final var targeted = new HashSet<MutableGroup>();

        for (final var army : armies) {
            final var otherArmies = otherArmies(armies, army);
            final var otherGroups = otherArmies.stream().flatMap(otherArmy -> otherArmy.getGroups().stream())
                    .filter(not(targeted::contains)).collect(Collectors.toCollection(ArrayList::new));
            army.getGroups().stream()
                    .sorted(Comparator.<MutableGroup>comparingInt(GroupAbilities::getEffectivePower)
                            .thenComparingInt(MutableGroup::getInitiative).reversed())
                    .forEachOrdered(group -> target(group, otherGroups).ifPresent(attack -> {
                        targeted.add(attack.defender());
                        otherGroups.remove(attack.defender());
                        attacks.add(attack);
                    }));
        }

        attacks.sort(Comparator.<Attack>comparingInt(attack -> attack.attacker().getInitiative()).reversed());

        return Collections.unmodifiableList(attacks);
    }

    private static Optional<Attack> target(final MutableGroup attacker, final List<MutableGroup> defenders) {
        return defenders.stream()
                .filter(defender -> !defender.getImmunities().contains(attacker.getDamageType()))
                .max(Comparator.<MutableGroup>comparingInt(defender -> computeDamage(attacker, defender))
                        .thenComparingInt(GroupAbilities::getEffectivePower)
                        .thenComparingInt(MutableGroup::getInitiative))
                .map(defender -> new Attack(attacker, defender));
    }

    private static int computeDamage(final MutableGroup attacker, final MutableGroup defender) {
        if (defender.getImmunities().contains(attacker.getDamageType())) {
            return 0;
        }
        if (defender.getWeaknesses().contains(attacker.getDamageType())) {
            return attacker.getEffectivePower() * 2;
        }

        return attacker.getEffectivePower();
    }

    private static List<MutableArmy> otherArmies(final List<MutableArmy> armies, final MutableArmy army) {
        final var ret = new ArrayList<>(armies);
        ret.removeIf(someArmy -> someArmy.equals(army));
        return Collections.unmodifiableList(ret);
    }

    private static int attack(final List<MutableArmy> armies, final List<Attack> attacks) {
        int killed = attacks.stream().filter(attack -> attack.attacker().getUnits() > 0)
                .mapToInt(attack -> damage(attack.defender(), computeDamage(attack.attacker(), attack.defender())))
                .sum();

        armies.forEach(army -> army.getGroups().removeIf(group -> group.getUnits() <= 0));
        return killed;
    }

    private static int damage(final MutableGroup group, final int damage) {
        int deaths = Math.min(damage / group.getHitPoints(), group.getUnits());
        group.reduceUnits(deaths);
        return deaths;
    }
}
