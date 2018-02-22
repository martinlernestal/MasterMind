/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication7;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Deque;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Elev
 */


/*
TODO:
    sänk opaciteten, remove:a eventhandlern på allt som redan är spelat eller som inte är nuvarande rundan
    
    läs in den sista rundan också innan spelet avslutas... annars har man ju bara nio chanser på sig

*/


public class FXMLDocumentController implements Initializable {
    
    //private ArrayList<Color> colorList = new ArrayList<>();
    private ColorList colorList;
    private int numOfColors = 6;
    private int roundCount = 43; // ELLER VAD DET NU ÄR SOM ÄR LÄNGST NER
    private ArrayList<Color> computerGenRow = new ArrayList<>();
    private Deque<FlowPane> round = new ArrayDeque<>();
    private Deque<Polygon> pointers = new ArrayDeque<>();
    private String userName = "unknown";
    private Game currGame;
    private Color[] currResult;
    private Color[] currColors; 
    private Label label;
//    @FXML
//    private Circle dropField;
    private Sphere sphereOne;  
    private PhongMaterial material = new PhongMaterial();
    @FXML
    private Circle dropField, dropField1, dropField2, dropField3, dropField4,
            dropField5, dropField6, dropField7, dropField8, dropField9,
            dropField10, dropField11, dropField12, dropField13, dropField14,
            dropField15, dropField16, dropField17, dropField18, dropField19,
            dropField20, dropField21, dropField22, dropField23, dropField24,
            dropField25, dropField26, dropField27, dropField28, dropField29,
            dropField30, dropField31, dropField32, dropField33, dropField34,
            dropField35, dropField36, dropField37, dropField38, dropField39,
            dropField40, dropField41, dropField42, dropField43;
    @FXML
    private Button checkButton;
    @FXML
    private GridPane gameGrid; 
    @FXML
    private FlowPane colorCircles, roundOne, roundTwo, roundThree, roundFour,
            roundSix, roundFive, roundSeven, roundEight, roundNine, roundTen;
    @FXML
    private Text questionMark1, questionMark2, questionMark3, questionMark4;
    @FXML
    private AnchorPane backGround;
    @FXML
    private Polygon pointerOne, pointerTwo, pointerThree, pointerFour, pointerTen,
            pointerNine, pointerEight, pointerSeven, pointerSix, pointerFive;
    @FXML
    private Button goToHighScore;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    public void setNumOfColors(Integer numOfCol){
        // om detta skapas innan man startar spelet så är det ju inte så bra...
        // fast allt måste ju kompileras innan så det kan väl inte varan ågora problem?
        // evnetuellt uppstår det ett nullpointerexception här
        //this.numOfColors = numOfCol;
        colorList = new ColorList(numOfCol);
    }
    
