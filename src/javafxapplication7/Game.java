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


// class som skapar och genererar spelomgångar


public class Game{
    
    // borde lägga in colorlist i gameklassen...!
    
    private Timestamp starttime, endtime;
    private String username;
    private ArrayList<Color> computerColors;
    private ArrayList<Round> gameRounds = new ArrayList<>();
    private Integer score;
    
    
    
    public Game(Date startTime, String userName, ArrayList<Color> computerArray){
    
        starttime = new Timestamp(startTime.getTime());
        username = userName;
        computerColors = new ArrayList<>(computerArray);
        
    }

    // constructor för att återskapa ett game
    
    public Game(int playTime, Timestamp endTime, String userName, ArrayList<Color> computerColors, ArrayList<Round> rounds){
    
        this.endtime = endTime;
        this.username = userName;
        this.computerColors = computerColors;
        this.gameRounds = rounds;
        setScore(rounds.size(), playTime);
        this.starttime = new Timestamp(endtime.getTime() - (long)playTime*1000);
     
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
    
    
    // metod för att generera en score
    
    public void setScore(int rounds, long playTime){
    
        int score = 100;
        if(computerColors.size() == 6){
            score *= 0.8;
        }
        score -= (playTime+rounds);
        if(score < 0){
            score = 0;
        }
        this.score = score; 
    }

    public Integer getScore(){
    
        return score;
    
    }
    
}
