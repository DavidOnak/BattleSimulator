package main;

/**
 *
 * @author David Onak
 */
public class PokemonMove {
    private final int type; // add constants for what each number in type corrisponds to
    private final String name;
    private int pp;

    public PokemonMove(String move) {
        name = move;

        switch (name) {
            case "Pound":
                type = 1; pp = 20;
                break;
            case "Double Slap":
                type = 2; pp = 30;
                break;
            case "Acupressure":
                type = 3; pp = 20;
                break;
            default:
                type = 4; pp = 100;
                System.out.println("The move "+move+" was not recognized!");
        }
    }

    public int dealDamage() {

        return 0;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getPP() {
        return pp;
    }
}
