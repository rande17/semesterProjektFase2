package Game;

import FileHandling.Logger;
import FileHandling.Save;
import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 * @author Nicolai
 * @version 2006.03.30daSFT
 */
public class Game {

    /* data field with the attributes parser and currentRoom
       making them private so we only can use them in the Game class */
    private Parser parser;
    private Room currentRoom;

    /* Constructor that runs the method createRooms and set our variable parser
       equal to the Parser method in the Parser class */
    public Game() {
        createRooms();
        parser = new Parser();
    }

    /* Private method createRoom which means we can only use createRoom in the Game class */
 /* In the method body we set the names of the rooms, create the rooms by using the Room 
       constructor from the Room class and then set where you can move to from the different rooms by
       using the method setExit from the Room class */
 /* The currentRoom is also given a value which is the start location = outside */
    ItemLocation itemLocation = new ItemLocation();
    Inventory inventory = new Inventory();
    Item debug = new Item("debug");
    Mission allMissions = new Mission();
    NPC npc1 = new NPC();
    NPC npc2 = new NPC();
    NPC npc3 = new NPC();

    //file thats gonna be written to and the extension
    Logger log = new Logger();
    Gson gson = new Gson();

    /**
     * Used to initialize different rooms and their respective items, and also
     * set the currentRoom
     */
    private void createRooms() {
        Room airport, beach, jungle, mountain, cave, camp, raft, seaBottom;

        airport = new Room("at the airport");
        beach = new Room("at the beach");
        jungle = new Room("in the jungle");
        mountain = new Room("at the mountain");
        cave = new Room("in the cave");
        camp = new Room("in the camp");
        seaBottom = new Room("at the bottom of the sea");
        raft = new Room("building the raft");

        airport.setExit("west", beach);

        //Initializing an item and putting it in a room
        itemLocation.addItem(airport, new Item("Bottle", 2));
        itemLocation.addItem(airport, new Item("Boardingpass", 1));

        //Initializing an mission and putting it in a room
        allMissions.addMission(airport, "First mission", "Find your boardingpass", 5);

        //Setting the the exit
        beach.setExit("north", jungle);
        beach.setExit("south", seaBottom);
        beach.setExit("west", camp);

        itemLocation.addItem(beach, new Item("Stone", 2));
        itemLocation.addItem(beach, new Item("Fish", 1));
        itemLocation.addItem(beach, new Item("Flint", 2));
        itemLocation.addItem(beach, new Item("Rope", 2));
        itemLocation.addItem(beach, new Item("Stick", 1));

        jungle.setExit("north", mountain);
        jungle.setExit("east", cave);
        jungle.setExit("south", beach);
        itemLocation.addItem(jungle, new Item("Berry", 1));
        itemLocation.addItem(jungle, new Item("Lumber", 3));
        itemLocation.addItem(jungle, new Item("Lian", 2));
        itemLocation.addItem(jungle, new Item("Stone", 2));
        itemLocation.addItem(jungle, new Item("Stick", 1));

        npc1.setName("BS Christiansen");
        npc1.setCurrentRoom(jungle);
        npc1.setDescribtion("The survivor of the plane crash look to be some kind of veteran soldier, but he is heavly injured on his right leg so he cant move ");
        npc1.addDialog("If you want to survive on this GOD forsaken island, you must first find food and shelter.");

        mountain.setExit("south", jungle);
        itemLocation.addItem(mountain, new Item("Stone", 2));
        itemLocation.addItem(mountain, new Item("Egg", 1));

        npc3.setName("Joseph Schitzel");
        npc3.setCurrentRoom(mountain);
        npc3.setDescribtion("A lonely surviver with very filthy hair, and a wierd smell of weinerschnitzel.");
        npc3.addDialog("Heeelloooo there my freshlooking friend, I am Joseph Schitzel, if you scratch my back I might scratch your's.");

        cave.setExit("west", jungle);
        itemLocation.addItem(cave, new Item("Shroom", 1));
        itemLocation.addItem(cave, new Item("Stone", 2));
        itemLocation.addItem(cave, new Item("Freshwater", 2));
        itemLocation.addItem(cave, new Item("Flint", 2));

        npc2.setName("Mysterious crab");
        npc2.setCurrentRoom(cave);
        npc2.setDescribtion("A mysterious crab that you dont really get why can talk");
        npc2.addDialog("MUHAHAHA i'm the finest and most knowledgeable crab of them all mr.Crab and know this island like the back of my hand.... oh i mean claw"
                + "\n SO if you want the rarest item you can find on this island, you must first help me find some stuff ");

        camp.setExit("east", beach);
        camp.setExit("west", raft);
        itemLocation.addItem(camp, new Item(""));

        seaBottom.setExit("north", beach);
        itemLocation.addItem(seaBottom, new Item("Backpack", 2));
        itemLocation.addItem(seaBottom, new Item("WaterBottle", 1));
        itemLocation.addItem(seaBottom, new Item("Rope", 2));

        raft.setExit("east", camp);

        currentRoom = airport;

    }

