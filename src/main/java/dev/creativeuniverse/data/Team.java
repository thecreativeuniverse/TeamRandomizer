package dev.creativeuniverse.data;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private final List<Player> players = new ArrayList<>();
    private final int id;

    public Team(int id) {
        this.id = id;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void list() {
        System.out.printf("Team %d:%n", id);
        players.forEach(p -> System.out.println("- " + p.getName()));
    }

}
