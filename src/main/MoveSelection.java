package main;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author David Onak
 */
public class MoveSelection {
    private final int saveSlot;
    private JFrame moveSelection;
    private JButton btnMovePound;
    private JButton btnMoveDoubleSlap;
    private JButton btnMoveAcupressure;
    private JButton btnMoveHoldHands;
    private JButton btnMoveFireSpin;
    private JButton btnMoveEmber;
    private JButton btnMoveBubble;
    private JButton btnMoveWithdraw;
    private JButton btnMoveThunderShock;
    private JButton btnMoveChargeBeam;
    private JButton btnMoveLeafage;
    private JButton btnMoveAbsorb;
    private JButton btnMoveTwister;
    private JButton btnMoveAstonish;
    private JButton btnMoveJudgment;
    private JButton btnMoveHydroCannon;
    private JButton btnMoveCottonGuard;
    private JButton btnMoveShadowBall;
    private JButton btnMoveNightDaze;
    private JButton btnMoveVCreate;
    private JButton btnMoveParabolicCharge;
    private JButton btnMoveClangingScales;
    private JButton btnMoveSnarl;
    private JButton btnMoveIlluminati;
    private JButton btnDatabase;
    private JButton btnDone;
    private JButton btnReset;
    private JLabel lblBackground;
    private JLabel lblTitle;
    private String[] identification = new String[2];
    private ArrayList<String> moves = new ArrayList<>();

    /**
     * Constructor that creates the move selection screen for this application, allows the user to pick upt to 4
     * moves and go into database or store the moves and go to the main menu.
     *
     * @param saveSlot the save slot to read and write too.
     */
    public MoveSelection(int saveSlot) {
        this.saveSlot = saveSlot;

        getMoves();

        // make new JFrame
        makeFrame();
        if (moves.size() == 4)
            btnDone.setEnabled(true);
        else
            btnDone.setEnabled(false);

        moveSelection.setVisible(true);

        // activate buttons
        buttonActivation();

        // disable locked moves
        setLockedMoves();

        // print moves from save
        System.out.println("*****");
        for (String move: moves) {
            System.out.println(move);
        }
    }

    /**
     * Constructor that creates the move selection screen for this application, allows the user to pick upt to 4
     * moves and go into database or store the moves and go to the main menu. If user enters this screen from
     * database, the moves they selected will still be selected.
     *
     * @param saveSlot the save slot to read and write too.
     * @param moves the moves that are already selected.
     * @param identification the name and gender of the user.
     */
    public MoveSelection(int saveSlot, ArrayList<String> moves, String[] identification) {
        this.saveSlot = saveSlot;
        this.moves = moves;
        this.identification = identification;

        // make new JFrame
        makeFrame();
        if (moves.size() == 4)
            btnDone.setEnabled(true);
        else
            btnDone.setEnabled(false);

        moveSelection.setVisible(true);

        // activate buttons
        buttonActivation();

        // disable locked moves
        setLockedMoves();

        // print moves selected
        System.out.println("*****");
        for (String move: moves) {
            System.out.println(move);
        }
    }

