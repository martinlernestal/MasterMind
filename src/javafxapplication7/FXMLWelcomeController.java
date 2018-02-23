/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication7;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Elev
 */
public class FXMLWelcomeController implements Initializable {

    @FXML
    private Button highScore;
    @FXML
    private Button newGame;
    @FXML
    private Button rules;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    @FXML
    public void goToHighScore(ActionEvent event) throws IOException{
    
        Parent goToHighScorePage = FXMLLoader.load(getClass().getResource("FXMLHighScore.fxml"));
        Scene goToHighScore = new Scene(goToHighScorePage);
        Stage goToHS = (Stage)((Node)event.getSource()).getScene().getWindow();
        goToHS.setScene(goToHighScore);
        goToHS.show();
    
    }
    
    @FXML
    public void goToNewGame(ActionEvent event) throws IOException{
    
        Parent goToNewGame = FXMLLoader.load(getClass().getResource("RegisterBeforeGame.fxml"));
        Scene goToNewG = new Scene(goToNewGame);
        Stage goToNG = (Stage)((Node)event.getSource()).getScene().getWindow();
        goToNG.setScene(goToNewG);
        goToNG.show();
    
    }
    
}
