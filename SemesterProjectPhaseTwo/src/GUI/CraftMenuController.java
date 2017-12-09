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
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author frede
 */
public class CraftMenuController implements Initializable {
    
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
    private boolean craftCampfire;
    private ArrayList craftingItems;
    private boolean craftItemsAdded = false;
    private ToggleGroup craftToggleGroup = new ToggleGroup();
    @FXML
    private ToggleGroup CraftToggle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        addButtonAndPrintCraftItems();
        populateShit();
    }
    
    public void changeScene(String newScene) throws IOException {
        root = FXMLLoader.load(getClass().getResource(newScene + ".fxml"));
        scene = backButton.getScene();
        scene.setRoot(root);
        scene.getRoot().requestFocus();
    }
    
    @FXML
    private void handleBackAction(ActionEvent event) throws IOException {
        changeScene(game.getRoom());
    }
    
    @FXML
    private void handleCraftAction(ActionEvent event) {
        requirementsTextArea.setText("Unsupported operation");
    }
    
    @FXML
    private void craftCampfire(ActionEvent event) {
        requirementsTextArea.setText("Items needed are: \nLumber, Stick and Flint");
    }
    
    @FXML
    private void showSpearItems(ActionEvent event) {
        requirementsTextArea.setText("Items needed are: \nStick, Flint and Rope or Lian");
    }
    
    public void addButtonAndPrintCraftItems() {
        Button testButton = new Button("Print items");
        
        craftingItems = game.getCraftableItemsArray();
        craftableItemsVBox.getChildren().add(testButton);
        
    }
    
    public void populateShit() {
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
