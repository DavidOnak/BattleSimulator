package main;

public class Pokemon {
    private String name;
    private int level;
    private int health;
    private int initialHealth; // change this to final

    public Pokemon(int tier) {

    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getHealth() {
        return health;
    }

    public int getInitialHealth() {
        return initialHealth;
    }
}
