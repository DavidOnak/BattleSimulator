package main;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author David Onak
 */
public class BattleScreen extends JFrame {
    private JFrame battleScreen;
    private JButton btnMove1;
    private JButton btnMove2;
    private JButton btnMove3;
    private JButton btnMove4;
    private JButton btnNext;
    private JButton btnReturn;
    private JLabel lblBattleField;
    private JLabel lblPlayerText;
    private JLabel lblPlayerText2;
    private JLabel lblPokemonText;
    private JLabel lblPokemonText2;
    private JLabel lblPlayerInfo;
    private JLabel lblCompInfo;
    private JLabel lblPlayer;
    private JLabel lblPlayerName;
    private JLabel lblPlayerLevel;
    private JLabel lblPlayerHealth;
    private JLabel lblPokeLevel;
    private JLabel lblPokeName;
    private JLabel lblPokemon;
    private JProgressBar pbExperience;
    private JProgressBar pbCompHealth;
    private JProgressBar pbPlayerHealth;
    private final int saveSlot;
    private int numOfOpp;
    private final int tier;
    private String name;
    private String gender;
    private PokemonMove playerMove1;
    private PokemonMove playerMove2;
    private PokemonMove playerMove3;
    private PokemonMove playerMove4;
    private PlayerStats player;
    private Pokemon pokemon;

    /**
     * Constructor that generates the battle screen in this application.
     *
     * @param saveSlot the save to retrieve and store stats from.
     * @param numOpp the number of Pokemon to battle.
     * @param tier the tier level for the opponent Pokemon.
     */
    public BattleScreen(int saveSlot, int numOpp, int tier) {
        this.saveSlot = saveSlot;
        numOfOpp = numOpp;
        this.tier = tier;
        player = new PlayerStats(saveSlot);
        pokemon = new Pokemon(tier);

        // get player moves
        getMoves();

        // make new battle screen frame
        makeFrame();
        battleScreen.setVisible(true);
        btnNext.setEnabled(false);
        btnReturn.setEnabled(false);

        // activate buttons
        buttonActivation();
    }

    /**
     * Activates 4 buttons representing the players moves to attack opponent, a button to get next Pokemon, and
     * a button to go back to the main menu.
     */
    private void buttonActivation() {
        btnMove1.addActionListener(e -> {
            playerMove1.consumePP();
            btnMove1.setText(playerMove1.getName() + "   PP " + playerMove1.getPP() + "/" + playerMove1.getInitialPP());
            if (playerMove1.getPP() == 0)
                btnMove1.setEnabled(false);

            playerAttack(playerMove1);
        });
        btnMove2.addActionListener(e -> {
            playerMove2.consumePP();
            btnMove2.setText(playerMove2.getName() + "   PP " + playerMove2.getPP() + "/" + playerMove2.getInitialPP());
            if (playerMove2.getPP() == 0)
                btnMove2.setEnabled(false);

            playerAttack(playerMove2);
        });
        btnMove3.addActionListener(e -> {
            playerMove3.consumePP();
            btnMove3.setText(playerMove3.getName() + "   PP " + playerMove3.getPP() + "/" + playerMove3.getInitialPP());
            if (playerMove3.getPP() == 0)
                btnMove3.setEnabled(false);

            playerAttack(playerMove3);
        });
        btnMove4.addActionListener(e -> {
            playerMove4.consumePP();
            btnMove4.setText(playerMove4.getName() + "   PP " + playerMove4.getPP() + "/" + playerMove4.getInitialPP());
            if (playerMove4.getPP() == 0)
                btnMove4.setEnabled(false);

            playerAttack(playerMove4);
        });
        btnNext.addActionListener(e -> {
            pokemon = new Pokemon(tier);
            makeFrame();
        });
        btnReturn.addActionListener(e -> {
            new MainMenu(saveSlot);
            battleScreen.dispose();
        });
    }

