package com.narxoz.rpg.arena;

public class ArenaOpponent {
    private final String name;
    private int currentHp;
    private final int maxHp;
    private final int attackPower;

    public ArenaOpponent(String name, int maxHp, int attackPower) {
        this.name = name;
        this.currentHp = maxHp;
        this.maxHp = maxHp;
        this.attackPower = attackPower;
    }

    public void takeDamage(int amount) { currentHp = Math.max(0, currentHp - damage); }

    public void restoreHp(int amount) {
        currentHp = Math.min(maxHp, currentHp + amount);
    }

    public boolean isAlive() {
        return currentHp > 0;
    }
    public String getName()       { return name; }
    public int    getCurrentHp()  { return currentHp; }
    public int    getMaxHp()      { return maxHp; }
    public int    getAttackPower(){ return attackPower; }

    @Override
    public String toString() {
        return String.format("%s [HP %d/%d | ATK %d]",
                name, currentHp, maxHp, attackPower);
    }
}
