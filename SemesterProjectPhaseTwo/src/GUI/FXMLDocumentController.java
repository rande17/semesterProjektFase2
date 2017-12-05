/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Game.GameFacade;
import Game.Time;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 *
 * @author rickie
 */
public class FXMLDocumentController implements Initializable {

    private static GameFacade game = new GameFacade();
    private int speed = 10;
    private GridPane roomGridPane;
    private Rectangle playerRectangle;
    static Scene scene;
    double x, y;
    boolean itemsDrawed = false;
    boolean NPCDrawed = false;
    Parent root;
    private double health;
    private double energy;

    @FXML
    private AnchorPane background;
    @FXML
    private Rectangle player;
    ArrayList itemsArray = new ArrayList(1);
    HashMap NPCHashMap;

    @FXML
    private Button inventoryButton;
    @FXML
    private Button helpButton;
    @FXML
    private Button quitButton;
    @FXML
    private Button missionButton;
    @FXML
    private TextArea textArea;
    @FXML
    private ProgressBar healthBar = new ProgressBar(game.maxPlayerHealth());
    @FXML
    private ProgressBar energyBar = new ProgressBar(game.maxPlayerEnergy());

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // populateItemsOnMap();
    }

    @FXML
    private void handleButtonAction(KeyEvent event) throws IOException, InterruptedException {
        x = player.getLayoutX();
        y = player.getLayoutY();
        scene = player.getScene();
        populateItemsOnMap();
        spawnNPC();
        switch (event.getCode()) {
            case O:
                System.out.println(background.getChildrenUnmodifiable().toString());
                break;
            case P:
                populateItemsOnMap();
                break;
            case W:
            case UP:
                if (player.getLayoutY() <= 0) {
                    if (game.checkExit("north")) {
                        go("north");

                        player.setLayoutX(x);
                        player.setLayoutY(background.getHeight() - player.getHeight());
                    }
                } else {
                    player.setLayoutY(player.getLayoutY() - speed);
                }
                break;
            case S:
            case DOWN:
                if (player.getLayoutY() >= background.getHeight() - speed) {
                    if (game.checkExit("south")) {
                        go("south");
                        player.setLayoutX(x);
                        player.setLayoutY(0);
                    }
                } else {
                    player.setLayoutY(player.getLayoutY() + speed);
                }
                break;
            case A:
            case LEFT:

                if (player.getLayoutX() <= 0) {
                    if (game.checkExit("west")) {
                        go("west");
                        player.setLayoutX(background.getWidth() - player.getWidth());
                        player.setLayoutY(y);
                    }
                } else {
                    player.setLayoutX(player.getLayoutX() - speed);
                }
                break;

            case D:
            case RIGHT:
                if (player.getLayoutX() >= background.getWidth() - player.getWidth()) {
                    if (game.checkExit("east")) {
                        go("east");
                        player.setLayoutX(0);
                        player.setLayoutY(y);
                    }
                } else {
                    player.setLayoutX(player.getLayoutX() + speed);
                }
                break;
            case L:
                System.out.println("x:" + player.getLayoutX() + " y: " + player.getLayoutY() + " bgHeight:" + background.getHeight() + " playerHeight: " + player.getHeight());
                break;
            case H:
                game.damageToPlayer();
                break;
        }
        intersectsItem();
        game.energyLossToPlayer();
        updateBars();
    }

    public void changeScene(String newScene) throws IOException {
        System.out.println(root.toString());
        root = FXMLLoader.load(getClass().getResource(newScene + ".fxml"));
        System.out.println(root.toString());
        itemsDrawed = false;
        NPCDrawed = false;
        scene.setRoot(root);
        scene.getRoot().requestFocus();
        if (!newScene.equals("craftMenu")) {
            player = (Rectangle) root.lookup("#player");
        }
    }

    public void intersectsItem() {
        double pxstart, pxend, pystart, pyend;
        pxstart = player.getLayoutX();
        pxend = pxstart + player.getWidth();
        pystart = player.getLayoutY();
        pyend = pystart + player.getHeight();
        if (!itemsArray.isEmpty()) {
            for (int i = 0; i < itemsArray.size(); i++) {
                Rectangle itemToCheck = (Rectangle) background.getChildren().get(i);

                String itemID = itemToCheck.getId();
                // System.out.println(itemID);
                if (!itemID.equals("player")) {
                    double ixstart = itemToCheck.getLayoutX();
                    double ixend = itemToCheck.getLayoutX() + itemToCheck.getWidth();
                    double iystart = itemToCheck.getLayoutY();
                    double iyend = itemToCheck.getLayoutY() + itemToCheck.getHeight();
                    if (pxstart >= ixstart && pxstart <= ixend && pystart >= iystart && pystart <= iyend) {
                        if (game.takeItemGUI(itemID)) {
                            System.out.println(background.getChildren().get(i).toString());
                            background.getChildren().remove(i);
                            game.goGUI(null);
                            itemsArray.remove(i);
                        }
                    }
                }
            }
        }
    }

    public void populateItemsOnMap() {
        if (!itemsDrawed) {
            itemsArray = game.getItemsOnMap();
            if (!itemsArray.isEmpty()) {
                for (int i = 0; i < itemsArray.size(); i++) {
                    Rectangle item = new Rectangle();
                    Paint color = Color.rgb(0, 0, 255);
                    item.setLayoutX(Math.random() * (background.getWidth() - 40));
                    item.setLayoutY(Math.random() * (background.getHeight() - 40));
                    item.setHeight(20);
                    item.setWidth(20);
                    item.setStroke(color);
                    item.setId((String) itemsArray.get(i));
                    item.setFill(color);
                    item.setVisible(true);
                    background.getChildren().add(item);
                }
            }
            itemsDrawed = true;
        }
    }

    public void go(String dir) throws IOException {

        game.goGUI(dir);
        changeScene(game.getRoom());
    }

    public void spawnNPC() {
        game.getNPC();

        if (!NPCDrawed) {
            NPCHashMap = game.getNPC();
            if (!NPCHashMap.isEmpty()) {

                Iterator iterator = NPCHashMap.entrySet().iterator();
                
                while (iterator.hasNext()) {  
                    HashMap.Entry entry = (HashMap.Entry) iterator.next();
                    if (entry.getValue().equals(game.getRoom())) {
                      
                    Circle NPC = new Circle();
                    Paint color = Color.rgb(255, 0, 0);
                    NPC.setLayoutX(Math.random() * (background.getWidth() - 40));
                    NPC.setLayoutY(Math.random() * (background.getHeight() - 40));
                    NPC.setRadius(20);
                    NPC.setStroke(color);
                    NPC.setId((String) (entry.getKey()));
                    NPC.setFill(color);
                    NPC.setVisible(true);
                    background.getChildren().add(NPC);
                    }
                    
               
                }
                NPCDrawed = true;
            }

        }

    }
    
    @FXML
    private void showInventory(ActionEvent event) {
        String inventory = "";
        inventory += game.printInventory();
        textArea.setText(inventory);
    }

    @FXML
    private void showHelp(ActionEvent event) {
        String help = "";
        help += game.showHelp();
        textArea.setText(help);
    }

    @FXML
    private void quitGame(ActionEvent event) {
        game.quitGame();
    }

    @FXML
    private void showMission(ActionEvent event) {
        textArea.setText(game.printMissions());
    }

    @FXML
    private void showHelp(MouseEvent event) {
    }

    public void updateBars() {
        //
        health = 1.0 * game.playerHealth() / game.maxPlayerHealth();
        energy = 1.0 * game.playerEnergy() / game.maxPlayerEnergy();
        healthBar.setProgress(health);
//        energyBar.setProgress(game.playerEnergy());
        energyBar.setProgress(energy);

        if (health == 0) {
            game.lose();
        }
        if (energy == 0) {
            game.lose();
        }
    }
    public void changeSceneBK(String newScene) throws IOException {
        root = FXMLLoader.load(getClass().getResource(newScene + ".fxml"));
        scene = player.getScene();
        scene.setRoot(root);
        scene.getRoot().requestFocus();
    }
    @FXML
    private void showCraftMenu(ActionEvent event) throws IOException {
        changeSceneBK("craftMenu");
    }
}
