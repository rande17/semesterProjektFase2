/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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

/**
 *
 * @author rickie
 */
public class FXMLDocumentController implements Initializable {

    private int speed = 10;
    private GridPane roomGridPane;
    private Rectangle playerRectangle;
    static Scene scene;
    double x, y;
    Parent root;
    @FXML
    private AnchorPane background;
    @FXML
    private Rectangle player;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    private void handleButtonAction(KeyEvent event) throws IOException, InterruptedException {
        System.out.println("x:" + player.getLayoutX() + " y: " + player.getLayoutY() + " bgHeight:" + background.getHeight() + " playerHeight: " + player.getHeight());
        scene = player.getScene();
        switch (event.getCode()) {
            case W:
            case UP:
                if (player.getLayoutY() <= 0) {
                    x = player.getLayoutX();
                    y = player.getLayoutY();
                    System.out.println(x + " " + y);
                    changeScene("cave");
                    System.out.println(x + " " + y);
                    player.setLayoutX(x);
                    player.setLayoutY(background.getHeight()-player.getHeight());
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
        }
    }

    public void changeScene(String newScene) throws IOException {
        root = FXMLLoader.load(getClass().getResource(newScene + ".fxml"));
        scene.setRoot(root);
        scene.getRoot().requestFocus();
        player = (Rectangle)root.getChildrenUnmodifiable().get(0);
    }
}
