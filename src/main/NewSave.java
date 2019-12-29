package main;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author David Onak
 */
public class NewSave extends JFrame {
    private final int saveSlot;
    private JFrame newSave;
    private JButton btnFemale;
    private JButton btnMale;
    private JButton btnSelect;
    private JLabel lblName;
    private JLabel lblPokeball;
    private JTextField tfName;

    /**
     * Constructor that creates a JFrame where the user can make a new save in the application,
     * this will take them to MoveSelection afterwards.
     *
     * @param saveSlot the save slot that is being created.
     */
    public NewSave(int saveSlot) {
        System.out.println("NewSave in operation");
        this.saveSlot = saveSlot;

        // make new JFrame
        makeFrame();
        newSave.setVisible(true);
        btnMale.setEnabled(false);
        btnFemale.setEnabled(false);

        // activate buttons
        buttonActivation();

    }

    /**
     * Activates the 3 buttons representing select, female, and male. Select locks typed name and allows
     * user to pick a gender which will take them to MoveSelection.
     */
    private void buttonActivation() {
        btnSelect.addActionListener(e -> {
            if (!tfName.getText().equals("")) {
                btnMale.setEnabled(true);
                btnFemale.setEnabled(true);
                tfName.setEnabled(false);
            }
        });

        btnMale.addActionListener(e -> {
            makeFile(tfName.getText(), "Male");
            newSave.dispose();
            //MoveSelection.;
            new MoveSelection(saveSlot);
        });

        btnFemale.addActionListener(e -> {
            makeFile(tfName.getText(), "Female");
            newSave.dispose();
            new MoveSelection(saveSlot);
        });
    }

    /**
     * Using inputted name, gander, given save slot; this method creates a save slot file for
     * identification and moves, a stats file, and an unlocks file.
     *
     * @param name the name associated with new save.
     * @param gender the gender associated with new save.
     */
    public void makeFile(String name, String gender) {
        if (saveSlot == 1) { // for slot 1
            System.out.println("This is save 1");
            try { // create save file
                PrintWriter writer = new PrintWriter("Slot1.txt", StandardCharsets.UTF_8);
                writer.println(name);
                writer.println(gender);
                writer.close();
            } catch (IOException ex) {
                System.out.println("Problem reading file.");
                System.err.println("IOException: " + ex.getMessage());
            }
            try { // create unlocks file
                PrintWriter writer = new PrintWriter("Slot1unlocks.txt", StandardCharsets.UTF_8);
                writer.close();
            } catch (IOException ex) {
                System.out.println("Problem reading file.");
                System.err.println("IOException: " + ex.getMessage());
            }
            try (FileWriter fw = new FileWriter("Slot1stats.txt", false);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.println(20); // level
                out.println(80); // hp
                out.println(70); // attack
                out.println(80); // defence
                out.println(85); // speed
                out.println(1); // type
                out.println(0); // xp
            } catch (IOException ex) {
                System.out.println("Problem reading file.");
                System.err.println("IOException: " + ex.getMessage());
            }
        } else if (saveSlot == 2) { // for slot 2
            System.out.println("This is save 2");
            try { // create save file
                PrintWriter writer = new PrintWriter("Slot2.txt", StandardCharsets.UTF_8);
                writer.println(name);
                writer.println(gender);
                writer.close();
            } catch (IOException ex) {
                System.out.println("Problem reading file.");
                System.err.println("IOException: " + ex.getMessage());
            }
            try { // create unlocks file
                PrintWriter writer = new PrintWriter("Slot2unlocks.txt", StandardCharsets.UTF_8);
                writer.close();
            } catch (IOException ex) {
                System.out.println("Problem reading file.");
                System.err.println("IOException: " + ex.getMessage());
            }
            try (FileWriter fw = new FileWriter("Slot2stats.txt", false);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.println(20); // level
                out.println(80); // hp
                out.println(70); // attack
                out.println(80); // defence
                out.println(85); // speed
                out.println(1); // type
                out.println(0); // xp
            } catch (IOException ex) {
                System.out.println("Problem reading file.");
                System.err.println("IOException: " + ex.getMessage());
            }
        } else if (saveSlot == 3) { // if slot 3
            System.out.println("This is save 3");
            try { // create save file
                PrintWriter writer = new PrintWriter("Slot3.txt", StandardCharsets.UTF_8);
                writer.println(name);
                writer.println(gender);
                writer.close();
            } catch (IOException ex) {
                System.out.println("Problem reading file.");
                System.err.println("IOException: " + ex.getMessage());
            }
            try { // create unlocks file
                PrintWriter writer = new PrintWriter("Slot3unlocks.txt", StandardCharsets.UTF_8);
                writer.close();
            } catch (IOException ex) {
                System.out.println("Problem reading file.");
                System.err.println("IOException: " + ex.getMessage());
            }
            try (FileWriter fw = new FileWriter("Slot3stats.txt", false);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.println(20); // level
                out.println(80); // hp
                out.println(70); // attack
                out.println(80); // defence
                out.println(85); // speed
                out.println(1); // type
                out.println(0); // xp
            } catch (IOException ex) {
                System.out.println("Problem reading file.");
                System.err.println("IOException: " + ex.getMessage());
            }
        }
    }

    /**
     * Creates a JFrame for NewSave with GUI, sets background image, text, a text field, two
     * buttons to select gender, and a select button when done making the save.
     */
    private void makeFrame() {
        newSave = new JFrame();
        newSave.setSize(300, 300);
        newSave.setResizable(false);
        newSave.setLocationRelativeTo ( null );
        newSave.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // make panel on JFrame
        JPanel panelTransparent = new JPanel();
        panelTransparent.setLayout(null);
        panelTransparent.setOpaque(false);

        // make female button
        btnFemale = new JButton("Female");
        Dimension sizeBF = btnFemale.getPreferredSize();
        btnFemale.setBounds(163, 125, sizeBF.width, sizeBF.height);

        // make male button
        btnMale = new JButton("Male");
        // keep same size as female button
        btnMale.setBounds(43, 125, sizeBF.width, sizeBF.height);

        // make select button
        btnSelect = new JButton("Select");
        Dimension sizeBS = btnSelect.getPreferredSize();
        btnSelect.setBounds(107, 210, sizeBS.width, sizeBS.height);

        // make text "Name:"
        lblName = new JLabel("Name:");
        lblName.setFont(new java.awt.Font("Verdana", Font.BOLD, 18));
        Dimension sizeLN = lblName.getPreferredSize();
        lblName.setBounds(40, 50, sizeLN.width+5, sizeLN.height);

        // make text field
        tfName = new JTextField("");
        tfName.setBounds(110, 50, 120, 30);

        // set background image
        lblPokeball = new JLabel();
        lblPokeball.setIcon(new ImageIcon("images/background/Pokeball.png"));

        // add background to frame
        lblPokeball.setLayout( new BorderLayout() );
        newSave.setContentPane( lblPokeball );

        //add components to panel
        panelTransparent.add(btnFemale);
        panelTransparent.add(btnMale);
        panelTransparent.add(btnSelect);
        panelTransparent.add(lblName);
        panelTransparent.add(tfName);

        newSave.add(panelTransparent);

        // make frame take size of pokeball image
        newSave.pack();
    }
}
