/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication7;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Text;
import javafxapplication7.ColorList;

/**
 *
 * @author Elev
 */
public class FXMLDocumentController implements Initializable {
    
    //private ArrayList<Color> colorList = new ArrayList<>();
    private int roundCount = 43; // ELLER VAD DET NU ÄR SOM ÄR LÄNGST NER
    private ArrayList<Color> computerGenRow = new ArrayList<>();
    private Deque<FlowPane> round = new ArrayDeque<FlowPane>();
    private Deque<Polygon> pointers = new ArrayDeque<Polygon>();
    private ArrayList<Date> playTime = new ArrayList<>();
    
    private Label label;
    @FXML
    private Circle dropField;
    private Sphere sphereOne;
    
    private PhongMaterial material = new PhongMaterial();
    @FXML
    private Circle dropField1;
    @FXML
    private Circle dropField2;
    @FXML
    private Circle dropField3;
    @FXML
    private Circle dropField31;
    @FXML
    private Circle dropField21;
    @FXML
    private Circle dropField11;
    @FXML
    private Circle dropField4;
    @FXML
    private Circle dropField32;
    @FXML
    private Circle dropField22;
    @FXML
    private Circle dropField12;
    @FXML
    private Circle dropField5;
    @FXML
    private Circle dropField33;
    @FXML
    private Circle dropField23;
    @FXML
    private Circle dropField13;
    @FXML
    private Circle dropField6;
    @FXML
    private Circle dropField34;
    @FXML
    private Circle dropField24;
    @FXML
    private Circle dropField14;
    @FXML
    private Circle dropField7;
    @FXML
    private Circle dropField35;
    @FXML
    private Circle dropField25;
    @FXML
    private Circle dropField15;
    @FXML
    private Circle dropField8;
    @FXML
    private Button checkButton;
    @FXML
    private Circle dropField9;
    @FXML
    private Circle dropField10;
    @FXML
    private Circle dropField16;
    @FXML
    private Circle dropField17;
    @FXML
    private Circle dropField18;
    @FXML
    private Circle dropField19;
    @FXML
    private Circle dropField20;
    @FXML
    private Circle dropField26;
    @FXML
    private Circle dropField27;
    @FXML
    private Circle dropField28;
    @FXML
    private Circle dropField29;
    @FXML
    private Circle dropField30;
    @FXML
    private Circle dropField36;
    @FXML
    private Circle dropField37;
    @FXML
    private Circle dropField38;
    @FXML
    private Circle dropField39;
    @FXML
    private Circle dropField40;
    @FXML
    private Circle dropField41;
    @FXML
    private Circle dropField42;
    @FXML
    private Circle dropField43;
    @FXML
    private GridPane gameGrid;
    @FXML
    private FlowPane colorCircles;

    @FXML
    private FlowPane roundOne;
    @FXML
    private FlowPane roundTwo;
    @FXML
    private FlowPane roundThree;
    @FXML
    private FlowPane roundFour;
    @FXML
    private FlowPane roundSix;
    @FXML
    private FlowPane roundFive;
    @FXML
    private FlowPane roundSeven;
    @FXML
    private FlowPane roundEight;
    @FXML
    private FlowPane roundTen;
    @FXML
    private FlowPane roundNine;
    @FXML
    private Text questionMark1;
    @FXML
    private Text questionMark2;
    @FXML
    private Text questionMark3;
    @FXML
    private Text questionMark4;
    @FXML
    private AnchorPane backGround;
    @FXML
    private Polygon pointerOne;
    @FXML
    private Polygon pointerTwo;
    @FXML
    private Polygon pointerThree;
    @FXML
    private Polygon pointerFour;
    @FXML
    private Polygon pointerTen;
    @FXML
    private Polygon pointerNine;
    @FXML
    private Polygon pointerEight;
    @FXML
    private Polygon pointerSeven;
    @FXML
    private Polygon pointerSix;
    @FXML
    private Polygon pointerFive;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    private void genColorCircles(ArrayList<Color> colors){
        
        int index = 1;
        
        System.out.println("genColorCircles init");
        
        for(Color currColor: colors){
            
            colorCircles.getChildren().add(new Circle(25, currColor));
            //colorCircles.getChildren().get(index).setId("colorCircle");
            System.out.println("genColorCircles runda:" + index);
            index++;

        }
        
        for(Node currCircle: colorCircles.getChildren()){
            
            currCircle.setId("colorCircle");
            currCircle.setOnDragDetected(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                Dragboard db = currCircle.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putString(currCircle.getId().toString());
                db.setContent(content);
                //System.out.println(content);
                System.out.println("onDragDetected");
            }
        });
        }
        
        System.out.println(colorCircles.getChildren());
        
