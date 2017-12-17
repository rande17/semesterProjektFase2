package Game;

import static Game.CommandWord.*;
import acquaintance.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Thomas
 * @author Nicolai
 * @author Rickie
 * @author Frederik
 * @author Julie
 * @author Martin
 */
public class GameFacade implements InterfaceGame {

    /**
     * Method that gets items in current room and adds them to an ArrayList
     *
     * @return ArrayList of item names
     */
    @Override
    public ArrayList getItemsOnMap() {
        ArrayList itemNameArrayList;
        Game game = new Game();
        if (game.getItemLocation().getItems(game.getCurrentRoom()) != null && game.getItemLocation().getItems(game.getCurrentRoom()).size() > 0) {
            ArrayList itemArrayList;
            itemNameArrayList = new ArrayList(game.getItemLocation().getItems(game.getCurrentRoom()).size());
            itemArrayList = game.getItemLocation().getItems(game.getCurrentRoom());
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
        Game game = new Game();
        Command command = new Command(TAKE, itemToTake);
        returnbool = game.takeItem(command);
        return returnbool;
    }

    /**
     * Method used to craft items in GUI
     *
     * @param itemToCraft, the craftitem
     */
    @Override
    public void craftItemGUI(String itemToCraft) {

        Game game = new Game();
        Command command = new Command(CRAFT, itemToCraft);
        game.craftItem(command);
    }

    /**
     * Method used to use the GO command in GUI
     *
     * @param direction direction of the player
     */
    @Override
    public void goGUI(String direction) {
        Game game = new Game();
        Command command = new Command(GO, direction);
        try {
            game.processCommand(command);
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
        Game game = new Game();
        return game.getCurrentRoom().getShortDescription();
    }

    /**
     * Method used to print inventory in GUI
     *
     * @return Inventory as Strings
     */
    @Override
    public String printInventory() {
        Game game = new Game();
        return game.getInventory().toString();
    }

    /**
     * Method used to print missions in GUI
     *
     * @return missions as Strings
     */
    @Override
    public String printMissions() {
        Game game = new Game();
        return game.getAllMissions().toString();
    }

    /**
     * Method used to show help in GUI
     *
     * @return help information as Strings
     */
    @Override
    public String showHelp() {
        Game game = new Game();
        return game.printHelp();
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
        Game game = new Game();
        return game.getHighscoreFromData();
    }

    /**
     * Method used to check exits
     *
     * @param direction is the direction for which the exit is checked
     * @return true or false, depending on if there is an exit in the direction
     * heading
     */
    @Override
    public boolean checkExit(String direction) {
        Game game = new Game();
        return game.getExitBool(direction);
    }

    /**
     * Method used to get health of the player
     *
     * @return player health
     */
    @Override
    public int playerHealth() {
        Game game = new Game();
        return game.getPlayer().getHealth();
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
        Game game = new Game();
        return game.getPlayer().getEnergy();
    }

    /**
     * Method used to get max health of the player
     *
     * @return player max health
     */
    @Override
    public int maxPlayerHealth() {
        Game game = new Game();
        return game.getPlayer().getMaxHealth();
    }

    /**
     * Method used to get max energy of the player
     *
     * @return player max energy
     */
    @Override
    public int maxPlayerEnergy() {
        Game game = new Game();
        return game.getPlayer().getMaxEnergy();
    }

    /**
     * Method used to decrease the energy of the player
     */
    @Override
    public void energyLossToPlayer() {
        Game game = new Game();
        game.getPlayer().loseEnergy(15);
    }

    /**
     * Method used to get HashMap containing NPCs
     *
     * @return HashmMap containing NPCs
     */
    @Override
    public HashMap<String, String> getNPC() {
        Game game = new Game();
        return game.storeNPC();
    }

    /**
     * Method used to lose the game
     */
    @Override
    public void lose() {
        Game game = new Game();
        game.lose();
    }

    /**
     * Method used to win the game
     */
    @Override
    public void win() {
        Game game = new Game();
        game.win();
    }

    /**
     * Method used to get craftable items
     *
     * @return ArrayList containing craftable items
     */
    @Override
    public ArrayList getCraftableItemsArray() {
        Game game = new Game();
        ArrayList craftableItemsArrayList;
        ArrayList craftableItemsNameArrayList = new ArrayList(game.getCraftableItemList().getCratableListArray().size());
        craftableItemsArrayList = game.getCraftableItemList().getCratableListArray();
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
        Game game = new Game();
        ArrayList craftArray;
        craftArray = game.getCraftableItemList().getCratableListArray();
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
        Game game = new Game();
        return game.getInventory().inventoryNames();
    }

    /**
     * Method that unlocks the possibility to escape the island. It checks if
     * current room is beach and if mission is completed
     *
     * @return wheter the option to escape the island is unlocked
     */
    @Override
    public boolean unlockedEscapeIsland() {
        Game game = new Game();
        return game.UnlockedEscapeTheIsland();
    }

    /**
     * Method that locks possibility to escape the island
     *
     * @return wheter the option to escape the island is unlocked
     */
    @Override
    public boolean lockedEscapeIsland() {
        Game game = new Game();
        return game.lockedEscapeIsland();
    }

    /**
     * Method used for dropping item from inventory
     *
     * @param itemToDrop, item to be dropped
     */
    @Override
    public void dropItem(String itemToDrop) {
        Game game = new Game();
        Command command = new Command(DROP, itemToDrop);
        game.dropItem(command);
    }

    /**
     * Method for using items stored in inventory
     *
     * @param itemToUse, item to be used
     * @return true or false depending on if item can be used or not
     */
    @Override
    public boolean useItem(String itemToUse) {
        Game game = new Game();
        Command command = new Command(USE, itemToUse);
        return game.useItem(command);
    }

    /**
     * Method used to submit highscore after completing the game
     *
     * @param playerName, refers to the name entered by the player
     */
    @Override
    public void submitHighScore(String playerName) {
        Game game = new Game();
        game.setHighscoreName(playerName);
        game.win();
    }

    /**
     * Method used for saving the game up in the GUI layer
     */
    @Override
    public void saveGameGUI() {
        Game game = new Game();
        Command command = new Command(SAVE, null);
        try {
            game.processCommand(command);
        } catch (Throwable ex) {
            Logger.getLogger(GameFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Method used for loading the game up in the GUI layer
     */
    @Override
    public void loadGameGUI() {
        Game game = new Game();
        Command command = new Command(LOAD, null);
        try {
            game.processCommand(command);
        } catch (Throwable ex) {
            Logger.getLogger(GameFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Method used for starting a new game in the GUI layer
     */
    @Override
    public void newGameGUI() {
        Game game = new Game();
        game.initGame();
    }

    /**
     *
     * @param NPCname Name of the NPC which the player wants to talk with
     * @return NPC dialog
     */
    @Override
    public String getDialog(String NPCname) {
        Game game = new Game();
        return game.talkToNPC(NPCname);
    }

    /**
     * Set whether or not to use the GUI
     */
    @Override
    public void useGUI() {
        Game game = new Game();
        game.setUsingGUI(true);
    }

    /**
     * 
     * @param opt is the response to the NPC, J.S.
     */
    @Override
    public void sendDialogOption(String opt) {
        Game game = new Game();
        game.setOption(opt);
    }

    /**
     *
     * @return the forced dialog with the npc
     */
    @Override
    public boolean forcedText() {
        Game game = new Game();
        return game.getForcedTextBox();
    }

    /**
     *
     * @return the position of player and all NPC's
     */
    @Override
    public String getPositionOfAllCharacters() {
        Game game = new Game();
        String returnString = "";
        String fillerText = ": " + System.lineSeparator() + "Is at the ";

        returnString += game.getPlayer().getName() + fillerText + game.getPlayer().
                getCurrentRoom().getShortDescription() + System.lineSeparator();
        returnString += game.getNPCFromName("BS_Christiansen").getName().replace("_", " ")
                + fillerText + game.getNPCFromName("BS_Christiansen").getCurrentRoom().
                        getShortDescription() + System.lineSeparator();
        returnString += game.getNPCFromName("Joseph_Schnitzel").getName().replace("_", " ")
                + fillerText + game.getNPCFromName("Joseph_Schnitzel").getCurrentRoom().
                        getShortDescription() + System.lineSeparator();
        returnString += game.getNPCFromName("Mysterious_Crab").getName().replace("_", " ")
                + fillerText + game.getNPCFromName("Mysterious_Crab").getCurrentRoom().
                        getShortDescription() + System.lineSeparator();

        return returnString;
    }
}
