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
                if (GridPane.getRowIndex(playerRectangle) > 0) {
                    GridPane.setRowIndex(playerRectangle, GridPane.getRowIndex(playerRectangle) - 1);
                }
                break;
            case D:
                if (GridPane.getColumnIndex(playerRectangle) < 15) {
                    GridPane.setColumnIndex(playerRectangle, GridPane.getColumnIndex(playerRectangle) + 1);
                }
                break;
            case S:
                if (GridPane.getRowIndex(playerRectangle) < 15) {
                    GridPane.setRowIndex(playerRectangle, GridPane.getRowIndex(playerRectangle) + 1);
                }
                break;
            case A:
                if (GridPane.getColumnIndex(playerRectangle) > 0) {
                    GridPane.setColumnIndex(playerRectangle, GridPane.getColumnIndex(playerRectangle) - 1);
                }
                break;
            case F1:
                //Kun for at se koordinaterne på figuren
                System.out.println(GridPane.getRowIndex(playerRectangle) + " : " + GridPane.getColumnIndex(playerRectangle));
                break;
            case ESCAPE:
                Stage stage = (Stage) roomGridPane.getScene().getWindow();
                stage.close();
                break;
            default:
//                System.out.println("Pressed not defined in switch!");
                break;
        }
    }

}
