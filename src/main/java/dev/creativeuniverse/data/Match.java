package dev.creativeuniverse.data;

import java.util.*;

public class Match {

    private Team[] teams = new Team[]{new Team(1), new Team(2), new Team(3), new Team(4)};
    private final Player[] players;

    public Match(Player[] players) throws IllegalStateException {
        if (players.length != 24) throw new IllegalStateException("There must be 24 players per match.");
        this.players = players;
    }

    public void sortTeams() {
        this.teams = new Team[]{new Team(1), new Team(2), new Team(3), new Team(4)};
        List<Player> topUtil = new ArrayList<>();
        List<Player> topDefence = new ArrayList<>();
        Arrays.sort(players, Comparator.comparing(Player::getUtility));
        for (int i = 0; i < 4; i++) {
            topUtil.add(players[players.length - 1 - i]);
        }
        Arrays.sort(players, Comparator.comparing(Player::getDefence));
        for (int i = 0; i < players.length; i++) {
            Player player = players[players.length - 1 - i];
            if (topUtil.contains(player)) continue;
            topDefence.add(player);
            if (topDefence.size() == 4) break;
        }
        Collections.shuffle(topUtil);
        Collections.shuffle(topDefence);

        for (int i = 0; i < teams.length; i++) {
            teams[i].addPlayer(topUtil.get(i));
            teams[i].addPlayer(topDefence.get(i));
        }

        Arrays.sort(players);
        int count = 0;
        for (int i = 0; i < players.length; i++) {
            Player player = players[i];
            if (topUtil.contains(player) || topDefence.contains(player)) continue;
            teams[count++ % 4].addPlayer(player);
        }

    }

    public void listTeams() {
        for (int i = 0; i < teams.length; i++) {
            teams[i].list();
        }
    }

}
