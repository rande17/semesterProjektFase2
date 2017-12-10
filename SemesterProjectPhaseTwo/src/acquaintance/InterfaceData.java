/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acquaintance;

import java.util.ArrayList;

/**
 *
 * @author Martin Sorensen
 */
public interface InterfaceData {
    public String printHighscore();
    public void logWrite (String str);
    public String objectToJson(Object src);
    public void addHighscore(String name, int score);
    public void saveGame(String str);
    public ArrayList loadGame();
}

