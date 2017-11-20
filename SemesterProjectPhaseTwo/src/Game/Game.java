package Game;

import FileHandling.Logger;
import FileHandling.Save;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.HashMap;
import jdk.nashorn.internal.parser.TokenType;

/**
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 * @author Nicolai
 * @version 2006.03.30daSFT
 */
public class Game {

    /* data field with the attributes parser and currentRoom
       making them private so we only can use them in the Game class */
    private boolean hasBoardingpass = false;
    private Parser parser;
    private Room currentRoom;
    private Room airport, beach, jungle, mountain, cave, camp, raft, seaBottom;

    /* Constructor that runs the method createRooms and set our variable parser
       equal to the Parser method in the Parser class */
    public Game() {
        initGame();
        parser = new Parser();
    }

    /* Private method createRoom which means we can only use createRoom in the Game class */
 /* In the method body we set the names of the rooms, create the rooms by using the Room 
       constructor from the Room class and then set where you can move to  from the different rooms by
       using the method setExit from the Room class */
 /* The currentRoom is also given a value which is the start location = outside */
    ItemLocation itemLocation = new ItemLocation();
    Inventory inventory = new Inventory();
    Item debug = new Item("debug");
    Item campfire = new Item("Campfire", "", 2);
    Item spear = new Item("Spear", "", 2);
    Item axe = new Item("Axe", "", 2);
    Item raftCraft = new Item("Raft", "", 2);
    Mission allMissions = new Mission();
    NPC npc1 = new NPC();
    NPC npc2 = new NPC();
    NPC npc3 = new NPC();

    //file thats gonna be written to and the extension
    Logger log = new Logger();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Used to initialize different rooms and their respective items, and also
     * set the currentRoom
     */
    private void createRooms() {
//        Room airport, beach, jungle, mountain, cave, camp, raft, seaBottom;

        airport = new Room("at the airport");
        beach = new Room("at the beach");
        jungle = new Room("in the jungle");
        mountain = new Room("at the mountain");
        cave = new Room("in the cave");
        camp = new Room("in the camp");
        seaBottom = new Room("at the bottom of the sea");
        raft = new Room("building the raft");

        //Setting the the exit
        beach.setExit("north", jungle);
        beach.setExit("south", seaBottom);
        beach.setExit("west", camp);

        jungle.setExit("north", mountain);
        jungle.setExit("east", cave);
        jungle.setExit("south", beach);

        mountain.setExit("south", jungle);

        cave.setExit("west", jungle);

        camp.setExit("east", beach);
        camp.setExit("west", raft);

        seaBottom.setExit("north", beach);

        raft.setExit("east", camp);

        currentRoom = airport;

    }

