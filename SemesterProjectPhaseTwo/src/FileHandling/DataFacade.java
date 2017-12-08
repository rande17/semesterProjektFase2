/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileHandling;

import acquaintance.InterfaceData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.logging.Level;

/**
 *
 * @author Martin Sorensen
 */
public class DataFacade implements InterfaceData{

    private HighscoreManager highscore = new HighscoreManager();
    private  Logger log = new Logger();
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    @Override
    public String printHighscore() {
      return HighscoreManager.getHighscoreList();
    }

    @Override
    public void logWrite(String str) {
        try {
            log.write(str);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(DataFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String gsonToJson(Object src) {
       return gson.toJson(src);
    }

    @Override
    public void addHighscore(String name, int score) {
        highscore.addHighscore(name, score);
    }

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

    
    
    
}
