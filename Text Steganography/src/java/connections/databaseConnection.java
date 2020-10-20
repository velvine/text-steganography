/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class databaseConnection {
    Connection connection;
    public Connection getConnection(){
        try{
        Class.forName("com.mysql.jdbc.Driver");
          connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital?user=root&password=");
          return connection;  
        } catch (ClassNotFoundException ex) {
//            System.out.println("Connection Error1 :Connss "+connection);
//            JOptionPane.showMessageDialog(null, ex, "Connection to Database Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(databaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null; 
        } catch (SQLException ex) {
//            System.out.println("Connection Error2 :Con:: "+connection);
//            JOptionPane.showMessageDialog(null, ex, "Connection to Database Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(databaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null; 
        }
    }
}
