package dev.creativeuniverse.data;

import java.util.Random;

public record Player(String name, int defence, int utility, int offence) implements Comparable<Player> {

    public static final int BIAS_RANGE = 5;

    public String getName() {
        return name;
    }

    public int getDefence() {
        return defence;
    }

    public int getUtility() {
        return utility;
    }

    public int getOffence() {
        return offence;
    }

    private int randomise(int score) {
        return score + new Random().nextInt(BIAS_RANGE);
    }

    private int getBiasScore() {
        return randomise(this.offence) + randomise(this.defence) + randomise(this.utility);
    }

    @Override
    public int compareTo(Player o) {
        return Integer.compare(getBiasScore(), o.getBiasScore());
    }

}
