/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication7;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author Elev
 */
public class ColorCircles {
    
    private ArrayList<Circle> circles = new ArrayList<Circle>();
    
    public ColorCircles(ArrayList<Color> colors){
        
        for(Color currColor: colors){
            circles.add(new Circle(20.0, currColor));
        }
    }
    
    public ArrayList<Circle> getCicles (){
        return circles;
    }
    
    
}
