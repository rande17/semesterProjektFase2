/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Game.Game;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author rickie
 */
public class SemesterProjectPhaseTwo extends Application {
        Parent root;
        Scene scene;
    @Override
    public void start(Stage stage) throws Exception {
        root = FXMLLoader.load(getClass().getResource("airport.fxml"));
        scene = new Scene(root);
        stage.setTitle("Semester Projekt");
        stage.setScene(scene);
        stage.show();
        scene.getRoot().requestFocus();
    }

    /**
     * @param args the command line arguments
     * @throws java.lang.Throwable
     */
    public static void main(String[] args) throws Throwable {
        
        Thread thread = new Thread() {
            @Override
            public void run() {
                Game game = new Game();
                try {
                    game.play();
                } catch (Throwable ex) {
                    Logger.getLogger(SemesterProjectPhaseTwo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        
        thread.start();
        
        launch(args);

    }

}