//        for(Node currCircle: colorCircles.getChildren()){
//            currCircle.setId("colorCircle");
//            System.out.println((Circle)currCircle.getProperties());
//        }
        
        System.out.println("genColorCircles klar");
        
        // alla får samma id... 
    
    }
    
    private boolean checkRow(ArrayList<Circle> circleList, ArrayList<Color> computerColorList){
        
        Collections.reverse(circleList);
        
        ArrayList<Color> copyOfCompColList = new ArrayList<>(computerColorList);
        
        // TODO: man kan inte remove:a från en array man itererar över, man får set:a eller ändra
        
        // ALLT BLIR SPEGELVÄNT EFTERSOM MAN ITERERAR BAKLÄNGES GENOM GameGrid ELEMENTEN!
        
        // circleList som kommer in är en array av det man får från rundan
        // computerColorlist är det som datorn har genererat
        
        // ARRAYINDEX OUT OF BOUND EXCEPTION!!!!!!!!
        // lösning, glöm inte continue när man removat! sizen minskar för varje remove
        
        // DEN FÖRBRUKAR LIKSOM COLORSLIST!

        ArrayList <Circle> checkArray = new ArrayList<>();
        
        if(circleList.isEmpty()){
            return false;
        } else if (copyOfCompColList.isEmpty()){
            return false;
        }
        
        boolean check = true;
        
        // MÅSTE TA BORT FRÅN EXAKT INDEX PÅ BÅDA STÄLLEN DVS COLORLIST OCH CIRCLELIST
        // SÅ MAN UNDVIKER DUBLETTER
        // OM MAN TAR BORT PÅ SAMMA INDEX I ARRAYERNA SÅ BEHÅLLS JU ORDNINGEN  
        
        int i = 0;
        
        for(Circle currCircle: circleList){
            
            if(i >= copyOfCompColList.size()){
                break;
            }

                if(currCircle.getFill().equals(copyOfCompColList.get(i))){

                    // så kan vi skapa en svart plupp med indexet som den hittades på
                    //checkArray.put(new Circle(10.0, Color.BLACK), i);
                    // då har vi en map för index och vilken färg
                    checkArray.add(new Circle(10.0, Color.BLACK));
                    //copyOfCompColList.remove(i);
                    
                    circleList.set(i, new Circle(10.0, Color.CADETBLUE));
                    copyOfCompColList.set(i , Color.DARKGRAY);
                    
                    i++;

                    continue;
                } else {
                    i++;
                    check = false;
                }
        }
        
        if(check){
            addResultsToRow(checkArray);
            return check;
        }

        
        for(Circle currCircle: circleList){
            if(!copyOfCompColList.isEmpty()){
             if(copyOfCompColList.contains(currCircle.getFill())){
                 checkArray.add(new Circle(10.0, Color.WHITE));
                 copyOfCompColList.remove(currCircle.getFill());
                 continue;
             }
           }
        }

        for(Circle currCircle: checkArray){
            
            if(currCircle.getFill().equals(Color.BLACK)){
                System.out.print("|BLACK|");
            } else if(currCircle.getFill().equals(Color.WHITE)){
                System.out.print("|WHITE|");
            }
        }

        addResultsToRow(checkArray);

        return check;
    }

    private ArrayList<Circle> readRow(int elementIndex){
 
        ArrayList<Circle> returnList = new ArrayList<>();
        
        for(int i = elementIndex; i > (elementIndex-4); i--){
            returnList.add((Circle)gameGrid.getChildren().get(i));
        }
        
        return returnList;
    }
 
    private void addResultsToRow(ArrayList<Circle> circleList){

        // man skulle kunna göra ett deque eller liksom en stack av dom olika flowpane objekten
        // och sen bara poppa av dom
        
        round.pop().getChildren().addAll(circleList);
        
    }
    
    private void showRoundPointer(){
    
        pointers.getFirst().setFill(Color.RED);
        pointers.getFirst().setVisible(true);
    
    }
    
    private void concealRoundPointer(){
        pointers.pop().setVisible(false);
    }
 
    private void showComputerRow(ArrayList<Color> computerGenRow){
    
        Circle currCircle;
        
        for(int i = 0; i < 4; i++){
            currCircle = (Circle)gameGrid.getChildren().get(i);
            currCircle.setFill(computerGenRow.get(i));
        }
        questionMark1.setVisible(false);
        questionMark2.setVisible(false);
        questionMark3.setVisible(false);
        questionMark4.setVisible(false);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

        // TODO
    
//           sphereTwo.setOnMouseDragged(event -> drag(event));
//            circleOne.setOnMouseDragged(event -> drag(event));

        
        // getColors() ska användas för att ladda antalet färgbluppar
        // man skulle kunna använda ett grid och typ getChildren().setChild()... för att generera dom olika blupparna
        
        // row är 0 indexerad...!!!!!
        // HÄR SKA MAN SÄTTA LIKSOM DATORNS FÄRGER... DOM BORDEK UNNA SÄTTAS I EN
        // CONSTANT IOM ATT MAN KÖR OM INITIALIZE VARENDA GÅNG MAN STARTAR OM...VIEWEN?
        // här ska datorns gissning genereras, typ sättas i en konstant eller nåt
        
        
        //round.push(roundEleven);
        round.push(roundTen);
        round.push(roundNine);
        round.push(roundEight);
        round.push(roundSeven);
        round.push(roundSix);
        round.push(roundFive);
        round.push(roundFour);
        round.push(roundThree);
        round.push(roundTwo);
        round.push(roundOne);
        
        pointers.push(pointerTen);
        pointers.push(pointerNine);
        pointers.push(pointerEight);
        pointers.push(pointerSeven);
        pointers.push(pointerSix);
        pointers.push(pointerFive);
        pointers.push(pointerFour);
        pointers.push(pointerThree);
        pointers.push(pointerTwo);
        pointers.push(pointerOne);
        // behöver inte pointer till första eftersom man aldrig kommer till den

        
        // LÄGGER IN STARTDATE
        
//        Calendar calendar = new Calendar.Builder().build();
//        java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
//        playTime.add(startDate);




        // TODO: detta ska kunna styras från en annan sida
        
        ColorList colorList = new ColorList(8);
        //colorList.getRandomRow();
        System.out.println(colorList.getColors());
        genColorCircles(colorList.getColors());
        colorList.setRandomRow();
        computerGenRow = colorList.getRandomColors();
        System.out.println("Computers code for this game:");
        for(Color currColor: computerGenRow){
            if(currColor.equals(Color.BLACK)){
                System.out.print("BLACK | ");
            } else if(currColor.equals(Color.RED)){
                System.out.print("RED | ");
            } else if(currColor.equals(Color.BLUE)){
                System.out.print("BLUE |");
            } else if(currColor.equals(Color.YELLOW)){
                System.out.print("YELLOW | ");
            } else if(currColor.equals(Color.TURQUOISE)){
                System.out.print("TURQUOISE | ");
            } else if(currColor.equals(Color.GREEN)){
                System.out.print("GREEN | ");
            }  else if(currColor.equals(Color.WHITE)){
                System.out.print("WHITE | ");
            } else if(currColor.equals(Color.ORANGE)){
                System.out.print("ORANGE | ");
            }
        }
        
        pointers.getFirst().setFill(Color.RED);
        pointers.getFirst().setVisible(true);

        
        System.out.println("VÄRDET AV RÖTT: " + Color.RED);
        

        checkButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                
                if(checkRow(readRow(roundCount), computerGenRow)){
                    
                    // LÄGGER IN ENDDATE
                    
//                    Calendar calendar = new Calendar.Builder().build();
//                    java.sql.Date endDate = new java.sql.Date(calendar.getTime().getTime());
//                    playTime.add(Math.abs(startDate-endDate));
//                    

                    System.out.println("Circlarnas värden" + readRow(roundCount));
                    System.out.println("You guessed right!");
                    showComputerRow(computerGenRow);
                    checkButton.setVisible(false);
                    
                    // LÄGGER IN PLAYTIME I DATABASEN MHA. CONNECTSINGLETONEN
                    
                    
                    
                } else {
                    if(round.isEmpty()){
                        showComputerRow(computerGenRow);
                        checkButton.setVisible(false);
                    } else {
                        System.out.println("Circlarnas värden" + readRow(roundCount));
                        roundCount = roundCount - 4;
                        System.out.println("You guessed wrong!");
                        concealRoundPointer();
                        showRoundPointer();
                        // remove listeners för alla berörda blubbar
                    }
                }
            }
        });
        
        
