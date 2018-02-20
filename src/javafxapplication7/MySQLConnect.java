/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication7;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.DriverManager;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
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
        
        String query = "SELECT * FROM games ORDER BY score";
        
        statement = instance.conn.createStatement();
        
        ResultSet res = statement.executeQuery(query);
        return res;
    }

    
    // den här borde göras om till en insert av en viss typ, så kan man använda preparedStatement etc.
    // meeen iom. att det inte finns möjlighet för input från användaren så spelar det å andra sidan ingen roll
    
    // det som ska in i tabellen är typ ID(primaryKey), TID(timestamp), ANVÄNDARNAMN(får man välja själv), POÄNG(antalet spelade rundor innan man vunnit), 
    // !!!!!!!!!!!får ta bort så defualtfärgen inte är vit!!!!!!!!!!!!
    
    public int insertNewGame(long playtime, String userName, int score, String color1, String color2, String color3, String color4) throws SQLException{
    
        Date endTime = new Date();
        
        Timestamp enddate = new Timestamp(endTime.getTime());
        
        PreparedStatement stmt= instance.conn.prepareStatement("INSERT INTO games (endDate, playTime, userName, score, color1, color2, color3, color4) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
        stmt.setTimestamp(1, enddate);
        // PLAYTIME FÅR GENERERAS under spelets gång, typ att man 
        stmt.setLong(2, playtime);
        stmt.setString(3, userName);
        stmt.setInt(4, score);
        stmt.setString(5, color1);
        stmt.setString(6, color2);
        stmt.setString(7, color3);
        stmt.setString(8, color4);
        int insertStatus = stmt.executeUpdate();
        instance.conn.close();
        
        return insertStatus;
        
    }
    
}