    /**
     * Performs attack of player onto the opponent pokemon with a given move.
     *
     * @param move the PokemonMove to attack with.
     */
    private void playerAttack(PokemonMove move) {
        lblPlayerText.setText("You used " + move.getName() + "!");

        // get potential damage from move
        int damage = move.determineDamage(player.getLevel(), player.getAttack(), pokemon.getDefence(), pokemon.getFirstType(), pokemon.getSecondType());
        int actualDamage = move.getActualDamage();
        int rawDamage = move.getRawDamage();

        // determine if a hit or miss
        Random rand = new Random();
        if (rand.nextInt(100) <= move.getAccuracy()) {

            // deal damage
            pokemon.dealDamage(damage);
            drainPokemonHealth(pokemon.getHealth());

            // give effect message
            if (actualDamage == 0)
                lblPlayerText.setText("You used " + move.getName() + ", it has no effect!");
            else if (actualDamage > rawDamage)
                lblPlayerText.setText("You used " + move.getName() + ", it is super effective!");
            else if (rawDamage > actualDamage)
                lblPlayerText.setText("You used " + move.getName() + ", it is not very effective!");

            if (pokemon.getHealth() == 0) {
                lblPokemonText2.setText(pokemon.getName() + " has fainted!");
                int originalLevel = player.getLevel();
                player.addXP(pokemon.getXPReward());
                lblPokemon.setIcon(null);

                while (originalLevel <= player.getLevel()) {
                    if (originalLevel < player.getLevel()) {
                        addPlayerExperience(player.calculateNextLevel(originalLevel));
                        pbExperience.setValue(0);
                        pbExperience.setMaximum(player.calculateNextLevel(originalLevel));
                        moveLearned(originalLevel);
                        lblPlayerLevel.setText("Lv." + player.getLevel());
                    } else {
                        addPlayerExperience(player.getXP());
                    }
                    originalLevel++;
                }

                lblPlayerHealth.setText(player.getHealth() + "/" + player.getInitialHealth());
                // lblPlayerHealth.setText(); rise up the heath info and adjust the bar too
                numOfOpp--;

                if (numOfOpp > 0) {
                    btnNext.setEnabled(true);
                } else {
                    btnReturn.setEnabled(true);
                }
            } else { // if move hit but did not K.O
                pokemonMove();
            }
        }
    }

    private void pokemonMove() {
        // AI to get move to attack with
        PokemonMove attackMove = pokemon.chooseMove(player.getDefence(), player.getType());
        pokemon.removePP(attackMove);
        lblPokemonText.setText(pokemon.getName() + " used " + attackMove.getName() + "!");

        // get damage with move
        int damage = attackMove.determineDamage(pokemon.getLevel(), pokemon.getAttack(), player.getDefence(), player.getType(), 0);
        int actualDamage = attackMove.getActualDamage();
        int rawDamage = attackMove.getRawDamage();

        // determine if a hit or miss
        Random rand = new Random();
        if (rand.nextInt(100) <= attackMove.getAccuracy()) {

            // deal damage
            player.dealDamage(damage);
            drainPlayerHealth(player.getHealth());

            // give effect message
            if (actualDamage == 0)
                lblPokemonText.setText(pokemon.getName() + " used " + attackMove.getName() + ", it has no effect!");
            else if (actualDamage > rawDamage)
                lblPokemonText.setText(pokemon.getName() + " used " + attackMove.getName() + ", it is super effective!");
            else if (rawDamage > actualDamage)
                lblPokemonText.setText(pokemon.getName() + " used " + attackMove.getName() + ", it is not very effective!");

            if (player.getHealth() == 0) {
                lblPlayerText2.setText("You have fainted!");
                lblPlayer.setIcon(null);

                btnReturn.setEnabled(true);
            }
        } else { // if move misses
            lblPokemonText.setText(pokemon.getName() + " used " + attackMove.getName() + ", but it missed!");
        }
    }

