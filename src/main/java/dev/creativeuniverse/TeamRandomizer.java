package dev.creativeuniverse;

import dev.creativeuniverse.data.DataManager;
import dev.creativeuniverse.data.Match;
import dev.creativeuniverse.data.Player;

import java.util.Scanner;

public class TeamRandomizer {

    public static final Scanner SCANNER = new Scanner(System.in);

    private Match match;

    public static void main(String[] args) {
        new TeamRandomizer();
    }

    public TeamRandomizer() {
        try {
            Player[] players = DataManager.initialise();
            this.match = new Match(players);
            sortAndList();
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    private void sortAndList() {
        match.sortTeams();
        match.listTeams();
        ask();
    }

    private void ask() {
        System.out.println("Re-randomize teams? (Y/N)");
        String s = SCANNER.nextLine();
        if (s.equalsIgnoreCase("Y")) {
            sortAndList();
        } else if (!s.equalsIgnoreCase("N")) {
            ask();
        }
    }

}
