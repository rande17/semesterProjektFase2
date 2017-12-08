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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Martin Sorensen
 */
public class MainMenuController implements Initializable {

    private static GameFacade game = new GameFacade();
    boolean helpDrawed = false;
    private TextArea popupText;

    static Scene scene;
    @FXML
    private AnchorPane background;

    Parent root;
    @FXML
    private Button loadMenuButton;
    @FXML
    private Button highscoreMenuButton;
    @FXML
    private Button helpMenuButton;
    @FXML
    private Button newGameMenuButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    public void changeScene(String newScene) throws IOException {
        root = FXMLLoader.load(getClass().getResource(newScene + ".fxml"));
        scene = newGameMenuButton.getScene();
        scene.setRoot(root);
        scene.getRoot().requestFocus();
    }

    public void printHelpMainMenu() {
        if (!helpDrawed) {
            openWindow();
            popupText.setText(
                    "The game 'Stranded' is a survival game" + "\n"
                    + "\n"
                    + "You move the player around" + "\n"
                    + "by using W A S D or the arrows" + "\n"
                    + "\n"
                    + "You use the menu on the right" + "\n"
                    + "by using the mouse and" + "\n"
                    + "clicking the left mouse button" + "\n"
                    + "\n"
                    + "To complete the game" + "\n"
                    + "you must complete several missions" + "\n"
                    + "by exploring, collecting items, " + "\n"
                    + "crafting items and surviving" + "\n"
                    + "\n"
                    + "Press the Help button again to close this menu");

            background.getChildren().add(popupText);
            helpDrawed = true;
        } else {
            background.getChildren().remove(4);
            helpDrawed = false;
        }
    }

    private void openWindow() {
        int helpTextWidth = 280;
        int helpTextHeight;
        popupText = new TextArea();
        popupText.setPrefSize(helpTextWidth, 280);
        popupText.setLayoutX((background.getWidth() / 2) - (helpTextWidth / 2));
        popupText.setLayoutY(280);
    }

    @FXML
    private void loadgameMenuButtonAction(MouseEvent event) {
    }

    @FXML
    private void highscoreMenuButtonAction(MouseEvent event) {
        if (!helpDrawed) {
            openWindow();

            popupText.setText(game.printHighscoreGUI());
            background.getChildren().add(popupText);
            helpDrawed = true;
        } else {
            background.getChildren().remove(4);
            helpDrawed = false;
        }

    }

    @FXML
    private void helpMenuButtonAction(MouseEvent event) {
        printHelpMainMenu();

    }

    @FXML
    private void newgameButtonAction(ActionEvent event) throws IOException {
        changeScene("airport");
        
    }

    @FXML
    private void handleButtonAction(KeyEvent event) {
    }

}
