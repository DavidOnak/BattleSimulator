package main;

import javax.swing.*;
import java.awt.*;
import javax.swing.JOptionPane;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author David Onak
 */
public class MainMenu extends JFrame {
    private final int saveSlot;
    private int numOpponents;
    private int tier;
    private JFrame mainMenu;
    private JButton btnStartBattle;
    private JButton btnTier1;
    private JButton btnTier2;
    private JButton btnTier3;
    private JButton btnTier4;
    private JButton btnTier5;
    private JButton btnTier6;
    private JButton btnDatabase;
    private JButton btnSelectMoves;
    private JButton btnQuit;
    private JLabel lblRightBackground;
    private JLabel lblLeftBackground;
    private JLabel lblSelectTier;
    private JLabel lblNumOfOpp;
    private JLabel lblMenu;
    private JLabel lblTitle;
    private JTextField tfNumOpp;

    /**
     * Constructor that creates the main menu for the application, allows for setting up a battle or going to
     * other applications screens being the database and move selection.
     *
     * @param saveSlot the save slot to read and write too.
     */
    public MainMenu(int saveSlot) {
        this.saveSlot = saveSlot;

        // make new frame
        makeFrame();
        mainMenu.setVisible(true);
        btnStartBattle.setEnabled(false);

        // activate buttons
        buttonActivation();

    }

    /**
     * Activates the 6 buttons for the different tiers, and buttons for starting battle, quiting application,
     * going to database, and gains to move selection.
     */
    private void buttonActivation() {
        btnTier1.addActionListener(e -> {
            if (readTextField())
                tier = 1;
        });
        btnTier2.addActionListener(e -> {
            if (readTextField())
                tier = 2;
        });
        btnTier3.addActionListener(e -> {
            if (readTextField())
                tier = 3;
        });
        btnTier4.addActionListener(e -> {
            if (readTextField())
                tier = 4;
        });
        btnTier5.addActionListener(e -> {
            if (readTextField())
                tier = 5;
        });
        btnTier6.addActionListener(e -> {
            if (readTextField())
                tier = 6;
        });
        btnDatabase.addActionListener(e -> {
            new Database(saveSlot);
            mainMenu.dispose();
        });
        btnSelectMoves.addActionListener(e -> {
            new MoveSelection(saveSlot);
            mainMenu.dispose();
        });
        btnQuit.addActionListener(e -> {
            mainMenu.dispose();
            System.exit(0);
        });
        btnStartBattle.addActionListener(e -> {
            new BattleScreen(saveSlot, numOpponents, tier);
            mainMenu.dispose();
        });
    }

    /**
     * Reads the text field and deactivates the tier buttons and text field if valid.
     *
     * @return true if the text field entry is valid and false otherwise.
     */
    private boolean readTextField() {
        boolean validText = false;

        try {
            numOpponents = Integer.parseInt(tfNumOpp.getText());
        } catch (NumberFormatException e) {
            System.out.println("The text field entry is invalid!");
        }

        if (1 <= numOpponents && numOpponents <= 10) {
            validText = true;
            btnStartBattle.setEnabled(true);
            btnTier1.setEnabled(false);
            btnTier2.setEnabled(false);
            btnTier3.setEnabled(false);
            btnTier4.setEnabled(false);
            btnTier5.setEnabled(false);
            btnTier6.setEnabled(false);
            tfNumOpp.setEditable(false);
        } else {
            JOptionPane.showMessageDialog(null, "Please enter a number from 1 to 10.");
        }
        return validText;
    }

