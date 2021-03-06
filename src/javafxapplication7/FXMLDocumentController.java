/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication7;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Deque;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
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
    private boolean uniqueColor = true;
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
    @FXML
    private Button startButton;
    @FXML
    private Button startNewGame;
    @FXML
    private Button backButton;

    // METODER SOM ANROPAS FRÅN REGISTERBEFOREGAME SIDAN >>>
    
    // Den här metoden sätter antalet färger för spelomgången
    
    public void setNumOfColors(Integer numOfCol){
        
        this.numOfColors = numOfCol;
    }
    
    // Den här metoden sätter användarnamne för spelomgången
    
    public void setUserName(String userName){
    
        this.userName = userName;
    
    }
    
    // Den här metoden sätter om färgerna ska vara unika eller inte för spelomgången
    
    public void setColorConstraints(boolean condition){
    
        this.uniqueColor = condition;
 
    }
    
    //  <<< METODER SOM ANROPAS FRÅN EN REGISTERBEFOREGAME SIDAN >>>

    
    // Den här metoden genererar färgerna man kan drag-dropa i spelomgången
    
    
    private void genColorCircles(ArrayList<Color> colors){
        
        int index = 1;
        
        System.out.println("genColorCircles init");
        
        for(Color currColor: colors){
            
            colorCircles.getChildren().add(new Circle(20, currColor));
            //colorCircles.getChildren().get(index).setId("colorCircle");
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
        
    }
    
    
    
    // Den här metoden kollar den senaste raden i spelet
    
    private boolean checkRow(ArrayList<Circle> circleList, ArrayList<Color> computerColorList){
        
        Collections.reverse(circleList);
        
        ArrayList<Color> copyOfCompColList = new ArrayList<>(computerColorList);

        ArrayList <Circle> checkArray = new ArrayList<>();
        
        if(circleList.isEmpty()){
            return false;
        } else if (copyOfCompColList.isEmpty()){
            return false;
        }
        
        boolean check = true;
        
        int i = 0;
        
        for(Circle currCircle: circleList){
            
            if(i >= copyOfCompColList.size()){
                break;
            }

                if(currCircle.getFill().equals(copyOfCompColList.get(i))){

                    checkArray.add(new Circle(10.0, Color.BLACK));
                    
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
        
        // TEST för att se vilka resultatfärger som genererats

//        for(Circle currCircle: checkArray){
//            
//            if(currCircle.getFill().equals(Color.BLACK)){
//                System.out.print("|BLACK|");
//            } else if(currCircle.getFill().equals(Color.WHITE)){
//                System.out.print("|WHITE|");
//            }
//        }

        addResultsToRow(checkArray);
        i = 0;
        currResult = new Color[checkArray.size()];
        for(Circle currCircle: checkArray){
            currResult[i] = (Color)currCircle.getFill();
            i++;
        }
        
        return check;
    }
    
    // Den här metoden läser resultatraderna för senaste rundan

    private ArrayList<Color> readResultRow(FlowPane currPane){
    
        ArrayList<Color> resultColors = new ArrayList<>();
        
        
        for(Node currNode: currPane.getChildren()){
            resultColors.add((Color)((Circle)currNode).getFill());
        }
        
        return resultColors;
    
    }
    
    // Den här metoden läser in en viss runda och returnerar circlar i en list

    private ArrayList<Circle> readRow(int elementIndex){
 
        ArrayList<Circle> returnList = new ArrayList<>();
        
        for(int i = elementIndex; i > (elementIndex-4); i--){
            returnList.add((Circle)gameGrid.getChildren().get(i));
        }
        
        return returnList;
    }
    
    // Den här metoden sätter en ny resultat row
 
    private void addResultsToRow(ArrayList<Circle> circleList){
        
        Collections.reverse(circleList);
        round.pop().getChildren().addAll(circleList);
        
    }
    
    // Den här metoden visar pekaren för ivlken omgång man är på
    
    private void showRoundPointer(){
    
        pointers.getFirst().setFill(Color.RED);
        pointers.getFirst().setVisible(true);
    
    }
    
    // Den här metoden gömmer pekaren för senaste omgånge
    
    private void concealRoundPointer(){
        pointers.pop().setVisible(false);
    }
    
    
    // Den här metoden visar färgerna som datorn har genereat
 
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
    
    // Den här metoden läser in och genererar en ny runda
    
    private void generateRound(){
    
        int i = 0;
        currColors = new Color[readRow(roundCount).size()];
        for(Circle currCircle: readRow(roundCount)){
            currColors[i] = (Color)currCircle.getFill();
            i++;
        }
                
        Round currRound = new Round(currColors, currResult, new Date());
    
    }
    
    // Den här metoden lägger till ett nytt spel till databasen
    
    private void addNewGame(){
    
        Date endTime = new Date();
        java.sql.Timestamp endDate = new java.sql.Timestamp(endTime.getTime());
        
        currGame.setEndTime(endDate);
        currGame.setScore(currGame.getNumOfRounds(), currGame.getPlayTime()/2000);
        
        MySQLConnect connection = MySQLConnect.connect();
                    
        try {
            
            // LÄGGER IN SPELTID, USERNAME, NUMBER OF ROUNDS, DATORNS FÄRGER
            connection.insertNewGame(currGame);
            for(Round currRound: currGame.getRounds()){
                // lägger in dom med samma endtime alltså så får dom samma "id"
                connection.insertNewRound(currGame.getEndTime(), currRound); 
            }
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        startButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
        
        
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

        
        // LÄGGER IN STARTDATE

        
        colorList = new ColorList(numOfColors, uniqueColor);

        genColorCircles(colorList.getColors());
        computerGenRow = colorList.getRandomColors();
        
        Date startTime = new Date();
        currGame = new Game(startTime, userName, computerGenRow);
        
        
        // TEST RUNDANS KOD
        
//        System.out.println("Computers code for this game:");
//        for(Color currColor: computerGenRow){
//            if(currColor.equals(Color.BLACK)){
//                System.out.print("BLACK | ");
//            } else if(currColor.equals(Color.RED)){
//                System.out.print("RED | ");
//            } else if(currColor.equals(Color.BLUE)){
//                System.out.print("BLUE |");
//            } else if(currColor.equals(Color.YELLOW)){
//                System.out.print("YELLOW | ");
//            } else if(currColor.equals(Color.TURQUOISE)){
//                System.out.print("TURQUOISE | ");
//            } else if(currColor.equals(Color.GREEN)){
//                System.out.print("GREEN | ");
//            }  else if(currColor.equals(Color.WHITE)){
//                System.out.print("WHITE | ");
//            } else if(currColor.equals(Color.ORANGE)){
//                System.out.print("ORANGE | ");
//            }
//        }
        
        startButton.setVisible(false);
        
        pointers.getFirst().setFill(Color.RED);
        pointers.getFirst().setVisible(true);
        
        checkButton.setVisible(true);
        gameGrid.setVisible(true);
        colorCircles.setVisible(true);
        goToHighScore.setVisible(true);
        

        checkButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                
                // OM MAN VUNNIT
                if(checkRow(readRow(roundCount), computerGenRow)){
                    
                    currGame.setEndTime(new Date());
                    generateRound();
                    currGame.setScore(currGame.getNumOfRounds(), currGame.getPlayTime());
                    
                    // TEST info om rundan
                    
//                    System.out.println("You guessed right!");            
//                    System.out.println("Rundor: " + currGame.getNumOfRounds());
//                    System.out.println("Speltid : " + ((currGame.getEndTime().getTime()-currGame.getTime().getTime())/1000) + " sekunder");
//                    System.out.println("Starttid : " + currGame.getTime().toString());
                    

                    showComputerRow(computerGenRow);
                    checkButton.setVisible(false);
                    startNewGame.setVisible(true);
                    
                    addNewGame();
                    
                    // ALERT att man hade rätt
                    endOfGame("You guessed right!");
                } else {
                    if(round.isEmpty()){
                        currGame.setEndTime(new Date());
                        showComputerRow(computerGenRow);
                        checkButton.setVisible(false);
                        
                        // ALERT att man hade fel
                        endOfGame("You guessed wrong!");
                        
                    } else {
                        generateRound();
                        roundCount = roundCount - 4;
                        System.out.println("You guessed wrong!");
                        concealRoundPointer();
                        showRoundPointer();
                        // remove listeners för alla berörda blubbar
                    }
                }
            }
        });
        }
        });
    }

    // Den här metoden sätter upp så att ett dropfield kan acceptera ett drop
    @FXML
    private void handleDropField(DragEvent event) {
            event.acceptTransferModes(TransferMode.ANY);
    }

    // Den här metoden sätter färgen på cirklen man har droppat på
    
    @FXML
    private void dropFieldOnDrop(DragEvent event) {
        
        event.acceptTransferModes(TransferMode.ANY);
        
        Circle tmpDropField = (Circle) event.getAcceptingObject();
        Circle tmpColorCircle = (Circle) event.getGestureSource();
        
        
        if(event.getDragboard().getContent(DataFormat.PLAIN_TEXT).toString().equals("colorCircle")){
            tmpDropField.setFill(tmpColorCircle.getFill());
        }
        
        
    }
    
    // Den här metoden sätter byter view till higscore sidan

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
    
    // Den här metoden byter view till registreringen innan spelet, startar om spelet
    
    @FXML
    public void startNewGame(ActionEvent event) throws IOException{
    
        Parent highScoreParent = FXMLLoader.load(getClass().getResource("RegisterBeforeGame.fxml"));
        Scene highScoreScene = new Scene(highScoreParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
       
        window.setScene(highScoreScene);
        window.show();
    
    }
    
    // Den här metoden byter view till första sidan
    
    @FXML
    public void goBack(ActionEvent event) throws IOException{
    
        Parent highScoreParent = FXMLLoader.load(getClass().getResource("FXMLWelcome.fxml"));
        Scene highScoreScene = new Scene(highScoreParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(highScoreScene);
        window.show();
    
    }
    
    // Den här metoden kommer upp antingen när vi har vunnit eller förlorat och promptar om man vill spela igen
    
    public void endOfGame(String message){
    
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(message);
        alert.setContentText(message + "\nDo you want to play again?");
        Optional<ButtonType> result = alert.showAndWait();
        if((result.isPresent())&&(result.get()==ButtonType.OK)){
            Parent highScoreParent;
            try {
                highScoreParent = FXMLLoader.load(getClass().getResource("RegisterBeforeGame.fxml"));
                Scene highScoreScene = new Scene(highScoreParent);
                Stage window = JavaFXApplication7.getPrimaryStage();
                window.setScene(highScoreScene);
                window.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }         
        }else{
            alert.close();
        }
    
    }

    
}
