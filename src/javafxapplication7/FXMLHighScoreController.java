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
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Elev
 */
public class FXMLHighScoreController implements Initializable {

    // man använder bara playTime för att avgöra scoren... scoren får beräknas utifrån det
    
    @FXML TableView<PropertyRenderedGame> tableView;
    @FXML TableColumn<PropertyRenderedGame, Integer> tableScore;
    @FXML TableColumn<PropertyRenderedGame, String> userName;
    @FXML TableColumn<PropertyRenderedGame, Timestamp> tableTime;
    @FXML
    private Button newGameButton;
    @FXML
    private Button backButton;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        userName.setCellValueFactory(new PropertyValueFactory<>("user"));
        tableScore.setCellValueFactory(new PropertyValueFactory<>("score"));
        tableTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        tableView.setItems(getGames());

    }
    
    @FXML
    public void goToNewGame(ActionEvent event) throws IOException{
    
        Parent goToNewGame = FXMLLoader.load(getClass().getResource("RegisterBeforeGame.fxml"));
        Scene newGameScene = new Scene(goToNewGame);
        
        Stage newGame = (Stage)((Node)event.getSource()).getScene().getWindow();
        newGame.setScene(newGameScene);
        newGame.show();
    }
    
    @FXML
    public void goBack(ActionEvent event) throws IOException{
    
        Parent highScoreParent = FXMLLoader.load(getClass().getResource("FXMLWelcome.fxml"));
        Scene highScoreScene = new Scene(highScoreParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        // get stage info
        // detta måste göras till en metod för buttonen ska liksom bubbla
        // ett event och sen ska man fånga därifrån
        
        window.setScene(highScoreScene);
        window.show();
    
    }
    
    

    public ObservableList<PropertyRenderedGame> getGames(){
        
        MySQLConnect connection = MySQLConnect.connect();
        connection.getConnection();
        ArrayList<PropertyRenderedGame> gameArray = new ArrayList<>();
        
        try {
            gameArray = connection.getHighScore();
        } catch (SQLException ex) {
            System.out.println("Fel att försöka ladda: " + ex.getMessage());
        }
        
        ObservableList<PropertyRenderedGame> games = FXCollections.observableArrayList();
        for(PropertyRenderedGame game : gameArray){
            games.add(game);
        }

        return games;
        
    }
    
}
