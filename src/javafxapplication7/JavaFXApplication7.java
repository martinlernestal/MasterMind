/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Elev
 */
public class JavaFXApplication7 extends Application {
    
    private static Stage primaryStage; // **Declare static Stage**

    private void setPrimaryStage(Stage stage) {
        JavaFXApplication7.primaryStage = stage;
    }

    static public Stage getPrimaryStage() {
        return JavaFXApplication7.primaryStage;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        setPrimaryStage(stage);
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLWelcome.fxml"));

        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
