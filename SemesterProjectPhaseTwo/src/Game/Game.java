package Game;

import FileHandling.DataFacade;
import acquaintance.InterfaceGame;
import FileHandling.HighscoreManager;
import FileHandling.Logger;
import FileHandling.Save;
import acquaintance.InterfaceData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Random;

/**
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 * @author Nicolai
 * @version 2006.03.30daSFT
 */
public class Game {

    /* data field with the attributes parser and currentRoom
       making them private so we only can use them in the Game class */
    static private boolean hasBoardingpass = false;
    static private Parser parser;
    static Room currentRoom;
    private static Room airport, beach, jungle, mountain, cave, camp, seaBottom;
    static private String name;
    static private boolean hasTalkedWithEvilGuy = false;

    

    /* Constructor that runs the method createRooms and set our variable parser
       equal to the Parser method in the Parser class */
    public Game() {
        initGame();
        parser = new Parser();
    }
        public static boolean getExitBool(String dir){
        
        return (currentRoom.getExit(dir) != null);
    }

    /* Private method createRoom which means we can only use createRoom in the Game class */
 /* In the method body we set the names of the rooms, create the rooms by using the Room 
       constructor from the Room class and then set where you can move to  from the different rooms by
       using the method setExit from the Room class */
 /* The currentRoom is also given a value which is the start location = outside */
    private static DataFacade data = new DataFacade();
    static ItemLocation itemLocation = new ItemLocation();
    static Inventory inventory = new Inventory();
    static HighscoreManager highscore = new HighscoreManager();
    static CraftableItem craftableItem = new CraftableItem();
    static Player player = new Player("Player", "???", currentRoom, 100, 100);
    static Score score = new Score();
    static Item debug = new Item("debug");
    static Item campfire = new CraftableItem("Campfire", "Campfire: Lumber, Stick and Flint", 3);
    static Item spear = new CraftableItem("Spear", "Spear: Stick, Fint and Rope or Lian", 3);
    static Item axe = new CraftableItem("Axe", "Axe: Stick, Stone and Rope or Lian", 3);
    static Item raft = new CraftableItem("Raft", "Raft: Lumber, Stick and Rope or Lian", 3);
    static Mission allMissions = new Mission();
    static NPC npc1 = new NPC();
    static NPC npc2 = new NPC();
    static NPC npc3 = new NPC();

    //file thats gonna be written to and the extension
    static Logger log = new Logger();
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Used to initialize different rooms and their respective items, and also
     * set the currentRoom
     */
    private void createRooms() {
//        Room airport, beach, jungle, mountain, cave, camp, raft, seaBottom;

        airport = new Room("airport");
        beach = new Room("beach");
        jungle = new Room("jungle");
        mountain = new Room("mountain");
        cave = new Room("cave");
        camp = new Room("camp");
        seaBottom = new Room("seabuttom");

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

        seaBottom.setExit("north", beach);

        currentRoom = airport;

    }

