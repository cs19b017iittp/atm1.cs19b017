package cs19b017;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class withdrawl implements ActionListener {
    private static JLabel wlabel;
    private static JTextField func;
    private static JButton wbutton;

   public static void wdwrl() {
       JPanel panel = new JPanel();
       JFrame frame = new JFrame();
       frame.setSize(650, 100);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.add(panel);
       panel.setLayout(null);

       wlabel = new JLabel("ENTER THE AMMOUNT YOU WANT TO DRAW");
       wlabel.setBounds(20, 20, 400, 25);
       panel.add(wlabel);

       func=new JTextField(25);
       func.setBounds(40,60,200,25);
       panel.add(func);


       wbutton = new JButton("SUBMIT");
       wbutton.setBounds(80, 100, 80, 25);
       wbutton.addActionListener(new withdrawl());
       panel.add(wbutton);
       frame.setVisible(true);


   }

    @Override
    public void actionPerformed(ActionEvent e) {
        int amu = Integer.parseInt(func.getText());
        String user = customers.getUsername();
        dbconnection.withdraw(user, amu);


        Connection con = dbconnection.connect();
        PreparedStatement ps = null,ps2=null;
        ResultSet rs2=null;
        try {

            String sql1="Select cashDisp from dispencer where no= ?";
            ps2=con.prepareStatement(sql1);
            ps2.setString(1,"1");
            rs2=ps2.executeQuery();
            int am=rs2.getInt(1);

            System.out.println(am);

            String sql = "UPDATE dispencer set cashDisp = ? WHERE no=? ";
            ps = con.prepareStatement(sql);
            ps.setInt(1,am-amu);
            ps.setString(2,"1");
            ps.execute();


        } catch(SQLException q) {
            System.out.println(q.toString());
            JOptionPane.showMessageDialog(null,"error please try again");
            // always remember to close database connections
        }
   }

}
