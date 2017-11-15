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
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author rickie
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private GridPane roomGridPane;
    @FXML
    private Rectangle playerRectangle;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }

    @FXML
    private void handleKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case W:
                GridPane.setRowIndex(playerRectangle, GridPane.getRowIndex(playerRectangle) - 1);
                System.out.println("Pressed: " + keyEvent.getCode());
                break;
            case D:
                GridPane.setColumnIndex(playerRectangle, GridPane.getColumnIndex(playerRectangle) + 1);
                System.out.println("Pressed: " + keyEvent.getCode());
                break;
            case S:
                GridPane.setRowIndex(playerRectangle, GridPane.getRowIndex(playerRectangle) + 1);
                System.out.println("Pressed: " + keyEvent.getCode());
                break;
            case A:
                GridPane.setColumnIndex(playerRectangle, GridPane.getColumnIndex(playerRectangle) - 1);
                System.out.println("Pressed: " + keyEvent.getCode());
                break;
            case ESCAPE:
                Stage stage = (Stage) roomGridPane.getScene().getWindow();
                stage.close();
                break;
            default:
                System.out.println("Pressed not defined in switch!");
                break;
        }
    }

}