    public void createItem() {
        //Initializing an item and putting it in a room airport
        itemLocation.addItem(airport, new PickableItem("Bottle", "This is a bottle that have been left behind by someone", 2));
        itemLocation.addItem(airport, new PickableItem("Boardingpass", "This is a boardingpass to get on the plane to Hawaii", 1));

        //non pickable item
        itemLocation.addItem(airport, new Item("Shop", "this is a shop", 1));

        //Initializing an item and putting it in a room beach
        itemLocation.addItem(beach, new PickableItem("Stone", "This is a stone, maybe it can be used to create something more usefull", 2));
        itemLocation.addItem(beach, new PickableItem("Fish", "Why are you inspecting this item, its GOD damn fish", 1));
        itemLocation.addItem(beach, new PickableItem("Flint", "This a flint, maybe it can be used to create something more usefull", 2));
        itemLocation.addItem(beach, new PickableItem("Rope", "This is some rope that has been washed up on the beach shore from the plane crash ", 2));
        itemLocation.addItem(beach, new PickableItem("Stick", "This is a small stick, maybe it can be used to create something more usefull", 1));

        //Initializing an item and putting it in a room jungle
        itemLocation.addItem(jungle, new PickableItem("Berry", "this is berries, maybe its poisonous try ur luck!! ", 1));
        itemLocation.addItem(jungle, new PickableItem("Lumber", "This is a log of tree, maybe it can be used to craft something to get away from this island ", 3));
        itemLocation.addItem(jungle, new PickableItem("Lian", "This is a lian from the jungle, maybe it can be used to create something more usefull", 2));
        itemLocation.addItem(jungle, new PickableItem("Stone", "This is a stone, maybe it can be used to create something more usefull", 2));
        itemLocation.addItem(jungle, new PickableItem("Stick", "This is a small stick, maybe it can be used to create something more usefull", 1));

        //Initializing an item and putting it in a room mountain
        itemLocation.addItem(mountain, new PickableItem("Stone", "This is a stone, maybe it can be used to create something more usefull", 2));
        itemLocation.addItem(mountain, new PickableItem("Egg", "This is some wild eggs, maybe it can be used for food", 1));

        //Initializing an item and putting it in a room cave
        itemLocation.addItem(cave, new PickableItem("Shroom", "these shrooms look suspecius, but maybe the can be", 1));
        itemLocation.addItem(cave, new PickableItem("Stone", "This is a stone, maybe it can be used to create something more usefull", 2));
        itemLocation.addItem(cave, new PickableItem("Freshwater", "This is freshwater found in the jungle, maybe you can drink it", 2));
        itemLocation.addItem(cave, new PickableItem("Flint", "This a flint, maybe it can be used to create something more usefull", 2));

        //Initializing an item and putting it in a room camp
        itemLocation.addItem(camp, new Item(""));

        //Initializing an item and putting it in a room seaBottom
        itemLocation.addItem(seaBottom, new PickableItem("Backpack", "This is a backpack from the plane crash maybe you can use it to carry more items ", 2));
        itemLocation.addItem(seaBottom, new PickableItem("WaterBottle", "This is a water bottle from the plan crash ", 1));
        itemLocation.addItem(seaBottom, new PickableItem("Rope", "This is some rope that has been washed up on the beach shore from the plane crash", 2));

    }

    public void createMissions() {

        //Creating missions and putting and putting them in the game
        allMissions.addMission(airport, "First mission", "Find your boardingpass", 100);
        allMissions.addMission(airport, "First item", "Picking up your first item", 100);
        allMissions.addMission(beach, "Find food to survive", "Pick up food", 100);
        allMissions.addMission(jungle, "Find survivors", "Get into contact with other survivors", 100);
        allMissions.addMission(cave, "Get high", "Eat the shrooms", 100);
    }

    public void createNPC() {
        //create the good npc
        npc1.setName("BS Christiansen");
        npc1.setCurrentRoom(jungle);
        npc1.setDescribtion("The survivor of the plane crash look to be some kind of veteran soldier, but he is heavly injured on his right leg so he cant move ");
        npc1.addDialog("If you want to survive on this GOD forsaken island, you must first find food and shelter."
                + "You can craft items to help you survive, if you have the right components");

        //create the bad npc
        npc3.setName("Joseph Schitzel");
        npc3.setCurrentRoom(mountain);
        npc3.setDescribtion("A lonely surviver with very filthy hair, and a wierd smell of weinerschnitzel.");
        npc3.addDialog("Heeelloooo there my freshlooking friend, I am Joseph Schitzel, if you scratch my back I might scratch your's." + "\n" + "Come on, fetch me some eggs!");

        //create another npc
        npc2.setName("Mysterious crab");
        npc2.setCurrentRoom(cave);
        npc2.setDescribtion("A mysterious crab that you dont really get why can talk");
        npc2.addDialog("MUHAHAHA i'm the finest and most knowledgeable crab of them all mr.Crab and know this island like the back of my hand.... oh i mean claw"
                + "\n SO if you want the rarest item you can find on this island, you must first help me find some stuff " + "\n"
                + "If you answer my very cool questions correctly, you will get an awesome unique reward, hehehe!");
    }

    //Initializing game
    public void initGame() {
        
        createRooms();
        createItem();
        createMissions();
        createNPC();


    }