    public void createItem() {
        //Initializing an item and putting it in a room airport
        itemLocation.addItem(airport, new PickableItem("Bottle", "This is a bottle that have been left behind by someone", 2));
        itemLocation.addItem(airport, new PickableItem("Boardingpass", "This is a boardingpass to get on the plane to Hawaii: 126AB", 1));
//        itemLocation.addItem(airport, new PickableItem("Boardingpass to Hawaii: 126AB", "This is a boardingpass to get on the plane to Hawaii", 1));
//        itemLocation.addItem(airport, new PickableItem("Boardingpass to Kenya: 426DB", "this is a boardingpass to Kenya", 1));
//        itemLocation.addItem(airport, new PickableItem("Boardingpass to Russia: 139BA", "This boardingpass has expired", 1));
        //non pickable item
        itemLocation.addItem(airport, new Item("Stop sign", "this is a shop", 100));

        //Initializing an item and putting it in a room beach
        itemLocation.addItem(beach, new PickableItem("Stone", "This is a stone, maybe it can be used to create something more usefull", 2));
        itemLocation.addItem(beach, new PickableItem("Fish", "Why are you inspecting this item, its GOD damn fish", 1));
        itemLocation.addItem(beach, new PickableItem("Fish", "Why are you inspecting this item, its GOD damn fish", 1));
        itemLocation.addItem(beach, new PickableItem("Flint", "This a flint, maybe it can be used to create something more usefull", 2));
        itemLocation.addItem(beach, new PickableItem("Rope", "This is some rope that has been washed up on the beach shore from the plane crash ", 2));
        itemLocation.addItem(beach, new PickableItem("Stick", "This is a small stick, maybe it can be used to create something more usefull", 1));
        itemLocation.addItem(beach, new PickableItem("Stick", "This is a small stick, maybe it can be used to create something more usefull", 1));
        //non pickable item
        itemLocation.addItem(beach, new Item("Giant rock", "The giant rock dont look like it can be moved", 100));
        itemLocation.addItem(beach, new Item("Giant log", "The giant log dont look like it can be moved", 100));

        //Initializing an item and putting it in a room jungle
        itemLocation.addItem(jungle, new PickableItem("Berry", "this is berries, maybe its poisonous try ur luck!! ", 1));
        itemLocation.addItem(jungle, new PickableItem("Berry", "this is berries, maybe its poisonous try ur luck!! ", 1));
        itemLocation.addItem(jungle, new PickableItem("Lumber", "This is a log of tree, maybe it can be used to craft something to get away from this island ", 3));
        itemLocation.addItem(jungle, new PickableItem("Lian", "This is a lian from the jungle, maybe it can be used to create something more usefull", 2));
        itemLocation.addItem(jungle, new PickableItem("Lian", "This is a lian from the jungle, maybe it can be used to create something more usefull", 2));
        itemLocation.addItem(jungle, new PickableItem("Stone", "This is a stone, maybe it can be used to create something more usefull", 2));
        itemLocation.addItem(jungle, new PickableItem("Stick", "This is a small stick, maybe it can be used to create something more usefull", 1));
        //non pickable item
        itemLocation.addItem(jungle, new Item("Giant log", "The giant log dont look like it can be moved", 100));

        //Initializing an item and putting it in a room mountain
        itemLocation.addItem(mountain, new PickableItem("Stone", "This is a stone, maybe it can be used to create something more usefull", 2));
        itemLocation.addItem(mountain, new PickableItem("Egg", "This is some wild eggs, maybe it can be used for food", 1));
        itemLocation.addItem(mountain, new PickableItem("Egg", "This is some wild eggs, maybe it can be used for food", 1));
        itemLocation.addItem(mountain, new PickableItem("Lumber", "This is a log of tree, maybe it can be used to craft something to get away from this island ", 3));

        //Initializing an item and putting it in a room cave
        itemLocation.addItem(cave, new PickableItem("Shroom", "these shrooms look suspecius, but maybe the can be", 1));
        itemLocation.addItem(cave, new PickableItem("Stone", "This is a stone, maybe it can be used to create something more usefull", 2));
        itemLocation.addItem(cave, new PickableItem("Stone", "This is a stone, maybe it can be used to create something more usefull", 2));
        itemLocation.addItem(cave, new PickableItem("Freshwater", "This is freshwater found in the jungle, maybe you can drink it", 2));
        itemLocation.addItem(cave, new PickableItem("Flint", "This a flint, maybe it can be used to create something more usefull", 2));
        itemLocation.addItem(cave, new Item("Giant rock", "The giant rock dont look like it can be moved", 100));

        //Initializing an item and putting it in a room camp
        itemLocation.addItem(camp, new Item(""));

        //Initializing an item and putting it in a room seaBottom
        itemLocation.addItem(seaBottom, new PickableItem("Backpack", "This is a backpack from the plane crash maybe you can use it to carry more items ", 0));
        itemLocation.addItem(seaBottom, new PickableItem("WaterBottle", "This is a water bottle from the plan crash ", 1));
        itemLocation.addItem(seaBottom, new PickableItem("Rope", "This is some rope that has been washed up on the beach shore from the plane crash", 2));

    }

    public void putCraftableItemInHashmap() {

        craftableItem.craftableList.put("Campfire", campfire);
        craftableItem.craftableList.put("Raft", raft);
        craftableItem.craftableList.put("Axe", axe);
        craftableItem.craftableList.put("Spear", spear);

    }

