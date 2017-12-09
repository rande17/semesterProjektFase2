/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.FXMLDocumentController.scene;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Nicolai
 */
public class FXMLSplash implements Initializable {

    @FXML
    private Button nextButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void changeScene(String newScene) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(newScene + ".fxml"));
        
        scene.setRoot(root);
        scene.getRoot().requestFocus();
    }  
    @FXML
    private void handleButtonAction(KeyEvent event) {
    }

    @FXML
    private void changeSceneButtonAction(ActionEvent event) throws IOException {
        changeScene("beach");
    }
    
}
