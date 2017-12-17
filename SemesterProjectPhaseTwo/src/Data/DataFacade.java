/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import acquaintance.InterfaceData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 *
 * @author Martin Sorensen
 */
public class DataFacade implements InterfaceData {

    private HighscoreManager highscore = new HighscoreManager();
    private Logger log = new Logger();

    /**
     *
     * @return return the current highscores
     */
    @Override
    public String printHighscore() {
        return HighscoreManager.getHighscoreList();
    }

    /**
     *
     * @param str write the input to our lof file
     */
    @Override
    public void logWrite(String str) {
        try {
            log.write(str);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(DataFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param src takes an object that should be represented as in JSON format
     * @return returns an object in JSON format
     */
    @Override
    public String objectToJson(Object src) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(src);
    }

    /**
     *
     * @param name String that should be used to add a player to the hishscore
     * list
     * @param score int that should be added to hishscore list
     */
    @Override
    public void addHighscore(String name, int score) {
        highscore.addHighscore(name, score);
    }

    /**
     *
     * @param str takes String that should be written to the save file, usable a
     * JSON string with a bunch of objects
     */
    @Override
    public void saveGame(String str) {
        Save save = new Save("01");
        save.addToSaveGame(str);
        try {
            save.saveGame();
        } catch (Throwable ex) {
            java.util.logging.Logger.getLogger(DataFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @return returns the content of the save file as an array
     */
    @Override
    public ArrayList loadGame() {
        Save save = new Save("01");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String loadstring = null;
        try {
            loadstring = save.loadGame();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(DataFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList SaveData = (ArrayList) gson.fromJson(loadstring, new TypeToken<ArrayList>() {
        }.getType());
        return SaveData;

    }

}
