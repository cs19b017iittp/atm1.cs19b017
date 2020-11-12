package cs19b017;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class bank implements ActionListener {

private static int amodisp;
    private static JLabel label;
    private static JTextField func,func1;
    private static JButton button;

    public static void bankgui(){
    JPanel panel=new JPanel();
    JFrame frame=new JFrame();
    frame.setSize(300,200);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(panel);
    panel.setLayout(null);
    label = new JLabel("PUT CASH IN THE DISPENSER");
    label.setBounds(50, 20, 200, 25);
    panel.add(label);

    label = new JLabel("Enter the amount.");
    label.setBounds(50, 40, 200, 25);
    panel.add(label);

    func1=new JTextField(25);
    func1.setBounds(40,60,170,25);
    panel.add(func1);

    label = new JLabel("Enter the no.");
    label.setBounds(65, 90, 200, 25);
    panel.add(label);

    func=new JTextField(25);
    func.setBounds(40,110,170,25);
    panel.add(func);



    label = new JLabel("1.SHOW ACCOUNT DETAILS");
    label.setBounds(50, 130, 200, 25);
    panel.add(label);

    label = new JLabel("2.SHOW TRANSACTIONS IN ATM");
    label.setBounds(50, 150, 200, 25);
    panel.add(label);



    button = new JButton("SUBMIT");
    button.setBounds(50,180 , 80, 25);
    button.addActionListener(new bank());
    panel.add(button);


    frame.setVisible(true);
}



    @Override
    public void actionPerformed(ActionEvent e) {
    int cd=Integer.parseInt(func1.getText());
    String op=func.getText();
        Connection con = dbconnection.connect();
        PreparedStatement ps = null,ps2=null;
         ResultSet rs2=null;
        try {

            String sql1="Select cashDisp from dispencer where no= ?";
            ps2=con.prepareStatement(sql1);
            ps2.setString(1,"1");
            rs2=ps2.executeQuery();
            int ads=rs2.getInt(1);
            System.out.println(ads);


            String sql = "UPDATE dispencer set cashDisp = ? WHERE no=? ";
            ps = con.prepareStatement(sql);
            ps.setInt(1,ads+cd);
            ps.setString(2,"1");
            ps.execute();
            System.out.println(amodisp+cd);
            JOptionPane.showMessageDialog(null,"cash in dispencer updated");
            JOptionPane.showMessageDialog(null,"current amount in dispencer "+(ads+cd));



        } catch(SQLException q) {
            System.out.println(q.toString());
            JOptionPane.showMessageDialog(null,"error please try again");
            // always remember to close database connections
        }






        if(op.equals("1")){
            Connection con1 = dbconnection.connect();
            PreparedStatement ps1 = null;
            ResultSet rs = null;

            try {
                String sql = "SELECT * FROM custinfo";
                ps1 = con1.prepareStatement(sql);
                rs = ps1.executeQuery();
                System.out.println("ALL USERS\n");
                while(rs.next()) {
                    String firstName = rs.getString("userName");
                    String secondName = rs.getString("accNum");
                    String acbal = rs.getString("accBal");
                    String pno = rs.getString("phoneNO");



                    System.out.println("First Name: "+firstName);
                    System.out.println("Second Name: "+secondName);
                    System.out.println("Email: "+acbal);
                    System.out.println("Password: "+pno);

                }
            } catch(SQLException q) {
                System.out.println(q.toString());
            } finally {
                try {
                    rs.close();
                    ps.close();
                    con.close();
                } catch(SQLException q) {
                    System.out.println(q.toString());
                }
            }

        }





    }
}
