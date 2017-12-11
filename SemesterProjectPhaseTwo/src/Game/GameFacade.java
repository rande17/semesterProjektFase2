package Game;

import static Game.CommandWord.*;
import acquaintance.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameFacade implements InterfaceGame {

    /**
     * Method that gets items in current room and adds them to an ArrayList
     *
     * @return ArrayList of item names
     */
    @Override
    public ArrayList getItemsOnMap() {
        ArrayList itemNameArrayList;
        if (Game.itemLocation.getItems(Game.currentRoom) != null && Game.itemLocation.getItems(Game.currentRoom).size() > 0) {
            ArrayList itemArrayList = new ArrayList(Game.itemLocation.getItems(Game.currentRoom).size());
            itemNameArrayList = new ArrayList(Game.itemLocation.getItems(Game.currentRoom).size());
            itemArrayList = Game.itemLocation.getItems(Game.currentRoom);
            for (int i = 0; i < itemArrayList.size(); i++) {
                Item item = (Item) itemArrayList.get(i);
                itemNameArrayList.add(item.getName());
            }
        } else {
            itemNameArrayList = new ArrayList();
        }
        return itemNameArrayList;
    }

    /**
     * Method that checks if you can pickup an Item playing the the GUI
     *
     * @param itemToTake, the item interacted with
     * @return true or false
     */
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

    /**
     * Method used to craft items in GUI
     *
     * @param itemToCraft, the craftitem
     */
    @Override
    public void craftItemGUI(String itemToCraft) {
        Command command = new Command(CRAFT, itemToCraft);
        Game.craftItem(command);
    }

    /**
     * Method used to use the GO command in GUI
     *
     * @param direction direction of the player
     */
    @Override
    public void goGUI(String direction) {
        Command command = new Command(GO, direction);
        try {
            Game.processCommand(command);
        } catch (Throwable ex) {
            Logger.getLogger(GameFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Method used to get shortDescribtion of current room
     *
     * @return current room describtion as String
     */
    @Override
    public String getRoomDescribtion() {
        return Game.currentRoom.getShortDescription();
    }

    /**
     * Method used to print inventory in GUI
     *
     * @return Inventory as Strings
     */
    @Override
    public String printInventory() {
        return Game.inventory.toString();
    }

    /**
     * Method used to print missions in GUI
     *
     * @return missions as Strings
     */
    @Override
    public String printMissions() {
        return Game.allMissions.toString();
    }

    /**
     * Method used to show help in GUI
     *
     * @return help information as Strings
     */
    @Override
    public String showHelp() {
        return Game.printHelp();
    }

    /**
     * Method used to quit game
     */
    @Override
    public void quitGame() {
        System.exit(0);
    }

    /**
     * Method used to print highscore in GUI
     *
     * @return highscore as Strings
     */
    @Override
    public String printHighscoreGUI() {
        return Game.getHighscoreFromData().toString();
    }

    /**
     * Method used to check exits
     *
     * @param direction
     * @return true or false, depending on if there is an exit in the direction
     * heading
     */
    @Override
    public boolean checkExit(String direction) {
        return Game.getExitBool(direction);
    }

    /**
     * Method used to get health of the player
     *
     * @return player health
     */
    @Override
    public int playerHealth() {
        return Game.player.getHealth();
    }

    /**
     * Method used to get time passed during play time.
     *
     * @return seconds passed during play time
     */
    @Override
    public int gameTime() {
        return Time.getSecondsPassed();
    }

    /**
     * Method used to get energi of the player
     *
     * @return player energy
     */
    @Override
    public int playerEnergy() {
        return Game.player.getEnergy();
    }

    /**
     * Method used to get max health of the player
     *
     * @return player max health
     */
    @Override
    public int maxPlayerHealth() {
        return Game.player.getMaxHealth();
    }

    /**
     * Method used to get max energy of the player
     *
     * @return player max energy
     */
    @Override
    public int maxPlayerEnergy() {
        return Game.player.getMaxEnergy();
    }

    /**
     * Method used to deal damage to the player
     */
    @Override
    public void damageToPlayer() {
        Game.player.loseHealth(25);
    }

    /**
     * Method used to decrease the energy of the player
     */
    @Override
    public void energyLossToPlayer() {
        Game.player.loseEnergy(5);
    }

    /**
     * Method used to get HashMap containing NPCs
     *
     * @return HashmMap containing NPCs
     */
    @Override
    public HashMap<String, String> getNPC() {
        return Game.storeNPC();
    }

    /**
     * Method used to lose the game
     */
    @Override
    public void lose() {
        Game.lose();
    }

    /**
     * Method used to win the game
     */
    @Override
    public void win() {
        Game.win();
    }

    /**
     * Method used to get craftable items
     *
     * @return ArrayList containing craftable items
     */
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

    /**
     * Method used to get desribtion of craftable item
     *
     * @param itemName, name of the craftable item
     * @return craftable item desribtion as String
     */
    @Override
    public String getCraftableItemDescribtion(String itemName) {
        ArrayList craftArray = new ArrayList(CraftableItem.craftableListArray.size());
        craftArray = CraftableItem.craftableListArray;
        String itemDescribtion = "";
        for (int i = 0; i < craftArray.size(); i++) {
            Item item = (Item) craftArray.get(i);
            if (item.getName().equals(itemName)) {
                itemDescribtion = item.getItemDescription();
                break;
            }
        }
        return itemDescribtion;
    }

    /**
     * Method used to get name of all items in inventory
     *
     * @return ArrayList of item names in inventory
     */
    @Override
    public ArrayList inventoryNames() {
        return Game.inventory.inventoryNames();
    }

    /**
     * Method that unlocks the possibility to escape the island. It checks if
     * current room is beach and if mission is completed
     * @return 
     */
    @Override
    public boolean unlockedEscapeIsland() {
        return Game.UnlockedEscapeTheIsland();
    }

    /**
     * Method that locks possibility to escape the island
     * @return 
     */
    @Override
    public boolean lockedEscapeIsland() {
        return Game.lockedEscapeIsland();
    }

    /**
     * Method used for dropping item from inventory
     *
     * @param itemToDrop, item to be dropped
     */
    @Override
    public void dropItem(String itemToDrop) {
        Command command = new Command(DROP, itemToDrop);
        Game.dropItem(command);
    }

    /**
     * Method for using items stored in inventory
     *
     * @param itemToUse, item to be used
     * @return true or false depending on if item can be used or not
     */
    @Override
    public boolean useItem(String itemToUse) {
        Command command = new Command(DROP, itemToUse);
        return Game.useItem(command);
    }

    /**
     * Method used to submit highscore after completing the game
     *
     * @param playerName, refers to the name entered by the player
     */
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

    @Override
    public void loadGameGUI() {
        Command command = new Command(LOAD, null);
        try {
            Game.processCommand(command);
        } catch (Throwable ex) {
            Logger.getLogger(GameFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void newGameGUI() {
        Game.initGame();
    }
}
