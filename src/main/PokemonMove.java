package main;

/**
 *
 * @author David Onak
 */
public class PokemonMove {
    private final int type;
    private final String name;
    private int pp;
    private final int power;
    private int accuracy;
    private final int NORMAL = 1;
    private final int FIRE = 2;
    private final int WATER = 3;
    private final int ELECTRIC = 4;
    private final int GRASS = 5;
    private final int DRAGON = 6;
    private final int GHOST = 7;
    private final int DARK = 8;

    /**
     * Constructor that creates a pokemon move based on the given name of the move.
     *
     * @param move the name of the pokemon move to be created.
     */
    public PokemonMove(String move) {
        name = move;

        switch (name) {
            case "Pound":
                type = NORMAL; pp = 35; power = 40; accuracy = 100;
                break;
            case "Double Slap":
                type = NORMAL; pp = 10; power = 15; accuracy = 85; //Hits 2-5 times in one turn.
                break;
            case "Acupressure":
                type = NORMAL; pp = 30; power = 0; accuracy = 100; //Sharply raises a random stat.
                break;
            case "Hold Hands":
                type = NORMAL; pp = 40; power = 0; accuracy = 100; //Does nothing
                break;
            case "Fire Spin":
                type = FIRE; pp = 15; power = 35; accuracy = 85; //Traps opponent, damaging them for 4-5 turns.
                break;
            case "Ember":
                type = FIRE; pp = 25; power = 40; accuracy = 100;
                break;
            case "Bubble":
                type = WATER; pp = 30; power = 40; accuracy = 100; //May lower opponent's Speed. 10%
                break;
            case "Withdraw":
                type = WATER; pp = 40; power = 0; accuracy = 100; //raises user's defence
                break;
            case "Thunder Shock":
                type = ELECTRIC; pp = 30; power = 40; accuracy = 100; //May paralyze opponent. 10%
                break;
            case "Charge Beam":
                type = ELECTRIC; pp = 10; power = 50; accuracy = 90; //May raise user's Special Attack.
                break;
            case "Leafage":
                type = GRASS; pp = 40; power = 40; accuracy = 100;
                break;
            case "Absorb":
                type = GRASS; pp = 25; power = 20; accuracy = 100; //Recover half the damage dealt.
                break;
            case "Twister":
                type = DRAGON; pp = 20; power = 40; accuracy = 100;
                break;
            case "Astonish":
                type = GHOST; pp = 15; power = 30; accuracy = 100;
                break;
            case "Judgment":
                type = NORMAL; pp = 10; power = 100; accuracy = 100;
                break;
            case "Hydro Cannon":
                type = WATER; pp = 5; power = 150; accuracy = 90; //User must recharge next turn.
                break;
            case "Cotton Guard":
                type = GRASS; pp = 10; power = 0; accuracy = 100; //Sharply increases users defence
                break;
            case "Shadow Ball":
                type = GHOST; pp = 15; power = 80; accuracy = 100; //may lowers opponents defence %20
                break;
            case "Night Daze":
                type = DARK; pp = 5; power = 150; accuracy = 90; //may lower opponents acurracy 40%
                break;
            case "V-create":
                type = FIRE; pp = 15; power = 80; accuracy = 100; //may lowers opponents defence %20
                break;
            case "Parabolic Charge":
                type = ELECTRIC; pp = 20; power = 65; accuracy = 100; //User recovers half the HP inflicted on opponent.
                break;
            case "Clanging Scales":
                type = DRAGON; pp = 5; power = 110; accuracy = 100; //slightly lowers user's defence
                break;
            case "Snarl":
                type = DARK; pp = 10; power = 85; accuracy = 95; //lowers opponents attack
                break;
            case "Illuminati":
                type = DARK; pp = 1; power = 200; accuracy = 100; //the user looses half of their health
                break;
            default:
                type = NORMAL; pp = 100; power = 5; accuracy = 90;
                System.out.println("The move "+move+" was not recognized!");
        }
    }


    public int dealDamage(int[] characterStats) {
        // uses input stats to return damage infilicted in attack
        return 0;
    }

    /**
     * Return the move's type to caller.
     *
     * @return the move's type.
     */
    public int getType() {
        return type;
    }

    /**
     * Return the move's name to caller.
     *
     * @return the move's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Return the move's pp to caller.
     *
     * @return the move's pp.
     */
    public int getPP() {
        return pp;
    }
}
