/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author burak
 */
public class DbConnection {

    private Connection con;
    private String user = "username"; // PLEASE ENTER YOUR USER NAME
    /*mysql user name*/
    private String pass = "password"; //PLEASE ENTER YOUR MYSQL PASSWORD
    /*password*/
    private String dbName = "izinTakip";
    /*DB Name*/
    private String database_url = "jdbc:mysql://127.0.0.1:3306/"+dbName+"?characterEncoding=utf8";

    /*db url*/

    public boolean Connect() {
        try {
            System.out.println("Connecting MySQL Database...");
            Class.forName("com.mysql.jdbc.Connection");
            con = (Connection) DriverManager.getConnection(database_url, user, pass);

            System.out.println("Database Connected...");
            return true;
        } catch (Exception e) {
            try {
                System.out.println("Database not found..\n Please wait.. ");
                Restoredbfromsql("asd");
                Connect();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Please select a database...");
            }

            return false;
        }
    }

    public boolean disConnect() {
        try {
            System.out.println("MySQL Database disconnecting...");
            getCon().close();
            System.out.println("Database Disconneted...");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Can not disconnect database...\n Error:  " + e.getMessage());
            return false;
        }
    }

    /**
     * @return the con
     */
    public Connection getCon() {
        return con;
    }

}
