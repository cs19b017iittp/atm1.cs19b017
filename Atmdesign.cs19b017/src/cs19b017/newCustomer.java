package cs19b017;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.sql.*;

public class newCustomer implements ActionListener {
    Scanner scan = new Scanner(System.in);
    private String CustomerId = "";
    private static JLabel label;
    private static JTextField usertxt;
    private static JLabel passlabel;
    private static JTextField pass,phoNo,accBal;
    private static JButton button;
    public String getCustomerId() {
        return CustomerId;
    }
    public void setCustomerId() {
        int n = 5;
        String AlphaNumericString = "0123456789";

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        CustomerId = sb.toString();

        System.out.println("your  CUSTOMER ID:" + CustomerId);
    }

    public static void newCust(){

        JPanel panel=new JPanel();
        JFrame frame=new JFrame();
        frame.setSize(300,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);


        label = new JLabel("Create Account");
        label.setBounds(75, 20, 200, 25);
        panel.add(label);

        label = new JLabel("username");
        label.setBounds(10, 50, 80, 25);
        panel.add(label);

        usertxt = new JTextField(25);
        usertxt.setBounds(100, 50, 165, 25);
        panel.add(usertxt);

        passlabel = new JLabel("password");
        passlabel.setBounds(10, 80, 80, 25);
        panel.add(passlabel);

        pass = new JTextField(5);
        pass.setBounds(100, 80, 165, 25);
        panel.add(pass);

        label = new JLabel("PhoneNo");
        label.setBounds(10, 110, 80, 25);
        panel.add(label);

        phoNo = new JTextField(5);
        phoNo.setBounds(100, 110, 165, 25);
        panel.add(phoNo);



        label = new JLabel("Add accbal");
        label.setBounds(10, 140, 80, 25);
        panel.add(label);

        accBal = new JTextField(5);
        accBal.setBounds(100, 140, 165, 25);
        panel.add(accBal);


        button = new JButton("Create");
        button.setBounds(50,170 , 80, 25);
        button.addActionListener(new newCustomer());
        panel.add(button);


        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user=usertxt.getText();
        String password=pass.getText();
        String phone=phoNo.getText();
        String bal=accBal.getText();
        setCustomerId();
        dbconnection.create(user,password,phone,bal,getCustomerId());


    }
}
