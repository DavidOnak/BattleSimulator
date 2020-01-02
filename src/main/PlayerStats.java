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
    private int attack;
    private int defence;
    private int speed;
    private int[] stats;
    private int health; // TODO: ADD thing to stasts to save a point to add to a base stat each time levelign up
    private int healthInitial;

    /**
     * Constructor that obtains data from a save.
     *
     * @param save, determines which save to retrieve stats from in this class.
     */
    public PlayerStats(int save) {
        this.save = save;
        stats = retrieveStats();

        level = stats[0];
        useBaseStats();
        health = healthInitial;
        xp = stats[6];
        System.out.println(xp  + " the xp level");

        calculateNextLevel(level);
    }

    public void dealDamage(int damage) {
        health = health - damage;

        if (health < 0)
            health = 0;
    }

    /**
     * Return the player's type to caller.
     *
     * @return the type of the player.
     */
    public int getType() {
        return stats[5];
    }

    /**
     * Return the player's defence to caller.
     *
     * @return the defence of the player.
     */
    public int getDefence() {
        return defence;
    }

    /**
     * Return the player's attack to caller.
     *
     * @return the attack of the player.
     */
    public int getAttack() {
        return attack;
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
    public int calculateNextLevel(int level) {
        return nextLevelXP = (int) (2.5*level) + 15;
    }

    /**
     * Increases the xp stat, if xp is high enough then level will increase which will
     * increase all other stats. Level increase will reset xp and add the excess xp onto the stats.
     *
     * @param xpAdd, amount of xp to add to existing amount.
     */
    public void addXP(int xpAdd) {
        int newXP = xp + xpAdd;

        while (newXP >= nextLevelXP) {
            newXP -= nextLevelXP;
            useBaseStats();
            calculateNextLevel(level);
        }

        stats[6] = newXP;
        xp = newXP;

        storeStats();
    }

    /**
     * Using the base stats of the player, creates stats to use for battle.
     */
    private void useBaseStats() {
        level++;
        stats[0] = level;

        healthInitial = (((stats[1] * 2) * level) / 100) + level + 10;
        attack = (((stats[2] * 2) * level) / 100) + 5;
        defence = (((stats[3] * 2) * level) / 100) + 5;
        speed = (((stats[4] * 2) * level) / 100) + 5;

        System.out.println("Your stats: Health " + health + ", attack " + attack + ", defence " + defence + ", speed " + speed);
    }

    /**
     * Stores current stats onto a text document corresponding to the players save slot.
     */
    private void storeStats() {
        switch (save) {
            case 1:
                try (FileWriter fw = new FileWriter("saves/Slot1stats.txt", false);
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
                try (FileWriter fw = new FileWriter("saves/Slot2stats.txt", false);
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
                try (FileWriter fw = new FileWriter("saves/Slot3stats.txt", false);
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
                    Scanner scanner = new Scanner(new FileReader("saves/Slot1stats.txt"));
                    for (int t = 0; t < 7; t++) {
                        stats[t] = scanner.nextInt();
                    }
                } catch (IOException e) {
                    System.out.println("Something went wrong reading the file.");
                }
                break;
            case 2:
                try {
                    Scanner scanner = new Scanner(new FileReader("saves/Slot2stats.txt"));
                    for (int t = 0; t < 7; t++) {
                        stats[t] = scanner.nextInt();
                    }
                } catch (IOException e) {
                    System.out.println("Something went wrong reading the file.");
                }
                break;
            case 3:
                try {
                    Scanner scanner = new Scanner(new FileReader("saves/Slot3stats.txt"));
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