    public void createMissions() {

        //Creating missions and putting and putting them in the game
        allMissions.addMission(airport, "Find your boardingpass");
        allMissions.addMission(airport, "Picking up first item");
        allMissions.addMission(beach, "Pick up food");
        allMissions.addMission(jungle, "Find survivors");
        allMissions.addMission(cave, "Eat the shrooms");
        allMissions.addMission(camp, "prepare to escape the island");
        allMissions.addMission(camp, "Escape the island");
    }

    public void createNPC() {
        //create the good npc
        npc1.setName("BS Christiansen");
        npc1.setCurrentRoom(jungle);
        npc1.setDescribtion("The survivor of the plane crash look to be some kind of veteran soldier, "
                + "\nbut he is heavly injured on his right leg so he cant move ");
        npc1.addDialog("If you want to survive on this GOD forsaken island, you must first find food and shelter."
                + "\nYou can craft items to help you survive, if you have the right components.");

        //create the bad npc
        npc3.setName("Joseph Schitzel");
        npc3.setCurrentRoom(mountain);
        npc3.setDescribtion("A lonely surviver with very filthy hair, and a wierd smell of weinerschnitzel.");
        npc3.addDialog("Heeelloooo there my freshlooking friend, I am Joseph Schitzel, if you scratch my back I might scratch your's." + "\n" + "Will you, fetch me some eggs?: Yes or no");
        npc3.addDialog("");
        npc3.setDamageValue(100);

        //create another npc
        npc2.setName("Mysterious crab");
        npc2.setCurrentRoom(cave);
        npc2.setDescribtion("A mysterious crab that you dont really get why can talk");
        npc2.addDialog("MUHAHAHA i'm the finest and most knowledgeable crab of them all mr.Crab and know this island like the back of my hand.... oh i mean claw"
                + "\n SO if you want the rarest item you can find on this island, you must first help me find some stuff " + "\n"
                + "If you answer my very cool questions correctly, you will get an awesome unique reward, hehehe!");
    }
    
    static HashMap<String, String> storeNPC() {
        HashMap<String, String> npcMap = new HashMap<>();
        npcMap.put(npc1.getName(),npc1.getCurrentRoom().getShortDescription());
        npcMap.put(npc2.getName(), npc2.getCurrentRoom().getShortDescription());
        npcMap.put(npc3.getName(), npc3.getCurrentRoom().getShortDescription());
       return npcMap;   
    } 
    
 
    //Initializing game
    public void initGame() {

        createRooms();
        createItem();
        createMissions();
        createNPC();
        putCraftableItemInHashmap();

    }

    /**
     * this method is responisble for moving the npc3
     */
    static private void npcPath() {
//        Random picker = new Random();
//        Room[] roomString = {beach, jungle, mountain};
//            int indexOfRoomString = picker.nextInt(roomString.length);
//            npc3.setCurrentRoom(roomString[indexOfRoomString]);

        if (Time.secondsPassed % 45 == 0) {
            Random picker = new Random();
            String[] roomString = {"south", "north"};
            boolean hasMoved = false;
            while (!hasMoved) {
                if (npc3.getCurrentRoom().equals(mountain)) {
                    String[] newRoomString = {"south"};
                    int index = picker.nextInt(newRoomString.length);
                    Room next = npc3.getCurrentRoom().getExit(newRoomString[index]);
                    npc3.setCurrentRoom(next);
                    hasMoved = true;
                    break;
                }

                if (npc3.getCurrentRoom().equals(beach)) {
                    String[] newRoomString = {"north"};
                    int indexOfNewRoomString = picker.nextInt(newRoomString.length);
                    Room nextRoom = npc3.getCurrentRoom().getExit(newRoomString[indexOfNewRoomString]);
                    npc3.setCurrentRoom(nextRoom);
                    hasMoved = true;
                    break;
                }

                if (npc3.getCurrentRoom().equals(jungle)) {
                    String[] newRoomString = {"north", "south"};
                    int indexOfNewRoomString = picker.nextInt(newRoomString.length);
                    Room nextRoom = npc3.getCurrentRoom().getExit(newRoomString[indexOfNewRoomString]);
                    npc3.setCurrentRoom(nextRoom);
                    hasMoved = true;
                    break;
                }
            }
        }
    }

