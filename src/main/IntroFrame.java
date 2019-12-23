package main;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.applet.*;
//import javazoom.jl.player.StdPlayer;
import java.io.*;
//import sun.*;

/**
 *
 * @author David Onak
 */
public class IntroFrame extends JFrame implements ActionListener {

    private JFrame intro;
    private JButton start;
    private JLabel lblPokemon1;
    private JLabel lblPokemon2;
    private JLabel lblTitle;
    private JLabel lblLightningL;
    private JLabel lblLightningR;
    private SaveSlots nextFrame;

    /**
     * Constructor that creates the intro start frame for this application.
     */
    public IntroFrame() {
        System.out.println("IntroFrame in operation");

        //make new JFrame
        makeFrame();
        nextFrame = new SaveSlots();

        //activate button
        start.addActionListener(this);

        // add music
        //music(addImage());
    }

    /**
     * Opens next frame containing save slots and destroys this frame.
     *
     * @param e, ActionEvent from pressing the start button
     */
    public void actionPerformed(ActionEvent e) {
        System.out.println("Clicked start button, destroying IntroFrame");
        intro.dispose();
        nextFrame.slots.setVisible(true);
    }

    /**
     * Adds distinct images to all labels in this class and lblPokemon1 and lblPokemon2 get random
     * images each time, will return 1 or 0 depending if pokemon 4 (nyan cat) is added.
     *
     * @return 1 if pokemon 4 is added to a label, returns 0 otherwise
     */
    private int addImages() {
        //get random numbers for images
        int image1= (int)(7*Math.random()+1);
        int image2;
        do{
            image2 = (int)(7*Math.random()+1);
        }while(image1==image2);

        switch (image1) { // set image for lblPokemon1
            case 1:
                lblPokemon1.setIcon(new ImageIcon("images/pokemon/Pokemon1.gif"));
                break;
            case 2:
                lblPokemon1.setIcon(new ImageIcon("images/pokemon/Pokemon2.gif"));
                break;
            case 3:
                lblPokemon1.setIcon(new ImageIcon("images/pokemon/Pokemon3.gif"));
                break;
            case 4:
                lblPokemon1.setIcon(new ImageIcon("images/pokemon/Pokemon4.gif"));
                break;
            case 5:
                lblPokemon1.setIcon(new ImageIcon("images/pokemon/Pokemon5.gif"));
                break;
            case 6:
                lblPokemon1.setIcon(new ImageIcon("images/pokemon/Pokemon6.gif"));
                break;
            case 7:
                lblPokemon1.setIcon(new ImageIcon("images/pokemon/Pokemon7.gif"));
        }

        switch (image2){ // set image for lblPokemon2
            case 1:
                lblPokemon2.setIcon(new ImageIcon("images/pokemon/Pokemon1.gif"));
                break;
            case 2:
                lblPokemon2.setIcon(new ImageIcon("images/pokemon/Pokemon2.gif"));
                break;
            case 3:
                lblPokemon2.setIcon(new ImageIcon("images/pokemon/Pokemon3.gif"));
                break;
            case 4:
                lblPokemon2.setIcon(new ImageIcon("images/pokemon/Pokemon4.gif"));
                break;
            case 5:
                lblPokemon2.setIcon(new ImageIcon("images/pokemon/Pokemon5.gif"));
                break;
            case 6:
                lblPokemon2.setIcon(new ImageIcon("images/pokemon/Pokemon6.gif"));
                break;
            case 7:
                lblPokemon2.setIcon(new ImageIcon("images/pokemon/Pokemon7.gif"));
        }

        // set rest of label images
        lblTitle.setIcon(new ImageIcon("images/titles/IntroTitle.gif"));
        lblLightningL.setIcon(new ImageIcon("images/background/LightningL.gif"));
        lblLightningR.setIcon(new ImageIcon("images/background/LightningR.gif"));

        if (image1 == 4 || image2 == 4) {
            return 1;
        } else {
            return 0;
        }
    }

    // TODO: Get muisic working here or make a class that can deal with muisic generation so other classes can use it.
    /**
     * Adds background music to the frame
     *
     * @param type, 1 or 0 for type of music to play
     *//*
    public static void music(int type){//add background music
        AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM;
        AudioData MD;
        ContinuousAudioDataStream loop = null;
        int x = (int)(2*Math.random()+1);//for radom selected song
        try{
            if (image1 == 4 || image2 == 4){//for nyan pikacho theame
                InputStream test = new FileInputStream("U:\\Nyan-Cat.wav");
                BGM = new AudioStream(test);
                AudioPlayer.player.start(BGM);
            }else{//for regualar theame
                if (x==1){
                    InputStream test = new FileInputStream("U:\\Pokemon Music 1.wav");
                    BGM = new AudioStream(test);
                    AudioPlayer.player.start(BGM);
                }else{
                    InputStream test = new FileInputStream("U:\\Pokemon Music 2.wav");
                    BGM = new AudioStream(test);
                    AudioPlayer.player.start(BGM);
                }
            }
            //MD = BGM.getData();
            //loop = new ContinuousAudioDataStream(MD);
        }
        catch(FileNotFoundException e){
            System.out.print(e.toString());
        }
        catch(IOException error)
        {
            System.out.print(error.toString());
        }

        MGP.start(loop);

    }
*/
    /**
     * Creates a JFrame for intro with GUI.
     */
    private void makeFrame() {
        intro = new JFrame();
        intro.setSize(850, 520);
        intro.setResizable(false);
        intro.setLocationRelativeTo ( null );
        intro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // make panel on JFrame
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new java.awt.Color(255, 255, 255));

        // make button
        start = new JButton("Start");
        Dimension sizeB = start.getPreferredSize();
        start.setBounds(375, 300, sizeB.width, sizeB.height);

        // make labels
        lblPokemon1 = new JLabel();
        lblPokemon2 = new JLabel();
        lblTitle = new JLabel();
        lblLightningL = new JLabel();
        lblLightningR = new JLabel();
        addImages();
        Dimension sizeLP1 = lblPokemon1.getPreferredSize();
        Dimension sizeLP2 = lblPokemon2.getPreferredSize();
        Dimension sizeLT = lblTitle.getPreferredSize();
        Dimension sizeLLL = lblLightningL.getPreferredSize();
        Dimension sizeLLR = lblLightningR.getPreferredSize();
        lblPokemon1.setBounds(175, 250, sizeLP1.width, sizeLP1.height);
        lblPokemon2.setBounds(475, 250, sizeLP2.width, sizeLP2.height);
        lblTitle.setBounds(95, 0, sizeLT.width, sizeLT.height);
        lblLightningL.setBounds(0, 250, sizeLLL.width, sizeLLL.height);
        lblLightningR.setBounds(675, 250, sizeLLR.width, sizeLLR.height);

        //add components to panel
        panel.add(start);
        panel.add(lblPokemon1);
        panel.add(lblPokemon2);
        panel.add(lblTitle);
        panel.add(lblLightningL);
        panel.add(lblLightningR);

        //add to frame
        intro.add(panel);
        intro.setVisible(true);
    }
}
