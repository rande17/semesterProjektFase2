/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Game.Game;
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
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
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
        launch(args);
        
        //initializing a game object and starting the game
        Game game = new Game(); //Laver en instans af klassen Game{} og kalder den game
        game.play(); //Her invoker vi metoden play() som ligger i Game{}-klassen
        
    }
    
}
