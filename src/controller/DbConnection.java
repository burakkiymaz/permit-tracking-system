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
    private String user = "root";
    /*mysql user name*/
    private String pass = "bnmsql";
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

    public void Backupdbtosql(String saveLocation) {
        try {

            String executeCmd = "";
            executeCmd = "mysqldump -u " + user + " -p" + pass + " --add-drop-database -B " + dbName + " -r "+saveLocation+"/backup.sql";

            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
            if (processComplete == 0) {
                System.out.println("Backup taken successfully..!");
            } else {
                System.out.println("Could not take mysql backup");
            }

        } catch (IOException | InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "Error at Backuprestore" + ex.getMessage());
        }
    }

    public void Restoredbfromsql(String recieveLocation) {
        try {
            String[] executeCmd;
            executeCmd = new String[]{"/bin/sh", "-c", "mysql -u" + user + " -p" + pass +  " < "+ recieveLocation +"/backup.sql"};//burayı stored prosedure ile yap.. 
            System.out.println("Database Restoring..");
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
            if (processComplete == 0) {
                JOptionPane.showMessageDialog(null, "Restore Successfull!");
            } else {
                JOptionPane.showMessageDialog(null, "Restore Failure!");
            }
            

        } catch (IOException | InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "Error at Backuprestore" + ex.getMessage());
        }
    }

}
