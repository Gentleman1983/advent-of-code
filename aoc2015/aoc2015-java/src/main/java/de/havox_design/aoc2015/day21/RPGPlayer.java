package de.havox_design.aoc2015.day21;

public class RPGPlayer {
    final int hitPoints;
    final int damage;
    final int armor;

    public RPGPlayer(int hitPoints, int damage, int armor) {
        this.hitPoints = hitPoints;
        this.damage = damage;
        this.armor = armor;
    }

    public RPGPlayer(RPGPlayer origin) {
        this.hitPoints = origin.hitPoints;
        this.damage = origin.damage;
        this.armor = origin.armor;
    }
}
