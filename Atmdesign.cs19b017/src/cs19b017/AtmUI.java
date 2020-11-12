package cs19b017;

import javax.swing.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class AtmUI implements ActionListener {
    private static JLabel label;
    private static JTextField func;
    private static JButton button;
    Scanner scan = new Scanner(System.in);


    public static void Atm2(){
        JPanel panel=new JPanel();
        JFrame frame=new JFrame();
        frame.setSize(300,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);
        label = new JLabel("WHAT DO U WANT TO DO");
        label.setBounds(75, 20, 200, 25);
        panel.add(label);

        label = new JLabel("Enter the NO.");
        label.setBounds(75, 40, 200, 25);
        panel.add(label);

        func=new JTextField(25);
        func.setBounds(40,60,170,25);
        panel.add(func);

        label = new JLabel("1.withdrawl");
        label.setBounds(75, 80, 200, 25);
        panel.add(label);


        label = new JLabel("2.deposit");
        label.setBounds(75, 100, 200, 25);
        panel.add(label);

        label = new JLabel("3.Account balance");
        label.setBounds(75, 120, 200, 25);
        panel.add(label);

        label = new JLabel("4.change password");
        label.setBounds(75, 140, 200, 25);
        panel.add(label);

        button = new JButton("SUBMIT");
        button.setBounds(50,170 , 80, 25);
        button.addActionListener(new AtmUI());
        panel.add(button);


        frame.setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        String op=func.getText();
        String user=customers.getUsername();
        if(op.equals("1"))
        {
            withdrawl.wdwrl();
        }

        if(op.equals("2"))
        {
            deposit.depo();
        }

        if(op.equals("3"))
        {
            Connection con = dbconnection.connect();
            PreparedStatement ps = null,ps1=null;
            ResultSet rs = null;
            try {
                String sql1="Select accBal from custinfo where accNum = ?";
                ps1=con.prepareStatement(sql1);
                ps1.setString(1, user);
                rs= ps1.executeQuery();
                int bal= rs.getInt(1);
                JOptionPane.showMessageDialog(null,"Account Balance  "+ bal);


            } catch (
                    SQLException q) {
                // TODO: handle exception
                System.out.println(q.toString());
            }




        }
        if(op.equals("4"))
        {

            Connection con = dbconnection.connect();
            PreparedStatement ps=null;
            ResultSet rs = null;


            try {
                String sql = "UPDATE custinfo set password = ? WHERE accNum = ? ";
                System.out.println("ENTER THE NEW PASSWORD HERE");
                String nwps=scan.next();

                ps = con.prepareStatement(sql);
                ps.setString(2, user);
                ps.setString(1, nwps);
                ps.execute();
                System.out.println("PASSWORD CHANGED TO"+nwps);
                JOptionPane.showMessageDialog(null,"PASSWORD CHANGED TO"+nwps);

                System.out.println("Data has been updated");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }




        }

    }
}
