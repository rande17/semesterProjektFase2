package semesterprojekt;

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
    Mission mission1 = new Mission();
    Mission mission2 = new Mission();
    Mission mission3 = new Mission();

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
        itemLocation.addItem(airport, new Item("Bottle"));
        itemLocation.addItem(airport, new Item("Boardingpass"));

        //Setting the the exit
        beach.setExit("north", jungle);
        beach.setExit("south", seaBottom);
        beach.setExit("west", camp);

        itemLocation.addItem(beach, new Item("Stone"));
        itemLocation.addItem(beach, new Item("Fish"));
        itemLocation.addItem(beach, new Item("Flint"));
        itemLocation.addItem(beach, new Item("Rope"));
        itemLocation.addItem(beach, new Item("Stick"));

        jungle.setExit("north", mountain);
        jungle.setExit("east", cave);
        jungle.setExit("south", beach);
        itemLocation.addItem(jungle, new Item("Berry"));
        itemLocation.addItem(jungle, new Item("Lumber"));
        itemLocation.addItem(jungle, new Item("Lian"));
        itemLocation.addItem(jungle, new Item("Stone"));
        itemLocation.addItem(jungle, new Item("Stick"));

        NPC npc1 = new NPC("Good guy", jungle);
        npc1.setDescribtion("The survivor of the plane crash look to be some kind of veteran soldier, but he is heavly injured on his right leg so he cant move ");
        npc1.addDialog("If you want to survive on this GOD forsaken island, you must first find food and shelter");

        mountain.setExit("south", jungle);
        itemLocation.addItem(mountain, new Item("Stone"));
        itemLocation.addItem(mountain, new Item("Egg"));

        NPC npc3 = new NPC("Evil guy", mountain);

        cave.setExit("west", jungle);
        itemLocation.addItem(cave, new Item("Shroom"));
        itemLocation.addItem(cave, new Item("Stone"));
        itemLocation.addItem(cave, new Item("Freshwater"));
        itemLocation.addItem(cave, new Item("Flint"));

        NPC npc2 = new NPC("Mysterious crab", cave);
        npc2.setDescribtion("A mysterious crab that you dont really get why can talk");
        npc2.addDialog("MUHAHAHA i'm the finest and most knowledgeable crab of them all mr.Crab and know this island like the back of my hand.... oh i mean claw"
                + "\n SO if you want the rarest item you can find on this island, you must first help me find some stuff ");

        camp.setExit("east", beach);
        camp.setExit("west", raft);
        itemLocation.addItem(camp, new Item(""));

        seaBottom.setExit("north", beach);
        itemLocation.addItem(seaBottom, new Item("Backpack"));
        itemLocation.addItem(seaBottom, new Item("WaterBottle"));
        itemLocation.addItem(seaBottom, new Item("Rope"));

        raft.setExit("east", camp);

        currentRoom = airport;

    }

    private void createMissions() {
        mission1.addMission("Getting started", "First item", 10);
        mission2.addMission("Adventure", "Visited the whole island", 20);
        mission3.addMission("Waking up", "Discovered the beach", 5);

    }
//    private void createItems(){
//    
//    ob1.addItem(airport, new Item("Bottle"));
//    ob1.addItem(airport, new Item("Boardingpass"));    
//    }

    /* A method that is initialized when we start the game, that first print out a message with the printWelcome method  
       and then checks if the game is finished or not with a while loop where finished is set to false when the game start*/
    public void play() {
        printWelcome();

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
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

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
//            inspectRoom(command);
            inspectRoom();
        } else if (commandWord == CommandWord.TAKE) {
            takeItem(command);
        } else if (commandWord == CommandWord.TALK) {
//            TalkTo(command);
        } else if (commandWord == CommandWord.DROP) {
            dropItem(command);
        } else if (commandWord == CommandWord.MISSION) {
//            showMissions(command); 
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
        String itemList = "";
        Item seeItem;

        System.out.print("Items in room is: ");
        for (int i = 0; i < items.size(); i++) {
            seeItem = (Item) items.get(i);
            itemList+=seeItem.getName();
            if (i < items.size() - 1) {
                itemList = itemList + ", ";
            }

        }
        System.out.println(itemList);
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

//     private void TalkTo(Command command){
//        ArrayList talk =  
//        if(npc1 == currentRoom)
//          getdialog();
//     }
//
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
            itemLocation.addItem(currentRoom, new Item(indexItem));

        } else {
            System.out.println("Can't drop item that isn't in inventory " + command.getSecondWord());
        }

    }

//    private void showMissions(Command command){
//      
//      HashMap<String, String> viewMission = mission.getMissionDescribtion(key);
//
//        for (String i : viewMission.keySet()) {
//            System.out.println("Your missions are: ");
//            System.out.println(viewMission.get(i) + mission.missionStatus + mission.missionPoint);
//        }
//    }
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
}
