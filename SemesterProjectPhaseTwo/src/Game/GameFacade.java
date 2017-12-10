/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import static Game.CommandWord.*;
import acquaintance.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rickie
 */
public class GameFacade implements InterfaceGame {
    
    @Override
    public ArrayList getItemsOnMap() {
        ArrayList itemArrayList = new ArrayList(Game.itemLocation.getItems(Game.currentRoom).size());
        ArrayList itemNameArrayList = new ArrayList(Game.itemLocation.getItems(Game.currentRoom).size());
        itemArrayList = Game.itemLocation.getItems(Game.currentRoom);
        for (int i = 0; i < itemArrayList.size(); i++) {
            Item item = (Item) itemArrayList.get(i);
            itemNameArrayList.add(item.getName());
        }
        return itemNameArrayList;
    }

    @Override
    public boolean takeItemGUI(String itemToTake) {
        boolean returnbool;
        Command command = new Command(TAKE, itemToTake);
        returnbool = Game.takeItem(command);
//            Command commandd = new Command(UNKNOWN, itemToTake);
//        try {
//            Game.processCommand(commandd);
//        } catch (Throwable ex) {
//            Logger.getLogger(GameFacade.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return returnbool;
    }

    @Override
    public void craftItemGUI(String itemToCraft) {
        Command command = new Command(CRAFT, itemToCraft);
        Game.craftItem(command);
    }

    @Override
    public void goGUI(String dir) {
        Command command = new Command(GO, dir);
        try {
            Game.processCommand(command);
        } catch (Throwable ex) {
            Logger.getLogger(GameFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getRoom() {
        return Game.currentRoom.getShortDescription();
    }

    @Override
    public String printInventory() {
        return Game.inventory.toString();
    }

    @Override
    public String printMissions() {
        return Game.allMissions.toString();
    }

    @Override
    public String showHelp() {
        return Game.printHelp();
    }

    @Override
    public void quitGame() {
        System.exit(0);
    }

    @Override
    public String printHighscoreGUI() {
        return Game.getHighscoreFromData().toString();
    }

    @Override
    public boolean checkExit(String dir) {
        return Game.getExitBool(dir);
    }

    @Override
    public int playerHealth() {
        return Game.player.getHealth();
    }

    @Override
    public int gameTime() {
        return Time.getSecondsPassed();
    }

    @Override
    public int playerEnergy() {
        return Game.player.getEnergy();
    }

    @Override
    public int maxPlayerHealth() {
        return Game.player.getMaxHealth();
    }

    @Override
    public int maxPlayerEnergy() {
        return Game.player.getMaxEnergy();
    }

    @Override
    public void damageToPlayer() {
        Game.player.loseHealth(25);
    }

    @Override
    public void energyLossToPlayer() {
        Game.player.loseEnergy(5);
    }

    @Override
    public HashMap<String, String> getNPC() {
        return Game.storeNPC();
    }

    @Override
    public void lose() {
        Game.lose();
    }

    @Override
    public void win() {
        Game.win();
    }

    @Override
    public ArrayList getCraftableItemsArray() {
        ArrayList craftableItemsArrayList = new ArrayList(CraftableItem.craftableListArray.size());
        ArrayList craftableItemsNameArrayList = new ArrayList(CraftableItem.craftableListArray.size());
        craftableItemsArrayList = CraftableItem.craftableListArray;
        for (int i = 0; i < craftableItemsArrayList.size(); i++) {
            Item item = (Item) craftableItemsArrayList.get(i);
            craftableItemsNameArrayList.add(item.getName());
        }
        return craftableItemsNameArrayList;
    }

    @Override
    public String getCraftableItemDescribtion(String itemName) {
        ArrayList craftArray = new ArrayList(CraftableItem.craftableListArray.size());
        craftArray = CraftableItem.craftableListArray;
        String itemDescribtion = "";
        for (int i = 0; i < craftArray.size(); i++) {
            Item item = (Item) craftArray.get(i);
            if (item.getName().equals(itemName)) {
                itemDescribtion = item.getItemDescribtion();
                break;
            }
        }
        return itemDescribtion;
    }

    @Override
    public ArrayList inventoryNames() {
        return Game.inventory.inventoryNames();
    }

    @Override
    public void unlockedEscapeIsland() {
        Game.UnlockedEscapeTheIsland();
    }

    @Override
    public void lockedEscapeIsland() {
        Game.lockedEscapeIsland();
    }

    @Override
    public void dropItem(String itemToDrop) {
        Command command = new Command(DROP, itemToDrop);
        Game.dropItem(command);
    }

    @Override
    public boolean useItem(String itemToUse) {
        Command command = new Command(DROP, itemToUse);
        return Game.useItem(command);
    }

    @Override
    public void submitHighScore(String playerName) {
        System.out.println(playerName + "Facade");
        Game.setHighscoreName(playerName);
        Game.win();
    }

    @Override
    public void saveGameGUI() {
        Command command = new Command(SAVE, null);
        try {
            Game.processCommand(command);
        } catch (Throwable ex) {
            Logger.getLogger(GameFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
