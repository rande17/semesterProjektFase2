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
    public String getRoomDescribtion();
    public String printInventory();
    public String showHelp();
    public void quitGame();
    public String printMissions();
    public String printHighscoreGUI();
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
    public void win();
    public int gameTime();
    public ArrayList inventoryNames();
    public void dropItem(String item);
    public ArrayList getCraftableItemsArray();
    public String getCraftableItemDescribtion(String itemName);
    public void craftItemGUI(String itemToCraft);
    public void submitHighScore(String playerName);
    public boolean useItem(String itemToUse);
    public void saveGameGUI();
    public boolean unlockedEscapeIsland();
    public boolean lockedEscapeIsland();
    
}