    /**
     * A method that is initialized when we start the game, that first print out
     * a message with the printWelcome method and then checks if the game is
     * finished or not with a while loop where finished is set to false when the
     * game start
     *
     * @throws FileNotFoundException
     * @throws IOException
     * @throws Throwable
     */
    public void play() throws FileNotFoundException, IOException, Throwable {
        Time time = new Time();
        printWelcome();
        time.start();
        log.write(System.lineSeparator() + System.lineSeparator() + " >>>  Starting new game <<< " + System.lineSeparator() + System.lineSeparator());

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
    static boolean processCommand(Command command) throws FileNotFoundException, IOException, Throwable {
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
        } else if (commandWord == CommandWord.CRAFT) {
            craftItem(command);
        } else if (commandWord == CommandWord.WIN) {
            win();
        } else if (commandWord == CommandWord.LOSE) {
            lose();
        } else if (commandWord == CommandWord.ESCAPE) {
            escapeTheIsland();
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
//        while (inventory.getInventory().containsKey("Boardingpass")) {
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

    /* A method to print a message that show the different commands everytime the command help is used */
    static String printHelp() {
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
    static void goRoom(Command command) {
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
            //System.out.println(currentRoom.getLongDescription());
            System.out.println(player.getCurrentRoom().getLongDescription());

        }

        forceDialog();
    }

// Method used for showing contents in inventory
//    private void showInventory(Command command) {
    static private void showInventory() {

        HashMap<String, Integer> inventoryHM = inventory.getInventory();

        System.out.println("Items in inventory is: " + inventory.getCurrentInventoryWeight() + "/" + inventory.getInventoryMaxWeight());
        for (String i : inventoryHM.keySet()) {
            System.out.println(inventoryHM.get(i) + "x" + i);
        }
    }

    //Method used for inspecting room and showing items in that room
//    private void inspectRoom(Command command){
    static private void inspectRoom() {
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
        int currentNPCsInRoom = 0;

        if (npc1.getCurrentRoom() == currentRoom) {
            System.out.println("There seems to be someone resting in the leaves");
            currentNPCsInRoom++;
        }

        if (npc2.getCurrentRoom() == currentRoom) {
            System.out.println("You sense somebody in the cave");
            currentNPCsInRoom++;
        }

        if (npc3.getCurrentRoom() == currentRoom) {
            System.out.println("There is an intense smell, somebody seems to be near!");
            currentNPCsInRoom++;
        }
        if (currentNPCsInRoom == 0) {
            System.out.println("You are alone in the room");
        }
    }

    /**
     * Method used for taking and placing an item in inventory
     *
     * @param command used for checking if an item exists in current room
     * @return
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
//                    inventory.addItemInInventoryBoolean(addToInventory);
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

    static private void TalkTo() {
        if (npc1.getCurrentRoom() == currentRoom) {
            System.out.println(npc1.getDescribtion() + ", yet he still gives you good advice:\n");
            System.out.println(npc1.getDialog(0));
            System.out.println("");
            woundedSurvivor();

        } else if (npc2.getCurrentRoom() == currentRoom && inventory.getInventory().containsKey("Shroom")) {
            System.out.println(npc2.getDescribtion() + "\n" + npc2.getDialog(0));
            pregnant();
        } //        else if (npc3.getCurrentRoom() == currentRoom) {
        //            System.out.println(npc3.getDescribtion() + "\n" + npc3.getDialog(0));
        //            evilGuyDialog();
        //        } 
        else {
            System.out.println("There is nobody to communicate with in this Room");
        }

    }

    /**
     * force dialog with npc3, if player is in same room
     */
    static private void forceDialog() {
        if (hasTalkedWithEvilGuy == false) {
            if (npc3.getCurrentRoom() == currentRoom) {
                System.out.println(npc3.getDescribtion() + "\n" + npc3.getDialog(0));
                evilGuyDialog();
                hasTalkedWithEvilGuy = true;
            }
        }
    }

    /**
     * creates a dialog for npc1
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
//            if (allMissions.missionStatus.get("Helping the injured survivor") == true){
//                System.out.println("");
    }

    /**
     * Creates question when talking with npc2
     */
    static public void pregnant() {
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

    /**
     * creates a short dialog when talking to npc3
     */
    static public void evilGuyDialog() {
        Scanner scan = new Scanner(System.in); //Creates a new scanner
        String input = scan.nextLine(); //Waits for input

        if (input.equalsIgnoreCase("yes")) {
            System.out.println("You got a mission, please use the show command for more information");
            allMissions.addMission(npc3.getCurrentRoom(), "Get me some eggs or I will kill you!!!!");
            System.out.println("You survived snitzel this time, but take care: " + player.getHealth());
        } else if (input.equalsIgnoreCase("no")) {
            System.out.println("");
            player.loseHealth(npc3.getDamageValue());
        }

    }

    /**
     *
     * @param command to craft item
     * @return 
     */
    @Override
    public String toString(){
       if (inventory.getInventory().containsKey("Lumber") && inventory.getInventory().containsKey("Stick") && inventory.getInventory().containsKey("Flint")) {
                inventory.dropItemInventory("Lumber");
                inventory.dropItemInventory("Stick");
                inventory.dropItemInventory("Flint");
                if (craftableItem.craftableList.containsKey("Campfire")) {
                    inventory.addItemInInventory(campfire);
                    
    }
       }
        return null;
       
    }
    
    static public void craftItem(Command command) {
        if (command.hasSecondWord()) {
            String craft = command.getSecondWord();
            if (craft.equalsIgnoreCase("Recipe")) {
                System.out.println("This is a list of the games craftable items and their recipes:");
                System.out.println("Campfire: Lumber, Stick and Flint" + "\n" + "Spear: Stick, Fint and Rope or Lian" + "\n" + "Axe: Stick, Stone and Rope or Lian" + "\n" + "Raft: Lumber, Stick and Rope or Lian");
            } else if (craft.equalsIgnoreCase("Campfire") && inventory.getInventory().containsKey("Lumber") && inventory.getInventory().containsKey("Stick") && inventory.getInventory().containsKey("Flint")) {
                inventory.dropItemInventory("Lumber");
                inventory.dropItemInventory("Stick");
                inventory.dropItemInventory("Flint");
                if (craftableItem.craftableList.containsKey("Campfire")) {
                    inventory.addItemInInventory(campfire);
                    System.out.println("A Campfire is added to your inventory");
                }

            } else if (craft.equalsIgnoreCase("Spear") && inventory.getInventory().containsKey("Stick") && inventory.getInventory().containsKey("Flint")
                    && (inventory.getInventory().containsKey("Lian") || inventory.getInventory().containsKey("Rope"))) {
                inventory.dropItemInventory("Stick");
                inventory.dropItemInventory("Flint");
                if (inventory.getInventory().containsKey("Rope")) {
                    inventory.dropItemInventory("Rope");
                } else {
                    inventory.dropItemInventory("Lian");
                }
                if (craftableItem.craftableList.containsKey("Spear")) {
                    inventory.addItemInInventory(spear);
                    System.out.println("A Spear is added to your inventory");
                }

            } else if (craft.equalsIgnoreCase("Axe") && inventory.getInventory().containsKey("Stick") && inventory.getInventory().containsKey("Stone")
                    && (inventory.getInventory().containsKey("Lian") | inventory.getInventory().containsKey("Rope"))) {
                inventory.dropItemInventory("Stick");
                inventory.dropItemInventory("Stone");
                if (inventory.getInventory().containsKey("Rope")) {
                    inventory.dropItemInventory("Rope");
                } else {
                    inventory.dropItemInventory("Lian");
                }
                if (craftableItem.craftableList.containsKey("Axe")) {
                    inventory.addItemInInventory(axe);
                    System.out.println("An axe is added to your inventory");
                }

            } else if (craft.equalsIgnoreCase("Raft") && inventory.getInventory().containsKey("Lumber") && inventory.getInventory().containsKey("Stick")
                    && (inventory.getInventory().containsKey("Lian") || inventory.getInventory().containsKey("Rope"))) {
                inventory.dropItemInventory("Lumber");
                inventory.dropItemInventory("Stick");
                if (inventory.getInventory().containsKey("Rope")) {
                    inventory.dropItemInventory("Rope");
                } else {
                    inventory.dropItemInventory("Lian");
                }
                if (craftableItem.craftableList.containsKey("Raft")) {
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
    * Method used for showing missions
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
    static private void dropItem(Command command) {
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
            itemLocation.addItem(currentRoom, new PickableItem(indexItem, inventory.getItemWeight(indexItem)));

        } else {
            System.out.println("Can't drop item that isn't in inventory " + command.getSecondWord());
        }

    }

    //set it so you cant go to the beach before you have the boardingpass
    private static void lockRoom(Command command) {
//        System.out.println(inventory.getInventory().containsKey("Boardingpass"));
//        System.out.println(command.getSecondWord());
//        System.out.println(command.getCommandWord().name());
        if (command.getCommandWord().name().equalsIgnoreCase(CommandWord.GO.toString())) {
//            if (inventory.getInventory().containsKey("Boardingpass") == false && command.getSecondWord().equalsIgnoreCase("west")) {
            if (inventory.getInventory().containsKey("Boardingpass") == false) {
//                airport.setExit("west", airport);                                 // This lines prints out the exit for some reason, which is not good
                System.out.println("You have no boardingpass, please return when you do!!!");
            }
//            else {
//                airport.setExit("west", beach);
//                System.out.println("Exits: west");
        }

        if (inventory.getInventory().containsKey("Boardingpass") && !hasBoardingpass) {
            hasBoardingpass = true;
            airport.setExit("west", beach);
            System.out.println(airport.getExitString());
        }
    }

    //command to leave the island if you choose to stay to complete more quest for at better highscore
    static private void escapeTheIsland() {
        while (currentRoom == beach) {
            if (allMissions.missionStatus.get("Escape the island") == true) {

                Scanner scan = new Scanner(System.in); //create new scanner
                System.out.println("");
                System.out.println("- Are you sure you want to leave: Yes or no");
                System.out.println("");
                String input = scan.nextLine(); //Waits for input
                while (input.equalsIgnoreCase("yes")) {
                    win();
                    break;
                }
//                while (input.equalsIgnoreCase("no")) {
//                break;
//                }

            }
            break;
        }
        if (allMissions.missionStatus.get("Escape the island") == false) {
            System.out.println("- You cant use this command yet");
        }
    }

    //calculate point for each mission completed
    static private void calculateMissionScore() {

        HashMap<String, Boolean> missionStatus = allMissions.missionStatus;

        for (String i : missionStatus.keySet()) {

            if (missionStatus.get(i) == true) {
                score.addToPoints(250);
            }
        }
    }

    //method to set the name of the highscore
    static private void setHighscoreName() {

        //create Scanner
        Scanner input = new Scanner(System.in);
        //prompt the user to enter the name their highscore
        System.out.println("");
        System.out.println("Please enter your highscore name:");
        String name = input.next();

        if (!(name.length() <= 16)) {
            String substringOfName = name.substring(0, 15);
            score.setName(substringOfName);
        } else {
            score.setName(name);
        }
    }

    static private void loseCondition() {
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
     *
     * @throws IOException
     * @throws Throwable
     */
    static private void saveGame() throws IOException, Throwable {
        Save save = new Save("01");
        save.addToSaveGame(objectsToSave());
        save.saveGame();
    }

    static private String objectsToSave() {
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

    static String getHighscoreFromData() {
        String highscoreString = data.printHighscore();
        return highscoreString;
    }

    static void win() {
        if (Time.getSecondsPassed() < 1) {
            calculateMissionScore();
            setHighscoreName();
            int totalSum = score.getCurrentScore() + (10000 / 1);
            System.out.println("You have won the game!" + "\n" + "You spend: " + 1 + " seconds playing the game!");
            System.out.println("Your score is: " + totalSum);
            System.out.println("your score has been added to highscore");
            highscore.addHighscore(score.getName(), totalSum);
            System.out.println("");
            System.out.println("Current highscore list is: ");
            System.out.println(data.printHighscore());
            System.exit(0);
        } else {
            calculateMissionScore();
            setHighscoreName();
            int totalSum = score.getCurrentScore() + (10000 / Time.getSecondsPassed());
            System.out.println("You have won the game!" + "\n" + "You spend: " + Time.getSecondsPassed() + " seconds playing the game!");
            System.out.println("Your score is: " + totalSum);
            System.out.println("your score has been added to highscore");
            highscore.addHighscore(score.getName(), totalSum);
            System.out.println("");
            System.out.println("Current highscore list is: ");
            System.out.println(data.printHighscore());
            System.exit(0);
        }
    }

    static void lose() {
        System.out.println("You have lost the game!!!" + "\n" + "You spend: " + Time.getSecondsPassed() + " seconds playing the game!");
        System.exit(0);

    }
}
