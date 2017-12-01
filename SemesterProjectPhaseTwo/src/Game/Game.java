package Game;

import FileHandling.HighscoreManager;
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
    private boolean hasBoardingpass = false;
    private Parser parser;
    private Room currentRoom;
    private Room airport, beach, jungle, mountain, cave, camp, seaBottom;
    private String name;

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
    HighscoreManager highscore = new HighscoreManager();
    Player player = new Player("Player", "???", currentRoom, 100, 100);
    Score score = new Score();
    Item debug = new Item("debug");
    Item campfire = new Item("Campfire", "", 2);
    Item spear = new Item("Spear", "", 2);
    Item axe = new Item("Axe", "", 2);
    Item raft = new Item("Raft", "", 2);
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

    public void createMissions() {

        //Creating missions and putting and putting them in the game
        allMissions.addMission(airport, "First mission", "Find your boardingpass");
        allMissions.addMission(airport, "First item", "Picking up your first item");
        allMissions.addMission(beach, "Find food to survive", "Pick up food");
        allMissions.addMission(jungle, "Find survivors", "Get into contact with other survivors");
        allMissions.addMission(cave, "Get high", "Eat the shrooms");
        allMissions.addMission(camp, "craft raft", "craft raft to prepare to escape the island");
        allMissions.addMission(camp, "Escape the island", "Collect items to survive on the sea");
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

    //Initializing game
    public void initGame() {

        createRooms();
        createItem();
        createMissions();
        createNPC();

    }

    private void npcPath() {

        Random picker = new Random();
        Room[] roomString = {beach, jungle, mountain};
        if (Time.secondsPassed % 45 == 0) {
            int indexOfRoomString = picker.nextInt(roomString.length);
            npc3.setCurrentRoom(roomString[indexOfRoomString]);
        }
    }

    /* A method that is initialized when we start the game, that first print out a message with the printWelcome method  
       and then checks if the game is finished or not with a while loop where finished is set to false when the game start*/
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
        } else if (commandWord == commandWord.WIN) {
            win();
        } else if (commandWord == commandWord.LOSE) {
            lose();
        } else if (commandWord == commandWord.ESCAPE) {
            escapeTheIsland();
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

        if (inventory.getInventory().containsKey("Berry")) {
            allMissions.setMissionComplete("Find food to survive");
        }

        if (CommandWord.TALK == commandWord) {
            allMissions.setMissionComplete("Find survivors");
        }

        if (inventory.getInventory().containsKey("raft")) {
            allMissions.setMissionComplete("craft raft");
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
    }

    // Method used for showing contents in inventory
//    private void showInventory(Command command) {
    private void showInventory() {

        HashMap<String, Integer> inventoryHM = inventory.getInventory();

        System.out.println("Items in inventory is: " + inventory.getCurrentInventoryWeight() + "/" + inventory.getInventoryMaxWeight());
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
            System.out.println(npc1.getDescribtion() + ", yet he still gives you good advice:\n");
            System.out.println(npc1.getDialog(0));
            System.out.println("");
            System.out.println("Maybe you could bring me some food and something to defend myself now that i cant move");
            System.out.println("Do you want to accept his mission: Yes or no");
            Scanner scan = new Scanner(System.in); //Creates a new scanner
            String input = scan.nextLine(); //Waits for input
            if (input.equalsIgnoreCase("yes")) {
                System.out.println("You got a mission, please use the show command for more information");
                allMissions.addMission(jungle, "Helping the injured survivor", "helping the survivor, because of his great advice he have given you");
            } else if (input.equalsIgnoreCase("no")) {
                System.out.println("Come back again if you change your mind");
            } else {
                System.out.println("Come back again if you change your mind");
            }
//            if (allMissions.missionStatus.get("Helping the injured survivor") == true){
//                System.out.println("");
//                

        } else if (npc2.getCurrentRoom() == currentRoom && inventory.getInventory().containsKey("Shroom")) {
            System.out.println(npc2.getDescribtion() + "\n" + npc2.getDialog(0));
            pregnant();
        } else if (npc3.getCurrentRoom() == currentRoom) {

            System.out.println(npc3.getDescribtion() + "\n" + npc3.getDialog(0));

            Scanner scan = new Scanner(System.in); //Creates a new scanner
            String input = scan.nextLine(); //Waits for input

            if (input.equalsIgnoreCase("yes")) {
                System.out.println("You got a mission, please use the show command for more information");
                allMissions.addMission(npc3.getCurrentRoom(), "Live or die", "Get me some eggs or I will kill you!!!!");
                System.out.println("You survived snitzel this time, but take care: " + player.getHealth());
            } else if (input.equalsIgnoreCase("no")) {
                System.out.println("");
                player.LoseHealth(npc3.getDamageValue());
            }

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
        if (command.hasSecondWord()) {
            String craft = command.getSecondWord();
            if (craft.equalsIgnoreCase("Recipe")) {
                System.out.println("This is a list of the games craftable items and their recipes:");
                System.out.println("Campfire: Lumber, Stick and Flint" + "\n" + "Spear: Stick, Fint and Rope or Lian" + "\n" + "Axe: Stick, Stone and Rope or Lian" + "\n" + "Raft: Lumber, Stick and Rope or Lian");
            } else if (craft.equalsIgnoreCase("Campfire") && inventory.getInventory().containsKey("Lumber") && inventory.getInventory().containsKey("Stick") && inventory.getInventory().containsKey("Flint")) {
                inventory.dropItemInventory("Lumber");
                inventory.dropItemInventory("Stick");
                inventory.dropItemInventory("Flint");
                inventory.addItemInInventory(campfire);
                System.out.println("A Campfire is added to your inventory");

            } else if (craft.equalsIgnoreCase("Spear") && inventory.getInventory().containsKey("Stick") && inventory.getInventory().containsKey("Flint")
                    && (inventory.getInventory().containsKey("Lian") || inventory.getInventory().containsKey("Rope"))) {
                inventory.dropItemInventory("Stick");
                inventory.dropItemInventory("Flint");
                if (inventory.getInventory().containsKey("Rope")) {
                    inventory.dropItemInventory("Rope");
                } else {
                    inventory.dropItemInventory("Lian");
                }
                inventory.addItemInInventory(spear);
                System.out.println("A Spear is added to your inventory");

            } else if (craft.equalsIgnoreCase("Axe") && inventory.getInventory().containsKey("Stick") && inventory.getInventory().containsKey("Stone")
                    && (inventory.getInventory().containsKey("Lian") | inventory.getInventory().containsKey("Rope"))) {
                inventory.dropItemInventory("Stick");
                inventory.dropItemInventory("Stone");
                if (inventory.getInventory().containsKey("Rope")) {
                    inventory.dropItemInventory("Rope");
                } else {
                    inventory.dropItemInventory("Lian");
                }
                inventory.addItemInInventory(axe);
                System.out.println("An axe is added to your inventory");

            } else if (craft.equalsIgnoreCase("Raft") && inventory.getInventory().containsKey("Lumber") && inventory.getInventory().containsKey("Stick")
                    && (inventory.getInventory().containsKey("Lian") || inventory.getInventory().containsKey("Rope"))) {
                inventory.dropItemInventory("Lumber");
                inventory.dropItemInventory("Stick");
                if (inventory.getInventory().containsKey("Rope")) {
                    inventory.dropItemInventory("Rope");
                } else {
                    inventory.dropItemInventory("Lian");
                }
                inventory.addItemInInventory(raft);
                System.out.println("Raft has been added to inventory");
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
            itemLocation.addItem(currentRoom, new PickableItem(indexItem, inventory.getItemWeight(indexItem)));

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

    //command to leave the island if you choose to stay to complete more quest for at better highscore
    private void escapeTheIsland() {
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
    private void calculateMissionScore() {

        HashMap<String, Boolean> missionStatus = allMissions.missionStatus;

        for (String i : missionStatus.keySet()) {

            if (missionStatus.get(i) == true) {
                score.addToPoints(250);
            }
        }
    }

    //method to set the name of the highscore
    private void setHighscoreName() {

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
    public void NPCMove() {
        Random random = new Random();
        if (Time.getSecondsPassed() % 10 == 0) {

            if (npc3.getCurrentRoom() == mountain) {
                npc3.setCurrentRoom(jungle);
                System.out.println(npc3.currentRoom.toString());
            } else if (npc3.getCurrentRoom() == cave) {
                npc3.setCurrentRoom(jungle);
                System.out.println(npc3.currentRoom);
            } else if (npc3.getCurrentRoom() == seaBottom) {
                npc3.setCurrentRoom(beach);
                System.out.println(npc3.currentRoom);
            } else if (npc3.getCurrentRoom() == jungle) {
                Room[] arr = {beach, cave, mountain};
                int select = random.nextInt(arr.length);
                npc3.setCurrentRoom(arr[select]);
                System.out.println(npc3.currentRoom);

            } else {
                System.out.println("ikke godt");
            }
        }
    }

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

    void win() {
        calculateMissionScore();
        setHighscoreName();
        int totalSum = score.getCurrentScore() + (10000 / Time.getSecondsPassed());
        System.out.println("You have won the game!" + "\n" + "You spend: " + Time.getSecondsPassed() + " seconds playing the game!");
        System.out.println("Your score is: " + totalSum);
        System.out.println("your score has been added to highscore");
        highscore.addHighscore(score.getName(), totalSum);
        System.out.println("");
        System.out.println("Current highscore list is: ");
        System.out.println(highscore.getHighscoreList());
        System.exit(0);
    }

    void lose() {
        System.out.println("You have lost the game!!!" + "\n" + "You spend: " + Time.getSecondsPassed() + " seconds playing the game!");
        System.exit(0);

    }
}