    private void moveLearned(int currentLevel) {
        boolean learned = false;
        int check = currentLevel;

        // unlock moves up to level 60
        if (check <= 60) {
            // check if multiple of 5
            while (check > 0) {
                check -= 5;
                if (check == 0) {
                    learned = true;
                    break;
                }
            }
        }

        if (learned) {
            ArrayList<String> unlocks = new ArrayList<>();
            switch (saveSlot) {
                case 1:
                    unlocks = retrieveUnlocks("saves/Slot1unlocks.txt");
                    break;
                case 2:
                    unlocks = retrieveUnlocks("saves/Slot2unlocks.txt");
                    break;
                case 3:
                    unlocks = retrieveUnlocks("saves/Slot3unlocks.txt");
            }

            String[] extraMoves = {"Judgment", "V-create", "Hydro Cannon", "Parabolic Charge", "Cotton Guard",
                    "Clanging Scales", "Shadow Ball", "Snarl", "Night Daze", "Illuminati"};
            String unlocked = "";

            if (unlocks.size() < 9) {
                // get new move that has not been unlocked yet
                int u = (int) (9 * Math.random());
                while (unlocks.contains(extraMoves[u])) {
                    u = (int) (9 * Math.random());
                }

                unlocks.add(extraMoves[u]);
                unlocked = extraMoves[u];

            } else if (unlocks.size() == 9) {
                unlocked = extraMoves[9];
            }

            // store unlocks
            switch (saveSlot) {
                case 1:
                    storeUnlocks("saves/Slot1unlocks.txt", unlocks);
                    break;
                case 2:
                    storeUnlocks("saves/Slot2unlocks.txt", unlocks);
                    break;
                case 3:
                    storeUnlocks("saves/Slot3unlocks.txt", unlocks);
            }

            if (!unlocked.equals(""))
                lblPlayerText2.setText("You unlocked " + unlocked);
        }
    }

    private ArrayList<String> retrieveUnlocks(String file) {
        ArrayList<String> unlocks = new ArrayList<>();

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String move = br.readLine();
            while (move != null) {
                unlocks.add(move);
                move = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Something went wrong reading the file.");
        }

        return unlocks;
    }

    private void storeUnlocks(String file, ArrayList<String> unlocks) {
        try (FileWriter fw = new FileWriter(file, false);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            for (String move: unlocks) {
                out.println(move);
            }
        } catch (IOException e) {
            System.out.println("Something went wrong writing the file.");
        }
    }

    /**
     * Lowers the health of the player while sliding health bar down to target health.
     *
     * @param targetHealth the health to go down too.
     */
    private void drainPlayerHealth(int targetHealth) {
        int i = pbPlayerHealth.getValue();
        System.out.println("Going to " + targetHealth + " for player health");
        try {
            while (i > targetHealth) {
                // delay the thread
                Thread.sleep(175);

                i --;
                pbPlayerHealth.paintImmediately(0, 0, 200, 200);
                pbPlayerHealth.setValue(i);
                lblPlayerHealth.setText(i + "/" + player.getInitialHealth());

                pbPlayerHealth.setForeground(colourCheck(pbPlayerHealth.getValue()));
            }
        }
        catch (InterruptedException ex) {
            System.out.println("An Interrupted Exception occurred when trying to stall");
        }
    }

    /**
     * Lowers the health of the pokemon while sliding health bar down to target health.
     *
     * @param targetHealth the health to go down too.
     */
    private void drainPokemonHealth(int targetHealth) {
        int i = pbCompHealth.getValue();
        System.out.println("Going to " + targetHealth);
        try {
            while (i > targetHealth) {
                // delay the thread
                Thread.sleep(175);

                pbCompHealth.paintImmediately(0, 0, 200, 200);
                pbCompHealth.setValue(i - 1);
                i --;

                pbCompHealth.setForeground(colourCheck(pbCompHealth.getValue()));
            }
        }
        catch (InterruptedException ex) {
            System.out.println("An Interrupted Exception occurred when trying to stall");
        }
    }

