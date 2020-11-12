package cs19b017;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class atm implements ActionListener {
    private static JLabel label;
    private static JTextField func;
    private static JButton button;

    public static void Atm1() {
        Scanner scanner = new Scanner(System.in);

        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);
        label = new JLabel("LET ME KNOW WHO ARE YOU");
        label.setBounds(75, 20, 200, 25);
        panel.add(label);

        func = new JTextField(25);
        func.setBounds(65, 60, 200, 25);
        panel.add(func);

        label = new JLabel("1.EXISTING CUSTOMER");
        label.setBounds(75, 80, 200, 25);
        panel.add(label);


        label = new JLabel("2.NEW CUSTOMER");
        label.setBounds(75, 100, 200, 25);
        panel.add(label);

        label = new JLabel("3.BANK ADMIN");
        label.setBounds(75, 120, 200, 25);
        panel.add(label);

        button=new JButton("SUBMIT");
        button.setBounds(85,150,80,25);
        button.addActionListener(new atm());
        panel.add(button);



        frame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String option=func.getText();
        if(option.equals("1")){
            customers.existcust();
        }
        if(option.equals("2")){
            newCustomer.newCust();

        }
        if(option.equals("3")){
            customers.existcust();

        }
    }

    public static void main(String[] args) {
        atm.Atm1();
    }
}
