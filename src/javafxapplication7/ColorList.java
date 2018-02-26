/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication7;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.paint.Color;
import java.util.Random;

/**
 *
 * @author Elev
 */

// en klass som sköter antal färger och datorns gissning


public class ColorList{
    
    private int numOfColors;
    private ArrayList<Color> generatedColors = new ArrayList<>();
    private ArrayList<Color> colorList = new ArrayList<>(Arrays.asList(Color.RED, 
                                                 Color.BLUE,
                                                 Color.YELLOW,
                                                 Color.GREEN,
                                                 Color.ORANGE,
                                                 Color.TURQUOISE,
                                                 Color.WHITE,
                                                 Color.BLACK));
    private ArrayList<Color> trimmedList = new ArrayList<>();
    
    // constructorn kollar hur många färger som ska genereras och om värdena skall vara unika eller ej
    
    public ColorList(int numOfColors, boolean condition){
        
        // OM CONDITION ÄR SANN SÅ SKA DET VARA UNIKT
        
        this.numOfColors = numOfColors;
        if(numOfColors == 6){
            trimmedList = colorList;
            trimmedList.remove(7);
            trimmedList.remove(6);
            trimmedList.trimToSize();
        }
        
        setRandomRow(condition);
    }
    
    
    public ArrayList<Color> getColors(){
    
        if(numOfColors==6){
            return trimmedList;
        }
        return colorList;
    
    }
    
    public void setRandomRow(boolean condition){
    
        if(condition){
            
            // UNIKT
            
            if(numOfColors==6){
                this.generatedColors = getRandomSequenceUnique(trimmedList);
            }
            this.generatedColors = getRandomSequenceUnique(colorList);
        } else {
            
            // STANDARD
            
            if(numOfColors==6){
                this.generatedColors = getRandomSequence(trimmedList);
            }
            this.generatedColors = getRandomSequence(colorList);
        }
    }
    
    // ger tillbaka en randomsequence som inte tar hänsyn till om färgerna skall vara unika
    
    private ArrayList<Color> getRandomSequence(ArrayList<Color> inColors){
        
        
        int index;
        Random randIndex = new Random();
        ArrayList<Color> outColors = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            outColors.add(inColors.get(randIndex.nextInt(inColors.size())));
        }
        return outColors;
    }
    
    // ger tillbaka en randomsequence som tar hänsyn till att färgerna skall vara unika
    
    private ArrayList<Color> getRandomSequenceUnique(ArrayList<Color> inColors){
    
        Random randIndex = new Random();
        ArrayList<Color> outColors = new ArrayList<>();
        Color currColor;
        
        while(outColors.size() < 4){
            currColor = inColors.get(randIndex.nextInt(inColors.size()));
            if(!outColors.contains(currColor)||outColors.isEmpty()){
                outColors.add(currColor);
            }
        }
        
        return outColors;
    }
    
    public ArrayList<Color> getRandomColors(){
    
        return generatedColors;
    
    }

}