    /**
     * Activates the 24 buttons representing different moves that can be added, a button "reset" that can deselect
     * chosen moves, a button that can go to database keeping currently selected moves, and a button "done" to
     * finish selecting moves and go to main menu.
     */
    private void buttonActivation() {
        btnMovePound.addActionListener(e -> {
            //pp 35 power 40 accuracy 100%
            if (addMove("Pound") == 1) {
                btnMovePound.setBackground(new Color(153, 153, 153));
            }
        });

        btnMoveDoubleSlap.addActionListener(e -> {
            //Hits 2-5 times in one turn.
            //pp 10 power 15 accuracy 85%
            if (addMove("Double Slap") == 1)
                btnMoveDoubleSlap.setBackground(new Color(153, 153, 153));
        });

        btnMoveAcupressure.addActionListener(e -> {
            //Sharply raises a random stat.
            //pp 30 power - accuracy -
            if (addMove("Acupressure") == 1)
                btnMoveAcupressure.setBackground(new Color(153, 153, 153));
        });

        btnMoveHoldHands.addActionListener(e -> {
            //Makes the user and an ally very happy.
            //pp 40
            if (addMove("Hold Hands") == 1)
                btnMoveHoldHands.setBackground(new Color(153, 153, 153));
        });

        btnMoveFireSpin.addActionListener(e -> {
            //Traps opponent, damaging them for 4-5 turns.
            //pp 15 power 35 accuracy 85%
            if (addMove("Fire Spin") == 1)
                btnMoveFireSpin.setBackground(new Color(153, 153, 153));
        });

        btnMoveEmber.addActionListener(e -> {
            //pp 25 power 40 accuracy 100%
            if (addMove("Ember") == 1)
                btnMoveEmber.setBackground(new Color(153, 153, 153));
        });

        btnMoveBubble.addActionListener(e -> {
            //May lower opponent's Speed. 10%
            //pp 30 power 40 accuracy 100%
            if (addMove("Bubble") == 1)
                btnMoveBubble.setBackground(new Color(153, 153, 153));
        });

        btnMoveWithdraw.addActionListener(e -> {
            //raises user's defence
            //pp 40
            if (addMove("Withdraw") == 1)
                btnMoveWithdraw.setBackground(new Color(153, 153, 153));
        });

        btnMoveThunderShock.addActionListener(e -> {
            //May paralyze opponent. 10%
            //pp 30 power 40 accuracy 100%
            if (addMove("Thunder Shock") == 1)
                btnMoveThunderShock.setBackground(new Color(153, 153, 153));
        });

        btnMoveChargeBeam.addActionListener(e -> {
            //May raise user's Special Attack.
            //pp 10 power 50 accuracy 90%
            if (addMove("Charge Beam") == 1)
                btnMoveChargeBeam.setBackground(new Color(153, 153, 153));
        });

        btnMoveLeafage.addActionListener(e -> {
            //pp 40 power 40 accuracy 100%
            if (addMove("Leafage") == 1)
                btnMoveLeafage.setBackground(new Color(153, 153, 153));
        });

        btnMoveAbsorb.addActionListener(e -> {
            //Recover half the damage dealt.
            //pp 25 power 20 accuracy 100%
            if (addMove("Absorb") == 1)
                btnMoveAbsorb.setBackground(new Color(153, 153, 153));
        });

        btnMoveTwister.addActionListener(e -> {
            //pp 20 power 40 accuracy 100%
            if (addMove("Twister") == 1)
                btnMoveTwister.setBackground(new Color(153, 153, 153));
        });

        btnMoveAstonish.addActionListener(e -> {
            //pp 15 power 30 accuracy 100%
            if (addMove("Astonish") == 1)
                btnMoveAstonish.setBackground(new Color(153, 153, 153));
        });

        btnMoveJudgment.addActionListener(e -> {
            //pp 10 power 100 accuracy 100%
            if (addMove("Judgment") == 1)
                btnMoveJudgment.setBackground(new Color(153, 153, 153));
        });

        btnMoveHydroCannon.addActionListener(e -> {
            //User must recharge next turn.
            //pp 5 power 150 accuracy 90%
            if (addMove("Hydro Cannon") == 1)
                btnMoveHydroCannon.setBackground(new Color(153, 153, 153));
        });

        btnMoveCottonGuard.addActionListener(e -> {
            //Sharply increases users defence
            //pp 10
            if (addMove("Cotton Guard") == 1)
                btnMoveCottonGuard.setBackground(new Color(153, 153, 153));
        });

        btnMoveShadowBall.addActionListener(e -> {
            //may lowers opponents defence %20
            //pp 15 power 80 accuracy 100%
            if (addMove("Shadow Ball") == 1)
                btnMoveShadowBall.setBackground(new Color(153, 153, 153));
        });

        btnMoveNightDaze.addActionListener(e -> {
            //may lower opponents acurracy 40%
            //pp 5 power 150 accuracy 90%
            if (addMove("Night Daze") == 1)
                btnMoveNightDaze.setBackground(new Color(153, 153, 153));
        });

        btnMoveVCreate.addActionListener(e -> {
            //may lowers opponents defence %20
            //pp 15 power 80 accuracy 100%
            if (addMove("V-create") == 1)
                btnMoveVCreate.setBackground(new Color(153, 153, 153));
        });

        btnMoveParabolicCharge.addActionListener(e -> {
            //User recovers half the HP inflicted on opponent.
            //pp 20 power 65 accuracy 100%
            if (addMove("Parabolic Charge") == 1)
                btnMoveParabolicCharge.setBackground(new Color(153, 153, 153));
        });

        btnMoveClangingScales.addActionListener(e -> {
            //slightly lowers user's defence
            //pp 5 power 110 accuracy 100%
            if (addMove("Clanging Scales") == 1)
                btnMoveClangingScales.setBackground(new Color(153, 153, 153));
        });

        btnMoveSnarl.addActionListener(e -> {
            //lowers opponents attack
            //pp 10 power 85 accuracy 95%
            if (addMove("Snarl") == 1)
                btnMoveSnarl.setBackground(new Color(153, 153, 153));
        });

        btnMoveIlluminati.addActionListener(e -> {
            //the user looses half of their health
            //pp 1 power 200 accuracy 100%
            if (addMove("Illuminati") == 1)
                btnMoveIlluminati.setBackground(new Color(153, 153, 153));
        });

        btnReset.addActionListener(e -> {
            moves = new ArrayList<>(); // clears moves array
            btnDone.setEnabled(false);
            setButtonColours();
        });

        btnDone.addActionListener(e -> {
            storeMoves("");
            new MainMenu(saveSlot);
            moveSelection.dispose();
        });

        btnDatabase.addActionListener(e -> {
            new Database(saveSlot, moves, identification);
            moveSelection.dispose();
        });
    }

