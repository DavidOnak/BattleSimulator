package main;

/**
 *
 * @author David Onak
 */
public class Pokemon {
    private final String name;
    private int level;
    private int type1;
    private int type2;
    private int attack;
    private int defence;
    private int speed;
    private int health;
    private int initialHealth; // change this to final
    private int[] stats;
    private PokemonMove move1;
    private PokemonMove move2;
    private PokemonMove move3;
    private PokemonMove move4;
    private final int NORMAL = 1;
    private final int FIRE = 2;
    private final int WATER = 3;
    private final int ELECTRIC = 4;
    private final int GRASS = 5;
    private final int DRAGON = 6;
    private final int GHOST = 7;
    private final int DARK = 8;
    private final String[] LEGENDARY = {"Mew", "Lugia", "Palkia", "Death Angle"};
    private final String[] REGULAR = {"Charizard", "Pikachu", "Zubat", "Ditto", "Poliwhirl", "Weedle", "Oddish", "Eevee"};

    // for ai make 3 array lists for thresholds, eg pick random from this when hp is over 80 percent, pick most damaging when over 20 percent,
    // added in these if contain in overage list for that catagory
    public Pokemon(int tier) { // animation buggy for Ditto, Poliwhirl, pickachu
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
        generatePokemon(tier);
    }

    private void generatePokemon(int tier) {
        switch (name) {
            case "Charizard":
                move1 = new PokemonMove("Pound");
                move2 = new PokemonMove("Char");
                move3 = new PokemonMove("Fire Spin");
                if (tier > 3){ // higher level
                    move4 = new PokemonMove("V-create");
                }else{ // lower level
                    move4 = new PokemonMove("Ember");
                }

                generateStats(78, 84, 78, 100, FIRE, DRAGON); // TODO: actually fire and flying
                break;
            case "Pikachu":
                move1 = new PokemonMove("Pound");
                move2 = new PokemonMove("Zap");
                move3 = new PokemonMove("Thunder Shock");
                if (tier > 3){ // higher level
                    move4 = new PokemonMove("Parabolic Charge");
                }else{ // lower level
                    move4 = new PokemonMove("Charge Beam");
                }
                generateStats(35, 55, 40, 90, ELECTRIC, 0);
                break;
            case "Zubat":
                move1 = new PokemonMove("Pound");
                move2 = new PokemonMove("Bite");
                move3 = new PokemonMove("Acupressure");
                if (tier > 3){ // higher level
                    move4 = new PokemonMove("Night Daze");
                }else{ // lower level
                    move4 = new PokemonMove("Clamp");
                }
                generateStats(40, 45, 35, 55, DARK, 0); // TODO: actually poison and flying
                break;
            case "Ditto":
                move1 = new PokemonMove("Hold Hands");
                move2 = new PokemonMove("Double Slap");
                move3 = new PokemonMove("Acupressure");
                if (tier > 3){ // higher level
                    move4 = new PokemonMove("Judgment");
                }else{ // lower level
                    move4 = new PokemonMove("Pound");
                }
                generateStats(48, 48, 48, 48, NORMAL, 0);
                break;
            case "Poliwhirl":
                move1 = new PokemonMove("Withdraw");
                move2 = new PokemonMove("Double Slap");
                move3 = new PokemonMove("Bubble");
                if (tier > 3){ // higher level
                    move4 = new PokemonMove("Hydro Cannon");
                }else{ // lower level
                    move4 = new PokemonMove("Acupressure");
                }
                generateStats(65, 65, 65, 90, WATER, 0);
                break;
            case "Weedle":
                move1 = new PokemonMove("Leafage");
                move2 = new PokemonMove("Double Slap");
                move3 = new PokemonMove("Absorb");
                if (tier > 3){ // higher level
                    move4 = new PokemonMove("Judgment");
                }else{ // lower level
                    move4 = new PokemonMove("Pound");
                }
                generateStats(40, 35, 20, 50, GRASS, 0); // TODO: Actually poison and bug
                break;
            case "Oddish":
                move1 = new PokemonMove("Absorb");
                move2 = new PokemonMove("Acupressure");
                if (tier > 3){ // higher level
                    move3 = new PokemonMove("Hydro Cannon");
                    move4 = new PokemonMove("Judgment");
                }else{ // lower level
                    move3 = new PokemonMove("Leafage");
                    move4 = new PokemonMove("Pound");
                }
                generateStats(45, 65, 55, 30, GRASS, 0); // TODO: actually grass and poison
                break;
            case "Eevee":
                move1 = new PokemonMove("Hold Hands");
                move2 = new PokemonMove("Double Slap");
                move3 = new PokemonMove("Acupressure");
                if (tier > 3){ // higher level
                    move4 = new PokemonMove("Judgment");
                }else{ // lower level
                    move4 = new PokemonMove("Pound");
                }
                generateStats(55, 55, 50, 55, NORMAL, 0);
                break;
            case "Mew": // unique, can learn any type of move
                move1 = new PokemonMove("Dark Wave");
                move2 = new PokemonMove("Parabolic Charge");
                move3 = new PokemonMove("Justice");
                move4 = new PokemonMove("Hydro Cannon");
                generateStats(100, 100, 100, 100, NORMAL, 0); // TODO: type is actually psychic
                break;
            case "Lugia":
                move1 = new PokemonMove("Hydro Cannon");
                move2 = new PokemonMove("Withdraw");
                move3 = new PokemonMove("Quad Blast");
                move4 = new PokemonMove("Judgment");
                generateStats(106, 90, 130, 110, WATER, 0); // TODO: type is actually flying and psychic
                break;
            case "Palkia":
                move1 = new PokemonMove("Hydro Cannon");
                move2 = new PokemonMove("Clanging Scales");
                move3 = new PokemonMove("Acupressure");
                move4 = new PokemonMove("Judgment");
                generateStats(90, 120, 100, 100, WATER, DRAGON);
                break;
            case "Death Angle":
                move1 = new PokemonMove("Dark Wave");
                move2 = new PokemonMove("Night Daze");
                move3 = new PokemonMove("Snarl");
                move4 = new PokemonMove("Illuminati");
                generateStats(130, 90, 90, 80, DRAGON, DARK);
        }
        System.out.println(name + " has the moves "+ move1.getName() +", " + move2.getName() + ", " + move3.getName() + ", " + move4.getName());
    }

    private void generateStats(int hp, int attack, int defence, int speed, int firstType, int secondType) {
        type1 = firstType;
        type2 = secondType;
        health = (((hp * 2) * level) / 100) + level + 10;
        this.attack = (((attack * 2) * level) / 100) + 5;
        this.defence = (((defence * 2) * level) / 100) + 5;
        this.speed = (((speed * 2) * level) / 100) + 5;
        initialHealth = health;
        System.out.println("Health " + health + ", attack " + attack + ", defence " + defence + ", speed " + speed);
    }

    public void dealDamage(int damage) {
        health = health - damage;

        if (health < 0)
            health = 0;
    }

    public int getXPReward() {

        return 25;
    }

    /**
     * Return the pokemon's defence to caller.
     *
     * @return the pokemon's defence.
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Return the pokemon's defence to caller.
     *
     * @return the pokemon's defence.
     */
    public int getDefence() {
        return defence;
    }

    /**
     * Return the pokemon's first type to caller.
     *
     * @return the pokemon's first type.
     */
    public int getFirstType() {
        return type1;
    }

    /**
     * Return the pokemon's second type to caller.
     *
     * @return the pokemon's second type.
     */
    public int getSecondType() {
        return type2;
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
