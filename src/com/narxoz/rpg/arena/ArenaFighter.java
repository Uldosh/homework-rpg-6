package com.narxoz.rpg.arena;

public class ArenaFighter {
    private final String name;
    private int maxHp;
    private int currentHp;
    private int attackPower;
    private int armorValue;
    private int blockRating;
    private double dodgeChance;
    private int potions;

    public ArenaFighter(String name, int maxHp, int attackPower,
                        int armorValue, int blockRating,
                        double dodgeChance, int potions) {
        this.name        = name;
        this.maxHp       = maxHp;
        this.currentHp   = maxHp;
        this.attackPower = attackPower;
        this.armorValue  = armorValue;
        this.blockRating = blockRating;
        this.dodgeChance = dodgeChance;
        this.potions     = potions;
    }
    public void takeDamage(int damage) {
        currentHp = Math.max(0, currentHp - damage);
    }
    public int heal(int amount) {
        if (potions <= 0) return 0;
        int before = currentHp;
        currentHp = Math.min(maxHp, currentHp + amount);
        potions--;
        return currentHp - before;
    }
    public void boostDodge(double boost) {
        dodgeChance = Math.min(1.0, dodgeChance + boost);
    }
    public boolean isAlive() { return currentHp > 0; }

    public String getName() { return name; }
    public int    getCurrentHp()  { return currentHp; }
    public int    getMaxHp()      { return maxHp; }
    public double getDodgeChance() { return dodgeChance; }
    public int getBlockRating() { return blockRating; }
    public int getArmorValue() { return armorValue; }
    public int getAttackPower() { return attackPower; }
    public int getPotions() { return potions; }

    @Override
    public String toString() {
        return String.format("%s [HP %d/%d | ATK %d | ARM %d | BLK %d%% | DODGE %.0f%% | Potions %d]",
                name, currentHp, maxHp, attackPower,
                armorValue, blockRating, dodgeChance * 100, potions);
    }
}
