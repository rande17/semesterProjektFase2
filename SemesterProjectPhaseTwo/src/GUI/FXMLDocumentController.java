/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Game.GameFacade;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author rickie
 */
public class FXMLDocumentController implements Initializable {

    private static GameFacade game = new GameFacade();
    private int speed = 10;
    private TextArea popupText;
    boolean textDrawed = false;
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

    private Rectangle npc3;

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
                moveObject(player, "UP");
                break;
            case S:
            case DOWN:
                moveObject(player, "DOWN");
                break;
            case A:
            case LEFT:
                moveObject(player, "LEFT");
                break;

            case D:
            case RIGHT:
                moveObject(player, "RIGHT");
                break;

            case L:
                System.out.println("x:" + player.getLayoutX() + " y: " + player.getLayoutY() + " bgHeight:" + background.getHeight() + " playerHeight: " + player.getHeight());
                break;
            case H:
                game.damageToPlayer();
                break;
        }
        intersectsItem();
        moveObjectNPC(npc3);
        game.energyLossToPlayer();

        updateBars();
    }

    public void changeScene(String newScene) throws IOException {
        root = FXMLLoader.load(getClass().getResource(newScene + ".fxml"));
        itemsDrawed = false;
        NPCDrawed = false;
        scene.setRoot(root);
        while (!scene.getRoot().equals(root)) {

        }
        scene.getRoot().requestFocus();
        player = (Rectangle) root.lookup("#player");

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
                            popUpText(itemID);
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

                        Rectangle NPC = new Rectangle();
                        Paint color = Color.rgb(255, 0, 0);
                        NPC.setLayoutX(Math.random() * (background.getWidth() - 40));
                        NPC.setLayoutY(Math.random() * (background.getHeight() - 40));
                        NPC.setHeight(15);
                        NPC.setWidth(15);
                        NPC.setStroke(color);
                        NPC.setId((String) (entry.getKey()));
                        if (NPC.getId().equals("Joseph Schnitzel")) {
                            npc3 = NPC;
                        }
                        NPC.setFill(color);
                        NPC.setVisible(true);
                        background.getChildren().add(NPC);
                    }

                }
                NPCDrawed = true;
            }

        }

    }

    public void moveObject(Rectangle shapeToMove, String dir) throws IOException {
        boolean playerIsObject = shapeToMove.equals(player);
        String direction = "";
        boolean go = false;
        switch (dir) {
            case "UP":
                if (shapeToMove.getLayoutY() <= 0) {
                    if (game.checkExit("north")) {
                        direction = "north";
                        go = true;
                        shapeToMove.setLayoutX(x);
                        shapeToMove.setLayoutY(background.getHeight() - shapeToMove.getHeight());
                    }
                } else {
                    shapeToMove.setLayoutY(shapeToMove.getLayoutY() - speed);
                }
                break;

            case "DOWN":
                if (shapeToMove.getLayoutY() >= background.getHeight() - speed - shapeToMove.getHeight()) {
                    if (game.checkExit("south")) {
                        direction = "south";
                        shapeToMove.setLayoutX(x);
                        go = true;
                        shapeToMove.setLayoutY(0);
                        System.out.println(shapeToMove.getLayoutBounds().toString());
                    }
                } else {
                    shapeToMove.setLayoutY(shapeToMove.getLayoutY() + speed);
                }
                break;

            case "LEFT":

                if (shapeToMove.getLayoutX() <= 0) {
                    if (game.checkExit("west")) {
                        direction = "west";
                        go = true;
                        shapeToMove.setLayoutX(background.getWidth() - shapeToMove.getWidth());
                        shapeToMove.setLayoutY(y);
                    }
                } else {
                    shapeToMove.setLayoutX(shapeToMove.getLayoutX() - speed);
                }
                break;

            case "RIGHT":
                if (shapeToMove.getLayoutX() >= background.getWidth() - speed - shapeToMove.getWidth()) {
                    if (game.checkExit("east")) {
                        direction = "east";
                        go = true;
                        shapeToMove.setLayoutX(0);
                        shapeToMove.setLayoutY(y);
                    }
                } else {
                    shapeToMove.setLayoutX(shapeToMove.getLayoutX() + speed);
                }
                break;
        }
        if (playerIsObject) {
            if (go) {
                go(direction);
            player.setLayoutX(shapeToMove.getLayoutX());
            player.setLayoutY(shapeToMove.getLayoutY());
            
            }
        } else {

        }
    }

    private void moveObjectNPC(Rectangle npc) throws IOException {
        int moveRNG = (int) Math.floor(Math.random() * 5);
        String dir = "";
        switch (moveRNG) {
            case 0:
                dir = "UP";
                break;
            case 1:
                dir = "DOWN";
                break;
            case 2:
                dir = "LEFT";
                break;
            case 3:
                dir = "RIGHT";
                break;
            default:
                System.out.println("I didn't move");
        }
      // dir = "RIGHT"; //- debug line
        moveObject(npc, dir);
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

    public void popUpText(String item) {
        if (!textDrawed) {
            openWindow();
            popupText.setText(
                    item + " has been added to inventory.");

            background.getChildren().add(popupText);
            textDrawed = true;
        } else {
            background.getChildren().remove(4);
            textDrawed = false;
        }
    }

    private void openWindow() {
        int helpTextWidth = 320;
        int helpTextHeight;
        popupText = new TextArea();
        popupText.setFocusTraversable(false);
        popupText.setEditable(false);
        popupText.setMouseTransparent(true);
        popupText.setPrefSize(helpTextWidth, 100);
        popupText.setLayoutX((background.getWidth() / 2) - (helpTextWidth / 2));
        popupText.setLayoutY(450);
    }

    public void changeSceneCraftMenu(String newScene) throws IOException {
        root = FXMLLoader.load(getClass().getResource(newScene + ".fxml"));
        scene = player.getScene();
        scene.setRoot(root);
        scene.getRoot().requestFocus();
    }

    @FXML
    private void showCraftMenu(ActionEvent event) throws IOException {
        changeSceneCraftMenu("craftMenu");

    }
}