    /* A method that is initialized when we start the game, that first print out a message with the printWelcome method  
       and then checks if the game is finished or not with a while loop where finished is set to false when the game start*/
    public void play() throws FileNotFoundException, IOException, Throwable {
        printWelcome();
        log.write(System.lineSeparator() + System.lineSeparator() + " >>>  Starting new game <<< " + System.lineSeparator() + System.lineSeparator());

        boolean finished = false;

        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }

        System.out.println("Thank you for playing.  Good bye.");
        //added to shutdown
        System.exit(0);
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
    private boolean processCommand(Command command) throws FileNotFoundException, IOException, Throwable {
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
        } else if (commandWord == commandWord.CRAFT) {
            craftItem(command);
        }

        //setting the condition to complete the missions.
        if (inventory.getInventory().containsKey("Boardingpass")) {
            allMissions.setMissionComplete("First mission");
        }

        if (inventory.getInventory().containsKey("Boardingpass")) {
            allMissions.setMissionComplete("First item");
        }

        if (inventory.getInventory().containsKey("Bottle")) {
            allMissions.setMissionComplete("First item");
        }

        if (inventory.getInventory().containsKey("Fish")) {
            allMissions.setMissionComplete("Find food to survive");
        }

        if (CommandWord.TALK == commandWord) {
            allMissions.setMissionComplete("Find survivors");
        }

