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
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

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
    private AnchorPane popupBackground;
    @FXML
    private ImageView player;
    ArrayList itemsArray = new ArrayList(1);
    HashMap NPCHashMap;

    @FXML
    private Button inventoryButton;
    @FXML
    private Button helpButton;

    @FXML
    private Button missionButton;
    @FXML
    private TextArea textArea;
    @FXML
    private ProgressBar healthBar = new ProgressBar(game.maxPlayerHealth());
    @FXML
    private ProgressBar energyBar = new ProgressBar(game.maxPlayerEnergy());

    private Rectangle playerRect = new Rectangle();
    private Rectangle npcRect = new Rectangle();
    private ImageView npc3;
    @FXML
    private Button quitButton;
    @FXML
    private Button craftButton;
    @FXML
    private Button escapeButton;
    @FXML
    private Button saveButton;
    private boolean positionTextAdded = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // populateItemsOnMap();
        String imgPath = "GUI/Assets/Character/" + player.getId().toString().trim().toLowerCase() + "down.png";
        Image img = new Image(imgPath);
        player.setImage(img);

    }

    @FXML
    private void handleButtonAction(KeyEvent event) throws IOException, InterruptedException {
        if (player != null) {
            if (game.forcedText()) {
                forcedDialog();
            }
            x = player.getLayoutX();
            y = player.getLayoutY();
            scene = player.getScene();
            populateItemsOnMap();
            spawnNPC();
            updateBars();
            switch (event.getCode()) {
                case Y:
                    game.sendDialogOption("yes");
                    break;
                case N:
                    game.sendDialogOption("no");
                    break;
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
                    System.out.println("x:" + player.getLayoutX() + " y: " + player.getLayoutY() + " bgHeight:" + background.getHeight() + " playerHeight: " + player.getFitHeight());
                    break;
                case H:
//                    game.damageToPlayer();
                    textArea.setText("Previous eventhandler removed");
                    break;
            }
            intersectWithObject();
            moveObjectNPC(npc3);
            game.energyLossToPlayer();
            showEscapeButtonUnlocked();
        }
    }

    public void changeScene(String newScene) throws IOException {
        root = FXMLLoader.load(getClass().getResource(newScene + ".fxml"));
        itemsDrawed = false;
        NPCDrawed = false;
        scene.setRoot(root);
        while (!scene.getRoot().equals(root)) {

        }
        scene.getRoot().requestFocus();
        player = (ImageView) root.lookup("#player");

        if (!newScene.equals("craftMenu")) {
            player = (ImageView) root.lookup("#player");
        }

    }

    public void intersectWithObject() {
        if (player != null) {

            if (!itemsArray.isEmpty()) {
                for (int i = 0; i < background.getChildren().toArray().length; i++) {
                    String objectID = background.getChildren().get(i).getId();

                    if (objectID.contains("item")) {

                        ImageView objectToCheck = (ImageView) background.getChildren().get(i);

                        double oXStart = objectToCheck.getLayoutX();
                        double oXEnd = objectToCheck.getLayoutX() + objectToCheck.getFitWidth();
                        double oYStart = objectToCheck.getLayoutY();
                        double oYEnd = objectToCheck.getLayoutY() + objectToCheck.getFitHeight();
                        objectID = objectToCheck.getId().replace("item", "");

                        if (overlapsPlayer(oXStart, oXEnd, oYStart, oYEnd)) {
                            if (game.takeItemGUI(objectID)) {
                                background.getChildren().remove(i);
                                game.goGUI(null);
                                textDrawed = false;
                                PickItemPopUpText(objectID);
                            } else {
                                //System.out.println("TEST");
                                textDrawed = false;
                                PickItemFailedPopUpText(objectID);
                            }
                        }
                    } else if (objectID.contains("NPC")) {

                        ImageView objectToCheck = (ImageView) background.getChildren().get(i);

                        double oXStart = objectToCheck.getLayoutX();
                        double oXEnd = objectToCheck.getLayoutX() + objectToCheck.getFitWidth();
                        double oYStart = objectToCheck.getLayoutY();
                        double oYEnd = objectToCheck.getLayoutY() + objectToCheck.getFitHeight();
                        objectID = objectToCheck.getId().replace("NPC", "");

                        if (overlapsPlayer(oXStart, oXEnd, oYStart, oYEnd)) {
                            InteractNPC(objectID);
                        }

                    }

                }
            }
        }
    }

    private boolean overlapsPlayer(double xStart, double xEnd, double yStart, double yEnd) {

        double pxstart, pxend, pystart, pyend;
        pxstart = player.getLayoutX();
        pxend = pxstart + player.getFitWidth();
        pystart = player.getLayoutY();
        pyend = pystart + player.getFitHeight();
// 
        if (pxstart < xEnd && pxend > xStart && pystart < yEnd && pyend > yStart) {
            return true;
        } else {
            return false;
        }
    }

    public void populateItemsOnMap() {
        if (!itemsDrawed && background.getHeight() > 0) {
            itemsArray = game.getItemsOnMap();
            if (!itemsArray.isEmpty()) {
                for (int i = 0; i < itemsArray.size(); i++) {

                    ImageView item = new ImageView();
                    String imgPath = "GUI/Assets/items/" + itemsArray.get(i).toString().trim().toLowerCase() + ".png";
                    Image img = new Image(imgPath);

                    item.setLayoutX(Math.random() * (background.getWidth() - 40));
                    item.setLayoutY(Math.random() * (background.getHeight() - 40));
                    item.setFitHeight(20);
                    item.setFitWidth(20);
                    item.setImage(img);
                    item.setId((String) "item" + itemsArray.get(i));
                    item.setVisible(true);
                    background.getChildren().add(item);
                }
            }
            itemsDrawed = true;
        }
    }

    public void go(String dir) throws IOException {

        if (game.getRoomDescribtion().equals("airport")) {
            game.goGUI(dir);
            changeScene("splashscreen1");
        } else {
            game.goGUI(dir);
            changeScene(game.getRoomDescribtion());
        }

    }

    public void spawnNPC() {
        game.getNPC();

        if (!NPCDrawed && background.getWidth() > 0) {
            NPCHashMap = game.getNPC();
            if (!NPCHashMap.isEmpty()) {

                Iterator iterator = NPCHashMap.entrySet().iterator();

                while (iterator.hasNext()) {
                    HashMap.Entry entry = (HashMap.Entry) iterator.next();
                    if (entry.getValue().equals(game.getRoomDescribtion())) {

                        ImageView NPC = new ImageView();
                        String imgPath = "GUI/Assets/Character/" + entry.getKey().toString().trim().toLowerCase() + "down.png"; //.toString().trim().toLowerCase() + ".png";
                        Image img = new Image(imgPath);
                        Paint color = Color.rgb(255, 0, 0);
                        NPC.setLayoutX(Math.random() * (background.getWidth() - 40));
                        NPC.setLayoutY(Math.random() * (background.getHeight() - 40));
                        NPC.setFitHeight(35);
                        NPC.setFitWidth(35);
                        NPC.setImage(img);
                        NPC.setId("NPC" + ((String) (entry.getKey())));
                        if (NPC.getId().equals("NPCJoseph_Schnitzel")) {
                            npc3 = NPC;
                        }
                        NPC.setVisible(true);
                        background.getChildren().add(NPC);
                    }

                }
                NPCDrawed = true;
            }

        }

    }

    public void moveObject(ImageView shapeToMove, String dir) throws IOException {

        if (shapeToMove != null && background.getHeight() > 0) {
            boolean playerIsObject = shapeToMove.equals(player);
            String imgPath;
            Image img;
            String direction = "";
            boolean go = false;
            String shape = shapeToMove.getId().replace("NPC", "").toLowerCase();
            switch (dir) {
                case "UP":
                    imgPath = "GUI/Assets/Character/" + shape + "up.png";
                    img = new Image(imgPath);
                    shapeToMove.setImage(img);
                    if (shapeToMove.getLayoutY() <= 0) {
                        if (game.checkExit("north")) {
                            direction = "north";
                            go = true;

                            shapeToMove.setLayoutX(x);
                            shapeToMove.setLayoutY(background.getHeight() - shapeToMove.getFitHeight());
                        }
                    } else {
                        shapeToMove.setLayoutY(shapeToMove.getLayoutY() - speed);
                    }
                    break;

                case "DOWN":
                    imgPath = "GUI/Assets/Character/" + shape + "down.png";
                    img = new Image(imgPath);
                    shapeToMove.setImage(img);
                    if (shapeToMove.getLayoutY() >= background.getHeight() - speed - shapeToMove.getFitHeight()) {
                        if (game.checkExit("south")) {
                            direction = "south";
                            go = true;
                            shapeToMove.setLayoutX(x);
                            shapeToMove.setLayoutY(0);
                        }
                    } else {
                        shapeToMove.setLayoutY(shapeToMove.getLayoutY() + speed);
                    }
                    break;

                case "LEFT":
                    imgPath = "GUI/Assets/Character/" + shape + "left.png"; //.toString().trim().toLowerCase() + ".png";
                    img = new Image(imgPath);
                    shapeToMove.setImage(img);
                    if (shapeToMove.getLayoutX() <= 0) {
                        if (game.checkExit("west")) {
                            direction = "west";
                            go = true;

                            shapeToMove.setLayoutX(background.getWidth() - shapeToMove.getFitWidth());
                            shapeToMove.setLayoutY(y);
                        } else if (game.getRoomDescribtion().equalsIgnoreCase("airport")) {
                            textDrawed = false;
                            LockItemPopUpText();

                        }
                    } else {
                        shapeToMove.setLayoutX(shapeToMove.getLayoutX() - speed);
                    }
                    break;

                case "RIGHT":
                    imgPath = "GUI/Assets/Character/" + shape + "right.png"; //.toString().trim().toLowerCase() + ".png";
                    img = new Image(imgPath);
                    shapeToMove.setImage(img);
                    if (shapeToMove.getLayoutX() >= background.getWidth() - speed - shapeToMove.getFitWidth()) {
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
                    if (player != null) {
                        player.setLayoutX(shapeToMove.getLayoutX());
                        player.setLayoutY(shapeToMove.getLayoutY());

                    }
                }
            } else {

            }
        }
    }

    private void moveObjectNPC(ImageView npc) throws IOException {
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
        }

        moveObject(npc, dir);
    }

    @FXML
    private void showInventory(ActionEvent event) throws IOException {
        changeSceneCraftMenu("InventoryGUI");
    }

    private void quitDialog(ActionEvent event) {
        background.getChildren().remove(popupBackground);
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

        if (health == 0 || energy == 0) {
            game.lose();
        }
    }

    public void showMissionGiveByNpc() {
        if (textDrawed) {
            background.getChildren().remove(popupBackground);
            openWindow();

            popupText.setText(
                    "A mission has been added to the mission tab.");

            background.getChildren().add(popupBackground);
            textDrawed = true;

        }
    }

    private void forcedDialog() {
        if (!textDrawed) {
            Button noButton = new Button();
            Button yesButton = new Button();
            background.getChildren().remove(popupBackground);
            openWindow();
            popupText.setText(game.getDialog("Joseph_Schnitzel"));
            noButton.setOnAction((event) -> {
                game.sendDialogOption("no");
                popupBackground.getChildren().remove(noButton);
                popupBackground.getChildren().remove(yesButton);
                background.getChildren().remove(popupBackground);
            });
            yesButton.setOnAction((event) -> {
                showMissionGiveByNpc();
                game.sendDialogOption("yes");
                popupBackground.getChildren().remove(noButton);
                popupBackground.getChildren().remove(yesButton);
                showMissionGiveByNpc();
                //background.getChildren().remove(popupBackground);

            });

            yesButton.setLayoutX(10);
            yesButton.setLayoutY(65);
            yesButton.setMinWidth(30);
            yesButton.setText("yes");
            noButton.setMinWidth(30);
            noButton.setLayoutX(65);
            noButton.setLayoutY(65);
            noButton.setText("no");

            background.getChildren().add(popupBackground);
            popupBackground.getChildren().add(yesButton);
            popupBackground.getChildren().add(noButton);
            textDrawed = true;

        }
    }

    public void InteractNPC(String npcID) {
        if (!textDrawed) {
            background.getChildren().remove(popupBackground);
            openWindow();
            popupText.setText(game.getDialog(npcID));
            background.getChildren().add(popupBackground);
            textDrawed = true;

        }
    }

    public void PickItemPopUpText(String item) {
        if (!textDrawed) {
            background.getChildren().remove(popupBackground);
            openWindow();

            popupText.setText(
                    item + " has been added to inventory.");

            background.getChildren().add(popupBackground);
            textDrawed = true;

        }
    }

    public void PickItemFailedPopUpText(String item) {
        if (!textDrawed) {
            background.getChildren().remove(popupBackground);
            openWindow();

            popupText.setText(
                    item + " could not be added to inventory.");

            background.getChildren().add(popupBackground);
            textDrawed = true;

        }
    }

    public void LockItemPopUpText() {
        if (!textDrawed) {
            background.getChildren().remove(popupBackground);
            openWindow();

            popupText.setText(
                    " You cant get on the plane\n before you have your boardingpass");

            background.getChildren().add(popupBackground);
            textDrawed = true;

        }
    }

    private void winGame(ActionEvent event) {
        game.win();
    }

    private void changeToWinScreen() throws IOException {
        changeScene("winscreen");
    }

