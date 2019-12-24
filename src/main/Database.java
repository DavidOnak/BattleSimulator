package main;

import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.util.ArrayList;

/**
 *
 * @author David Onak
 */
public class Database extends JFrame{
    private final int saveSlot;
    private JFrame database;
    private JButton btnMainMenu;
    private JButton btnMoveSelection;
    private boolean fromMainMenu;
    private ArrayList<String> moves = new ArrayList<>();
    private String[] identification = new String[2];

    public Database(int saveSlot, ArrayList<String> moves, String[] identification) {
        this.saveSlot = saveSlot;
        this.moves = moves;
        this.identification = identification;
        fromMainMenu = false;

        // create new database frame
        makeFrame();
        database.setVisible(true);
        btnMainMenu.setEnabled(false);

        // activate buttons
        buttonActivation();
    }

    public Database(int saveSlot) {
        this.saveSlot = saveSlot;
        fromMainMenu = true;

        // create new database frame
        makeFrame();
        database.setVisible(true);

        // activate buttons
        buttonActivation();
    }

    private void buttonActivation() {
        btnMainMenu.addActionListener(e -> {
            new MainMenu(saveSlot);
            database.dispose();
        });
        btnMoveSelection.addActionListener(e -> {
            if (fromMainMenu) {
                new MoveSelection(saveSlot);
            } else {
                new MoveSelection(saveSlot, moves, identification);
            }
            database.dispose();
        });
    }

    private void makeFrame() {
        database = new JFrame();
        database.setSize(1350, 720);
        database.setResizable(false);
        database.setLocationRelativeTo(null);
        database.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // make panel on JFrame
        JPanel panel = new JPanel();
        panel.setLayout(null);
        //panel.setOpaque(false);


        // make mainMenu button
        btnMainMenu = new JButton("Main Menu");
        btnMainMenu.setFont(new java.awt.Font("Tahoma", Font.BOLD, 18));
        Dimension sizeBMM = btnMainMenu.getPreferredSize();
        btnMainMenu.setBounds(660, 620, sizeBMM.width, sizeBMM.height);

        // make moveSelection button
        btnMoveSelection = new JButton("Move Selection");
        btnMoveSelection.setFont(new java.awt.Font("Tahoma", Font.BOLD, 18));
        Dimension sizeBMS = btnMoveSelection.getPreferredSize();
        btnMoveSelection.setBounds(600, 570, sizeBMS.width, sizeBMS.height);

        //add components to panel
        panel.add(btnMoveSelection);
        panel.add(btnMainMenu);

        database.add(panel);
    }
}
