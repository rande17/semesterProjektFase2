/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.io.IOException;

/**
 * @author Thomas
 * @author Nicolai
 * @author Rickie
 * @author Frederik
 * @author Julie 
 * @author Martin
 */
public class SemesterProjekt {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException if the file cannot be found
     * @throws Throwable if an exception is thrown
     */
    public static void main(String[] args) throws IOException, Throwable{

        //initializing a game object and starting the game
        Game game = new Game(); //Laver en instans af klassen Game{} og kalder den game
        game.play(); //Her invoker vi metoden play() som ligger i Game{}-klassen
    }

}
