/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication7;
import java.util.Calendar;
//import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafxapplication7.MySQLConnect;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author Elev
 */
public class TestSingletonMySQL {
    
    public static void main(String[] args){
    
        
        //Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //System.out.println(timestamp.getTime());
        System.out.println(java.time.Instant.now());
        //Date date = new Date();
        //System.out.println(System.currentTimeMillis());
        
        //System.out.println(java.time.Instant.now().getEpochSecond());
        //System.out.println(Calendar.getInstance());
        
//        Date startDate = new Date(Calendar.getInstance().getTimeInMillis());
//        
//        Date endDate = new Date(Calendar.getInstance().getTimeInMillis());
//
//        Date playTime = new Date(startDate.getTime() - endDate.getTime());
//        
        




//        System.out.println(playTime.getTime());
//        
//        try {
//            MySQLConnect connect = MySQLConnect.connect();
//            connect.insertNewGame(playTime, "Martin", 4);
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
    
    }
    
}
