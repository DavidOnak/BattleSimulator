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
    private int chance = 0; // chance that damaging move does effect
    private int accuracy;
    private int rawDamage;
    private int actualDamage;
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
                type = NORMAL; pp = 10; power = 15; accuracy = 85; // Hits 2-5 times in one turn.
                break;
            case "Acupressure":
                type = NORMAL; pp = 30; power = 0; accuracy = 100; // Sharply raises a random stat.
                break;
            case "Hold Hands":
                type = NORMAL; pp = 40; power = 0; accuracy = 100; // Does nothing
                break;
            case "Fire Spin":
                type = FIRE; pp = 15; power = 35; accuracy = 85; // Traps opponent, damaging them for 4-5 turns.
                break;
            case "Ember":
                type = FIRE; pp = 25; power = 40; accuracy = 100;
                break;
            case "Bubble":
                type = WATER; pp = 30; power = 40; accuracy = 100; // May lower opponent's Speed. 10%
                break;
            case "Withdraw":
                type = WATER; pp = 40; power = 0; accuracy = 100; // raises user's defence
                break;
            case "Thunder Shock":
                type = ELECTRIC; pp = 30; power = 40; accuracy = 100; // May paralyze opponent. 10%
                break;
            case "Charge Beam":
                type = ELECTRIC; pp = 10; power = 50; accuracy = 90; chance = 30; // May raise user's Attack.
                break;
            case "Leafage":
                type = GRASS; pp = 40; power = 40; accuracy = 100;
                break;
            case "Absorb":
                type = GRASS; pp = 25; power = 20; accuracy = 100; // Recover half the damage dealt.
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
                type = WATER; pp = 5; power = 150; accuracy = 90; // User must recharge next turn.
                break;
            case "Cotton Guard":
                type = GRASS; pp = 10; power = 0; accuracy = 100; // Sharply increases users defence
                break;
            case "Shadow Ball":
                type = GHOST; pp = 15; power = 80; accuracy = 100; chance = 20; // may lowers opponents defence
                break;
            case "Night Daze":
                type = DARK; pp = 5; power = 150; accuracy = 90; chance = 40; // may lower opponents acurracy
                break;
            case "V-create":
                type = FIRE; pp = 15; power = 80; accuracy = 100; chance = 20; // may lowers opponents defence
                break;
            case "Parabolic Charge":
                type = ELECTRIC; pp = 20; power = 65; accuracy = 100; // User recovers half the HP inflicted on opponent.
                break;
            case "Clanging Scales":
                type = DRAGON; pp = 5; power = 110; accuracy = 100; chance = 100; // slightly lowers user's defence
                break;
            case "Snarl":
                type = DARK; pp = 10; power = 85; accuracy = 95; chance = 100; // lowers opponents attack
                break;
            case "Illuminati":
                type = DARK; pp = 1; power = 200; accuracy = 100; // the user looses half of their health
                break;
            case "Char": // onwards are hidden moves
                type = FIRE; pp = 15; power = 60; accuracy = 85;
                break;
            case "Zap":
                type = ELECTRIC; pp = 15; power = 60; accuracy = 85;
                break;
            case "Bite":
                type = DARK; pp = 35; power = 40; accuracy = 100;
                break;
            case "Clamp":
                type = DARK; pp = 10; power = 40; accuracy = 100; // recover half of damage done
                break;
            case "Quad Blast":
                type = WATER; pp = 8; power = 40; accuracy = 80; // attacks 4 times
                break;
            case "Dark Wave":
                type = DARK; pp = 5; power = 0; accuracy = 0; // raises attack and defense
                break;
            default:
                type = NORMAL; pp = 100; power = 5; accuracy = 90;
                System.out.println("The move "+move+" was not recognized!");
        }
        initialPP = pp;
    }

    /**
     * Reduces the pp in this move by 1.
     */
    public void consumePP() {
        pp --;
    }

    /**
     * Calculates the actual damage inflicted by the move by taking the type of the move and of the defender into
     * effect by applying a multiplier based on effectiveness.
     *
     * @param typeD the type of the defender.
     * @param rawDamage the raw damage of the attack.
     * @return the actual damage inflicted from the attack.
     */
    private int findActualDamage(int typeD, int rawDamage) {
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

    /**
     * Uses the equation from the actual Pokemon games to calculate raw damage, does not take effects and factors
     * into effect such as multipliers from types.
     *
     * @param level the level of the attacker.
     * @param attack the attack stat of the attacker.
     * @param defence the defence stat of the defender.
     * @return the raw damage inflicted from the attack.
     */
    private int calculateRawDamage(int level, int attack, int defence) {
        int d1 = ((2 * level) / 5) + 2;
        int d2 = (d1 * power * attack) / defence;
        return (d2 / 50) + 2;
    }

    /**
     * Return the damage that the move will inflict based on given stats of the attacker and defender.
     *
     * @param attackerLevel the level of the attacker.
     * @param attackerA the attack stat of the attacker.
     * @param defenderD the defence stat of the defender.
     * @param firstType the primary type of the defender.
     * @param secondType the secondary type of the defender.
     * @return the damage that will be inflicted, returns 0 if the move has 0 power.
     */
    public int determineDamage(int attackerLevel, int attackerA, int defenderD, int firstType, int secondType) {
        if (power == 0)
            return power;

        // get raw damage and apply effect
        rawDamage = calculateRawDamage(attackerLevel, attackerA, defenderD);
        actualDamage = findActualDamage(firstType, rawDamage);
        if (secondType != 0)
            actualDamage = findActualDamage(secondType, actualDamage);

        // get random factor in attacks
        int random = (int) (15 * Math.random() + 85);
        return (actualDamage * random) / 100;
    }

    /**
     * Return the move's last raw damage calculation to caller.
     *
     * @return the move's last raw damage calculation.
     */
    public int getRawDamage() {
        return rawDamage;
    }

    /**
     * Return the move's last actual damage calculation to caller.
     *
     * @return the move's last actual damage calculation.
     */
    public int getActualDamage() {
        return actualDamage;
    }

    /**
     * Return the move's power to caller.
     *
     * @return the move's power.
     */
    public int getPower() {
        return power;
    }

    /**
     * Return the move's chance to caller.
     *
     * @return the move's chance.
     */
    public int getChance() {
        return chance;
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
