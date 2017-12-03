/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import static Game.CommandWord.*;
import acquaintance.*;
import java.util.ArrayList;
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
    public void getCraftableItems() {

    }

    @Override
    public String printHighscoreGUI() {
        return Game.getHighscoreFromData().toString();
    }

}