    /**
     * Gets a move and if less than 4 have been selected and that move has not been selected before than
     * it is added to the list of selected moves. If this is the 4th move added, the 'done' button is enabled.
     *
     * @param move the move to add to list of selected moves if allowed.
     * @return 1 if move was added and 0 otherwise.
     */
    private int addMove(String move) {
        if (! moves.contains(move) && moves.size() < 4) {
            moves.add(move);
            if (moves.size() == 4)
                btnDone.setEnabled(true);
            System.out.println("Added "+move+" to moves.");
            return 1;
        } else {
            System.out.println("Can't add move!");
            return 0;
        }
    }

    /**
     * Enable buttons representing special moves that has been unlocked by user and disable the rest if the
     * special moves.
     */
    private void setLockedMoves() {
        ArrayList<String> unlocked = new ArrayList<>();

        // disable extra moves
        btnMoveJudgment.setEnabled(false);
        btnMoveHydroCannon.setEnabled(false);
        btnMoveCottonGuard.setEnabled(false);
        btnMoveShadowBall.setEnabled(false);
        btnMoveNightDaze.setEnabled(false);
        btnMoveVCreate.setEnabled(false);
        btnMoveParabolicCharge.setEnabled(false);
        btnMoveClangingScales.setEnabled(false);
        btnMoveSnarl.setEnabled(false);
        btnMoveIlluminati.setEnabled(false);

        // retrieve unlocked moves
        switch (saveSlot) {
            case 1:
                try { // get moves from file
                    System.out.println("checking unlocks for save 1");
                    FileReader s = new FileReader("saves/Slot1unlocks.txt");
                    BufferedReader br = new BufferedReader(s);
                    for (int m = 0; m < 10; m++)
                        unlocked.add(br.readLine());
                } catch (IOException e) {
                    System.out.println("Something went wrong reading the file.");
                }
                break;
            case 2:
                try { // get moves from file
                    System.out.println("checking unlocks for save 2");
                    FileReader s = new FileReader("saves/Slot2unlocks.txt");
                    BufferedReader br = new BufferedReader(s);
                    for (int m = 0; m < 10; m++)
                        unlocked.add(br.readLine());
                } catch (IOException e) {
                    System.out.println("Something went wrong reading the file.");
                }
                break;
            case 3:
                try { // get moves from file
                    System.out.println("checking unlocks for save 3");
                    FileReader s = new FileReader("saves/Slot3unlocks.txt");
                    BufferedReader br = new BufferedReader(s);
                    for (int m = 0; m < 10; m++)
                        unlocked.add(br.readLine());
                } catch (IOException e) {
                    System.out.println("Something went wrong reading the file.");
                }
        }

        for (String move: unlocked) {
            // make special move buttons non visible if unlocked
            if (move != null) {
                switch (move) {
                    case "Judgment":
                        btnMoveJudgment.setEnabled(true);
                        break;
                    case "Hydro Cannon":
                        btnMoveHydroCannon.setEnabled(true);
                        break;
                    case "Cotton Guard":
                        btnMoveCottonGuard.setEnabled(true);
                        break;
                    case "Shadow Ball":
                        btnMoveShadowBall.setEnabled(true);
                        break;
                    case "Night Daze":
                        btnMoveNightDaze.setEnabled(true);
                        break;
                    case "V-create":
                        btnMoveVCreate.setEnabled(true);
                        break;
                    case "Parabolic Charge":
                        btnMoveParabolicCharge.setEnabled(true);
                        break;
                    case "Clanging Scales":
                        btnMoveClangingScales.setEnabled(true);
                        break;
                    case "Snarl":
                        btnMoveSnarl.setEnabled(true);
                        break;
                    case "Illuminati":
                        btnMoveIlluminati.setEnabled(true);
                }
                System.out.println("you have the move " + move);
            }
        }
    }

