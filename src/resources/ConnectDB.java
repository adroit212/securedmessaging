/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ephraim
 */
public class ConnectDB {

    public String USERS_TABLE = "users";
    public String MESSAGES_TABLE = "messages";
    
    //Database connection
    public Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            // db parameters
            String url = "jdbc:sqlite::resource:resources/desktopmessaging.db";
            //String url = "jdbc:sqlite:C:/icdesktop.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            System.out.println("Connected.");
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