        if (currentRoom == airport) {
            lockRoom(command);
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
            if (addToInventory instanceof PickableItem) {
                System.out.println("Item has been added to inventory: " + addToInventory.getName());
                inventory.addItemInInventory(addToInventory);
                currentRoomItem.remove(indexItem);
                itemLocation.setItem(currentRoom, currentRoomItem);
            } else {
                System.out.println("You can't pickup this item");
            }

        } else {
            System.out.println("could not find " + command.getSecondWord());
        }
    }

    private void TalkTo() {
        if (npc1.getCurrentRoom() == currentRoom) {
            System.out.println(npc1.getDescribtion() + ", yet he still gives u good advice:\n" + npc1.getDialog(0));
        } else if (npc2.getCurrentRoom() == currentRoom && inventory.getInventory().containsKey("Shroom")) {
            System.out.println(npc2.getDescribtion() + "\n" + npc2.getDialog(0));
            pregnant();
        } else if (npc3.getCurrentRoom() == currentRoom) {
            System.out.println(npc3.getDescribtion() + "\n" + npc3.getDialog(0));
        } else {
            System.out.println("There is nobody to communicate with in this Room");
        }

    }

    //Create a question
    public void pregnant() {
        Scanner scan = new Scanner(System.in); //Creates a new scanner
        System.out.println("Are u pregnant?"); //Asks question
        String input = scan.nextLine(); //Waits for input
        if (input.equalsIgnoreCase("yes")) {
            System.out.println("Great! congratulations");
        } else if (input.equalsIgnoreCase("no")) {
            System.out.println("Keep trying it will happen.");
        } else { //If the input is anything else
            System.out.println("This is a yes or no question.");
        }

    }

    public void craftItem(Command command) {

        String craft = command.getSecondWord();
        if (craft.equalsIgnoreCase("Recipe")) {
            System.out.println("This is a list of the games craftable items and their recipes:");
            System.out.println("Campfire: Lumber, Stick and Flint" + "\n" + "Spear: Stick, Fint and Rope or Lian" + "\n" + "Axe: Stick, Stone and Rope or Lian" + "\n" + "Raft: Lumber, Stick and Rope or Lian");
        } else if (craft.equalsIgnoreCase("Campfire") && inventory.getInventory().containsKey("Lumber") && inventory.getInventory().containsKey("Stick") && inventory.getInventory().containsKey("Flint")) {
            inventory.addItemInInventory(campfire);
            inventory.dropItemInventory("Lumber");
            inventory.dropItemInventory("Stick");
            inventory.dropItemInventory("Flint");
            System.out.println("A Campfire is added to your inventory");
        } else if (craft.equalsIgnoreCase("Spear") && inventory.getInventory().containsKey("Stick") && inventory.getInventory().containsKey("Flint")
                && (inventory.getInventory().containsKey("Lian") || inventory.getInventory().containsKey("Rope"))) {
            inventory.addItemInInventory(spear);
            inventory.dropItemInventory("Stick");
            inventory.dropItemInventory("Flint");
            System.out.println("A Spear is added to your inventory");
            if (inventory.getInventory().containsKey("Rope")) {
                inventory.dropItemInventory("Rope");
            } else {
                inventory.dropItemInventory("Lian");
            }
        } else if (craft.equalsIgnoreCase("Axe") && inventory.getInventory().containsKey("Stick") && inventory.getInventory().containsKey("Stone")
                && (inventory.getInventory().containsKey("Lian") | inventory.getInventory().containsKey("Rope"))) {
            inventory.addItemInInventory(axe);
            inventory.dropItemInventory("Stick");
            inventory.dropItemInventory("Stone");
            System.out.println("An axe is added to your inventory");
            if (inventory.getInventory().containsKey("Rope")) {
                inventory.dropItemInventory("Rope");
            } else {
                inventory.dropItemInventory("Lian");
            }

        } else if (craft.equalsIgnoreCase("Raft") && inventory.getInventory().containsKey("Lumber") && inventory.getInventory().containsKey("Stick")
                && (inventory.getInventory().containsKey("Lian") | inventory.getInventory().containsKey("Rope"))) {
            inventory.addItemInInventory(spear);
            inventory.dropItemInventory("Lumber");
            inventory.dropItemInventory("Stick");
            System.out.println("A raft is added to your inventory");
            if (inventory.getInventory().containsKey("Rope")) {
                inventory.dropItemInventory("Rope");
            } else {
                inventory.dropItemInventory("Lian");
            }
        } else {
            System.out.println("You can not craft that Item.");
        }
    }

    /* 
    * Method used for showing missions
     */
    private void showMissions() {

        HashMap<String, String> viewMission = allMissions.missionInfo;
        HashMap<String, Boolean> missionStatus = allMissions.missionStatus;

        System.out.println("Your missions are: ");
        System.out.println("");

        for (String i : viewMission.keySet()) {

            System.out.printf(viewMission.get(i) + "| The mission is complete: " + missionStatus.get(i) + "\n");

            if (missionStatus.get(i) == false) {
                System.out.println("Mission is in progress.");
            }

            if (missionStatus.get(i) == true) {
                System.out.println("Mission is complete");
            }
            System.out.println("");
        }

        for (String i : missionStatus.keySet()) {
            missionStatus.get(i);
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
            itemLocation.addItem(currentRoom, new Item(indexItem));

        } else {
            System.out.println("Can't drop item that isn't in inventory " + command.getSecondWord());
        }

    }

    //set it so you cant go to the beach before you have the boardingpass
    private void lockRoom(Command command) {
//        System.out.println(inventory.getInventory().containsKey("Boardingpass"));
//        System.out.println(command.getSecondWord());
//        System.out.println(command.getCommandWord().name());
        if (command.getCommandWord().name().equalsIgnoreCase(CommandWord.GO.toString())) {
            if (inventory.getInventory().containsKey("Boardingpass") == false && command.getSecondWord().equalsIgnoreCase("west")) {

                airport.setExit("west", airport);

                System.out.println("You have no boardingpass, please return when you do!!!");

            }
//            else {
//                airport.setExit("west", beach);
//                System.out.println("Exits: west");
        }
        if (inventory.getInventory().containsKey("Boardingpass") && !hasBoardingpass) {
            hasBoardingpass = true;
            airport.setExit("west", beach);
            System.out.println("Exits: west");
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

    private void saveGame() throws IOException, Throwable {
        Save save = new Save("01");
        save.addToSaveGame(objectsToSave());
        save.saveGame();
    }

    private String objectsToSave() {
        ArrayList saveObjectsJSON;
        saveObjectsJSON = new ArrayList(10);
        saveObjectsJSON.add(inventory);
        saveObjectsJSON.add(itemLocation);
        saveObjectsJSON.add(allMissions);
        //saveObjectsJSON.add(npc1);
        //saveObjectsJSON.add(npc2);
        //saveObjectsJSON.add(npc3);

        return gson.toJson(saveObjectsJSON);
    }
}