    public void setUserName(String userName){
    
        this.userName = userName;
    
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
        
        // Man kan inte remove:a från en array man itererar över, man får set:a eller ändra
        
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
        i = 0;
        currResult = new Color[checkArray.size()];
        for(Circle currCircle: checkArray){
            System.out.println("Circle color: " + currCircle.getFill());
            currResult[i] = (Color)currCircle.getFill();
            i++;
        }

        // på första rundan får man inte gissningen, är det för att man bara läser i efterhand???
        
        return check;
    }
    
    
    // DELA UPP ALLT MER!
    
    
    private ArrayList<Color> readResultRow(FlowPane currPane){
    
        ArrayList<Color> resultColors = new ArrayList<>();
        
        
        for(Node currNode: currPane.getChildren()){
            resultColors.add((Color)((Circle)currNode).getFill());
        }
        
        return resultColors;
    
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
    
    private void generateRound(){
    
        int i = 0;
        currColors = new Color[readRow(roundCount).size()];
        for(Circle currCircle: readRow(roundCount)){
            currColors[i] = (Color)currCircle.getFill();
            i++;
        }
                
        Round currRound = new Round(currColors, currResult, new Date());
                
        if(currGame.setRound(new Round(currColors, currResult, new Date()))){
            System.out.println("round was set");
        }
    
    }
    
    private void addNewGame(){
    
        Date endTime = new Date();
        java.sql.Timestamp endDate = new java.sql.Timestamp(endTime.getTime());
        
        MySQLConnect connection = MySQLConnect.connect();
                    
        try {
            
            // LÄGGER IN SPELTID, USERNAME, NUMBER OF ROUNDS, DATORNS FÄRGER
            connection.insertNewGame((currGame.getPlayTime()/1000), 
                                      currGame.getUser(), currGame.getNumOfRounds(), 
                                      currGame.getComputerColors().get(0).toString(), 
                                      currGame.getComputerColors().get(1).toString(), 
                                      currGame.getComputerColors().get(2).toString(), 
                                      currGame.getComputerColors().get(3).toString());
            for(Round currRound: currGame.getRounds()){
                connection.insertNewRound(currGame.getEndTime(), currRound); 
            }
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        // FÖR ATT TESTA ATT VI FÅR ALLT FRÅN ETT ÅTERSKAPAT GAME
        // DETTA FUNKAR MEN DET GER GAMET I DESCENDING ORDER:
        
        
        
//        Game testGame = null;
//        MySQLConnect testConn = MySQLConnect.connect();
//        try {
//            
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            Date testDate = sdf.parse("2018-02-21 11:23:02");
//            Timestamp testTime = new Timestamp(testDate.getTime());
//            testGame = testConn.recreateGame(testTime);
//            
//            System.out.println("Endtime:"+ testGame.getEndTime());
//            System.out.println("Datorns färger: ");
//            for(Color currColor : testGame.getComputerColors()){
//                System.out.print(currColor + "|");
//            }
//            System.out.println("Antal rundor: " + testGame.getNumOfRounds());
//            System.out.println("Rundor: ");
//            for(Round currRound : testGame.getRounds()){
//                
//                // Det funkar.... allt är dock descending... obs!
//                
//                System.out.println("\n" + currRound.getTime().toString());
//                System.out.println("Guesses for current round: ");
//                for(Color currColor : currRound.getGuess()){
//                    System.out.print(currColor.toString()+" ");
//                }
//                System.out.println("\nResults for current round:");
//                for(Color currColor : currRound.getResult()){
//                    System.out.print(currColor.toString()+" ");
//                }
//                System.out.println("");
//            }
//            
//        } catch (ParseException ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        

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

        // TODO: detta ska kunna styras från en annan sida
        
        //colorList = new ColorList(numOfColors);
        System.out.println("USERNAME:" + userName);

        genColorCircles(colorList.getColors());
        colorList.setRandomRow();
        computerGenRow = colorList.getRandomColors();
        
        Date startTime = new Date();
        currGame = new Game(startTime, userName, computerGenRow);
        
        
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

        checkButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                
                // OM MAN VUNNIT
                if(checkRow(readRow(roundCount), computerGenRow)){
                    
                    currGame.setEndTime(new Date());
                    generateRound();
                    currGame.setScore(currGame.getNumOfRounds(), currGame.getPlayTime());
                    System.out.println("You guessed right!");
                    
                    // måste vända på den, för när man addar så läggs det in spegelvänt
                    
                    
                    
                    // obs. att score kan räknas ut med typ 10000 / (rundor * tid/1000)
                    // 10000 ska vara omöjligt att nå...!
                    // då får manliksom en icke linjär score
                    // det ska ju vara tomt på toppen...
                    
                    
                    // men hur ska man lagra varje drag... det är ju en relational database...
                    // spelen sparas ju med ett unikt id
                    // man kan ju också spara dom som något slags hexindex som alltid blir unikt
                    // sen kan man knyta allt till det, ta tillvara på hexet som lagras och lägga till dom olika rundorna etc.
                    
                    // detta är ju typ verkligen gjort för att lagra som någon slags objekt form, att man göra ett objekt för varje game
                    // och lagrar allt i olika fields
                    

                    
                    System.out.println("Rundor: " + currGame.getNumOfRounds());
                    // det är här det blir error
                    System.out.println("Speltid : " + ((currGame.getEndTime().getTime()-currGame.getTime().getTime())/1000) + " sekunder");
                    System.out.println("Starttid : " + currGame.getTime().getTime());
                    

                    showComputerRow(computerGenRow);
                    checkButton.setVisible(false);
                    
                    addNewGame();
                    
                    for(Round currRoun : currGame.getRounds()){
                        System.out.println(currRoun.toString());
                    }
                    
                    
                } else {
                    if(round.isEmpty()){
                        currGame.setEndTime(new Date());
                        showComputerRow(computerGenRow);
                        checkButton.setVisible(false);
                        addNewGame();
                    } else {
                        generateRound();
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
        
        
        // EXEMPEL PÅ ANNAN LÖSNING FÖR CIRKLARNA:
        
        
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

    @FXML
    public void changeScreenToHighScore(ActionEvent event) throws IOException{
        
        // byta till highscore sidan
        Parent highScoreParent = FXMLLoader.load(getClass().getResource("FXMLHighScore.fxml"));
        Scene highScoreScene = new Scene(highScoreParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        
        
                    
        // get stage info
        // detta måste göras till en metod för buttonen ska liksom bubbla
        // ett event och sen ska man fånga därifrån
        
        window.setScene(highScoreScene);
        window.show();
    }
    
    
}
