/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.MainMenuController.scene;
import Game.GameFacade;
import java.io.IOException;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;


/**
 * FXML Controller class
 *
 * @author frede
 */
public class CraftMenuController implements Initializable {
    
    public static GameFacade craftItemList = new GameFacade();
    
    
    Parent root;

    @FXML
    private Button backButton;
    @FXML
    private VBox craftableItemsVBox;
    @FXML
    private TextArea requirementsTextArea;
    @FXML
    private Button craftButton;
    @FXML
    private RadioButton buttonCraftCampfire;
    @FXML
    private RadioButton craftSpear;
    private boolean craftCampfire;

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
        changeScene(craftItemList.getRoom());
    }

    @FXML
    private void handleCraftAction(ActionEvent event) {
       
           
       if(craftCampfire == true){
      
                    
       }
    }

    @FXML
    private void craftCampfire(ActionEvent event) {
        requirementsTextArea.setText("Items needed are: \nLumber, Stick and Flint");
    }

    @FXML
    private void showSpearItems(ActionEvent event){
    requirementsTextArea.setText("Items needed are: \nStick, Flint and Rope or Lian");
    }
    
    
    
}