//create a popup text for when you hit the escape button so you ether win the game or continue to play
    public void escapePopUpText() {

        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        yesButton.setOnAction((event) -> {
            try {
                changeToWinScreen();
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }

            //winGame(event);
            //background.getChildren().remove(popupBackground);
        });

        noButton.setOnAction((event) -> {
            quitDialog(event);
        });

        if (!game.unlockedEscapeIsland()) {
            background.getChildren().remove(popupBackground);
            textDrawed = false;
            openWindow();

            popupText.setText(
                    " Are you sure you want to leave: Yes or no");

            popupBackground.getChildren().add(yesButton);
            popupBackground.getChildren().add(noButton);
            yesButton.setLayoutX(10);
            yesButton.setMinWidth(30);
            yesButton.setLayoutY(65);
            noButton.setLayoutX(65);
            noButton.setLayoutY(65);

            background.getChildren().add(popupBackground);
            textDrawed = true;

        } else if (!game.lockedEscapeIsland()) {

            background.getChildren().remove(popupBackground);
            textDrawed = false;

            openWindow();

            popupText.setText(
                    " You haven't unlocked this command yet");

            background.getChildren().add(popupBackground);
            textDrawed = true;

        }
    }

    private void showEscapeButtonUnlocked() {
        if (!game.unlockedEscapeIsland()) {
            textArea.setText(
                    " You have unlocked the\n escape button, so when\n you are "
                    + "ready to leave\n the island go to the\n beach "
                    + "and use it." + System.lineSeparator()
                    + System.lineSeparator());

        }
    }

    private void openWindow() {
        Button quitButton = new Button("X");

        if (popupBackground == null) {
            popupBackground = new AnchorPane();
        }

        //Creating an eventhandler for backButton
        quitButton.setOnAction((event) -> {
            quitDialog(event);
        });

        popupBackground.setPrefSize(10, 10);
//        popupBackground.setLayoutX((background.getWidth() / 1) - (background.getHeight() / 2));
        popupBackground.setLayoutX(130);
        popupBackground.setLayoutY(470);
        popupBackground.setOpacity(0.6);
        popupBackground.setId("popup");

        popupText = new TextArea();
        popupText.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        popupText.setFocusTraversable(false);
        popupText.setEditable(false);
        popupText.setMouseTransparent(true);

        popupText.setPrefSize(400, 100);
        popupText.setLayoutX(0);
        popupText.setLayoutY(0);
        quitButton.setLayoutX(370);
        quitButton.setLayoutY(5);
        quitButton.setId("quit");
        popupBackground.getChildren().add(popupText);
        popupBackground.getChildren().add(quitButton);

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

    private void showDropMenu(ActionEvent event) throws IOException {
        changeSceneCraftMenu("dropItemMenu");
    }

    @FXML
    private void EscapeIslandOnAction(ActionEvent event) throws IOException {
//        changeScene("winscreen");
        escapePopUpText();
    }

    @FXML
    private void handleSaveAction(ActionEvent event) {
        game.saveGameGUI();
        textArea.setText("Game saved");
    }

    @FXML
    public void getPositionOfCharacters() {
        textArea.appendText(game.getPositionOfAllCharacters());
    }
}
