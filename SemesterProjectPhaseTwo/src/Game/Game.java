package Game;

import Data.DataFacade;
import Data.Save;
import com.google.gson.internal.LinkedTreeMap;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game {

    /* data field with the attributes parser and currentRoom
       making them private so we only can use them in the Game class */
    private static String GUIoption;
    private static boolean usingGui = false;
    private static boolean hasBoardingpass = false;
    private static Parser parser;
    private static Room currentRoom;
    private static Room airport, beach, jungle, mountain, cave, camp, seaBottom;
    private static boolean hasTalkedWithEvilGuy = false;
    private static DataFacade data = new DataFacade();
    private static ItemLocation itemLocation = new ItemLocation();
    private static Inventory inventory = new Inventory();
    private static CraftableItem craftableItem = new CraftableItem();
    private static Player player = new Player("Player", "???", currentRoom, 100, 100);
    private static Score score = new Score();
    private static Item debug = new Item("debug");
    private static Item campfire = new CraftableItem("Campfire", "Campfire: Lumber, Stick and Flint", 3);
    private static Item spear = new CraftableItem("Spear", "Spear: Stick, Flint and Rope or Lian", 3);
    private static Item axe = new CraftableItem("Axe", "Axe: Stick, Stone and Rope or Lian", 3);
    private static Item raft = new CraftableItem("Raft", "Raft: Lumber, Stick and Rope or Lian", 3);
    private static Mission allMissions = new Mission();
    private static NPC BSChristiansen = new NPC();
    private static NPC mysteriousCrab = new NPC();
    private static NPC josephSchnitzel = new NPC();

    /* Constructor that runs the method createRooms and set our variable parser
       equal to the Parser method in the Parser class */
    public Game() {
        parser = new Parser();
    }

    public boolean getExitBool(String direction) {

        return (currentRoom.getExit(direction) != null);
    }
    


    //file thats gonna be written to and the extension
    /**
     * Used to initialize different rooms and their respective items, and also
     * set the currentRoom
     */
    private static void createRooms() {
//      Room airport, beach, jungle, mountain, cave, camp, raft, seaBottom;

        airport = new Room("airport");
        beach = new Room("beach");
        jungle = new Room("jungle");
        mountain = new Room("mountain");
        cave = new Room("cave");
        camp = new Room("camp");
        seaBottom = new Room("seabottom");

        //Setting the the exits in different rooms
        beach.setExit("north", jungle);
        beach.setExit("south", seaBottom);
        beach.setExit("west", camp);

        jungle.setExit("north", mountain);
        jungle.setExit("east", cave);
        jungle.setExit("south", beach);

        mountain.setExit("south", jungle);

        cave.setExit("west", jungle);

        camp.setExit("east", beach);

        seaBottom.setExit("north", beach);

        // Starting room
        currentRoom = airport;
    }

    /**
     * Initializes pickable & non pickable items and adding them to rooms
     */
    public static void createItem() {
        //Initializing an item and putting it in a room airport
        itemLocation.addItem(airport, new PickableItem("Bottle", "This is a bottle that have been left behind by someone", 2, false));
        itemLocation.addItem(airport, new PickableItem("Boardingpass", "This is a boardingpass to get on the plane to Hawaii: 126AB", 1, false));

        //Initializing an item and putting it in a room beach
        itemLocation.addItem(beach, new PickableItem("Stone", "This is a stone, maybe it can be used to create something more usefull", 2, false));
        itemLocation.addItem(beach, new PickableItem("Fish", "Why are you inspecting this item, its GOD damn fish", 1, true));
        itemLocation.addItem(beach, new PickableItem("Fish", "Why are you inspecting this item, its GOD damn fish", 1, true));
        itemLocation.addItem(beach, new PickableItem("Flint", "This a flint, maybe it can be used to create something more usefull", 2, false));
        itemLocation.addItem(beach, new PickableItem("Rope", "This is some rope that has been washed up on the beach shore from the plane crash ", 2, false));
        itemLocation.addItem(beach, new PickableItem("Stick", "This is a small stick, maybe it can be used to create something more usefull", 1, false));
        itemLocation.addItem(beach, new PickableItem("Stick", "This is a small stick, maybe it can be used to create something more usefull", 1, false));
        //non pickable item
        itemLocation.addItem(beach, new Item("GiantRock", "The giant rock dont look like it can be moved", 100));
        itemLocation.addItem(beach, new Item("GiantLog", "The giant log dont look like it can be moved", 100));

        //Initializing an item and putting it in a room jungle
        itemLocation.addItem(jungle, new PickableItem("Berry", "this is berries, maybe its poisonous try ur luck!! ", 1, true));
        itemLocation.addItem(jungle, new PickableItem("Berry", "this is berries, maybe its poisonous try ur luck!! ", 1, true));
        itemLocation.addItem(jungle, new PickableItem("Lumber", "This is a log of tree, maybe it can be used to craft something to get away from this island ", 3, false));
        itemLocation.addItem(jungle, new PickableItem("Lian", "This is a lian from the jungle, maybe it can be used to create something more usefull", 2, false));
        itemLocation.addItem(jungle, new PickableItem("Lian", "This is a lian from the jungle, maybe it can be used to create something more usefull", 2, false));
        itemLocation.addItem(jungle, new PickableItem("Stone", "This is a stone, maybe it can be used to create something more usefull", 2, false));
        itemLocation.addItem(jungle, new PickableItem("Stick", "This is a small stick, maybe it can be used to create something more usefull", 1, false));
        //non pickable item
        itemLocation.addItem(jungle, new Item("GiantLog", "The giant log dont look like it can be moved", 100));

        //Initializing an item and putting it in a room mountain
        itemLocation.addItem(mountain, new PickableItem("Stone", "This is a stone, maybe it can be used to create something more usefull", 2, false));
        itemLocation.addItem(mountain, new PickableItem("Egg", "This is some wild eggs, maybe it can be used for food", 1, true));
        itemLocation.addItem(mountain, new PickableItem("Lumber", "This is a log of tree, maybe it can be used to craft something to get away from this island ", 3, false));

        //Initializing an item and putting it in a room cave
        itemLocation.addItem(cave, new PickableItem("Shroom", "these shrooms look suspecius, but maybe the can be", 1, true));
        itemLocation.addItem(cave, new PickableItem("Stone", "This is a stone, maybe it can be used to create something more usefull", 2, false));
        itemLocation.addItem(cave, new PickableItem("Stone", "This is a stone, maybe it can be used to create something more usefull", 2, false));
        itemLocation.addItem(cave, new PickableItem("Waterbottle", "This is freshwater found in the jungle, maybe you can drink it", 2, true));
        itemLocation.addItem(cave, new PickableItem("Flint", "This a flint, maybe it can be used to create something more usefull", 2, false));
        itemLocation.addItem(cave, new Item("GiantRock", "The giant rock dont look like it can be moved", 100));

        //Initializing an item and putting it in a room camp
        itemLocation.addItem(camp, new PickableItem("Stone", "This is a stone, maybe it can be used to create something more usefull", 2, false));
        itemLocation.addItem(camp, new PickableItem("Stick", "This is a small stick, maybe it can be used to create something more usefull", 1, false));
        itemLocation.addItem(camp, new PickableItem("Egg", "This is some wild eggs, maybe it can be used for food", 1, true));

        //Initializing an item and putting it in a room seaBottom
        itemLocation.addItem(seaBottom, new PickableItem("Backpack", "This is a backpack from the plane crash maybe you can use it to carry more items ", 0, false));
        itemLocation.addItem(seaBottom, new PickableItem("WaterBottle", "This is a water bottle from the plan crash ", 1, true));
        itemLocation.addItem(seaBottom, new PickableItem("Rope", "This is some rope that has been washed up on the beach shore from the plane crash", 2, false));
    }

    /**
     * Crafted items added to ArrayList
     */
    public static void putCraftableItemInArrayList() {

        craftableItem.addItemToCraftableList(campfire);
        craftableItem.addItemToCraftableList(raft);
        craftableItem.addItemToCraftableList(axe);
        craftableItem.addItemToCraftableList(spear);
    }
    
    public CraftableItem getCraftableItemList(){
        return craftableItem;
    } 

    /**
     * Creating missions and putting and putting them in the game
     */
    public static void createMissions() {
        allMissions.addMission(airport, "Find your boardingpass");
        allMissions.addMission(airport, "Picking up first item");
        allMissions.addMission(beach, "Pick up food");
        allMissions.addMission(jungle, "Find survivors");
        allMissions.addMission(cave, "Eat the shrooms");
        allMissions.addMission(camp, "Escape the island");
    }

    /**
     * Initializing NPCs, with name, current room position, desribtion and
     * dialog
     */
    public static void createNPC() {
        //create the good npc
        BSChristiansen.setName("BS_Christiansen");
        BSChristiansen.setCurrentRoom(jungle);
        BSChristiansen.setDescription("The survivor of the plane crash look to be some kind of veteran soldier, "
                + "\nbut he is heavly injured on his right leg so he cant move ");
        BSChristiansen.addDialog("If you want to survive on this GOD forsaken island, you \nmust first find food and shelter."
                + "\nYou can craft items to help you survive, if you \nhave the right components.");
        BSChristiansen.addDialog("To escape the island you need a raft, \nsome berries and fish so you can survive on the sea,\nand a spear so you can hunt for more food");

        //create the bad npc
        josephSchnitzel.setName("Joseph_Schnitzel");
        josephSchnitzel.setCurrentRoom(mountain);
        josephSchnitzel.setDescription("A lonely surviver with very filthy hair, and a wierd smell of weinerschnitzel.");
        josephSchnitzel.addDialog("Heeelloooo there my freshlooking friend, I am Joseph\nSchnitzel, if you scratch my back I might scratch your's." + "\n" + "Go fetch me some eggs, or I'll kill you");
        josephSchnitzel.addDialog("Talks to himself\nis that muppet still alive");
        josephSchnitzel.addDialog("Talks to himself\nHow long is he going to last");
        josephSchnitzel.addDialog("Talks to himself\nI wonder what those noises were ing the cave");
        josephSchnitzel.addDialog("GET THE HELL OUT OF MY WAY!!!");
        josephSchnitzel.setDamageValue(100);

        //create another npc
        mysteriousCrab.setName("Mysterious_Crab");
        mysteriousCrab.setCurrentRoom(cave);
        mysteriousCrab.setDescription("A mysterious crab that you dont really get why can talk");
        mysteriousCrab.addDialog("MUHAHAHA i'm the finest and most knowledgeable \ncrab of them all mr.Crab and know this island\nlike the back of my hand! oh i mean claw..."
                + "\nA Random fact: "
                + "to escape this island you need a raft, \nsome berries and fish so you can survive on the sea,\nand a spear so you can hunt for more food");
    }

    /**
     * method is used to store all the npcs in a hashmap
     *
     * @return HashMap containing all NPCs
     */
    public HashMap<String, String> storeNPC() {
        HashMap<String, String> npcMap = new HashMap<>();
        npcMap.put(BSChristiansen.getName(), BSChristiansen.getCurrentRoom().getShortDescription());
        npcMap.put(mysteriousCrab.getName(), mysteriousCrab.getCurrentRoom().getShortDescription());
        npcMap.put(josephSchnitzel.getName(), josephSchnitzel.getCurrentRoom().getShortDescription());
        return npcMap;
    }
    
    static HashMap<String, NPC> NPCFromName() {
        HashMap<String, NPC> npcMap = new HashMap<>();
        npcMap.put(BSChristiansen.getName(), BSChristiansen);
        npcMap.put(mysteriousCrab.getName(), mysteriousCrab);
        npcMap.put(josephSchnitzel.getName(), josephSchnitzel);
        return npcMap;
    }

    /**
     * Initializing Game
     */
    public void initGame() {
        hasBoardingpass = false;
        hasTalkedWithEvilGuy = false;
        data = new DataFacade();
        itemLocation = new ItemLocation();
        inventory = new Inventory();
        player = new Player("Player", "???", currentRoom, 100, 100);
        score = new Score();
        allMissions = new Mission();
        BSChristiansen = new NPC();
        mysteriousCrab = new NPC();
        josephSchnitzel = new NPC();
        craftableItem = new CraftableItem();
        createRooms();
        createItem();
        createMissions();
        createNPC();
        putCraftableItemInArrayList();
    }

    /**
     * Method that handles NPC movement
     */
    static private void npcPath() {

        if (Time.secondsPassed % 45 == 44) {
            Random picker = new Random();
           while (true) {
                if (josephSchnitzel.getCurrentRoom().equals(mountain)) {
                    String[] newRoomString = {"south"};
                    int index = picker.nextInt(newRoomString.length);
                    Room next = josephSchnitzel.getCurrentRoom().getExit(newRoomString[index]);
                    josephSchnitzel.setCurrentRoom(next);
                    break;
                }

                if (josephSchnitzel.getCurrentRoom().equals(beach)) {
                    String[] newRoomString = {"north"};
                    int indexOfNewRoomString = picker.nextInt(newRoomString.length);
                    Room nextRoom = josephSchnitzel.getCurrentRoom().getExit(newRoomString[indexOfNewRoomString]);
                    josephSchnitzel.setCurrentRoom(nextRoom);
                    break;
                }

                if (josephSchnitzel.getCurrentRoom().equals(jungle)) {
                    String[] newRoomString = {"north", "south"};
                    int indexOfNewRoomString = picker.nextInt(newRoomString.length);
                    Room nextRoom = josephSchnitzel.getCurrentRoom().getExit(newRoomString[indexOfNewRoomString]);
                    josephSchnitzel.setCurrentRoom(nextRoom);
                    break;
                }
            }
        }
    }

    /**
     * Method is initialized when game is run. Runs welcomemessage Contains a
     * while loop that checks if the game is finished. The condition is set to
     * false when the game starts.
     *
     * @throws FileNotFoundException
     * @throws IOException
     * @throws Throwable
     */
    public void play() throws FileNotFoundException, IOException, Throwable {
        Time time = new Time();
        initGame();
        printWelcome();
        time.start();
        data.logWrite(System.lineSeparator() + System.lineSeparator() + " >>>  Starting new game <<< " + System.lineSeparator() + System.lineSeparator());

        boolean finished = false;

        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        time.stopTime();
        System.out.println("Thank you for playing.  Good bye." + "\n" + "You spend: " + Time.getSecondsPassed() + " seconds playing the game");
        //added to shutdown
        System.exit(0);
    }

    /* A method that is used in the play method to print a message when you start the game */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the game Stranded!");
        System.out.println("Stranded is an adventure game, where you are to "
                + "find out how to escape the island");
        System.out.println("");
        System.out.println("The first step on the journey is to find your lost boardingpass, that has the numeber 126AB, ");
        System.out.println("and then board the plane, to get your well deserved long and safe vacation...");
        System.out.println("Good luck");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * A method that set wantToQuit to false and the run a if loop that that
     * does so everytime the commandWord is not know to the game it print out
     * the message "I don't know what you mean..." and return false It does the
     * same with Help and GO where it print out a message with the use of the
     * method printHelp and goRoom and if the command word is quit it return
     * wantToQuit
     *
     * @param command process command
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @throws Throwable
     */
     boolean processCommand(Command command) throws FileNotFoundException, IOException, Throwable {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        data.logWrite(commandWord.toString() + " " + command.getSecondWord());

        if (commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        } else if (commandWord == CommandWord.GO) {
            goRoom(command);
        } else if (commandWord == CommandWord.SHOW) {
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
            data.saveGame(objectsToSave());
        } else if (commandWord == CommandWord.LOAD) {
            loadGame();
        } else if (commandWord == CommandWord.CRAFT) {
            craftItem(command);
        } else if (commandWord == CommandWord.WIN) {
            win();
        } else if (commandWord == CommandWord.LOSE) {
            lose();
        } else if (commandWord == CommandWord.ESCAPE) {
            UnlockedEscapeTheIslandWin();
            lockedEscapeIsland();
        } else if (commandWord == CommandWord.USE) {
            useItem(command);
        }

        //setting the condition to complete the missions.
        if (inventory.getInventory().containsKey("Boardingpass")) {
            allMissions.setMissionComplete("Find your boardingpass");
        }

        if (inventory.getInventory().containsKey("Boardingpass")) {
            allMissions.setMissionComplete("Picking up first item");
        }

        if (inventory.getInventory().containsKey("Bottle")) {
            allMissions.setMissionComplete("Picking up first item");
        }

        if (inventory.getInventory().containsKey("Fish")) {
            allMissions.setMissionComplete("Pick up food");
        }

        if (inventory.getInventory().containsKey("Berry")) {
            allMissions.setMissionComplete("Pick up food");
        }

        if (CommandWord.TALK == commandWord) {
            allMissions.setMissionComplete("Find survivors");
        }

        if (inventory.getInventory().containsKey("raft")) {
            allMissions.setMissionComplete("craft item to escape the island");
        }

        if (inventory.getInventory().containsKey("Backpack")) {
            inventory.setInventoryMaxWeight(25);
        }

        while (inventory.getInventory().containsKey("Raft") && inventory.getInventory().containsKey("Berry") && inventory.getInventory().containsKey("Fish")
                && inventory.getInventory().containsKey("Spear")) {
            allMissions.setMissionComplete("Escape the island");

            if (currentRoom != beach) {
                System.out.println("");
                System.out.println("You can escape the island now");
                System.out.println("when you are ready to do so, go to the beach and use the command escape");
                System.out.println("");
            }

            if (currentRoom == beach) {
                System.out.println("");
                System.out.println("You are now at the beach and can use the command escape");
            }
            break;
        }

        loseCondition();

        npcPath();
        if (currentRoom == airport) {
            lockRoom(command);
        }

        return wantToQuit;
    }

    /**
     *
     * @return Method that returns help message when using the help command
     */
    public String printHelp() {
        String helpDialog = "You are lost. You are alone." + "\n" + "You wander around on this" + "\n" + "god forsaken island."
                + "\n" + "\n" + "You move around using" + "\n" + "W A S D or the arrows";
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around on this god forsaken island.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
        return helpDialog;
    }

    /**
     * method that is initializing everytime you use the command "go" and print
     * the message "Go where"
     *
     * @param command to go to room
     */
    static void goRoom(Command command) throws InterruptedException {
        if (currentRoom == airport) {
            lockRoom(command);
        }
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);
        player.setCurrentRoom(nextRoom);
        /* loop that when next room is equel to null it print a message that says "there is no door" 
           but if nextRoom is not equel null set currentRoom to nextRoom and print the description of the new room*/
        if (nextRoom == null) {
            System.out.println("There is no path!");
        } else {
            currentRoom = nextRoom;
            System.out.println(player.getCurrentRoom().getLongDescription());

        }

        forceDialog();
    }

