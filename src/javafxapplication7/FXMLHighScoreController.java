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
import java.util.ResourceBundle;
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
    @FXML
    private TableColumn<PropertyRenderedGame, String> computerColors;
    
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
        computerColors.setCellValueFactory(new PropertyValueFactory("computerColors"));
        tableView.setItems(getGames());

    }
    
    // metod för att påbörja ett nytt spel
    
    @FXML
    public void goToNewGame(ActionEvent event) throws IOException{
    
        Parent goToNewGame = FXMLLoader.load(getClass().getResource("RegisterBeforeGame.fxml"));
        Scene newGameScene = new Scene(goToNewGame);
        
        Stage newGame = (Stage)((Node)event.getSource()).getScene().getWindow();
        newGame.setScene(newGameScene);
        newGame.show();
    }
    
    // metod för att gå tillbaka till första sidan/viewen
    
    @FXML
    public void goBack(ActionEvent event) throws IOException{
    
        Parent highScoreParent = FXMLLoader.load(getClass().getResource("FXMLWelcome.fxml"));
        Scene highScoreScene = new Scene(highScoreParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(highScoreScene);
        window.show();
    
    }
    
    // metod för att ge en list av alla rows som ska sättas in i tabellen

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