    /* A method that is initialized when we start the game, that first print out a message with the printWelcome method  
       and then checks if the game is finished or not with a while loop where finished is set to false when the game start*/
    public void play() throws FileNotFoundException, IOException {
        printWelcome();
        log.write("\n\n >>>  Starting new game <<< \n\n");

        boolean finished = false;

        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }

        System.out.println("Thank you for playing.  Good bye.");
    }

    /* A method that is used in the play method to print a message when you start the game */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the game Stranded!");
        System.out.println("Stranded is an adventure game, where you are to "
                + "find out how to escape the island");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /* A method that set wantToQuit to false and the run a if loop that that does so everytime the commandWord is  
       not know to the game it print out the message "I don't know what you mean..." and return false*/
 /* It does the same with Help and GO where it print out a message with the use of the method printHelp and goRoom
       and if the command word is quit it return wantToQuit*/
    private boolean processCommand(Command command) throws FileNotFoundException, IOException {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        log.write(commandWord.toString() + " " + command.getSecondWord());

        if (commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        } else if (commandWord == CommandWord.GO) {
            goRoom(command);
        } else if (commandWord == CommandWord.SHOW) {
//            showInventory(command);
            showInventory();
        } else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        } else if (commandWord == CommandWord.INSPECT) {
            inspectRoom();
        } else if (commandWord == CommandWord.TAKE) {
            takeItem(command);
        } else if (commandWord == CommandWord.TALK) {
            TalkTo();
        } else if (commandWord == CommandWord.DROP) {
            dropItem(command);
        } else if (commandWord == CommandWord.MISSION) {
            showMissions();
        } else if (commandWord == CommandWord.SAVE) {
            saveGame();
        }

        //setting the condition to complete the mission.
        if (inventory.getInventory().containsKey("Boardingpass")) {
            allMissions.setMissionComplete("First mission");
        }

        return wantToQuit;
    }

    /* A method to print a message that show the different commands everytime the command help is used */
    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around on this god forsaken island.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /* method that is initializing everytime you use the command "go" and print the message "Go where" */
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);
        /* loop that when next room is equel to null it print a message that says "there is no door" 
           but if nextRoom is not equel null set currentRoom to nextRoom and print the description of the new room*/
        if (nextRoom == null) {
            System.out.println("There is no path!");
        } else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    // Method used for showing contents in inventory
//    private void showInventory(Command command) {
    private void showInventory() {

        HashMap<String, Integer> inventoryHM = inventory.getInventory();

        System.out.println("Items in inventory is: ");
        for (String i : inventoryHM.keySet()) {
            System.out.println(inventoryHM.get(i) + "x" + i);
        }
    }

    //Method used for inspecting room and showing items in that room
