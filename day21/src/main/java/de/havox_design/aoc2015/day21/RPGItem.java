package de.havox_design.aoc2015.day21;

public class RPGItem {
    private String name;
    private int cost;
    private int damage;
    private int armor;

    public RPGItem(String name, int cost, int damage, int armor) {
        this.cost = cost;
        this.damage = damage;
        this.armor = armor;
    }

    public int getCost() {
        return cost;
    }

    public int getDamage() {
        return damage;
    }

    public int getArmor() {
        return armor;
    }

    public String getName() {
        return name;
    }
}