    /**
     * Rises the experience of the player while sliding experience bar down to target experience.
     *
     * @param targetExperience the experience to go up too.
     */
    private void addPlayerExperience(int targetExperience) {
        int i = pbExperience.getValue();
        System.out.println("Going to " + targetExperience);
        try {
            while (i < targetExperience) {
                // delay the thread
                Thread.sleep(100);

                pbExperience.paintImmediately(0, 0, 270, 200);
                pbExperience.setValue(i + 1);
                i ++;
            }
        }
        catch (InterruptedException ex) {
            System.out.println("An Interrupted Exception occurred when trying to stall");
        }
    }

    private Color colourCheck (double health) {
        double initialHealth = pokemon.getInitialHealth();
        double percent = (health / initialHealth) * 100;
        Color healthColour = new Color(51, 255, 51);

        if (percent <= 90) {
            if (percent > 85)
                healthColour = new Color(86, 255, 47);
            else if (percent > 75)
                healthColour = new Color(189, 255, 49);
            else if (percent > 65)
                healthColour = new Color(232, 255, 46);
            else if (percent > 60)
                healthColour = new Color(255, 241, 41);
            else if (percent > 50)
                healthColour = new Color(255, 207, 34);
            else if (percent > 40)
                healthColour = new Color(255, 174, 37);
            else if (percent > 30)
                healthColour = new Color(255, 135, 41);
            else if (percent > 20)
                healthColour = new Color(255, 104, 39);
            else if (percent > 10)
                healthColour = new Color(255, 80, 38);
            else
                healthColour = new Color(255, 46, 31);
        }
        return healthColour;
    }

    /**
     * Gets moves that have been saved from before and put them onto the global variable moves.
     * Assumes exactly 4 selected moves exists in save.
     */
    private void getMoves() {
        switch (saveSlot) { // retrieve moves
            case 1:
                readMoves("saves/Slot1.txt");
                break;
            case 2:
                readMoves("saves/Slot2.txt");
                break;
            case 3:
                readMoves("saves/Slot3.txt");
        }
    }

