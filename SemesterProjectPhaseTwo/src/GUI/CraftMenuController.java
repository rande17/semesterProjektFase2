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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

/**
 * @author Thomas
 * @author Nicolai
 * @author Rickie
 * @author Frederik
 * @author Julie 
 * @author Martin
*/
public class CraftMenuController implements Initializable {

    /**
     * instance of GameFacade
     */
    public static GameFacade game = new GameFacade();

    Parent root;

    @FXML
    private Button backButton;
    @FXML
    private VBox craftableItemsVBox;
    @FXML
    private TextArea requirementsTextArea;
    @FXML
    private Button craftButton;
    private ArrayList craftingItems;
    private boolean craftItemsAdded = false;
    private ToggleGroup craftToggleGroup = new ToggleGroup();

    /**
     * Initializes the controller class.
     * @param url never used
     * @param rb never used
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        populateVBoxWithRadioButtons();
    }
    
    /**
     * 
     * @param newScene the name of the fxml file which the GUI 
     * is switching to (without the .fxml notation)
     * @throws IOException 
     */
    public void changeScene(String newScene) throws IOException {
        root = FXMLLoader.load(getClass().getResource(newScene + ".fxml"));
        scene = backButton.getScene();
        scene.setRoot(root);
        scene.getRoot().requestFocus();
    }
    
    /**
     * 
     * @param event
     * @throws IOException 
     */
    @FXML
    private void handleBackAction(ActionEvent event) throws IOException {
        changeScene(game.getRoomDescribtion());
    }

    @FXML
    private void handleCraftAction(ActionEvent event) {
          RadioButton idOfSelectedRadioButton = (RadioButton)craftToggleGroup.getSelectedToggle();
          String idOfCraftableItem;
          idOfCraftableItem=(String)idOfSelectedRadioButton.getId().replace("RadioButton", "");
          game.craftItemGUI(idOfCraftableItem);
          if(game.inventoryNames().contains(idOfCraftableItem)){
            requirementsTextArea.setText(idOfCraftableItem + " has been added to inventory");  
          }
          else{
            requirementsTextArea.setText(idOfCraftableItem + " cant't be crafted");
          }
    }

    /**
     * used to populate the vbox in crafting menu with radiobuttons
     */
    public void populateVBoxWithRadioButtons() {
        if (!craftItemsAdded) {
            craftingItems = game.getCraftableItemsArray();
            if (!craftingItems.isEmpty()) {
                for (int i = 0; i < craftingItems.size(); i++) {
                    RadioButton item = new RadioButton();
                    item.setId((String) craftingItems.get(i) + "RadioButton");
                    item.setText((String) craftingItems.get(i));
                    item.setToggleGroup(craftToggleGroup);
                    item.setOnAction((event) -> {
                        requirementsTextArea.setText((String) (game.getCraftableItemDescribtion(item.getText())));
                    });
                    craftableItemsVBox.getChildren().add(item);
                }
            }
            craftItemsAdded = true;
        }
    }

}
