/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csd4464assign022019w;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author c0719943
 */
public class CSD4464Assign022019W {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        
        String name;
        Scanner kb = new Scanner(System.in);
        System.out.println("ENTER A MANUFACTURER NAME: ");
        name = kb.nextLine();
        
        String sql1 = "SELECT MANUFACTURER_ID FROM MANUFACTURER WHERE NAME = ?";
        
        String sql2 = "SELECT PRODUCT_ID, DESCRIPTION FROM PRODUCT WHERE " 
                + "MANUFACTURER_ID = ?  ORDER BY DESCRIPTION ASC" ;
           
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");

           String jdbc = ("jdbc:derby://localhost:1527/sample");
           
           Connection conn = DriverManager.getConnection(jdbc, "app", "app");
           
           PreparedStatement pstmt = conn.prepareStatement(sql1);
           pstmt.setString(1, name);
           ResultSet rs = pstmt.executeQuery();
           while (rs.next()) {
               int id = rs.getInt("MANUFACTURER_ID");
               PreparedStatement pstmt2 = conn.prepareStatement(sql2);
               pstmt2.setInt(1, id);
               ResultSet rs2 = pstmt2.executeQuery();
               
               // ANOTHER WAY TO GET IN DIFFERENT ORDER--
               //List<String> products = new ArrayList<>();
               //BY USING lIST METHOD--
               
               while (rs2.next()) {
                   System.out.printf("%s: %s\n",
                           rs2.getString("PRODUCT_ID"),
                           rs2.getString("DESCRIPTION"));
               }
               
               // SETTING IT UP
               //products.sort(null);
               //for (String s : products)
               //   System.out.println(s);    
           }
           conn.close();
          } catch (ClassNotFoundException ex) {
            Logger.getLogger(CSD4464Assign022019W.class.getName()).log(Level.SEVERE, null, ex);
        }         
                              
    }
    
}
