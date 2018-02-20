/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication7;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javafx.scene.paint.Color;

/**
 *
 * @author Elev
 */
public class Game{
    
    private Timestamp starttime, endtime;
    private String username;
    private ArrayList<Color> computerColors;
    private ArrayList<Round> gameRounds = new ArrayList<>();
    
    
    
    public Game(Date startTime, String userName, ArrayList<Color> computerArray){
    
        starttime = new Timestamp(startTime.getTime());
        username = userName;
        computerColors = new ArrayList<>(computerArray);
        
    }
    
    public boolean setRound(Round currRound){
        
        if(gameRounds.add(currRound)){
            return true;
        }
        return false;
    }
    
    public Timestamp getTime(){
        return starttime;
    }
    public String getUser(){
        return username;
    }
    public ArrayList<Color> getComputerColors(){
        return computerColors;
    }
    public ArrayList<Round> getRounds(){
        return gameRounds;
    }
    public int getNumOfRounds(){
        return gameRounds.size();
    }
    public void setEndTime(Date endTime){
        endtime = new Timestamp(endTime.getTime());
    }
    public Timestamp getEndTime(){
        return endtime;
    }
    public long getPlayTime(){
    
        return endtime.getTime() - starttime.getTime();
    
    }
}
