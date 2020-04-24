import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.io.File;

/**
 *
 * @author David Onak
 */
public class SaveSlots extends JFrame {
    public JFrame slots;
    private JButton btnSlot1;
    private JButton btnSlot2;
    private JButton btnSlot3;
    private JLabel lblBackground;
    private String slot1Name = "";
    private String slot2Name = "";
    private String slot3Name = "";

    /**
     * Constructor that creates the save selection page for this application, can
     * continue an old save and go to menu or start a new game going to the NewSave screen.
     */
    public SaveSlots() {

        System.out.println("SaveSlots in operation");

        // read names for saves
        readSaves();

        // make new JFrame
        makeFrame();

        // activate buttons
        buttonActivation();

        // add music
        //music(addImage());

    }

    /**
     * Activates the 3 buttons representing save slots so clicking them will open the save
     * or allow you to make a new one.
     */
    private void buttonActivation() {
        btnSlot1.addActionListener(e -> {
            System.out.println("Save Slot 1");
            slots.dispose();

            // if file does not exist or not read than go to NewSave
            if (slot1Name.equals("New Save") || slot1Name.equals("") ) {
                new NewSave(1);
            } else { // go to main menu
                new MainMenu(1);
            }
        });

        btnSlot2.addActionListener(e -> {
            System.out.println("Save Slot 2");
            slots.dispose();

            // if file does not exist or not read than go to NewSave
            if (slot2Name.equals("New Save") || slot2Name.equals("") ) {
                new NewSave(2);
            } else { // go to main menu
                new MainMenu(2);
            }
        });

        btnSlot3.addActionListener(e -> {
            System.out.println("Save Slot 3");
            slots.dispose();

            // if file does not exist or not read than go to NewSave
            if (slot3Name.equals("New Save") || slot3Name.equals("") ) {
                new NewSave(3);
            } else { // go to main menu
                new MainMenu(3);
            }
        });
    }

    /**
     * Looks for existing save slots and creates strings to be added on slots as names.
     */
    private void readSaves() {

        // check if save 1 exists
        File f1 = new File("saves/Slot1.txt");
        if (f1.exists()) {
            System.out.println("File 1 exists!");
            try{// get name from file
                FileReader s = new FileReader(f1);
                BufferedReader br = new BufferedReader(s);
                slot1Name = br.readLine();
            }catch (IOException e){
                System.out.println("Something went wrong reading the file.");
            }
        } else {// if empty
            slot1Name = "New Save";
        }

        // check if save 2 exists
        File f2 = new File("saves/Slot2.txt");
        if (f2.exists()) {
            System.out.println("File 2 exists!");
            try{//get name from file
                FileReader s = new FileReader(f2);
                BufferedReader br = new BufferedReader(s);
                slot2Name = br.readLine();
            }catch (IOException e){
                System.out.println("Something went wrong reading the file.");
            }
        } else {// if empty
            slot2Name = "New Save";
        }

        //check if save 3 exists
        File f3 = new File("saves/Slot3.txt");
        if (f3.exists()) {
            System.out.println("File 3 exists!");
            try{//get name from file
                FileReader s = new FileReader(f3);
                BufferedReader br = new BufferedReader(s);
                slot3Name = br.readLine();
            }catch (IOException e){
                System.out.println("Something went wrong reading the file.");
            }
        } else {// if empty
            slot3Name = "New Save";
        }
    }

    /**
     * Creates a JFrame for SaveSlots with GUI, sets background image and 3 buttons to hold saves.
     */
    private void makeFrame() {
        slots = new JFrame();
        slots.setSize(850, 520);
        slots.setResizable(false);
        slots.setLocationRelativeTo ( null );
        slots.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // make panel on JFrame
        JPanel panelTransparent = new JPanel();
        panelTransparent.setLayout(null);
        panelTransparent.setOpaque(false);

        // make slot1 button
        btnSlot1 = new JButton(slot1Name);
        btnSlot1.setBounds(150, 150, 135, 210);
        btnSlot1.setBackground(new java.awt.Color(102, 255, 204));
        btnSlot1.setFont(new java.awt.Font("Source Code Pro", Font.BOLD, 18));

        // make slot2 button
        btnSlot2 = new JButton(slot2Name);
        btnSlot2.setBounds(350, 150, 135, 210);
        btnSlot2.setBackground(new java.awt.Color(102, 255, 204));
        btnSlot2.setFont(new java.awt.Font("Source Code Pro", Font.BOLD, 18));

        // make slot3 button
        btnSlot3 = new JButton(slot3Name);
        btnSlot3.setBounds(550, 150, 135, 210);
        btnSlot3.setBackground(new java.awt.Color(102, 255, 204));
        btnSlot3.setFont(new java.awt.Font("Source Code Pro", Font.BOLD, 18));

        // set background image
        lblBackground = new JLabel();
        lblBackground.setBounds(0,0,850, 520);
        int x = (int) (3 * Math.random() + 1);
        switch (x) {
            case 1:
                lblBackground.setIcon(new ImageIcon("images/background/Background1.png"));
                break;
            case 2:
                lblBackground.setIcon(new ImageIcon("images/background/Background2.png"));
                break;
            case 3:
                lblBackground.setIcon(new ImageIcon("images/background/Background3.png"));
        }

        // add background to frame
        lblBackground.setLayout( new BorderLayout() );
        slots.setContentPane( lblBackground );

        //add components to panel
        panelTransparent.add(btnSlot1);
        panelTransparent.add(btnSlot2);
        panelTransparent.add(btnSlot3);

        slots.add(panelTransparent);
    }
}