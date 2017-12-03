/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Game.GameFacade;
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
import javafx.stage.Stage;
import acquaintance.InterfaceGame;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

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
    Parent root;

    @FXML
    private AnchorPane background;
    @FXML
    private Rectangle player;
    ArrayList itemsArray = new ArrayList(1);
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

                    go("north");

                    player.setLayoutX(x);
                    player.setLayoutY(background.getHeight() - player.getHeight());
                } else {
                    player.setLayoutY(player.getLayoutY() - speed);
                }
                break;
            case S:
            case DOWN:
                if (player.getLayoutY() >= background.getHeight() - speed) {
                    go("south");
                    player.setLayoutX(x);
                    player.setLayoutY(0);
                } else {
                    player.setLayoutY(player.getLayoutY() + speed);
                }
                break;
            case A:
            case LEFT:

                if (player.getLayoutX() <= 0) {
                    go("west");
                    player.setLayoutX(background.getWidth() - player.getWidth());
                    player.setLayoutY(y);

                } else {
                    player.setLayoutX(player.getLayoutX() - speed);
                }
                break;

            case D:
            case RIGHT:
                if (player.getLayoutX() >= background.getWidth() - player.getWidth()) {
                    go("east");
                    player.setLayoutX(0);
                    player.setLayoutY(y);
                } else {
                    player.setLayoutX(player.getLayoutX() + speed);
                }
                break;
            case L:
                System.out.println("x:" + player.getLayoutX() + " y: " + player.getLayoutY() + " bgHeight:" + background.getHeight() + " playerHeight: " + player.getHeight());
                break;
        }
        intersectsItem();
    }

    public void changeScene(String newScene) throws IOException {
        root = FXMLLoader.load(getClass().getResource(newScene + ".fxml"));
        itemsDrawed = false;
        scene.setRoot(root);
        scene.getRoot().requestFocus();
        player = (Rectangle) root.lookup("#player");
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

    @FXML
    private void showInventory(MouseEvent event) {
        String inventory = "";
        inventory += game.printInventory();
        textArea.setText(inventory);
    }

    @FXML
    private void quitGame(MouseEvent event) {
        game.quitGame();
    }

    @FXML
    private void showMission(MouseEvent event) {
    }

    @FXML
    private void showHelp(MouseEvent event) {
        String help = "";
        help += game.showHelp();
        textArea.setText(help);
    }
}