    /**
     * Stores selected moves onto the text file for the current save.
     *
     * @param file the text file to write too.
     */
    private void storeMoves(String file) {
        if (file.equals("saves/Slot1.txt") || file.equals("saves/Slot2.txt") || file.equals("saves/Slot3.txt")) {
            try (FileWriter fw = new FileWriter(file, false);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.println(identification[0]);
                out.println(identification[1]);
                for (String move : moves) { // add moves to file
                    out.println(move);
                }
                System.out.println("New data stored");
            } catch (IOException e) {
                System.out.println("Something went wrong reading the file.");
            }
        } else {
            switch (saveSlot) {
                case 1:
                    storeMoves("saves/Slot1.txt");
                    break;
                case 2:
                    storeMoves("saves/Slot2.txt");
                    break;
                case 3:
                    storeMoves("saves/Slot3.txt");
            }
        }
    }

    /**
     * Gets moves that have been saved from before and put them onto the global variable moves.
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

        // remove null entries in move ArrayList
        for (int x = 0; x < moves.size(); x++) {
            if (moves.get(x) == null) {
                moves.remove(x);
                x --;
            }
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
            identification[0] = br.readLine();
            identification[1] = br.readLine();
            for (int y = 0; y < 4; y++) {
                moves.add(br.readLine());
            }
        } catch (IOException e) {
            System.out.println("Something went wrong reading the file.");
        }
    }

    /**
     * Sets the colour for every button representing a move, depending on the type the move is and if it
     * has been selected or not.
     */
    private void setButtonColours() {
        if (moves.contains("Pound"))
            btnMovePound.setBackground(new Color(153, 153, 153));
        else
            btnMovePound.setBackground(new Color(240, 240, 240));
        if (moves.contains("Double Slap"))
            btnMoveDoubleSlap.setBackground(new Color(153, 153, 153));
        else
            btnMoveDoubleSlap.setBackground(new Color(240, 240, 240));
        if (moves.contains("Acupressure"))
            btnMoveAcupressure.setBackground(new Color(153, 153, 153));
        else
            btnMoveAcupressure.setBackground(new Color(240, 240, 240));
        if (moves.contains("Hold Hands"))
            btnMoveHoldHands.setBackground(new Color(153, 153, 153));
        else
            btnMoveHoldHands.setBackground(new Color(240, 240, 240));
        if (moves.contains("Fire Spin"))
            btnMoveFireSpin.setBackground(new Color(153, 153, 153));
        else
            btnMoveFireSpin.setBackground(new Color(255, 51, 0));
        if (moves.contains("Ember"))
            btnMoveEmber.setBackground(new Color(153, 153, 153));
        else
            btnMoveEmber.setBackground(new Color(255, 51, 0));
        if (moves.contains("Bubble"))
            btnMoveBubble.setBackground(new Color(153, 153, 153));
        else
            btnMoveBubble.setBackground(new Color(102, 153, 255));
        if (moves.contains("Withdraw"))
            btnMoveWithdraw.setBackground(new Color(153, 153, 153));
        else
            btnMoveWithdraw.setBackground(new Color(102, 153, 255));
        if (moves.contains("Thunder Shock"))
            btnMoveThunderShock.setBackground(new Color(153, 153, 153));
        else
            btnMoveThunderShock.setBackground(new Color(255, 255, 0));
        if (moves.contains("Charge Beam"))
            btnMoveChargeBeam.setBackground(new Color(153, 153, 153));
        else
            btnMoveChargeBeam.setBackground(new Color(255, 255, 0));
        if (moves.contains("Leafage"))
            btnMoveLeafage.setBackground(new Color(153, 153, 153));
        else
            btnMoveLeafage.setBackground(new Color(0, 255, 0));
        if (moves.contains("Absorb"))
            btnMoveAbsorb.setBackground(new Color(153, 153, 153));
        else
            btnMoveAbsorb.setBackground(new Color(0, 255, 0));
        if (moves.contains("Twister"))
            btnMoveTwister.setBackground(new Color(153, 153, 153));
        else
            btnMoveTwister.setBackground(new java.awt.Color(153, 153, 255));
        if (moves.contains("Astonish"))
            btnMoveAstonish.setBackground(new Color(153, 153, 153));
        else {
            btnMoveAstonish.setBackground(new Color(102, 0, 102));
            btnMoveAstonish.setForeground(new Color(204, 204, 204));
        }
        if (moves.contains("Judgment"))
            btnMoveJudgment.setBackground(new Color(153, 153, 153));
        else
            btnMoveJudgment.setBackground(new java.awt.Color(240, 240, 240));
        if (moves.contains("Hydro Cannon"))
            btnMoveHydroCannon.setBackground(new Color(153, 153, 153));
        else
            btnMoveHydroCannon.setBackground(new java.awt.Color(102, 153, 255));
        if (moves.contains("Cotton Guard"))
            btnMoveCottonGuard.setBackground(new Color(153, 153, 153));
        else
            btnMoveCottonGuard.setBackground(new Color(0, 255, 0));
        if (moves.contains("Shadow Ball"))
            btnMoveShadowBall.setBackground(new Color(153, 153, 153));
        else {
            btnMoveShadowBall.setBackground(new Color(102, 0, 102));
            btnMoveShadowBall.setForeground(new Color(204, 204, 204));
        }
        if (moves.contains("Night Daze"))
            btnMoveNightDaze.setBackground(new Color(153, 153, 153));
        else {
            btnMoveNightDaze.setBackground(new Color(0, 0, 0));
            btnMoveNightDaze.setForeground(new Color(255, 255, 255));
        }
        if (moves.contains("V-create"))
            btnMoveVCreate.setBackground(new Color(153, 153, 153));
        else
            btnMoveVCreate.setBackground(new Color(255, 51, 0));
        if (moves.contains("Parabolic Charge"))
            btnMoveParabolicCharge.setBackground(new Color(153, 153, 153));
        else
            btnMoveParabolicCharge.setBackground(new Color(255, 255, 0));
        if (moves.contains("Clanging Scales"))
            btnMoveClangingScales.setBackground(new Color(153, 153, 153));
        else
            btnMoveClangingScales.setBackground(new Color(153, 153, 255));
        if (moves.contains("Snarl"))
            btnMoveSnarl.setBackground(new Color(153, 153, 153));
        else {
            btnMoveSnarl.setBackground(new Color(0, 0, 0));
            btnMoveSnarl.setForeground(new Color(255, 255, 255));
        }
        if (moves.contains("Illuminati"))
            btnMoveIlluminati.setBackground(new Color(153, 153, 153));
        else {
            btnMoveIlluminati.setBackground(new Color(0, 0, 0));
            btnMoveIlluminati.setForeground(new Color(255, 255, 255));
        }
    }