//        colorCircles.setOnDragDetected(new EventHandler<MouseEvent>(){
//            @Override
//            public void handle(MouseEvent event) {
//                
//                System.out.println();
//                //Circle colorCircle = (Circle)event.getSource();
//                
//                Dragboard db = colorCircles.startDragAndDrop(TransferMode.ANY);
//                ClipboardContent content = new ClipboardContent();
//                content.putString(event.getSource().toString());
//                db.setContent(content);
//                System.out.println(content);
//                System.out.println("onDragDetected");
//            }
//        });


//        circleSix.setOnDragDetected(new EventHandler<MouseEvent>(){
//            @Override
//            public void handle(MouseEvent event) {
//                Dragboard db = circleSix.startDragAndDrop(TransferMode.ANY);
//                ClipboardContent content = new ClipboardContent();
//                content.putString(circleSix.getId().toString());
//                db.setContent(content);
//                System.out.println("onDragDetected");
//            }
//        });
//        
//        circleSeven.setOnDragDetected(new EventHandler<MouseEvent>(){
//            @Override
//            public void handle(MouseEvent event) {
//                Dragboard db = circleSeven.startDragAndDrop(TransferMode.ANY);
//                ClipboardContent content = new ClipboardContent();
//                content.putString(circleSeven.getId().toString());
//                db.setContent(content);
//                System.out.println("onDragDetected");
//            }
//        });
//
//        circleEight.setOnDragDetected(new EventHandler<MouseEvent>(){
//            @Override
//            public void handle(MouseEvent event) {
//                Dragboard db = circleEight.startDragAndDrop(TransferMode.ANY);
//                ClipboardContent content = new ClipboardContent();
//                content.putString(circleEight.getId().toString());
//                db.setContent(content);
//                System.out.println("onDragDetected");
//            }
//        });
//        
//        circleOne.setOnDragDetected(new EventHandler<MouseEvent>(){
//            @Override
//            public void handle(MouseEvent event) {
//                Dragboard db = circleOne.startDragAndDrop(TransferMode.ANY);
//                ClipboardContent content = new ClipboardContent();
//                content.putString(circleOne.getId().toString());
//                db.setContent(content);
//                System.out.println("onDragDetected");
//            }
//        });
//        
//        circleThree.setOnDragDetected(new EventHandler<MouseEvent>(){
//            @Override
//            public void handle(MouseEvent event) {
//                Dragboard db = circleThree.startDragAndDrop(TransferMode.ANY);
//                ClipboardContent content = new ClipboardContent();
//                content.putString(circleThree.getId().toString());
//                db.setContent(content);
//                System.out.println("onDragDetected");
//            }
//        });
//        
//        circleFour.setOnDragDetected(new EventHandler<MouseEvent>(){
//            @Override
//            public void handle(MouseEvent event) {
//                Dragboard db = circleFour.startDragAndDrop(TransferMode.ANY);
//                ClipboardContent content = new ClipboardContent();
//                content.putString(circleFour.getId().toString());
//                db.setContent(content);
//                System.out.println("onDragDetected");   
//            }
//        });
//       
//        circleFive.setOnDragDetected(new EventHandler<MouseEvent>(){
//            @Override
//            public void handle(MouseEvent event) {
//                Dragboard db = circleFive.startDragAndDrop(TransferMode.ANY);
//                ClipboardContent content = new ClipboardContent();
//                content.putString(circleFive.getId().toString());
//                db.setContent(content);
//                System.out.println("onDragDetected");
//            }
//        });
//        
//        circleTwo.setOnDragDetected(new EventHandler<MouseEvent>(){
//            @Override
//            public void handle(MouseEvent event) {
//                Dragboard db = circleTwo.startDragAndDrop(TransferMode.ANY);
//                ClipboardContent content = new ClipboardContent();
//                content.putString(circleTwo.getId().toString());
//                db.setContent(content);
//                System.out.println("onDragDetected");
//                
//            }
//        });
        
