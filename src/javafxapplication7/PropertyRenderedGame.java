/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication7;

import java.sql.Timestamp;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Elev
 */
public class PropertyRenderedGame {

    private SimpleStringProperty user;
    private SimpleIntegerProperty score;
    private Timestamp time;
    
    
    public PropertyRenderedGame(Game currGame) {
        this.user = new SimpleStringProperty(currGame.getUser());
        this.score = new SimpleIntegerProperty(currGame.getScore());
        this.time = currGame.getTime();
    }
    
    // lowlevel assign
    
    public PropertyRenderedGame(String userName, int score, Timestamp time){
    
        this.time = time;
        this.user = new SimpleStringProperty(userName);
        this.score = new SimpleIntegerProperty(score);
    
    }
    
    public String getUser(){
    
        return user.get();
    }

    public void setUser(SimpleStringProperty user) {
        this.user = user;
    }

    public Integer getScore() {
        return score.get();
    }

    public void setScore(SimpleIntegerProperty score) {
        this.score = score;
    }

    public Timestamp getTime() {
        return time;
    }

    // object assign
    public void setTime(Timestamp time) {
        this.time = time;
    }
    
}
