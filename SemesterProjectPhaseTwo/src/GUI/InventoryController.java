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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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
 * @author Thomas
 * @author Nicolai
 * @author Rickie
 * @author Frederik
 * @author Julie 
 * @author Martin
*/
public class InventoryController implements Initializable {
    
    /**
     * instance of GameFacade
     */
    public static GameFacade inventoryMenu = new GameFacade();

    Parent root;
    
    @FXML
    private Button backButton;
    @FXML
    private ChoiceBox<String> dropItemChoice;
    @FXML
    private TextArea inventoryTextArea;
    @FXML
    private Button useItemButton;
    @FXML
    private Button dropItemButton1;
    @FXML
    private Button showInventortButton;

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
        root = FXMLLoader.load(getClass().getResource(newScene + ".fxml"));
        scene = backButton.getScene();
        scene.setRoot(root);
        scene.getRoot().requestFocus();
    }

    @FXML
    private void handleBackAction(ActionEvent event) throws IOException {
        changeScene(inventoryMenu.getRoomDescribtion());
    }

    @FXML
    private void dropItemButtonAction(ActionEvent event) {
        String itemName = dropItemChoice.getValue();
        inventoryMenu.dropItem(itemName);
        inventoryTextArea.setText("You have dropped " + itemName);
        
    }

    @FXML
    private void dropChoice(MouseEvent event) { 
        ObservableList<String> e = FXCollections.observableArrayList(inventoryMenu.inventoryNames());
        inventoryItems();
        dropItemChoice.setItems(e);
    }

    @FXML
    private void useItemButtonAction(ActionEvent event) {
        String itemName = dropItemChoice.getValue();
        if (inventoryMenu.useItem(itemName) == true){
            inventoryTextArea.setText("You have used " + itemName + "\n" + "Your enegy is now " + inventoryMenu.playerEnergy() + "%" 
                    + "\n" + "Your health is now " + inventoryMenu.playerHealth() + "%");
        } else {
            inventoryTextArea.setText("You can not use " + itemName);
        }
        
    }

    @FXML
    private void showInventoryAction(ActionEvent event) {
        inventoryItems();
    }
    
    private void inventoryItems(){
        String inventory = "";
        inventory += inventoryMenu.printInventory();
        inventoryTextArea.setText(inventory);
    }
    
}
