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
   // boolean itemsDrawed = false;
    Parent root;

    @FXML
    private AnchorPane background;
    @FXML
    private Rectangle player;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populateItemsOnMap();
    }

    @FXML
    private void handleButtonAction(KeyEvent event) throws IOException, InterruptedException {

        scene = player.getScene();
        switch (event.getCode()) {
            case F12:
                System.out.println(background.getChildrenUnmodifiable().toString());
                break;
            case F11:
                populateItemsOnMap();
                break;
            case W:
            case UP:
                if (player.getLayoutY() <= 0) {
                    x = player.getLayoutX();
                    y = player.getLayoutY();
                    changeScene("cave");
                    player.setLayoutX(x);
                    player.setLayoutY(background.getHeight() - player.getHeight());
                } else {
                    player.setLayoutY(player.getLayoutY() - speed);
                }
                break;
            case S:
            case DOWN:
                if (player.getLayoutY() >= background.getHeight() - speed) {
                    player.setLayoutY((background.getHeight() - player.getHeight()));
                } else {
                    player.setLayoutY(player.getLayoutY() + speed);
                }
                break;
            case A:
            case LEFT:

                if (player.getLayoutX() <= 0) {
                    player.setLayoutX(0);
                } else {
                    player.setLayoutX(player.getLayoutX() - speed);
                }
                break;

            case D:
            case RIGHT:
                if (player.getLayoutX() >= background.getWidth() - player.getWidth()) {
                    player.setLayoutX((background.getWidth() - player.getWidth()));
                } else {
                    player.setLayoutX(player.getLayoutX() + speed);
                }
            case F1:
                System.out.println("x:" + player.getLayoutX() + " y: " + player.getLayoutY() + " bgHeight:" + background.getHeight() + " playerHeight: " + player.getHeight());
                break;
        }
    }

    public void changeScene(String newScene) throws IOException {
        root = FXMLLoader.load(getClass().getResource(newScene + ".fxml"));
        //itemsDrawed = false;
        scene.setRoot(root);
        scene.getRoot().requestFocus();
        player = (Rectangle) root.lookup("#player");

    }
    
    

    public void populateItemsOnMap() {
      //  if (!itemsDrawed) {
            ArrayList itemsArray = game.getItemsOnMap();

            for (int i = 0; i < itemsArray.size(); i++) {
                Rectangle item = new Rectangle();
                Paint color = Color.rgb(0, 0, 255);
                item.setLayoutX(Math.random() * background.getWidth() - 20);
                item.setLayoutY(Math.random() * background.getHeight() - 20);
                item.setHeight(20);
                item.setWidth(20);
                item.setStroke(color);
                item.setId((String) itemsArray.get(i));
                item.setFill(color);
                item.setVisible(true);
                background.getChildren().add(item);
            }
       //     itemsDrawed = true;
        
    }
}
