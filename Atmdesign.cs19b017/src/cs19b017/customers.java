package cs19b017;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.sql.*;
import javax.swing.*;


public class customers implements ActionListener {

    Scanner scan = new Scanner(System.in);
    private static String Username = "";

    private float AccountBalance = 0;
    private String password;
    private static JLabel label;
    private static JTextField usertxt;
    private static JLabel passlabel;
    private static JTextField pass,phoNo,accBal;
    private static JButton button;
    public static String getUsername() {
        final String username = Username;
        return username;
    }

     public static void existcust() {


         JPanel panel=new JPanel();
         JFrame frame=new JFrame();
         frame.setSize(300,200);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.add(panel);
         panel.setLayout(null);

          label=new JLabel("Account Number");
         label.setBounds(10,20,80,25);
         panel.add(label);


          usertxt=new JTextField(25);
         usertxt.setBounds(100,20,165,25);
         panel.add(usertxt);


          passlabel=new JLabel("Password");
         passlabel.setBounds(10,50,80,25);
         panel.add(passlabel);

          pass=new JTextField(5);
         pass.setBounds(100,50,165,25);
         panel.add(pass);

          button=new JButton("login");
         button.setBounds(50,100,80,25);
         button.addActionListener(new customers());
         panel.add(button);

         frame.setVisible(true);

     }



    @Override
    public void actionPerformed(ActionEvent e) {

         String user=usertxt.getText();
        String password=pass.getText();
        if(user.equals("ADMIN"))

        {
            if(password.equals("1234"))
            JOptionPane.showMessageDialog(null,"welcome ADMIN");
            bank.bankgui();
        }
        else
        dbconnection.check(user,password);
       Username=user;


    }



}