// Method used for showing contents in inventory
//    private void showInventory(Command command) {
    /**
     * Method that runs through Inventory HashMap and prints content
     */
    static private void showInventory() {

        HashMap<String, Integer> inventoryHM = inventory.getInventory();

        System.out.println("Items in inventory is: " + inventory.getCurrentInventoryWeight() + "/" + inventory.getInventoryMaxWeight());
        for (String i : inventoryHM.keySet()) {
            System.out.println(inventoryHM.get(i) + "x" + i);
        }
    }

    /**
     * Method used for inspecting room Detects prints info on both items and
     * NPCs present in the room
     */
    static private void inspectRoom() {
        ArrayList items = itemLocation.getItems(currentRoom);
        Item seeItem;
        String itemList = "";
        for (int i = 0; i < items.size(); i++) {

            seeItem = (Item) items.get(i);
            itemList += seeItem.getName();
            if (i < items.size() - 1) {
                itemList = itemList + ", ";
            }
        }
        System.out.println(itemList);
        int currentNPCsInRoom = 0;

        if (BSChristiansen.getCurrentRoom() == currentRoom) {
            System.out.println("There seems to be someone resting in the leaves");
            currentNPCsInRoom++;
        }

        if (mysteriousCrab.getCurrentRoom() == currentRoom) {
            System.out.println("You sense somebody in the cave");
            currentNPCsInRoom++;
        }

        if (josephSchnitzel.getCurrentRoom() == currentRoom) {
            System.out.println("There is an intense smell, somebody seems to be near!");
            currentNPCsInRoom++;
        }
        if (currentNPCsInRoom == 0) {
            System.out.println("You are alone in the room");
        }
    }

    /**
     * @param command used for takeing items and adding them to inventory it
     * checks if the item exits in current room and if its pickable or
     * nonpickable
     * @return boolean, whether if you can or cannot take item
     */
    public static boolean takeItem(Command command) {
        ArrayList currentRoomItem = itemLocation.getItems(currentRoom);
        Item seeItem;
        int indexItem = -1;
        Item addToInventory = null;

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
                if (inventory.addItemInInventory(addToInventory) == true) {
                    System.out.println("Item has been added to inventory: " + addToInventory.getName());
                    currentRoomItem.remove(indexItem);
                    return true;
                }
                itemLocation.setItem(currentRoom, currentRoomItem);
                return false;
            } else {
                System.out.println("You can't pickup this item");
                return false;
            }

        } else {
            System.out.println("could not find " + command.getSecondWord());
            return false;
        }
    }

    /**
     * Method that checks if a NPC is present in current room. If yes, it prints
     * the dialog. If no, it prints that no one is present
     */
    static private void TalkTo() {
        if (BSChristiansen.getCurrentRoom() == currentRoom) {
            System.out.println(BSChristiansen.getDescription() + ", yet he still gives you good advice:\n");
            System.out.println(BSChristiansen.getDialog(0));
            System.out.println("");
            woundedSurvivor();
        } else if (mysteriousCrab.getCurrentRoom() == currentRoom && inventory.getInventory().containsKey("Shroom")) {
            System.out.println(mysteriousCrab.getDescription() + "\n" + mysteriousCrab.getDialog(0));
            pregnant();
        } else {
            System.out.println("There is nobody to communicate with in this Room");
        }

    }

    static boolean forcedTextBox = false;
    
    public boolean getForcedTextBox(){
        return forcedTextBox;
    }
    
    /**
     * Method: force dialog with josephSchnitzel, if player is in same room
     */
    static private void forceDialog() throws InterruptedException {
        if (hasTalkedWithEvilGuy == false) {
            if (josephSchnitzel.getCurrentRoom() == currentRoom) {
                forcedTextBox = true;
                System.out.println(josephSchnitzel.getDescription() + "\n" + josephSchnitzel.getDialog(0, false));
                evilGuyDialog();
                hasTalkedWithEvilGuy = true;
            }
        }
    }

    public static void setHasTalkedToEvilGuy(boolean bool) {
        hasTalkedWithEvilGuy = bool;
    }

    /**
     * Method that contains dialog for NPC1 (BS Christiansen)
     */
    static public void woundedSurvivor() {
        System.out.println("Maybe you could bring me some food and something to defend myself now that i cant move");
        System.out.println("Do you want to accept his mission: Yes or no");
        Scanner scan = new Scanner(System.in); //Creates a new scanner
        String input = scan.nextLine(); //Waits for input
        if (input.equalsIgnoreCase("yes")) {
            System.out.println("You got a mission, please use the show command for more information");
            allMissions.addMission(jungle, "Helping the injured survivor");
        } else if (input.equalsIgnoreCase("no")) {
            System.out.println("Come back again if you change your mind");
        } else {
            System.out.println("Come back again if you change your mind");
        }
    }

    /**
     * Method that creates question when talking with mysteriousCrab (crab)
     */
    static public void pregnant() {
        Scanner scan = new Scanner(System.in);//Creates a new scanner
        Thread thread = new Thread() {
            @Override
            public void run() {
                input = "";
                while (!(input.equals("yes") || input.equals("no"))) {
                    input = getOption();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (input.equalsIgnoreCase("yes")) {
                    System.out.println("You got a mission, please use the show command for more information");
                    allMissions.addMission(josephSchnitzel.getCurrentRoom(), "Get me some eggs or I will kill you!!!!");
                    System.out.println("You survived snitzel this time, but take care: " + player.getHealth());
                } else if (input.equalsIgnoreCase("no")) {
                    System.out.println("");
                    player.loseHealth(josephSchnitzel.getDamageValue());
                }

            }
        };
        thread.start();
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

    static String getOption() {
        return GUIoption;
    }

    public  void setOption(String opt) {
        GUIoption = opt;
    }
    /**
     * Method that contains a short dialog when talking to josephSchnitzel
     * (Joseph Schnitzel)
     */
    static String input = "";

    static public void evilGuyDialog() throws InterruptedException {

        Scanner scan = new Scanner(System.in); //Creates a new scanner
        if (GUIoption == null) {
            GUIoption = "";
        }
        if (usingGui) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    while (!(input.equals("yes") || input.equals("no"))) {
                        input = getOption();
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (input.equalsIgnoreCase("yes")) {
                        System.out.println("You got a mission, please use the show command for more information");
                        allMissions.addMission(josephSchnitzel.getCurrentRoom(), "Get me some eggs or I will kill you!!!!");
                        System.out.println("You survived snitzel this time, but take care: " + player.getHealth());
                    } else if (input.equalsIgnoreCase("no")) {
                        System.out.println("");
                        player.loseHealth(josephSchnitzel.getDamageValue());
                    }
                    forcedTextBox = false;
                    setHasTalkedToEvilGuy(true);
                }
            };
            thread.start();
        } else {
            input = scan.nextLine(); //Waits for input
            if (input.equalsIgnoreCase("yes")) {
                System.out.println("You got a mission, please use the show command for more information");
                allMissions.addMission(josephSchnitzel.getCurrentRoom(), "Get me some eggs or I will kill you!!!!");
                System.out.println("You survived snitzel this time, but take care: " + player.getHealth());
            } else if (input.equalsIgnoreCase("no")) {
                System.out.println("");
                player.loseHealth(josephSchnitzel.getDamageValue());
            }
        }

    }

    /**
     *
     * @param command for crafting items the method checks typing the command +
     * a second word (item name) then checks whether inventory contains the item
     * you need to craft the wanted craftitem. If yes, the craftitem is added to
     * inventory and the items used is removed. If no, crafting is unsuccessful.
     */
    public void craftItem(Command command) {
        if (command.hasSecondWord()) {
            String craft = command.getSecondWord();
            if (craft.equalsIgnoreCase("Recipe")) {
                System.out.println("This is a list of the games craftable items and their recipes:");
                System.out.println("Campfire: Lumber, Stick and Flint" + "\n" + "Spear: Stick, Fint and Rope or Lian" + "\n" + "Axe: Stick, Stone and Rope or Lian" + "\n" + "Raft: Lumber, Stick and Rope or Lian");
            } else if (craft.equalsIgnoreCase("Campfire") && inventory.getInventory().containsKey("Lumber") && inventory.getInventory().containsKey("Stick") && inventory.getInventory().containsKey("Flint")) {
                inventory.removeItemInventory("Lumber");
                inventory.removeItemInventory("Stick");
                inventory.removeItemInventory("Flint");
                if (craftableItem.craftableListArray.contains(campfire)) {
                    inventory.addItemInInventory(campfire);
                    System.out.println("A Campfire is added to your inventory");
                }

            } else if (craft.equalsIgnoreCase("Spear") && inventory.getInventory().containsKey("Stick") && inventory.getInventory().containsKey("Flint")
                    && (inventory.getInventory().containsKey("Lian") || inventory.getInventory().containsKey("Rope"))) {
                inventory.removeItemInventory("Stick");
                inventory.removeItemInventory("Flint");
                if (inventory.getInventory().containsKey("Rope")) {
                    inventory.removeItemInventory("Rope");
                } else {
                    inventory.removeItemInventory("Lian");
                }
                if (craftableItem.craftableListArray.contains(spear)) {
                    inventory.addItemInInventory(spear);
                    System.out.println("A Spear is added to your inventory");
                }

            } else if (craft.equalsIgnoreCase("Axe") && inventory.getInventory().containsKey("Stick") && inventory.getInventory().containsKey("Stone")
                    && (inventory.getInventory().containsKey("Lian") | inventory.getInventory().containsKey("Rope"))) {
                inventory.removeItemInventory("Stick");
                inventory.removeItemInventory("Stone");
                if (inventory.getInventory().containsKey("Rope")) {
                    inventory.removeItemInventory("Rope");
                } else {
                    inventory.removeItemInventory("Lian");
                }
                if (craftableItem.craftableListArray.contains(axe)) {
                    inventory.addItemInInventory(axe);
                    System.out.println("An axe is added to your inventory");
                }

            } else if (craft.equalsIgnoreCase("Raft") && inventory.getInventory().containsKey("Lumber") && inventory.getInventory().containsKey("Stick")
                    && (inventory.getInventory().containsKey("Lian") || inventory.getInventory().containsKey("Rope"))) {
                inventory.removeItemInventory("Lumber");
                inventory.removeItemInventory("Stick");
                if (inventory.getInventory().containsKey("Rope")) {
                    inventory.removeItemInventory("Rope");
                } else {
                    inventory.removeItemInventory("Lian");
                }
                if (craftableItem.craftableListArray.contains(raft)) {
                    inventory.addItemInInventory(raft);
                    System.out.println("Raft has been added to inventory");
                }
            } else {
                System.out.println("You can not craft that Item.");
            }

        } else {
            System.out.println("What do you want to craft?");
        }
    }

    /* 
     * Method used for showing missions and mission status
     */
    static private void showMissions() {

        Iterator iteratorMissionStatus = allMissions.missionStatus.entrySet().iterator();

        while (iteratorMissionStatus.hasNext()) {
            HashMap.Entry entry = (HashMap.Entry) iteratorMissionStatus.next();
            System.out.println((String) entry.getKey() + ": ");

            if ((boolean) (entry.getValue()) == false) {
                System.out.print("mission in progress");
                System.out.println("");
            }
            if ((boolean) (entry.getValue()) == true) {
                System.out.print("mission is complete");
                System.out.println("");
            }
            System.out.println("");

        }
    }

    /**
     * Method used for dropping item from inventory
     *
     * @param command used for checking if an item exists in inventory
     */
     void dropItem(Command command) {
        HashMap newInventory = inventory.getInventory();
        Iterator iterator = newInventory.entrySet().iterator();
        String seeItem;
//        int indexItem = -1;
        String nameOfItem = "";
        String dropFromInventory = "debug";

        while (iterator.hasNext()) {
            HashMap.Entry liste = (HashMap.Entry) iterator.next();
            String itemName = (String) liste.getKey();
            if (itemName.equalsIgnoreCase(command.getSecondWord())) {
                dropFromInventory = itemName;
                nameOfItem = itemName;
                break;
            }
        }
        if (!nameOfItem.equals("")) {
            itemLocation.addItem(currentRoom, new PickableItem(nameOfItem, inventory.getItemWeight(nameOfItem), inventory.getUseable(nameOfItem)));
            inventory.removeItemInventory(nameOfItem);
            System.out.println("You have dropped: " + nameOfItem);

        } else {
            System.out.println("Can't drop item that isn't in inventory " + command.getSecondWord());
        }
    }

    /**
     * Method that checks if the next room is locked or not and if the
     * commandword go it followed by a legal direction
     *
     * @param command is an object of the Command class and is used for checking
     * if the GO comamnd is used correctly.
     */
    private static void lockRoom(Command command) {
        if (command.getCommandWord().name().equalsIgnoreCase(CommandWord.GO.toString())) {
            if (inventory.getInventory().containsKey("Boardingpass") == false) {
                System.out.println("You have no boardingpass, please return when you do!!!");
            }
        }

        if (inventory.getInventory().containsKey("Boardingpass") && !hasBoardingpass) {
            hasBoardingpass = true;
            airport.setExit("west", beach);
            System.out.println(airport.getExitString());
        }
    }

    /**
     * Method that unlocks the possibility to escape the island. It checks if
     * current room is beach and if mission is completed
     */
     boolean UnlockedEscapeTheIsland() {
        if (allMissions.missionStatus.get("Escape the island") == true) {
            return false;
        }
        return true;
    }

     boolean UnlockedEscapeTheIslandWin() {
        if (currentRoom == beach && allMissions.missionStatus.get("Escape the island") == true) {
            win();
            return false;
        }
        return true;
    }

     boolean lockedEscapeIsland() {
        if (allMissions.missionStatus.get("Escape the island") == false) {
            System.out.println("You haven't unlocked this command yet");
            return false;
        }
        return true;
    }

    private static Room getRoomFromName(String roomName) {
        roomName = roomName.toLowerCase();
        switch (roomName) {
            case "airport":
                return airport;
            case "beach":
                return beach;
            case "jungle":
                return jungle;
            case "mountain":
                return mountain;
            case "cave":
                return cave;
            case "camp":
                return camp;
            case "seabottom":
                return seaBottom;
            default:
                return airport;
        }
    }

    /**
     * Method that calculates points for completed missions
     */
    static private void calculateMissionScore() {

        HashMap<String, Boolean> missionStatus = allMissions.missionStatus;

        for (String i : missionStatus.keySet()) {

            if (missionStatus.get(i) == true) {
                score.addToPoints(250);
            }
        }
    }

    /**
     * Method used to set player name for a highscore
     *
     * @param _playerName used to store a name entered by the player
     */
    void setHighscoreName(String _playerName) {
        String name = _playerName;

        System.out.println(name + "Game");
        if (name == null) {
            //create Scanner
            Scanner input = new Scanner(System.in);
            //prompt the user to enter the name their highscore
            System.out.println("");
            System.out.println("Please enter your highscore name:");
            name = input.next();
        }
//        String name = playerName;
        if (!(name.length() <= 16)) {
            String substringOfName = name.substring(0, 15);
            score.setName(substringOfName);
        } else {
            score.setName(name);
        }
    }

    /**
     * Method used to handle lose condition
     */
     private void loseCondition() {
        if (player.getHealth() <= 0) {
            lose();
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
    static private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Method used to save game
     *
     * @throws IOException
     * @throws Throwable
     */
    static void saveGame() throws IOException, Throwable {
        Save save = new Save("01");
        save.addToSaveGame(objectsToSave());
        save.saveGame();
    }

    /**
     *
     * @return
     */
    static private String objectsToSave() {
        ArrayList saveObjectsJSON;
        saveObjectsJSON = new ArrayList(10);
        saveObjectsJSON.add(inventory);
        saveObjectsJSON.add(itemLocation);
        saveObjectsJSON.add(allMissions);

        saveObjectsJSON.add(currentRoom.getShortDescription());

        System.out.println(saveObjectsJSON.toString());
        return data.objectToJson(saveObjectsJSON);
    }

    /**
     * Method used to load a saved game file, to be able to continue that
     * specific game
     */
    private static String removeCrapCharsFromString(String stringToClean) {
        stringToClean = stringToClean.replace("{", "");
        stringToClean = stringToClean.replace("}", "");
        stringToClean = stringToClean.replace("[", "");
        stringToClean = stringToClean.replace("]", "");
        stringToClean = stringToClean.replace(" ", "");
        return stringToClean;
    }

    static private void loadGame() {
        ArrayList loadData = data.loadGame();

        //inventory
        inventory = new Inventory();
        LinkedTreeMap saveMap = (LinkedTreeMap) loadData.get(0);
        LinkedTreeMap inventoryItemWeight = (LinkedTreeMap) saveMap.get("itemWeight");
        LinkedTreeMap inventoryItemQuantity = (LinkedTreeMap) saveMap.get("inventory");
        String inventoryItemQuantityString = inventoryItemQuantity.toString();
        inventoryItemQuantityString = removeCrapCharsFromString(inventoryItemQuantityString);
        String itemListString = inventoryItemWeight.toString();
        itemListString = removeCrapCharsFromString(itemListString);

        for (int i = 0; i < inventoryItemWeight.size(); i++) {
            String itemSet;
            double itemQuantity;
            if (itemListString.contains(",")) {
                itemQuantity = Double.parseDouble(inventoryItemQuantityString.substring(inventoryItemQuantityString.indexOf("=") + 1, inventoryItemQuantityString.indexOf(",")));
                inventoryItemQuantityString = inventoryItemQuantityString.substring(inventoryItemQuantityString.indexOf(",") + 1, inventoryItemQuantityString.length());
                itemSet = itemListString.substring(0, itemListString.indexOf(","));
                itemListString = itemListString.substring(itemListString.indexOf(",") + 1, itemListString.length());
            } else {
                itemSet = itemListString;
                itemQuantity = Double.parseDouble(inventoryItemQuantityString.substring(inventoryItemQuantityString.indexOf("=") + 1, inventoryItemQuantityString.length()));
            }
            String itemName = itemSet.substring(0, itemSet.indexOf("="));
            int itemWeight = Double.valueOf(itemSet.substring(itemSet.indexOf("=") + 1, itemSet.length())).intValue();
            while (itemQuantity > 0) {
                Item item = new Item(itemName, "", itemWeight, true);
                inventory.addItemInInventory(item);
                itemQuantity--;
            }
        }

        //itemList
        itemLocation = new ItemLocation();
        saveMap = (LinkedTreeMap) loadData.get(1);
        LinkedTreeMap itemLocationOnMap = (LinkedTreeMap) saveMap.get("itemList");
        String rooms = itemLocationOnMap.keySet().toString();
        rooms = removeCrapCharsFromString(rooms);
        System.out.println(itemLocationOnMap.toString());
        for (int j = 0; j <= itemLocationOnMap.size() - 1; j++) {
            String itemToAdd;

            if (rooms.contains(",")) {
                itemToAdd = rooms.substring(0, rooms.indexOf(","));
            } else {
                itemToAdd = rooms;
            }

            rooms = rooms.substring(rooms.indexOf(",") + 1, rooms.length());
            ArrayList itemInRoom = (ArrayList) itemLocationOnMap.get(itemToAdd);
            for (int i = 0; i < itemInRoom.size(); i++) {
                Item itemLocationToAdd;
                LinkedTreeMap itemT = (LinkedTreeMap) itemInRoom.get(i);
                System.out.println(itemT.toString());
                String itemName = itemT.get("name").toString();
                String itemDesc = "";
                int itemWeight = (int) Double.parseDouble(itemT.get("weight").toString());
                boolean itemUseable = Boolean.getBoolean(itemT.get("useable").toString());

                itemLocationToAdd = new PickableItem(itemName, itemDesc, itemWeight, itemUseable);
                itemLocation.addItem(itemToAdd, itemLocationToAdd);

            }
        }
        //set room
        String spawnRoom = loadData.get(3).toString();
        currentRoom = getRoomFromName(spawnRoom);
    }

    /**
     *
     * @return Highscore string that show stored highscores
     */
    String getHighscoreFromData() {
        String highscoreString = data.printHighscore();
        return highscoreString;
    }

    /**
     * Method used to handle actions when game is won
     */
     void win() {
        String name = score.getName();
        if (Time.getSecondsPassed() < 1) {
            calculateMissionScore();
            setHighscoreName(name);
            int totalSum = score.getCurrentScore() + (10000 / 1);
            System.out.println("You have won the game!" + "\n" + "You spend: " + 1 + " seconds playing the game!");
            System.out.println("Your score is: " + totalSum);
            System.out.println("your score has been added to highscore");
            data.addHighscore(score.getName(), totalSum);
            System.out.println("");
            System.out.println("Current highscore list is: ");
            System.out.println(data.printHighscore());
        } else {
            calculateMissionScore();
            setHighscoreName(name);
            int totalSum = score.getCurrentScore() + (10000 / Time.getSecondsPassed());
            System.out.println("You have won the game!" + "\n" + "You spend: " + Time.getSecondsPassed() + " seconds playing the game!");
            System.out.println("Your score is: " + totalSum);
            System.out.println("your score has been added to highscore");
            data.addHighscore(score.getName(), totalSum);
            System.out.println("");
            System.out.println("Current highscore list is: ");
            System.out.println(data.printHighscore());
        }
    }

    /**
     * Method used when game is lost
     */
    void lose() {
        System.out.println("You have lost the game!!!" + "\n" + "You spend: " + Time.getSecondsPassed() + " seconds playing the game!");
        System.exit(0);

    }

    /**
     * Method for using items stored in inventory
     *
     * @param command used to check if USE command is used correctly
     * @return boolean, whether if item is used or not
     */
     boolean useItem(Command command) {
        HashMap newInventory = inventory.getInventory();
        Iterator iterator = newInventory.entrySet().iterator();
        String seeItem;
        String nameOfItem = "";
        String useItem = "debug";

        while (iterator.hasNext()) {
            HashMap.Entry liste = (HashMap.Entry) iterator.next();
            String itemName = (String) liste.getKey();
            if (itemName.equalsIgnoreCase(command.getSecondWord())) {
                useItem = itemName;
                nameOfItem = itemName;
                break;
            }
        }
        if (!nameOfItem.equals("") && inventory.getUseable(nameOfItem)) {

            System.out.println("You have dropped: " + nameOfItem);
            itemLocation.addItem(currentRoom, new PickableItem(nameOfItem, inventory.getItemWeight(nameOfItem)));
            inventory.removeItemInventory(nameOfItem);
            player.setEnergy(player.getEnergy() + 20);
            player.setHealth(player.getHealth() + 5);

            return true;
        }
        return false;
    }

    public String talkToNPC(String npc) {
        System.out.println(npc);
        return NPCFromName().get(npc).getDialog(0);
    }
    
    public NPC getNPCFromName(String npc){
        return NPCFromName().get(npc);   
    }
    
    public ItemLocation getItemLocation(){
        return itemLocation;
    }
    
    public Room getCurrentRoom(){
        return currentRoom;
    }
    
    public Inventory getInventory(){
        return inventory;
    }
    public Mission getAllMissions(){
        return allMissions;
    }
    public Player getPlayer(){
        return player;
    }
    public void setUsingGUI(boolean _usingGui){
        usingGui = _usingGui;
    }
}