    /**
     * Reads stored moves from text file.
     *
     * @param file the text file to read from.
     */
    private void readMoves(String file) {
        try { // get moves from file
            FileReader s = new FileReader(file);
            BufferedReader br = new BufferedReader(s);
            name = br.readLine();
            gender = br.readLine();
            for (int y = 0; y < 4; y++) {
                switch (y) {
                    case 0: playerMove1 = new PokemonMove(br.readLine());
                        break;
                    case 1: playerMove2 = new PokemonMove(br.readLine());
                        break;
                    case 2: playerMove3 = new PokemonMove(br.readLine());
                        break;
                    case 3: playerMove4 = new PokemonMove(br.readLine());
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong reading the file.");
        }
    }

    /**
     * Creates a background colour corresponding to the type that a PokemonMove is.
     *
     * @param move the PokemonMove type to evaluate.
     * @return Color that is associated with the type's background. If type is not recognized (not 1-8),
     * then the colour will be black.
     */
    private Color getButtonColour(PokemonMove move) {
        Color colour = new Color(0, 0, 0);

        switch (move.getType()) {
            case 1: // normal
                colour = new Color(240, 240, 240);
                break;
            case 2: // fire
                colour = new Color(255, 51, 0);
                break;
            case 3: // water
                colour = new Color(102, 153, 255);
                break;
            case 4: // electric
                colour = new Color(255, 255, 0);
                break;
            case 5: // grass
                colour = new Color(0, 255, 0);
                break;
            case 6: // dragon
                colour = new Color(153, 153, 255);
                break;
            case 7: // ghost
                colour = new Color(102, 0, 102);
                break;
            case 8: // dark
                colour = new Color(0, 0, 0);
        }

        return colour;
    }

    /**
     * Creates a text colour corresponding to the type that a PokemonMove is.
     *
     * @param move the PokemonMove type to evaluate.
     * @return Color that is associated with the type's text. If type is not recognized (not 1-8),
     * then the colour will be black.
     */
    private Color getTextColour(PokemonMove move) {
        Color colour = new Color(0, 0, 0);

        if (move.getType() == 7)
            colour = new Color(204, 204, 204);
        else if ( move.getType() == 8)
            colour = new Color(255, 255, 255);

        return colour;
    }

    /**
     * Generates a random image out of 6 possible battle field images
     *
     * @return An ImageIcon representing a battle field image.
     */
    private ImageIcon generateRandomBattleField() {
        ImageIcon image;

        switch ((int) (6 * Math.random()+1)) {
            case 1: image = new ImageIcon("images/background/BattleScreen1.PNG");
                break;
            case 2: image = new ImageIcon("images/background/BattleScreen2.PNG");
                break;
            case 3: image = new ImageIcon("images/background/BattleScreen3.PNG");
                break;
            case 4: image = new ImageIcon("images/background/BattleScreen4.PNG");
                break;
            case 5: image = new ImageIcon("images/background/BattleScreen5.PNG");
                break;
            default: image = new ImageIcon("images/background/BattleScreen6.PNG");
        }

        return image;
    }

    //make method to get random file dir for batlle field, and for getting colours
    private void makeFrame() {
        battleScreen = new JFrame();
        battleScreen.setSize(1350, 720);
        battleScreen.setResizable(false);
        battleScreen.setLocationRelativeTo(null);
        battleScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // make panel on JFrame
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // make buttons for 4 moves
        btnMove1 = new JButton(playerMove1.getName() + "   PP " + playerMove1.getPP() + "/" + playerMove1.getInitialPP());
        btnMove1.setBackground(getButtonColour(playerMove1));
        btnMove1.setForeground(getTextColour(playerMove1));
        btnMove1.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnMove1.setBounds(640, 490, 240, 80);
        btnMove2 = new JButton(playerMove2.getName() + "   PP " + playerMove2.getPP() + "/" + playerMove2.getInitialPP());
        btnMove2.setBackground(getButtonColour(playerMove2));
        btnMove2.setForeground(getTextColour(playerMove2));
        btnMove2.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnMove2.setBounds(920, 490, 240, 80);
        btnMove3 = new JButton(playerMove3.getName() + "   PP " + playerMove3.getPP() + "/" + playerMove3.getInitialPP());
        btnMove3.setBackground(getButtonColour(playerMove3));
        btnMove3.setForeground(getTextColour(playerMove3));
        btnMove3.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnMove3.setBounds(640, 590, 240, 80);
        btnMove4 = new JButton(playerMove4.getName() + "   PP " + playerMove4.getPP() + "/" + playerMove4.getInitialPP());
        btnMove4.setBackground(getButtonColour(playerMove4));
        btnMove4.setForeground(getTextColour(playerMove4));
        btnMove4.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnMove4.setBounds(920, 590, 240, 80);

        // make next button
        btnNext = new JButton("Next");
        btnNext.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnNext.setBounds(1220, 590, 90, 70);

        // make return button
        btnReturn = new JButton("Return");
        btnReturn.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnReturn.setBounds(1220, 490, 90, 70);

        // add labels for info panels for player and computer
        lblCompInfo = new JLabel();
        lblCompInfo.setIcon(new ImageIcon("images/content/InfoPanel.png"));
        lblCompInfo.setBounds(940, 360, 290, 100);
        lblPlayerInfo = new JLabel();
        lblPlayerInfo.setIcon(new ImageIcon("images/content/InfoPanel.png"));
        lblPlayerInfo.setBounds(310, 40, 290, 100);

        // add content labels to player and computer info panels
        lblPlayerName = new JLabel(name);
        lblPlayerName.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblPlayerName.setBounds(960, 360, 160, 30);
        lblPlayerLevel = new JLabel("Lv."+player.getLevel());
        lblPlayerLevel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblPlayerLevel.setBounds(1170, 360, 60, 30);
        lblPlayerHealth = new JLabel(player.getHealth()+"/"+player.getInitialHealth());
        lblPlayerHealth.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblPlayerHealth.setBounds(1070, 410, 44, 30);
        lblPokeName = new JLabel(pokemon.getName());
        lblPokeName.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblPokeName.setBounds(330, 50, 160, 30);
        if (pokemon.getLevel() == 115)
            lblPokeLevel = new JLabel("Lv.X");
        else
            lblPokeLevel = new JLabel("Lv."+pokemon.getLevel());
        lblPokeLevel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblPokeLevel.setBounds(530, 50, 60, 30);

        // add progress bars to player and computer info panels
        pbCompHealth = new JProgressBar();
        pbCompHealth.setForeground(new Color(51, 255, 51));
        pbCompHealth.setBounds(390, 100, 146, 14);
        pbCompHealth.setMaximum(pokemon.getInitialHealth());
        pbCompHealth.setValue(pokemon.getHealth());
        pbExperience = new JProgressBar();
        pbExperience.setForeground(new Color(0, 153, 204));
        pbExperience.setBounds(960, 440, 260, 10);
        pbExperience.setMaximum(player.getXPForNextLevel());
        pbExperience.setValue(player.getXP());
        pbPlayerHealth = new JProgressBar();
        pbPlayerHealth.setForeground(new Color(51, 255, 51));
        pbPlayerHealth.setBounds(1020, 400, 146, 14);
        pbPlayerHealth.setMaximum(player.getInitialHealth());
        pbPlayerHealth.setValue(player.getHealth());

        // add battle field image
        lblBattleField = new JLabel();
        lblBattleField.setIcon(generateRandomBattleField());
        lblBattleField.setBounds(0, 0, 1350, 470);

        // add characters to battle field
        lblPokemon = new JLabel();
        if (pokemon.getName().equals("Death Angle"))
            lblPokemon.setIcon(new ImageIcon("images/pokemon/"+pokemon.getName()+".png"));
        else
            lblPokemon.setIcon(new ImageIcon("images/pokemon/"+pokemon.getName()+".gif"));
        lblPokemon.setBounds(830, 20, 370, 290);
        lblPlayer = new JLabel();
        lblPlayer.setIcon(new ImageIcon("images/pokemon/Player"+gender+".png"));
        lblPlayer.setBounds(190, 220, 350, 250);

        // add text on player
        lblPlayerText = new JLabel("A wild "+pokemon.getName()+" appeared! What do you use?");
        lblPlayerText.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblPlayerText.setBounds(10, 480, 350, 120);
        lblPlayerText2 = new JLabel();
        lblPlayerText2.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblPlayerText2.setBounds(380, 480, 280, 120);

        // add text on pokemon
        lblPokemonText = new JLabel();
        lblPokemonText.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblPokemonText.setBounds(10, 590, 350, 120);
        lblPokemonText2 = new JLabel();
        lblPokemonText2.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblPokemonText2.setBounds(360, 590, 280, 120);

        //add components to panel
        panel.add(btnNext);
        panel.add(btnReturn);
        panel.add(btnMove1);
        panel.add(btnMove2);
        panel.add(btnMove3);
        panel.add(btnMove4);
        panel.add(lblPlayerText);
        panel.add(lblPlayerText2);
        panel.add(lblPokemonText2);
        panel.add(lblPokemonText);
        panel.add(lblPlayerHealth);
        panel.add(lblPlayerName);
        panel.add(lblPlayerLevel);
        panel.add(lblPokeName);
        panel.add(lblPokeLevel);
        panel.add(lblPokemon);
        panel.add(lblPlayer);
        panel.add(pbCompHealth);
        panel.add(pbExperience);
        panel.add(pbPlayerHealth);
        panel.add(lblCompInfo);
        panel.add(lblPlayerInfo);
        panel.add(lblBattleField);
        
        battleScreen.add(panel);
    }
}