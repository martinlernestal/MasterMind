/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication7;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Elev
 */
public class RegisterBeforeGameController implements Initializable {

    @FXML
    private Button startGame;
    @FXML
    private ChoiceBox<Integer> colorBox;
    @FXML
    private TextField userNameField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<Integer> colorBoxItems = FXCollections.observableArrayList();
        colorBoxItems.add(6);
        colorBoxItems.add(8);
        colorBox.setItems(colorBoxItems);
    }


    @FXML
    public void startNewGame(ActionEvent event) throws IOException{
        
        FXMLLoader getToGame = new FXMLLoader();
        getToGame.setLocation(getClass().getResource("FXMLDocument.fxml"));
        Parent getTG = getToGame.load();
        FXMLDocumentController controller = getToGame.getController();
        controller.setNumOfColors(colorBox.getSelectionModel().getSelectedItem());
        controller.setUserName(userNameField.getText());
        Scene theGame = new Scene(getTG);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(theGame);
        window.show();
        
    
    }
}