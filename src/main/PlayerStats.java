package main;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author David Onak
 */
public class PlayerStats {
    private int save;
    private int level;
    private int xp;
    private int nextLevelXP;
    private int[] stats;
    private int health; // does not get saved and will have method to return heath and to apply damage to it!
    private final int healthInitial;

    /**
     * Constructor that obtains data from a save.
     *
     * @param save, determines which save to retrieve stats from in this class.
     */
    public PlayerStats(int save) {
        this.save = save;
        stats = retrieveStats();

        level = stats[0];
        health = stats[1];
        healthInitial = stats[1];
        xp = stats[6];

        calculateNextLevel();
    }

    /**
     * Return the player's attack to caller.
     *
     * @return the attack of the player.
     */
    public int getAttack() {
        return stats[2];
    }

    /**
     * Return the player level to caller.
     *
     * @return the level of the player.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Return the player health to caller.
     *
     * @return the health of the player.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Return the player initial health to caller.
     *
     * @return the initial health of the player.
     */
    public int getInitialHealth() {
        return healthInitial;
    }

    /**
     * Return the player xp to caller.
     *
     * @return the xp of the player.
     */
    public int getXP() {
        return xp;
    }

    /**
     * Return the player's XP for next level to caller.
     *
     * @return the XP for next level for the player.
     */
    public int getXPForNextLevel() {
        return nextLevelXP;
    }

    /**
     * Calculate value for nextLevel.
     */
    private void calculateNextLevel() {
        nextLevelXP = (int) (1.7*level) + 15;
    }

    /**
     * Increases the xp stat, if xp is high enough then level will increase which will
     * increase all other stats. Level increase will reset xp and add the excess xp onto the stats.
     *
     * @param xpAdd, amount of xp to add to existing amount.
     */
    public void AddXP(int xpAdd) {
        int newXP = xp + xpAdd;

        while (newXP >= nextLevelXP) {
            newXP -= nextLevelXP;
            levelUp();
            calculateNextLevel();
        }

        stats[6] = newXP;

        storeStats();
    }

    /**
     * Increases level in this class and adds it to stats, then increases all other values in stats excluding xp.
     */
    private void levelUp() {
        level++;
        stats[0] = level;

        stats[1] = (((stats[1] * 2) * stats[0]) / 100) + stats[0] + 10;
        stats[2] = (((stats[2] * 2) * stats[0]) / 100) + 5;
        stats[3] = (((stats[3] * 2) * stats[0]) / 100) + 5;
        stats[4] = (((stats[4] * 2) * stats[0]) / 100) + 5;
    }

    /**
     * Stores current stats onto a text document corresponding to the players save slot.
     */
    private void storeStats() {
        switch (save) {
            case 1:
                try (FileWriter fw = new FileWriter("Slot1stats.txt", false);
                     BufferedWriter bw = new BufferedWriter(fw);
                     PrintWriter out = new PrintWriter(bw)) {
                    for (int t = 0; t < 7; t++) {
                        out.println(stats[t]);
                    }
                } catch (IOException ex) {
                    System.out.println("Problem reading file.");
                    System.err.println("IOException: " + ex.getMessage());
                }
                break;
            case 2:
                try (FileWriter fw = new FileWriter("Slot2stats.txt", false);
                     BufferedWriter bw = new BufferedWriter(fw);
                     PrintWriter out = new PrintWriter(bw)) {
                    for (int t = 0; t < 7; t++) {
                        out.println(stats[t]);
                    }
                } catch (IOException ex) {
                    System.out.println("Problem reading file.");
                    System.err.println("IOException: " + ex.getMessage());
                }
                break;
            case 3:
                try (FileWriter fw = new FileWriter("Slot3stats.txt", false);
                     BufferedWriter bw = new BufferedWriter(fw);
                     PrintWriter out = new PrintWriter(bw)) {
                    for (int t = 0; t < 7; t++) {
                        out.println(stats[t]);
                    }
                } catch (IOException ex) {
                    System.out.println("Problem reading file.");
                    System.err.println("IOException: " + ex.getMessage());
                }
        }
    }

    /**
     * Reads stat values from a text document corresponding to the players save slot.
     */
    private int[] retrieveStats() {
        int[] stats = new int[7];
        switch (save) {//retreive stats file
            case 1:
                try {
                    Scanner scanner = new Scanner(new FileReader("Slot1stats.txt"));
                    for (int t = 0; t < 6; t++) {
                        stats[t] = scanner.nextInt();
                    }
                } catch (IOException e) {
                    System.out.println("Something went wrong reading the file.");
                }
                break;
            case 2:
                try {
                    Scanner scanner = new Scanner(new FileReader("Slot2stats.txt"));
                    for (int t = 0; t < 6; t++) {
                        stats[t] = scanner.nextInt();
                    }
                } catch (IOException e) {
                    System.out.println("Something went wrong reading the file.");
                }
                break;
            case 3:
                try {
                    Scanner scanner = new Scanner(new FileReader("Slot3stats.txt"));
                    for (int t = 0; t < 7; t++) {
                        stats[t] = scanner.nextInt();
                    }
                } catch (IOException e) {
                    System.out.println("Something went wrong reading the file.");
                }
        }

        return stats;
    }
}

