/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication7;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author Elev
 */
public class PropertyRenderedGame {

    private SimpleStringProperty user;
    private SimpleIntegerProperty score;
    private Timestamp time;
    private SimpleStringProperty computerColors;
    
    
    public PropertyRenderedGame(Game currGame) {
        this.user = new SimpleStringProperty(currGame.getUser());
        this.score = new SimpleIntegerProperty(currGame.getScore());
        this.time = currGame.getTime();
    }
    
    // lowlevel assign
    
    public PropertyRenderedGame(String userName, int score, Timestamp time, String color1, String color2, String color3, String color4){
    
        this.time = time;
        this.user = new SimpleStringProperty(userName);
        this.score = new SimpleIntegerProperty(score);
        this.computerColors = new SimpleStringProperty(generateColor(color1));
        
        String colors = "";
        colors += generateColor(color1);
        colors += generateColor(color2);
        colors += generateColor(color3);
        colors += generateColor(color4);
        
        this.computerColors = new SimpleStringProperty(colors);
    }
    
    public String getUser(){
    
        return user.get();
    }
    
    public String generateColor(String hexColor){
          
        Color inColor;
        inColor = Color.web(hexColor);
        if(inColor.equals(Color.BLACK)){
            return " Black ";
        } else if (inColor.equals(Color.WHITE)){
            return " White ";
        } else if (inColor.equals(Color.RED)){
            return " Red "; 
        } else if (inColor.equals(Color.BLUE)){
            return " Blue ";
        } else if (inColor.equals(Color.ORANGE)){
            return " Orange ";
        } else if (inColor.equals(Color.TURQUOISE)){
            return " Turquoise ";
        } else if(inColor.equals(Color.YELLOW)){
            return " Yellow ";
        } else if (inColor.equals(Color.GREEN)){
            return " Green ";
        }
        return "";
    }
    
    public String getComputerColors(){
        return computerColors.get();
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
