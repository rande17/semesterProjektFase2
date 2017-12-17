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
 * @author Thomas
 * @author Nicolai
 * @author Rickie
 * @author Frederik
 * @author Julie 
 * @author Martin
*/
public class FXMLSplashController implements Initializable {

    @FXML
    private Button nextButton;

    /**
     * Initializes the controller class.
     * @param url never used
     * @param rb never used
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    /**
     *
     * @param newScene what scene to change to
     * @throws IOException if the fxml cannot be found
     */
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
