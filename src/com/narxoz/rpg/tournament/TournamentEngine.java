package com.narxoz.rpg.tournament;

import com.narxoz.rpg.arena.ArenaFighter;
import com.narxoz.rpg.arena.ArenaOpponent;
import com.narxoz.rpg.arena.TournamentResult;
import com.narxoz.rpg.chain.ArmorHandler;
import com.narxoz.rpg.chain.BlockHandler;
import com.narxoz.rpg.chain.DefenseHandler;
import com.narxoz.rpg.chain.DodgeHandler;
import com.narxoz.rpg.chain.HpHandler;
import com.narxoz.rpg.command.ActionQueue;
import com.narxoz.rpg.command.AttackCommand;
import com.narxoz.rpg.command.DefendCommand;
import com.narxoz.rpg.command.HealCommand;
import java.util.*;

public class TournamentEngine {
    private static final int MAX_ROUNDS = 50;
    private final ArenaFighter hero;
    private final ArenaOpponent opponent;

    public TournamentEngine(ArenaFighter hero, ArenaOpponent opponent) {
        this.hero = hero;
        this.opponent = opponent;
    }

    public TournamentResult runTournament() {
        List<String> log = new ArrayList<>();

        DefenseHandler defenseChain = buildDefenseChain();

        ActionQueue actionQueue = new ActionQueue();
        int round = 0;

        log.add(String.format("Tournament begins! %s vs %s",
                hero.getName(), opponent.getName()));
        log.add(String.format("Hero stats   : %s", hero));
        log.add(String.format("Opponent stats: %s", opponent));

        while (hero.isAlive() && opponent.isAlive() && round < MAX_ROUNDS) {
            round++;
            log.add(String.format("--- Round %d ---", round));
            System.out.printf("%n=== Round %d ===%n", round);

            queueHeroActions(actionQueue);

            System.out.println("  [QUEUE] Hero action queue: " + actionQueue.getCommandDescriptions());

            System.out.println("  [QUEUE] Executing hero actions...");
            actionQueue.executeAll();

            log.add(String.format("  Hero  → Opponent HP: %d/%d",
                    opponent.getCurrentHp(), opponent.getMaxHp()));

            if (!opponent.isAlive()) break;

            System.out.printf("%n  [OPPONENT] %s attacks for %d raw damage%n",
                    opponent.getName(), opponent.getAttackPower());

            defenseChain.handle(opponent.getAttackPower(), hero);

            log.add(String.format("  Opponent → Hero HP: %d/%d",
                    hero.getCurrentHp(), hero.getMaxHp()));

            if (!hero.isAlive()) break;
        }
        String winner;
        if (round >= MAX_ROUNDS) {
            winner = "Draw (max rounds reached)";
        } else if (!opponent.isAlive()) {
            winner = hero.getName();
        } else {
            winner = opponent.getName();
        }

        log.add(String.format("Tournament over after %d round(s). Winner: %s", round, winner));
        System.out.printf("%nTournament over. Winner: %s%n", winner);

        return new TournamentResult(winner, round, log);
    }
    private DefenseHandler buildDefenseChain() {
        DefenseHandler dodge = new DodgeHandler(hero.getDodgeChance());
        DefenseHandler block = new BlockHandler(hero.getBlockRating() / 100.0);
        DefenseHandler armor = new ArmorHandler(hero.getArmorValue());
        DefenseHandler hp    = new HpHandler();

        dodge.setNext(block).setNext(armor).setNext(hp);
        return dodge;
    }
    private void queueHeroActions(ActionQueue queue) {
        queue.enqueue(new AttackCommand(opponent, hero.getAttackPower()));

        boolean healed = false;
        if (hero.getCurrentHp() < hero.getMaxHp() / 2 && hero.getPotions() > 0) {
            queue.enqueue(new HealCommand(hero, hero.getMaxHp() / 3));
            healed = true;
        }

        if (!healed) {
            queue.enqueue(new DefendCommand(hero, 0.10));
        }
    }
}
