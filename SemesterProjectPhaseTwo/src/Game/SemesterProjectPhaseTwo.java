///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//
///**
// *
// * @author rickie
// */
//public class SemesterProjectPhaseTwo extends Application {
//    
//    @Override
//    public void start(Stage stage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
//        
//        Scene scene = new Scene(root);
//        
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        launch(args);
//    }
//    
//}
package Game;

import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Thomas
 * @author Nicolai
 * @author Rickie
 * @author Frederik
 * @author Julie Dittmann Weimar Andersen
 * @author Martin
 */
public class SemesterProjectPhaseTwo extends Application{

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException, Throwable{
        
        //initializing a game object and starting the game
        Game game = new Game(); //Laver en instans af klassen Game{} og kalder den game
        game.play(); //Her invoker vi metoden play() som ligger i Game{}-klassen
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

