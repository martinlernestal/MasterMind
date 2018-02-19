/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication7;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
/**
 *
 * @author Elev
 */
public class MySQLConnect {

    private static MySQLConnect instance = null;
    private Connection conn;
    private Statement statement;
    private String url = "jdbc:mysql://localhost:3306/mastermind";
    private String user = "root";
    private String passwd = "password";
    
    private MySQLConnect(){
        try {
            conn = DriverManager.getConnection(url, user, passwd);
            // här är själva connectionen till databasen
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public Connection getConnection(){
        return conn;
    }
    
    public static MySQLConnect connect(){
        if(instance == null){
            instance = new MySQLConnect();
        }
        return instance;
    }
    
    public ResultSet queryHighScore() throws SQLException{
    
        // ORDER BY score AND 
        
        String query = "SELECT * FROM games ORDER BY score DESC";
        
        statement = instance.conn.createStatement();
        
        ResultSet res = statement.executeQuery(query);
        return res;
    }

    
    // den här borde göras om till en insert av en viss typ, så kan man använda preparedStatement etc.
    // meeen iom. att det inte finns möjlighet för input från användaren så spelar det å andra sidan ingen roll
    
    // det som ska in i tabellen är typ ID(primaryKey), TID(timestamp), ANVÄNDARNAMN(får man välja själv), POÄNG(antalet spelade rundor innan man vunnit), 
    // !!!!!!!!!!!får ta bort så defualtfärgen inte är vit!!!!!!!!!!!!
    
    public boolean insertNewGame(Date playtime, String userName, int score) throws SQLException{
    
        Calendar calendar = new Calendar.Builder().build();
        Date endDate = new Date(calendar.getTime().getTime());
        
        PreparedStatement statement = instance.conn.prepareStatement("INSERT INTO games (endDate, playTime, userName, score) VALUES(?, ?, ?, ?)");
        statement.setDate(1, endDate);
        // PLAYTIME FÅR GENERERAS under spelets gång, typ att man 
        statement.setDate(2, playtime);
        statement.setString(3, userName);
        statement.setInt(4, score);
        
        return statement.execute();
        
    }
    
}
