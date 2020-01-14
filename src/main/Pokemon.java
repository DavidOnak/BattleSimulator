package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//TODO: Modify xp reward based on number of pokemon being battled at once (20%)
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
    private int initialHealth;
    private int[] stats;
    private final int tier;
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
    private final String[] SECOND = {"Pound", "Double Slap", "Fire Spin", "Ember", "Bubble", "Thunder Shock", "Charge Beam",
            "Leafage", "Absorb", "Twister", "Astonish", "Judgment", "Shadow Ball", "Night Daze", "V-create", "Parabolic Charge", "Snarl"};
    private final String[] THIRD = {"Hydro Cannon", "Clanging Scales", "Illuminati"};

    /**
     * Constructor that creates an instance of a Pokemon.
     *
     * @param tier the tier to generate a Pokemon in.
     */
    public Pokemon(int tier) { // animation buggy for Ditto, Poliwhirl, pickachu
        this.tier = tier;
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

    /**
     * Using the inputted tier, creates a Pokemon and generates it stats.
     *
     * @param tier the tier to generate a Pokemon in.
     */
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

    /**
     * Generates stats for this Pokemon using the Pokemon's level and inputted base stats.
     *
     * @param hp the base stat for health.
     * @param attack the base stat for attack.
     * @param defence the base stat for defence.
     * @param speed the base stat for speed.
     * @param firstType the first type of this Pokemon.
     * @param secondType the second type of this Pokemon.
     */
    private void generateStats(int hp, int attack, int defence, int speed, int firstType, int secondType) {
        type1 = firstType;
        type2 = secondType;
        health = ((hp * 2 * level) / 100) + level + 10;
        this.attack = ((attack * 2 * level) / 100) + 5;
        this.defence = ((defence * 2 * level) / 100) + 5;
        this.speed = ((speed * 2 * level) / 100) + 5;
        initialHealth = health;
        System.out.println("Health " + health + ", attack " + this.attack + ", defence " + this.defence + ", speed " + this.speed);
    }

    /**
     * Decreases health by the input damage, until reaching 0 and then stays at 0.
     *
     * @param damage the damage to be done onto this Pokemon.
     */
    public void dealDamage(int damage) {
        health = health - damage;

        if (health < 0)
            health = 0;
    }

    /**
     * Returns the best PokemonMove to use based on given defence and type of target.
     *
     * @param playerD the defence of the target.
     * @param playerT the type of the target.
     * @return the best PokemonMove to use on the target.
     */
    public PokemonMove chooseMove(int playerD, int playerT) {
        List<String> secondMoves = Arrays.asList(SECOND);
        List<String> thirdMoves = Arrays.asList(THIRD);
        double initialHealth = this.initialHealth;
        double percent = (health / initialHealth) * 100;

        ArrayList<PokemonMove> firstContained = new ArrayList<>();
        if (move1.getPower() == 0 && move1.getPP() != 0)
            firstContained.add(move1);
        if (move2.getPower() == 0 && move2.getPP() != 0)
            firstContained.add(move2);
        if (move3.getPower() == 0 && move3.getPP() != 0)
            firstContained.add(move3);
        if (move4.getPower() == 0 && move4.getPP() != 0)
            firstContained.add(move4);
        ArrayList<PokemonMove> secondContained = checkContained(secondMoves);
        ArrayList<PokemonMove> thirdContained = checkContained(thirdMoves);

        if (percent > 80 && firstContained.size() > 0) {
            return firstContained.get((int) (firstContained.size() * Math.random()));
        } else if (percent > 20 && secondContained.size() > 0) {
            return findMostDamaging(secondContained, playerD, playerT);
        } else if (thirdContained.size() > 0) {
            return findMostDamaging(thirdContained, playerD, playerT);
        } else if (secondContained.size() > 0) {
            return findMostDamaging(secondContained, playerD, playerT);
        } else if (firstContained.size() > 0) {
            return firstContained.get((int) (firstContained.size() * Math.random()));
        } else {
            return new PokemonMove("Empty");
        }
    }

    /**
     * Returns the most damaging PokemonMove to use based on given defence and type of target in an Array of given
     * PokemonMoves.
     *
     * @param moves the Array of PokemonMoves to evaluate.
     * @param playerD the defence of the target.
     * @param playerT the type of the target.
     * @return the most damaging PokemonMove to use on the target.
     */
    private PokemonMove findMostDamaging(ArrayList<PokemonMove> moves, int playerD, int playerT) {
        int mostDamage = 0;
        PokemonMove selectedMove = new PokemonMove("Empty");

        for (PokemonMove move: moves) {
            int damage = move.determineDamage(level, attack, playerD, playerT, 0);
            if (move.getName().equals("Absorb") || move.getName().equals("Parabolic Charge"))
                damage = (int) (damage * 1.5);
            if (damage > mostDamage) {
                mostDamage = damage;
                selectedMove = move;
            }
        }
        return selectedMove;
    }

    /**
     * Returns an ArrayList of all the instances of PokemonMoves this Pokemon has that has its name contained
     * in the given list.
     *
     * @param moves the list of PokemonMove names to evaluate.
     * @return the ArrayList containing all of this Pokemon's PokemonMoves with its name contained in moves.
     */
    private ArrayList<PokemonMove> checkContained(List<String> moves) {
        ArrayList<PokemonMove> containedMoves = new ArrayList<>();

        if (moves.contains(move1.getName()) && move1.getPP() != 0)
            containedMoves.add(move1);
        if (moves.contains(move2.getName()) && move2.getPP() != 0)
            containedMoves.add(move2);
        if (moves.contains(move3.getName()) && move3.getPP() != 0)
            containedMoves.add(move3);
        if (moves.contains(move4.getName()) && move4.getPP() != 0)
            containedMoves.add(move4);

        return containedMoves;
    }

    /**
     * Lower PP for the PokemonMove that this Pokemon has with the given PokemonMove name.
     *
     * @param move the name of the PokemonMove to lower PP from.
     */
    public void removePP(String move) {
        if (move.equals(move1.getName())) {
            move1.consumePP();
        } else if (move.equals(move2.getName())) {
            move2.consumePP();
        } else if (move.equals(move3.getName())) {
            move3.consumePP();
        } else if (move.equals(move4.getName())) {
            move4.consumePP();
        }
    }

    /**
     * Changes the defence by a given percent.
     *
     * @param percent the percent to change the defence by.
     * @param add true if to increase the defence and decrease defence if false.
     * @return the text to add on display on how much defence was effected.
     */
    public String changeDefence(int percent, boolean add) {
        // get amount to add
        int change = ((percent * defence) / 100);

        if (add) {
            defence += change;
            if (percent < 10)
                return "defence has increased!";
            else
                return "defence has sharply increased!";
        } else {
            defence -= change;
            if (percent < 10)
                return "defence has decreased!";
            else
                return "defence has sharply decreased!";
        }
    }

    /**
     * Changes the attack by a given percent.
     *
     * @param percent the percent to change the attack by.
     * @param add true if to increase the attack and decrease attack if false.
     * @return the text to add on display on how much attack was effected.
     */
    public String changeAttack(int percent, boolean add) {
        // get amount to add
        int change = ((percent * attack) / 100);

        if (add) {
            attack += change;
            if (percent < 10)
                return "attack has increased!";
            else
                return "attack has sharply increased!";
        } else {
            attack -= change;
            if (percent < 10)
                return "attack has decreased!";
            else
                return "attack has sharply decreased!";
        }
    }

    /**
     * Return the XP to be gained for defeating this Pokemon.
     *
     * @return the XP reward for defeating this Pokemon.
     */
    public int getXPReward() {
        int reward = 2 * level * tier;
        if (name.equals("Charizard"))
            reward += 15;
        return reward;
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
