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
public class Round {
    
    private Color[] colorGuess = new Color[4];
    private Color[] guessResult = new Color[4];
    private Timestamp timeOfGuess;
    //Sedan ska varje drag registreras med färgkod, markörer och tidpunkt.
    
    public Round(javafx.scene.paint.Color[] colorGuess, javafx.scene.paint.Color[] guessResult, java.util.Date timeOfGuess){
   
        this.colorGuess = colorGuess;
        this.guessResult = guessResult;
        this.timeOfGuess = new Timestamp(timeOfGuess.getTime());
        
    }
    
    public Color[] getGuess(){
        return colorGuess;
    }
    
    public Color[] getResult(){
        return guessResult;
    }
    
    public Timestamp getTime(){
    
        return timeOfGuess;
    }
    

    
    public String toString(){
        
    
        return  "Colorguessed: " + getGuess() + "\n" +
                "Pins result: " + getResult() + "\n" +
                "Time of guess: " + getTime();
        
    }
    
}
