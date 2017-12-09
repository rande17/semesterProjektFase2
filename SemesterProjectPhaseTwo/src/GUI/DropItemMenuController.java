/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import static GUI.MainMenuController.scene;
import Game.GameFacade;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Zuzu
 */
public class DropItemMenuController implements Initializable {
    
    
    public static GameFacade dropItemMenu = new GameFacade();

    Parent root;
    
    @FXML
    private Button backButton;
    @FXML
    private Button dropItemButton;
    @FXML
    private ChoiceBox<String> dropItemChoice;
    @FXML
    private TextArea inventoryTextArea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void changeScene(String newScene) throws IOException {
        root = FXMLLoader.load(getClass().getResource(newScene + ".fxml"));
        scene = backButton.getScene();
        scene.setRoot(root);
        scene.getRoot().requestFocus();
    }

    @FXML
    private void handleBackAction(ActionEvent event) throws IOException {
        changeScene(dropItemMenu.getRoom());
    }

    @FXML
    private void dropItemButtonAction(ActionEvent event) {
    }

    @FXML
    private void dropChoice(MouseEvent event) { 
        String inventory = "";
        inventory += dropItemMenu.printInventory();
        inventoryTextArea.setText(inventory);
        

    }
    
}
