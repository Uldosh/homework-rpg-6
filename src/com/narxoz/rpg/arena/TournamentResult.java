package com.narxoz.rpg.arena;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TournamentResult {
    private String winner;
    private int rounds;
    private final List<String> log;

    public TournamentResult(String winner, int rounds, List<String> log) {
        this.winner = winner;
        this.rounds = rounds;
        this.log = Collections.unmodifiableList(log);
    }

    public String getWinner() { return winner; }
    public int getRounds() { return rounds; }
    public List<String> getLog() { return log; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Tournament Result ===\n");
        sb.append("Winner : ").append(winner).append("\n");
        sb.append("Rounds : ").append(rounds).append("\n");
        sb.append("--- Event Log ---\n");
        log.forEach(entry -> sb.append("  ").append(entry).append("\n"));
        return sb.toString();
    }
}
