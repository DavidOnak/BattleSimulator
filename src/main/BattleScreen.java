package main;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author David Onak
 */
public class BattleScreen extends JFrame {
    // TODO: JavaDOX!!!, it stats for moves make each player type a constant so ex. NORMAL is 1
//FOR BATTLE SCREEN HAVE MOVE Class have method for damage(return int array to account multi hit) and effect in stats(return string array) repretly
    private JFrame battleScreen;
    private JButton btnMove1;
    private JButton btnMove2;
    private JButton btnMove3;
    private JButton btnMove4;
    private JButton btnNext;
    private JButton btnReturn;
    private JLabel lblBattleField;
    private JLabel lblPlayerText;
    private JLabel lblPokemonText;
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

    // ADD JAVADOX
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

    // ADD JAVADOX
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

        });
        btnReturn.addActionListener(e -> {

        });
    }

    private void playerAttack(PokemonMove move) { // maybe change type to double, easy to do, just run effects with damage twice
        lblPlayerText.setText("You used " + move.getName() + "!");

        // get raw damage and apply effect
        int actualDamage;
        int rawDamage = move.calculateRawDamage(1,1,1);
        switch (move.findEffect(1)) { // get dam first and adjust text later for timing perpusers
            case "None":
                actualDamage = 0;
                lblPlayerText.setText("You used " + move + ", it has no effect!");
                break;
            case "not every effective":
                actualDamage = rawDamage / 2;
                lblPlayerText.setText("You used " + move + ", it is not very effective!");
                break;
            case "Normal":
                actualDamage = rawDamage;
                lblPlayerText.setText("You used " + move + "!");
            default:
                actualDamage = rawDamage * 2;
                lblPlayerText.setText("You used " + move + ", it is super effective!");
        }
        int ran = (int) (15 * Math.random() + 85);//random factor
        d4 = (d4 * ran) / 100;
        Random rand = new Random();
        if (rand.nextInt(100) < a) {
            ac = true;
        }
        if (ac == true) {
            statsC[0] = statsC[0] - d3;
            System.out.println(d3);
            System.out.println(d4 + " damage done");
            System.out.println("");
        } else {
            lblBattleText1.setText("You used " + move + ", but it missed!");
            System.out.println("Attack missed!");
            System.out.println("");
        }
        pbHealth.setValue(statsC[0]);
        r--;

    }

    private void waitOneSecond() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            System.out.println("Interrupted Exception from one second delay");
        }
    }

    /**
     * Gets moves that have been saved from before and put them onto the global variable moves.
     * Assumes exactly 4 selected moves exists in save.
     */
    private void getMoves() {
        switch (saveSlot) { // retrieve moves
            case 1:
                readMoves("Slot1.txt");
                break;
            case 2:
                readMoves("Slot2.txt");
                break;
            case 3:
                readMoves("Slot3.txt");
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
        lblPlayerText.setBounds(10, 480, 340, 120);

        // add text on pokemon
        lblPokemonText = new JLabel();
        lblPokemonText.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblPokemonText.setBounds(10, 590, 350, 120);

        // TODO: FIX STUFF BELOW
        /*
        lblBattleText3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(lblBattleText3);
        lblBattleText3.setBounds(360, 590, 280, 120);

        lblBattleText2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(lblBattleText2);
        lblBattleText2.setBounds(360, 480, 280, 120);
*/

        //add components to panel
        panel.add(btnNext);
        panel.add(btnReturn);
        panel.add(btnMove1);
        panel.add(btnMove2);
        panel.add(btnMove3);
        panel.add(btnMove4);
        panel.add(lblPlayerText);
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
