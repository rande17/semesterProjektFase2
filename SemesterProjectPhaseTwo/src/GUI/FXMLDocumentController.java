/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import Game.*;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author rickie
 */
public class FXMLDocumentController implements Initializable {

    NPC player = new NPC();
    int x;
    int y;

    @FXML
    private GridPane roomGridpane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createPlayer();
        
    }

    public void createPlayer() {
        Rectangle r = new Rectangle();
        x=7;
        y=7;
//        r.setX(300);
//        r.setY(300);
        r.setWidth(20);
        r.setHeight(20);
        r.setArcWidth(20);
        r.setArcHeight(20);
        roomGridpane.add(r, x, y);

    }

    @FXML
    private void move(KeyEvent event) {
        switch (event.getCode()) {
            case W:
                y--;
                break;
            case D:
                x++;
                break;
            case S:
                y++;
                break;
            case A:
                x--;
                break;
        }
        createPlayer();

    }
    

}
