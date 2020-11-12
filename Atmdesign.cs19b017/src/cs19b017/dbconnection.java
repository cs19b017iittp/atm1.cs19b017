package cs19b017;

import javax.swing.*;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
public class dbconnection {
    public static Connection connect() {
        Connection con = null;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:customersinfo.db"); // connecting to our database
            System.out.println("Connected!");
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            System.out.println(e + "");
        }

        return con;
    }

    public static void check(String user, String password) {
        Connection con = dbconnection.connect();
        PreparedStatement ps = null;
        PreparedStatement ps1=null;
        ResultSet rs = null;
        ResultSet rs1=null;
        try {
            String sql = "Select password from custinfo where accNum = ? ";
            String sql1="Select userName from custinfo where accNum = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, user);
            rs = ps.executeQuery();

            ps1=con.prepareStatement(sql1);
            ps1.setString(1,user);
            rs1= ps1.executeQuery();

            // we are reading one row, so no need to loop
            String pass = rs.getString(1);
            String uname=rs1.getString(1);
            if(pass.equals(password)){
                JOptionPane.showMessageDialog(null,"welcome "+uname);
                AtmUI.Atm2();
            }
            else {
                JOptionPane.showMessageDialog(null, "invalid login details");
            }


        } catch(SQLException q) {
            System.out.println(q.toString());
        } finally {
            // close connections
            try{
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException q) {
                // TODO: handle exception
                System.out.println(q.toString());
            }

        }

    }
public static void create(String user,String pass,String phone,String acbal,String aId){
    Connection con = dbconnection.connect();
    PreparedStatement ps = null;
    try {
        String sql = "INSERT INTO custinfo(userName,password,phoneNO,accBal,accNum) VALUES(?,?,?,?,?)";
        ps = con.prepareStatement(sql);
        ps.setString(1,user);
        ps.setString(2,pass);
        ps.setString(3,phone);
        ps.setString(4,acbal);
        ps.setString(5,aId);
        ps.execute();
        System.out.println("Data has been inserted!");
        JOptionPane.showMessageDialog(null,"congrats your account has been created");
        JOptionPane.showMessageDialog(null,"your account No."+aId);
        JOptionPane.showMessageDialog(null,"please cuntinue with Existing Customer");
        atm.Atm1();
    } catch(SQLException e) {
        System.out.println(e.toString());
        JOptionPane.showMessageDialog(null,"error please try again");
        // always remember to close database connections
    }

}

public static void withdraw(String user,int amu){
    Connection con = dbconnection.connect();
    PreparedStatement ps = null,ps1=null;
    ResultSet rs = null;
    try {
        String sql = "UPDATE custinfo set accBal = ? WHERE accNum = ? ";
        String sql1="Select accBal from custinfo where accNum = ?";
        ps1=con.prepareStatement(sql1);
        ps1.setString(1,user);
        rs= ps1.executeQuery();

        int bal= rs.getInt(1);
        JOptionPane.showMessageDialog(null,"Account Balance  "+ bal);

        ps = con.prepareStatement(sql);
        ps.setString(2, user);
        ps.setInt(1, bal-amu);
        ps.execute();
        JOptionPane.showMessageDialog(null,"Account Balance After Withdrawl"+(bal-amu));
        System.out.println("Data has been updated");
    } catch (
            SQLException q) {
        // TODO: handle exception
        System.out.println(q.toString());
    }


    }

    public static void deposit(String user,int amu){
        Connection con = dbconnection.connect();
        PreparedStatement ps = null,ps1=null;
        ResultSet rs = null;
        try {
            String sql = "UPDATE custinfo set accBal = ? WHERE accNum = ? ";
            String sql1="Select accBal from custinfo where accNum = ?";
            ps1=con.prepareStatement(sql1);
            ps1.setString(1,user);
            rs= ps1.executeQuery();

            int bal= rs.getInt(1);
            JOptionPane.showMessageDialog(null,"Account Balance  "+ bal);

            ps = con.prepareStatement(sql);
            ps.setString(2, user);
            ps.setInt(1, bal+amu);
            ps.execute();
            JOptionPane.showMessageDialog(null,"Account Balance After Deposit  "+(bal+amu));
            System.out.println("Data has been updated");
        } catch (
                SQLException q) {
            // TODO: handle exception
            System.out.println(q.toString());
        }



    }
}

