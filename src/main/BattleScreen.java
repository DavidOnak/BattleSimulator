package main;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
    private JLabel lblBattleText;
    private JLabel lblHealth;
    private JLabel lblPlayerInfo;
    private JLabel lblCompInfo;
    private JLabel lblPlayer;
    private JLabel lblPlayerName;
    private JLabel lblPlayerLevel;
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

    public BattleScreen(int saveSlot, int numOpp, int tier) {
        this.saveSlot = saveSlot;
        numOfOpp = numOpp;
        this.tier = tier;
        player = new PlayerStats(saveSlot);

        // get player moves
        getMoves();

        // make new battle screen frame
        makeFrame();
        battleScreen.setVisible(true);

        // activate buttons
        //buttonActivation();
    }

    private void buttonActivation() {
        btnMove1.addActionListener(e -> {

        });
        btnMove2.addActionListener(e -> {

        });
        btnMove3.addActionListener(e -> {

        });
        btnMove4.addActionListener(e -> {

        });
        btnNext.addActionListener(e -> {

        });
        btnReturn.addActionListener(e -> {

        });
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
        btnMove1 = new JButton(playerMove1.getName());
        btnMove1.setBackground(getButtonColour(playerMove1));
        btnMove1.setForeground(getTextColour(playerMove1));
        btnMove1.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnMove1.setBounds(640, 490, 240, 80);
        btnMove2 = new JButton(playerMove2.getName());
        btnMove2.setBackground(getButtonColour(playerMove2));
        btnMove2.setForeground(getTextColour(playerMove2));
        btnMove2.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnMove2.setBounds(920, 490, 240, 80);
        btnMove3 = new JButton(playerMove3.getName());
        btnMove3.setBackground(getButtonColour(playerMove3));
        btnMove3.setForeground(getTextColour(playerMove3));
        btnMove3.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnMove3.setBounds(640, 590, 240, 80);
        btnMove4 = new JButton(playerMove4.getName());
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

        // add content to player and computer info panels


        // TODO: FIX STUFF BELOW
        lblBattleText.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(lblBattleText);
        lblBattleText.setBounds(10, 590, 350, 120);

        lblPokemon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pokemon/Slim.png"))); // NOI18N
        getContentPane().add(lblPokemon);
        lblPokemon.setBounds(830, 20, 370, 290);

        lblPokeName.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblPokeName.setText("Charzard");
        getContentPane().add(lblPokeName);
        lblPokeName.setBounds(330, 50, 160, 30);

        lblPokeLevel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPokeLevel.setText("Lv.15");
        getContentPane().add(lblPokeLevel);
        lblPokeLevel.setBounds(530, 50, 60, 30);

        pbHealth.setForeground(new java.awt.Color(51, 255, 51));
        getContentPane().add(pbHealth);
        pbHealth.setBounds(390, 100, 146, 14);

        lblPokeLevel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPokeLevel1.setText("Lv.15");
        getContentPane().add(lblPokeLevel1);
        lblPokeLevel1.setBounds(1170, 360, 60, 30);

        lblPokeName1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblPokeName1.setText("Me");
        getContentPane().add(lblPokeName1);
        lblPokeName1.setBounds(960, 360, 160, 30);

        lblHealth.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblHealth.setText("56/56");
        getContentPane().add(lblHealth);
        lblHealth.setBounds(1070, 410, 44, 30);

        pbExperience.setForeground(new java.awt.Color(0, 153, 204));
        getContentPane().add(pbExperience);
        pbExperience.setBounds(960, 440, 260, 10);

        pbPHealth.setForeground(new java.awt.Color(51, 255, 51));
        getContentPane().add(pbPHealth);
        pbPHealth.setBounds(1020, 400, 146, 14);



        lblPlayer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pokemon/Player.png"))); // NOI18N
        getContentPane().add(lblPlayer);
        lblPlayer.setBounds(190, 220, 350, 250);

        lblBattleBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pokemon/BB1.PNG"))); // NOI18N
        getContentPane().add(lblBattleBackground);
        lblBattleBackground.setBounds(0, 0, 1350, 470);

        lblBattleText3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(lblBattleText3);
        lblBattleText3.setBounds(360, 590, 280, 120);

        lblBattleText2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(lblBattleText2);
        lblBattleText2.setBounds(360, 480, 280, 120);

        lblBattleText1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblBattleText1.setText("Text area for battle");
        getContentPane().add(lblBattleText1);
        lblBattleText1.setBounds(10, 480, 340, 120);

        //add components to panel
        panel.add(btnNext);
        panel.add(btnReturn);
        panel.add(btnMove1);
        panel.add(btnMove2);
        panel.add(btnMove3);
        panel.add(btnMove4);
        panel.add(lblCompInfo);
        panel.add(lblPlayerInfo);
        
        battleScreen.add(panel);
    }
}
