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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Martin Sorensen
 */
public class MainMenu implements Initializable {
static Scene scene;
Parent root;
    @FXML
    private Button loadMenuButton;
    @FXML
    private Button highscoreMenuButton;
    @FXML
    private Button helpMenuButton;
    @FXML
    private Button newGameMenuButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    public void changeScene(String newScene) throws IOException {
        root = FXMLLoader.load(getClass().getResource(newScene + ".fxml"));
        scene = newGameMenuButton.getScene();
        scene.setRoot(root);
        scene.getRoot().requestFocus();
    }
    @FXML
    private void loadgameMenuButtonAction(MouseEvent event) {
    }

    @FXML
    private void highscoreMenuButtonAction(MouseEvent event) {
    }

    @FXML
    private void helpMenuButtonAction(MouseEvent event) {
    }

    @FXML
    private void newgameButtonAction(ActionEvent event) throws IOException {
        changeScene("airport");
        
    }

    @FXML
    private void handleButtonAction(KeyEvent event) {
    }
    
}