    /**
     * Creates a JFrame for MainMenu with GUI, sets 2 background images, text, a text field,
     * buttons for selecting tier, closing application and going to other Jframes.
     */
    private void makeFrame() {
        mainMenu = new JFrame();
        mainMenu.setSize(1350, 720);
        mainMenu.setResizable(false);
        mainMenu.setLocationRelativeTo ( null );
        mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // make panel on JFrame
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // make tier1 to tier6 buttons
        btnTier1 = new JButton("Tier 1");
        btnTier1.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnTier1.setBounds(540, 340, 190, 70);
        btnTier2 = new JButton("Tier 2");
        btnTier2.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnTier2.setBounds(760, 340, 190, 70);
        btnTier3 = new JButton("Tier 3");
        btnTier3.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnTier3.setBounds(980, 340, 190, 70);
        btnTier4 = new JButton("Tier 4");
        btnTier4.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnTier4.setBounds(540, 440, 190, 70);
        btnTier5 = new JButton("Masters");
        btnTier5.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnTier5.setBounds(760, 440, 190, 70);
        btnTier6 = new JButton("Legends");
        btnTier6.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnTier6.setBounds(980, 440, 190, 70);

        // make button to go to database
        btnDatabase = new JButton("Database");
        btnDatabase.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnDatabase.setBounds(70, 330, 170, 50);

        // make button to go to Select Moves
        btnSelectMoves = new JButton("Select Moves");
        btnSelectMoves.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnSelectMoves.setBounds(70, 210, 170, 50);

        // make button to go to Quit game
        btnQuit = new JButton("Quit Game");
        btnQuit.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnQuit.setBounds(70, 570, 170, 50);

        // make button to go to battle screen
        btnStartBattle = new JButton("Start Battle");
        btnStartBattle.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnStartBattle.setBounds(775, 570, 158, 50);

        // make text "lblSelectTier"
        lblSelectTier = new JLabel("Select Tier Level...");
        lblSelectTier.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblSelectTier.setForeground(new Color(255, 255, 255));
        lblSelectTier.setBounds(750, 260, 230, 50);

        // make text "lblNumOfOpp"
        lblNumOfOpp = new JLabel("Number of Opponents:");
        lblNumOfOpp.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNumOfOpp.setForeground(new Color(255, 255, 255));
        Dimension sizeNumOfOpp = lblNumOfOpp.getPreferredSize();
        lblNumOfOpp.setBounds(650, 165, sizeNumOfOpp.width, sizeNumOfOpp.height);

        // make text "lblMenu"
        lblMenu = new JLabel("Menu");
        lblMenu.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblMenu.setForeground(new Color(0, 150, 225));
        Dimension sizeMenu = lblMenu.getPreferredSize();
        lblMenu.setBounds(120, 50, sizeMenu.width + 5, sizeMenu.height);

        // make text "lblTitle"
        lblTitle = new JLabel();
        lblTitle.setIcon(new ImageIcon("images/titles/MainMenuTitle.png"));
        Dimension sizeTitle = lblTitle.getPreferredSize();
        lblTitle.setBounds(610, 10, sizeTitle.width, sizeTitle.height);

        // make text field
        tfNumOpp = new JTextField("");
        tfNumOpp.setBounds(880, 160, 170, 39);

        // add backgrounds to main menu
        lblLeftBackground = new JLabel();
        lblLeftBackground.setIcon(new ImageIcon("images/background/MainBackLeft.jpg"));
        lblLeftBackground.setBounds(0, 0, 320, 720);
        lblRightBackground = new JLabel();
        lblRightBackground.setIcon(new ImageIcon("images/background/MainBackRight.jpg"));
        lblRightBackground.setBounds(320, 0, 1030, 720);

        //add components to panel
        panel.add(tfNumOpp);
        panel.add(lblSelectTier);
        panel.add(lblNumOfOpp);
        panel.add(lblMenu);
        panel.add(lblTitle);
        panel.add(btnTier1);
        panel.add(btnTier2);
        panel.add(btnTier3);
        panel.add(btnTier4);
        panel.add(btnTier5);
        panel.add(btnTier6);
        panel.add(btnStartBattle);
        panel.add(btnDatabase);
        panel.add(btnSelectMoves);
        panel.add(btnQuit);
        panel.add(lblLeftBackground);
        panel.add(lblRightBackground);

        mainMenu.add(panel);
    }
}
