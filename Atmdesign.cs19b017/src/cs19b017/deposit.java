package cs19b017;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class deposit implements ActionListener {
    private static JLabel wlabel;
    private static JTextField func;
    private static JButton wbutton;

    public static void depo() {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(650, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);

        wlabel = new JLabel("ENTER THE AMMOUNT YOU WANT TO DEPOSIT");
        wlabel.setBounds(20, 20, 400, 25);
        panel.add(wlabel);

        func=new JTextField(25);
        func.setBounds(40,60,200,25);
        panel.add(func);


        wbutton = new JButton("SUBMIT");
        wbutton.setBounds(80, 100, 80, 25);
        wbutton.addActionListener(new deposit());
        panel.add(wbutton);
        frame.setVisible(true);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        int amu=Integer.parseInt(func.getText());
        String user=customers.getUsername();
        dbconnection.deposit(user,amu);

    }
}
