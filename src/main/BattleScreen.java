package main;

import javax.swing.*;
import java.awt.*;

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
    private JLabel lblPlayerName;
    private JLabel lblPokeLevel;
    private JLabel lblPokeName;
    private JLabel lblPokemon;
    private JProgressBar pbExperience;
    private JProgressBar pbCompHealth;
    private JProgressBar pbPlayerHealth;
    private int saveSlot;

    public BattleScreen(int saveSlot, int numOpp, int tier) {
        this.saveSlot = saveSlot;

        // make new battle screen frame
        makeFrame();
        battleScreen.setVisible(true);
    }

    private void makeFrame() {
        battleScreen = new JFrame();
        battleScreen.setSize(1350, 720);
        battleScreen.setResizable(false);
        battleScreen.setLocationRelativeTo(null);
        battleScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // make panel on JFrame
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // add label for title
        JLabel lblTitle = new JLabel();
        lblTitle.setIcon(new ImageIcon("images/pokemon/Pokemon1.gif"));
        Dimension sizeTitle = lblTitle.getPreferredSize();
        lblTitle.setBounds(10, 10, sizeTitle.width+10, sizeTitle.height+10);

        //add components to panel
        panel.add(lblTitle);
        
        battleScreen.add(panel);
    }
}
