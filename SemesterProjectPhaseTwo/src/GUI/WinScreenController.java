/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.FXMLDocumentController.scene;
import Game.GameFacade;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author rickie
 */
public class WinScreenController implements Initializable {
    private static GameFacade game = new GameFacade();
    @FXML
    private TextField PlayerName;
    @FXML
    private Button HighScoreSubmitButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SubmitHighScore(ActionEvent event) throws IOException {
        String playername = PlayerName.getText();
        System.out.println(playername + "controller");
        game.submitHighScore(playername);
        changeScene("mainMenu");
    }

      public void changeScene(String newScene) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(newScene + ".fxml"));
        
        scene.setRoot(root);
        scene.getRoot().requestFocus();
    } 
    @FXML
    private void handleButtonAction(KeyEvent event) {
    }
    
}