//    private void inspectRoom(Command command){
    private void inspectRoom() {
        ArrayList items = itemLocation.getItems(currentRoom);
        Item seeItem;
        String itemList = "";
        for (int i = 0; i < items.size(); i++) {

            seeItem = (Item) items.get(i);
//            System.out.println(seeItem.getName());
            itemList += seeItem.getName();
            if (i < items.size() - 1) {
                itemList = itemList + ", ";
            }
        }
        System.out.println(itemList);
        if (npc1.getCurrentRoom() == currentRoom) {
            System.out.println("There seems to be someone resting in the leaves");
        } else if (npc2.getCurrentRoom() == currentRoom) {
            System.out.println("You sense somebody in the cave");
        } else if (npc3.getCurrentRoom() == currentRoom) {
            System.out.println("There is an intense smell, somebody seems to be near!");
        } else {
            System.out.println("You are alone in the room");
        }
    }

    /**
     * Method used for taking and placing an item in inventory
     *
     * @param command used for checking if an item exists in current room
     */
    private void takeItem(Command command) {
        ArrayList currentRoomItem = itemLocation.getItems(currentRoom);
        Item seeItem;
        int indexItem = -1;
        Item addToInventory = debug;

        for (int i = 0; i < currentRoomItem.size(); i++) {
            seeItem = (Item) currentRoomItem.get(i);
            if (seeItem.getName().equalsIgnoreCase(command.getSecondWord())) {
                addToInventory = seeItem;
                indexItem = i;
                break;
            }
        }

        if (indexItem >= 0) {
            System.out.println("Item has been added to inventory: " + addToInventory.getName());
            inventory.addItemInInventory(addToInventory);
            currentRoomItem.remove(indexItem);
            itemLocation.setItem(currentRoom, currentRoomItem);
        } else {
            System.out.println("could not find " + command.getSecondWord());
        }
    }

    private void TalkTo() {

        if (npc1.getCurrentRoom() == currentRoom) {
            System.out.println(npc1.getDescribtion() + ", yet he still gives u good advice:\n" + npc1.getDialog(0));
        } else if (npc2.getCurrentRoom() == currentRoom) {
            System.out.println(npc2.getDescribtion() + "\n" + npc2.getDialog(0));
        } else if (npc3.getCurrentRoom() == currentRoom) {
            System.out.println(npc3.getDescribtion() + "\n" + npc3.getDialog(0));
        } else {
            System.out.println("There is nobody to communicate with in this Room");
        }

    }

    /* 
    * Method used for showing missions
     */
    private void showMissions() {

        HashMap<String, String> viewMission = allMissions.missionInfo;
        HashMap<String, Boolean> missionStatus = allMissions.missionStatus;

        System.out.println("Your missions are: ");
        for (String i : viewMission.keySet()) {

            System.out.println(viewMission.get(i) + "\t| The mission is complete: " + missionStatus.get(i));
        }

        for (String i : missionStatus.keySet()) {
            missionStatus.get(i);

            if (missionStatus.get(i) == false) {
                System.out.println("Mission is in progress.");
            }
            if (missionStatus.get(i) == true) {
                System.out.println("Mission is complete");
            }
        }

    }

    /**
     * Method used for dropping item from inventory
     *
     * @param command used for checking if an item exists in inventory
     */
    private void dropItem(Command command) {
        HashMap newInventory = inventory.getInventory();
        Iterator itte = newInventory.entrySet().iterator();
        String seeItem;
//        int indexItem = -1;
        String indexItem = "";
        String dropFromInventory = "debug";

        while (itte.hasNext()) {
            HashMap.Entry liste = (HashMap.Entry) itte.next();
            String itemName = (String) liste.getKey();
            if (itemName.equalsIgnoreCase(command.getSecondWord())) {
                dropFromInventory = itemName;
                indexItem = itemName;
                break;
            }
        }
        if (!indexItem.equals("")) {
            inventory.dropItemInventory(indexItem);
            System.out.println("You have dropped: " + indexItem);

        } else {
            System.out.println("Can't drop item that isn't in inventory " + command.getSecondWord());
        }

    }

    /**
     * method to quit the game and if there is a second word it print out a line
     * "Quit what"
     *
     * @param command used for checking if input has a second word, when the
     * first word is quit
     * @return gives either true or false, returns true when input has no second
     * word other than "quit" and terminates program
     */
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;
        }
    }

    private void saveGame() throws IOException {
        Save save = new Save("01");
        save.addToSaveGame(gson.toJson(inventory));
        save.addToSaveGame(gson.toJson(itemLocation));
        save.addToSaveGame(gson.toJson(allMissions));
//        save.addToSaveGame(gson.toJson(npc1));
//        save.addToSaveGame(gson.toJson(npc2));
//        save.addToSaveGame(gson.toJson(npc3));
        save.saveGame();
    }
}