    /**
     * Creates a JFrame for MoveSelection with GUI, sets background image, 24 buttons for moves, buttons
     * for done, reset, and database link. Also adds a label to hold the title of the frame.
     */
    private void makeFrame() {
        moveSelection = new JFrame();
        moveSelection.setSize(1350, 720);
        moveSelection.setResizable(false);
        moveSelection.setLocationRelativeTo(null);
        moveSelection.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // make panel on JFrame
        JPanel panelTransparent = new JPanel();
        panelTransparent.setLayout(null);
        panelTransparent.setOpaque(false);

        // add label for title
        lblTitle = new JLabel();
        lblTitle.setIcon(new ImageIcon("images/titles/MoveSelTitle.png"));
        Dimension sizeTitle = lblTitle.getPreferredSize();
        lblTitle.setBounds(400, 10, sizeTitle.width, sizeTitle.height);

        // make 24 move buttons
        btnMovePound = new JButton("Pound");
        btnMovePound.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnMovePound.setBounds(40, 130, 210, 80);
        btnMoveDoubleSlap = new JButton("Double Slap");
        btnMoveDoubleSlap.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnMoveDoubleSlap.setBounds(40, 240, 210, 80);
        btnMoveAcupressure = new JButton("Acupressure");
        btnMoveAcupressure.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnMoveAcupressure.setBounds(40, 350, 210, 80);
        btnMoveHoldHands = new JButton("Hold Hands");
        btnMoveHoldHands.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnMoveHoldHands.setBounds(40, 460, 210, 80);
        btnMoveFireSpin = new JButton("Fire Spin");
        btnMoveFireSpin.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnMoveFireSpin.setBounds(40, 570, 210, 80);
        btnMoveEmber = new JButton("Ember");
        btnMoveEmber.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnMoveEmber.setBounds(300, 130, 210, 80);
        btnMoveBubble = new JButton("Bubble");
        btnMoveBubble.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnMoveBubble.setBounds(300, 240, 210, 80);
        btnMoveWithdraw = new JButton("Withdraw");
        btnMoveWithdraw.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnMoveWithdraw.setBounds(300, 350, 210, 80);
        btnMoveThunderShock = new JButton("Thunder Shock");
        btnMoveThunderShock.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnMoveThunderShock.setBounds(300, 460, 210, 80);
        btnMoveChargeBeam = new JButton("Charge Beam");
        btnMoveChargeBeam.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnMoveChargeBeam.setBounds(300, 570, 210, 80);
        btnMoveLeafage = new JButton("Leafage");
        btnMoveLeafage.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnMoveLeafage.setBounds(560, 130, 210, 80);
        btnMoveAbsorb = new JButton("Absorb");
        btnMoveAbsorb.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnMoveAbsorb.setBounds(560, 240, 210, 80);
        btnMoveTwister = new JButton("Twister");
        btnMoveTwister.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnMoveTwister.setBounds(560, 350, 210, 80);
        btnMoveAstonish = new JButton("Astonish");
        btnMoveAstonish.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnMoveAstonish.setBounds(560, 460, 210, 80);
        btnMoveJudgment = new JButton("Judgment");
        btnMoveJudgment.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnMoveJudgment.setBounds(820, 130, 210, 80);
        btnMoveHydroCannon = new JButton("Hydro Cannon");
        btnMoveHydroCannon.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnMoveHydroCannon.setBounds(820, 240, 210, 80);
        btnMoveCottonGuard = new JButton("Cotton Guard");
        btnMoveCottonGuard.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnMoveCottonGuard.setBounds(820, 350, 210, 80);
        btnMoveShadowBall = new JButton("Shadow Ball");
        btnMoveShadowBall.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnMoveShadowBall.setBounds(820, 460, 210, 80);
        btnMoveNightDaze = new JButton("Night Daze");
        btnMoveNightDaze.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnMoveNightDaze.setBounds(820, 570, 210, 80);
        btnMoveVCreate = new JButton("V-create");
        btnMoveVCreate.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnMoveVCreate.setBounds(1080, 130, 210, 80);
        btnMoveParabolicCharge = new JButton("Parabolic Charge");
        btnMoveParabolicCharge.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnMoveParabolicCharge.setBounds(1080, 240, 210, 80);
        btnMoveClangingScales = new JButton("Clanging Scales");
        btnMoveClangingScales.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnMoveClangingScales.setBounds(1080, 350, 210, 80);
        btnMoveSnarl = new JButton("Snarl");
        btnMoveSnarl.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnMoveSnarl.setBounds(1080, 460, 210, 80);
        btnMoveIlluminati = new JButton("Illuminati");
        btnMoveIlluminati.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnMoveIlluminati.setBounds(1080, 570, 210, 80);
        setButtonColours();

        // make database button
        btnDatabase = new JButton("Database");
        btnDatabase.setFont(new java.awt.Font("Tahoma", Font.BOLD, 18));
        btnDatabase.setBounds(660, 620, 130, 30);

        // make restart button
        btnReset = new JButton("Reset");
        btnReset.setFont(new java.awt.Font("Tahoma", Font.BOLD, 18));
        btnReset.setBounds(550, 620, 90, 31);

        // make done button
        btnDone = new JButton("Done");
        btnDone.setFont(new java.awt.Font("Tahoma", Font.BOLD, 18));
        btnDone.setBounds(600, 570, 130, 40);

        // set background image
        lblBackground = new JLabel();
        lblBackground.setIcon(new ImageIcon("images/background/BackgroundMoveSelection.png"));

        // add background to frame
        lblBackground.setLayout(new BorderLayout());
        moveSelection.setContentPane(lblBackground);

        //add components to panel
        panelTransparent.add(btnMovePound);
        panelTransparent.add(btnMoveDoubleSlap);
        panelTransparent.add(btnMoveAcupressure);
        panelTransparent.add(btnMoveHoldHands);
        panelTransparent.add(btnMoveFireSpin);
        panelTransparent.add(btnMoveEmber);
        panelTransparent.add(btnMoveBubble);
        panelTransparent.add(btnMoveWithdraw);
        panelTransparent.add(btnMoveThunderShock);
        panelTransparent.add(btnMoveChargeBeam);
        panelTransparent.add(btnMoveLeafage);
        panelTransparent.add(btnMoveAbsorb);
        panelTransparent.add(btnMoveTwister);
        panelTransparent.add(btnMoveAstonish);
        panelTransparent.add(btnMoveJudgment);
        panelTransparent.add(btnMoveHydroCannon);
        panelTransparent.add(btnMoveCottonGuard);
        panelTransparent.add(btnMoveShadowBall);
        panelTransparent.add(btnMoveNightDaze);
        panelTransparent.add(btnMoveVCreate);
        panelTransparent.add(btnMoveParabolicCharge);
        panelTransparent.add(btnMoveClangingScales);
        panelTransparent.add(btnMoveSnarl);
        panelTransparent.add(btnMoveIlluminati);
        panelTransparent.add(btnDatabase);
        panelTransparent.add(btnDone);
        panelTransparent.add(btnReset);
        panelTransparent.add(lblTitle);

        moveSelection.add(panelTransparent);
    }
}
