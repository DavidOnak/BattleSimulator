package main;

/**
 *
 * @author David Onak
 */
public class Pokemon {
    private final String name;
    private int level;
    private int health;
    private int initialHealth; // change this to final
    private int[] stats;
    private final String[] LEGENDARY = {"Mew", "Lugia", "Palkia", "Death Angle"};
    private final String[] REGULAR = {"Charizard", "Pikachu", "Zubat", "Ditto", "Poliwhirl", "Weedle", "Oddish", "Eevee"};

    public Pokemon(int tier) {
        switch (tier) {
            case 1: //levels 10 to 25
                level = (int) (15 * Math.random() + 10);
                break;
            case 2: //levels 26 to 40
                level = (int) (14 * Math.random() + 26);
                break;
            case 3: //levels 41 to 65
                level = (int) (14 * Math.random() + 41);
                break;
            case 4: //levels 66 to 80
                level = (int) (14 * Math.random() + 66);
                break;
            case 5: //**masters** levels 81 to 100
                level = (int) (19 * Math.random() + 81);
                break;
            case 6: //**legendary** LV.X (programmed as 115)
                level = 115;
        }

        //pick pokemon
        int randomPokemon;
        if (tier == 6) {//draw legendary pokemon
            randomPokemon = (int) (4 * Math.random());
            name = LEGENDARY[randomPokemon];
        } else {//draw regular pokemon
            randomPokemon = (int) (8 * Math.random());
            name = REGULAR[randomPokemon];
        }

        generateStats();
    }

    private void generateStats() {
        switch (name) {

        }
    }

    /**
     * Return the pokemon's name to caller.
     *
     * @return the pokemon's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Return the pokemon's level to caller.
     *
     * @return the pokemon's level.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Return the pokemon's health to caller.
     *
     * @return the pokemon's health.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Return the pokemon's initial health to caller.
     *
     * @return the pokemon's initial health.
     */
    public int getInitialHealth() {
        return initialHealth;
    }
}