//        sphereTwo.setOnDragDetected(new EventHandler<MouseEvent>(){
//            @Override
//            public void handle(MouseEvent event) {
//                Dragboard db = sphereTwo.startDragAndDrop(TransferMode.ANY);
//                ClipboardContent content = new ClipboardContent();
//                content.putString(sphereTwo.getId().toString());
//                db.setContent(content);
//                System.out.println("onDragDetected");
//            }
//        });
        
    }

    public void drag(MouseEvent event){
        Node n = (Node)event.getSource();
        n.setTranslateX(n.getTranslateX() + event.getX());
        n.setTranslateY(n.getTranslateY() + event.getY());
    }

    @FXML
    private void handleDropField(DragEvent event) {
        // kan man liksom ta emot en sträng, alltså en html färgkod?
        // som kan färga det eventuella klotet...?
            event.acceptTransferModes(TransferMode.ANY);
            //System.out.println(event.getDragboard().getContent(DataFormat.PLAIN_TEXT));
    }

    
    private void circleTwoDragDetect(MouseEvent event) {
    
        System.out.println("Drag started from circle two");
    
    }

    private void circleOneDragDetect(MouseEvent event) {
        
        System.out.println("Drag started from circle one");
        
    }

    @FXML
    private void dropFieldOnDrop(DragEvent event) {
        
        event.acceptTransferModes(TransferMode.ANY);
        
        Circle tmpDropField = (Circle) event.getAcceptingObject();
        Circle tmpColorCircle = (Circle) event.getGestureSource();
        
        
        if(event.getDragboard().getContent(DataFormat.PLAIN_TEXT).toString().equals("colorCircle")){
            System.out.println(tmpColorCircle.getFill());
            tmpDropField.setFill(tmpColorCircle.getFill());
        }
        
//        if(event.getDragboard().getContent(DataFormat.PLAIN_TEXT).toString().equals("circleOne")){
//            System.out.println("DROP BY: Circle one");
//            tmpDropField.setFill(Color.GREEN);
//        } else if (event.getDragboard().getContent(DataFormat.PLAIN_TEXT).toString().equals("circleTwo")) {
//            System.out.println("DROP BY: Circle two");
//            tmpDropField.setFill(Color.RED);
//        } else if (event.getDragboard().getContent(DataFormat.PLAIN_TEXT).toString().equals("circleThree")){
//            System.out.println("DROP BY: Circle three");
//            tmpDropField.setFill(Color.YELLOW);
//        } else if (event.getDragboard().getContent(DataFormat.PLAIN_TEXT).toString().equals("circleFour")){
//            System.out.println("DROP BY: Circle four");
//            tmpDropField.setFill(Color.BLUE);
//        } else if (event.getDragboard().getContent(DataFormat.PLAIN_TEXT).toString().equals("circleFive")){
//            System.out.println("DROP BY: Circle five");
//            tmpDropField.setFill(Color.BLACK);
//        } else if (event.getDragboard().getContent(DataFormat.PLAIN_TEXT).toString().equals("circleSix")) {
//            System.out.println("DROP BY: Circle six");
//            tmpDropField.setFill(Color.ORANGE);
//        } else if (event.getDragboard().getContent(DataFormat.PLAIN_TEXT).toString().equals("circleSeven")) {
//            System.out.println("DROP BY: Circle seven");
//            tmpDropField.setFill(Color.WHITE);
//        } else if (event.getDragboard().getContent(DataFormat.PLAIN_TEXT).toString().equals("circleEight")) {
//            System.out.println("DROP BY: Circle eight");
//            tmpDropField.setFill(Color.TURQUOISE);
//        }
        
        System.out.println(tmpDropField.getFill().equals(Color.BLACK));
        //System.out.println("DROP BY :" + event.getDragboard().getContent(DataFormat.PLAIN_TEXT).toString());
    }

    private void sphereDragDetect(MouseEvent event) {
        
        System.out.println(event.getEventType());
         System.out.println("Drag detected on sphere");
         System.out.println(event.getTarget());
        
    }

    private void sphereDragDropped(DragEvent event) {
        
        event.acceptTransferModes(TransferMode.ANY);
        System.out.println(event.getEventType());
        
        PhongMaterial material = new PhongMaterial();
        
        
        System.out.println(event.getSource().toString());
        
        if(event.getDragboard().getContent(DataFormat.PLAIN_TEXT).toString().equals("circleOne")){
            System.out.println("DROP BY: Circle one");
            material.setDiffuseColor(Color.DARKGREEN);
            material.setSpecularColor(Color.GREEN);
            sphereOne.setMaterial(material);
        } else if (event.getDragboard().getContent(DataFormat.PLAIN_TEXT).toString().equals("circleTwo")) {
            System.out.println("DROP BY: Circle two");
            material.setDiffuseColor(Color.DARKRED);
            material.setSpecularColor(Color.RED);
            sphereOne.setMaterial(material);
        } else if (event.getDragboard().getContent(DataFormat.PLAIN_TEXT).toString().equals("circleThree")){
            System.out.println("DROP BY: Circle three");
            material.setDiffuseColor(Color.WHEAT);
            material.setSpecularColor(Color.YELLOW);
            sphereOne.setMaterial(material);
        } else if (event.getDragboard().getContent(DataFormat.PLAIN_TEXT).toString().equals("circleFour")){
            System.out.println("DROP BY: Circle four");
            material.setDiffuseColor(Color.DARKBLUE);
            material.setSpecularColor(Color.BLUE);
            sphereOne.setMaterial(material);
        } else if (event.getDragboard().getContent(DataFormat.PLAIN_TEXT).toString().equals("circleFive")){
            System.out.println("DROP BY: Circle five");
            material.setDiffuseColor(Color.BLACK);
            material.setSpecularColor(Color.GRAY);
            sphereOne.setMaterial(material);
        }

        
    }

    
}
