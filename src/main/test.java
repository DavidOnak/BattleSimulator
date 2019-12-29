package main;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class test extends JFrame implements ActionListener {

    private JTextField t1;
    private JTextField t2;
    private JTextField t3;
    private JButton b1;
    JProgressBar progressBar;
    int i = 0;

    public test() {

        setLayout(new FlowLayout());

        t1 = new JTextField(20);
        t2 = new JTextField(20);
        t3 = new JTextField(20);

        b1 = new JButton("OK");
        b1.addActionListener(this);

        progressBar = new JProgressBar(1, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setForeground(Color.GRAY);
        progressBar.setBackground(Color.white);

        add(t1);
        add(t2);
        add(t3);
        add(b1);
        add(progressBar);

        setSize(600, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int i = 0;
        if (e.getSource() == b1) {
            progressBar.setVisible(true);
            if (t1.getText().isEmpty() || t2.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Empty Fields");
            } else {
                try {
                    while (i <= 100) {

                        int w = Integer.parseInt(t1.getText());
                        int x = Integer.parseInt(t2.getText());
                        int res = w + x;
                        t3.setText("" + res);

                        Thread.sleep(50);
                        progressBar.paintImmediately(0, 0, 200, 200);
                        progressBar.setValue(i);
                        i++;
                    }
                } catch (Exception e1) {
                    System.out.print("Exception =" + e1);
                }
                progressBar.setValue(0);
            }
        }

    }
}
