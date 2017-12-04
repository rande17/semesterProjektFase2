/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acquaintance;

import Game.Command;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author rickie
 */
public interface InterfaceGame {
    public ArrayList getItemsOnMap();
    public boolean takeItemGUI(String itemToTake);
    public void goGUI(String dir);
    public String getRoom();
    public String printInventory();
    public String showHelp();
    public void quitGame();
    public String printMissions();
    public String printHighscoreGUI();
    public int playerHealth();
    public int playerEnergi();
    public int maxPlayerHealth();
    public int maxPlayerEnergy();
    public void damageToPlayer();
    public boolean checkExit(String dir);
    public void getCraftableItems();
    public HashMap<String, String> getNPC();
    public void lose();
}
