package dev.creativeuniverse.data;

import dev.creativeuniverse.TeamRandomizer;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DataManager {

    public static final String FILEPATH = "players.yml";

    private static final Map<String, Player> PLAYERS = new HashMap<>();

    public static Player[] initialise() throws IllegalStateException {
        File file = new File(FILEPATH);
        if (!file.exists()) {
            throw new IllegalStateException(FILEPATH + " does not exist. Cannot access player data.");
        }
        try {
            InputStream is = new FileInputStream(file);

            Yaml yaml = new Yaml();
            Map<String, Object> data = (Map<String, Object>) yaml.load(is);
            data.forEach((name, playerData) -> {
                name = name.toLowerCase();
                Map<String, Integer> map = (Map<String, Integer>) playerData;
                Player player = new Player(name, map.get("defence"), map.get("utility"), map.get("offence"));
                PLAYERS.put(player.getName(), player);
            });
        } catch (IOException | ClassCastException e) {
            e.printStackTrace();
        }
        Scanner scanner = TeamRandomizer.SCANNER;
        Player[] players = new Player[24];

        for (int i = 0; i < players.length; i++) {
            System.out.println("Please input next player's name.");
            String playerName = scanner.nextLine().toLowerCase();
            if (!PLAYERS.containsKey(playerName)) {
                System.out.println(playerName + " does not exist.");
                i--;
            } else if (Arrays.stream(players).anyMatch(p -> playerName.equalsIgnoreCase(p == null ? "" : p.getName()))) {
                System.out.println(playerName + " has already been entered into this match!");
                i--;
            } else {
                players[i] = PLAYERS.get(playerName);
                System.out.println("Successfully added player to match.");
            }
        }

        return players;
    }

}
