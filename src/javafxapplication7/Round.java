/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication7;

import java.sql.Timestamp;
import javafx.scene.paint.Color;

/**
 *
 * @author Elev
 */

// en klass som generear en ny runda

public class Round {
    
    private Color[] colorGuess = new Color[4];
    private Color[] guessResult = new Color[4];
    private Timestamp timeOfGuess;
    
    public Round(javafx.scene.paint.Color[] colorGuess, javafx.scene.paint.Color[] guessResult, java.util.Date timeOfGuess){
   
        this.colorGuess = colorGuess;
        this.guessResult = guessResult;
        this.timeOfGuess = new Timestamp(timeOfGuess.getTime());
        
    }
    
    public Round(javafx.scene.paint.Color[] colorGuess, javafx.scene.paint.Color[] guessResult, Timestamp timeOfGuess){
    
        this.timeOfGuess = timeOfGuess;
        this.colorGuess = colorGuess;
        this.guessResult = guessResult;
    
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
    
}
