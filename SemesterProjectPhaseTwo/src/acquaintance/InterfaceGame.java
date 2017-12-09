/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acquaintance;

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
    public String getCraftableItems();
//   public String showHelp();
    public int playerHealth();
    public int playerEnergy();
    public int maxPlayerHealth();
    public int maxPlayerEnergy();
    public void damageToPlayer();
    public void energyLossToPlayer();
    public boolean checkExit(String dir);
    public HashMap<String, String> getNPC();
    public void lose();
    public int gameTime();
    public ArrayList getCraftableItemsArray();
}
