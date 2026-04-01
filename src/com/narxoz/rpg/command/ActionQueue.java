package com.narxoz.rpg.command;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class ActionQueue {
    private final Deque<ActionCommand> queue = new ArrayDeque<>();

    public void enqueue(ActionCommand command) {
        if (command == null) throw new IllegalArgumentException("command must not be null");
        queue.addLast(command);
    }

    public void undoLast() {
        if (queue.isEmpty()) {
            System.out.println("  [QUEUE] Nothing to undo — queue is empty.");
            return;
        }
        ActionCommand last = queue.removeLast();
        System.out.printf("  [QUEUE] Undoing: %s%n", last.getDescription());
        last.undo();
    }

    public void executeAll() {
        if (queue.isEmpty()) {
            System.out.println("  [QUEUE] Queue is empty — nothing to execute.");
            return;
        }
        while (!queue.isEmpty()) {
            ActionCommand cmd = queue.pollFirst();
            cmd.execute();
        }
    }

    public List<String> getCommandDescriptions() {
        List<String> descriptions = new ArrayList<>();
        for (ActionCommand cmd : queue) {          // Deque iteration is front-to-back
            descriptions.add(cmd.getDescription());
        }
        return List.copyOf(descriptions);
    }

    public boolean isEmpty() { return queue.isEmpty(); }

    public int size() { return queue.size(); }
}
