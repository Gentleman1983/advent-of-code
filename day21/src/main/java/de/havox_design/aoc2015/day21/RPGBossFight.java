package de.havox_design.aoc2015.day21;

import de.havox_design.aoc2015.utils.DataReader;

import java.util.List;
import java.util.function.BiFunction;

public class RPGBossFight {
    private final List<String> input;
    private final RPGPlayer boss;
    private final List<RPGItem> weapons;
    private final List<RPGItem> armors;
    private final List<RPGItem> rings;

    public RPGBossFight(String fileName) {
        input = readData(fileName);
        boss = parseBoss();
        weapons = List.of(
                new RPGItem("Dagger", 8, 4, 0),
                new RPGItem("Shortsword", 10, 5, 0),
                new RPGItem("Warhammer", 25, 6, 0),
                new RPGItem("Longsword", 40, 7, 0),
                new RPGItem("Greataxe", 74, 8, 0)
        );
        armors = List.of(
                new RPGItem("Leater", 13, 0, 1),
                new RPGItem("Chainmail", 31, 0, 2),
                new RPGItem("Splintmail", 53, 0, 3),
                new RPGItem("Bandemail", 75, 0, 4),
                new RPGItem("Platemail", 102, 0, 5),
                new RPGItem("none", 0, 0, 0)
        );
        rings = List.of(
                new RPGItem("Damage +1", 25, 1, 0),
                new RPGItem("Damage +2", 50, 2, 0),
                new RPGItem("Damage +3", 100, 3, 0),
                new RPGItem("Defense +1", 20, 0, 1),
                new RPGItem("Defense +2", 40, 0, 2),
                new RPGItem("Defense +3", 80, 0, 3),
                new RPGItem("none", 0, 0, 0),
                new RPGItem("none", 0, 0, 0)
        );
    }

    public static int solvePart1(String fileName) {
        RPGBossFight instance = new RPGBossFight(fileName);
        return instance.solvePart1();
    }

    public static int solvePart2(String fileName) {
        RPGBossFight instance = new RPGBossFight(fileName);
        return instance.solvePart2();
    }

    public int solvePart1() {
        return simulate(Math::min, true, Integer.MAX_VALUE);
    }

    public int solvePart2() {
        return simulate(Math::max, false, Integer.MIN_VALUE);
    }

    @SuppressWarnings({"squid:S3776", "squid:S4276"})
    public int simulate(BiFunction<Integer, Integer, Integer> resultAggregator, boolean expectedToWin, int startCost){
        int resultCost = startCost;

        for (RPGItem weapon : weapons) {
            for (RPGItem armor : armors) {
                for (RPGItem ring1 : rings) {
                    for (RPGItem ring2 : rings) {
                        if (ring1 == ring2) {
                            continue;
                        }
                        int cost = weapon.getCost() + armor.getCost() + ring1.getCost() + ring2.getCost();
                        RPGPlayer player = new RPGPlayer(
                                100,
                                weapon.getDamage() + armor.getDamage() + ring1.getDamage() + ring2.getDamage(),
                                weapon.getArmor() + armor.getArmor() + ring1.getArmor() + ring2.getArmor()
                        );
                        if (expectedToWin == simulate(player)) {
                            resultCost = resultAggregator.apply(resultCost, cost);
                        }
                    }
                }
            }
        }
        return resultCost;
    }

    public boolean simulate(RPGPlayer player) {
        int bossHit = boss.hitPoints;
        int playherHit = player.hitPoints;
        while (true) {
            bossHit -= Math.max(1, player.damage - boss.armor);
            if (bossHit <= 0) {
                return true;
            }
            playherHit -= Math.max(1, boss.damage - player.armor);
            if (playherHit <= 0) {
                return false;
            }
        }
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }

    private RPGPlayer parseBoss() {
        int hitPoints = Integer.parseInt(input.get(0).split(": ")[1]);
        int damage = Integer.parseInt(input.get(1).split(": ")[1]);
        int amor = Integer.parseInt(input.get(2).split(": ")[1]);

        return new RPGPlayer(hitPoints, damage, amor);
    }
}
