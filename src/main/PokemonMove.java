package main;

/**
 *
 * @author David Onak
 */
public class PokemonMove {
    private final int type;
    private final String name;
    private final int initialPP;
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
        initialPP = pp;
    }

    public void consumePP() {
        pp --;
    }

    public int findActualDamage(int typeD, int rawDamage) {
        String effect = "";
        int actualDamage;

        switch (this.type) {
            case NORMAL:
                if (typeD == GHOST)
                    effect = "None";
                else
                    effect = "Normal";
                break;
            case FIRE:
                switch (typeD) {
                    case FIRE:
                    case WATER:
                    case DRAGON:
                        effect = "not every effective";
                        break;
                    case GRASS:
                        effect = "super effective";
                        break;
                    default:
                        effect = "Normal";
                }
                break;
            case WATER:
                switch (typeD) {
                    case FIRE:
                        effect = "super effective";
                        break;
                    case WATER:
                    case GRASS:
                    case DRAGON:
                        effect = "not every effective";
                        break;
                    default:
                        effect = "Normal";
                }
                break;
            case ELECTRIC:
                switch (typeD) {
                    case WATER:
                        effect = "super effective";
                        break;
                    case ELECTRIC:
                    case GRASS:
                    case DRAGON:
                        effect = "not every effective";
                        break;
                    default:
                        effect = "Normal";
                }
                break;
            case GRASS:
                switch (typeD) {
                    case FIRE:
                    case GRASS:
                    case DRAGON:
                        effect = "not every effective";
                        break;
                    case 3:
                        effect = "super effective";
                        break;
                    default:
                        effect = "Normal";
                }
                break;
            case DRAGON:
                if (typeD == DRAGON)
                    effect = "super effective";
                else
                    effect = "Normal";
                break;
            case GHOST:
                switch (typeD) {
                    case NORMAL:
                        effect = "None";
                        break;
                    case GHOST:
                        effect = "super effective";
                        break;
                    case DARK:
                        effect = "not every effective";
                        break;
                    default:
                        effect = "Normal";
                }
                break;
            case DARK:
                switch (typeD) {
                    case GHOST:
                        effect = "super effective";
                        break;
                    case DARK:
                        effect = "not every effective";
                        break;
                    default:
                        effect = "Normal";
                }
        }
        switch (effect) {
            case "None":
                actualDamage = 0;
                break;
            case "not every effective":
                actualDamage = rawDamage / 2;
                break;
            case "Normal":
                actualDamage = rawDamage;
                break;
            default:
                actualDamage = rawDamage * 2;
        }

        return actualDamage;
    }

    // using equation to calculate damage from actual pokemon game !!! does not consider effect from types
    // ((((2*stats[0])/5+2)*power*a)/(50*statsC[2])+2);
    // attack and level from user of move, defence from character being hit.
    public int calculateRawDamage(int level, int attack, int defence) {
        int d1 = ((2 * level) / 5) + 2;
        int d2 = (d1 * power * attack) / defence;
        return (d2 / 50) + 2;
    }

    /**
     * Return the move's accuracy to caller.
     *
     * @return the move's accuracy.
     */
    public int getAccuracy() {
        return accuracy;
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

    /**
     * Return the move's initial pp to caller.
     *
     * @return the move's initial pp.
     */
    public int getInitialPP() {
        return initialPP;
    }
}
