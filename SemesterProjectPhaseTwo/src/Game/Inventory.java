package Game;

import java.util.*;

public class Inventory {

    private int currentQuantity = 0;
    private int inventoryMaxWeight = 10; //set default inventory weight 
    private int currentInventoryWeight = 0;
    private HashMap<String, Integer> inventory = new HashMap<>(); //Create a HashMap
    private HashMap<String, Integer> itemWeight = new HashMap<>();
    private HashMap<String, Boolean> useableItems = new HashMap<>();

    /**
     * Constructor, no args for creating Inventory instance
     */
    Inventory() {
    }

    /**
     * Constructor, for creating Inventory instance
     *
     * @param _InventoryMaxWeight used to set the max weight of the inventory
     */
    Inventory(int _InventoryMaxWeight) {
        inventoryMaxWeight = _InventoryMaxWeight;
    }

    /**
     * @return max weight of inventory
     */
    public int getInventoryMaxWeight() {
        return inventoryMaxWeight;
    }

    /**
     * @param _item used for adding item to inventory
     * @return true or false depending on if inventory max weight is reached
     */
    public boolean addItemInInventory(Item _item) {
        int quantity = 0;
        String itemName = _item.getName();
        int weightOfItem = _item.getWeight();
        boolean useable = _item.getUseable();

        /*currentQuantity < inventoryMaxQuantity */
        if ((weightOfItem + currentInventoryWeight) <= inventoryMaxWeight) {
            if (inventory.containsKey(itemName)) {
                quantity = inventory.get(itemName) + 1;
//                return true;
            } else {
                quantity = 1;
                itemWeight.put(itemName, weightOfItem);
                useableItems.put(itemName, useable);
            }
            inventory.put(itemName, quantity);
            currentQuantity += 1;
            currentInventoryWeight += weightOfItem;
            return true;

        } else {
            System.out.println("You can't pickup this item :P");
        }
        return false;
    }

    /**
     * Method: Drops item from inventory and adds it to current room
     *
     * @param _string used for ether remove or decrease number of items
     */
    public void removeItemInventory(String _string) {
        int quantity = inventory.get(_string) - 1;
        int weight = itemWeight.get(_string);
        if (inventory.get(_string) <= 1) {
            inventory.remove(_string);
            itemWeight.remove(_string);
            useableItems.remove(_string);
        } else {
            inventory.replace(_string, quantity);

        }
        currentInventoryWeight -= weight;
        currentQuantity = currentQuantity - 1;
    }

    /**
     * Method that prints inventory
     */
    public void showInventory() {
        System.out.println(inventory + "\n");
    }

    /**
     * @return the current weight of items in inventory
     */
    public int getCurrentInventoryWeight() {
        return currentInventoryWeight;
    }

    /**
     * @return inventory HashMap
     */
    public HashMap getInventory() {
        return inventory;
    }

    /**
     * Method to set new weight for the inventory
     *
     * @param _InventoryMaxWeight used to set the max weight
     */
    void setInventoryMaxWeight(int _InventoryMaxWeight) {
        inventoryMaxWeight = _InventoryMaxWeight;
        System.out.println(inventoryMaxWeight);
    }

    /**
     *
     * @return inventory status to give an overview of how much the inventory
     * contains
     */
    public String getInventoryStatus() {
        String inventoryStatus = "Inventory weight: ";
        inventoryStatus += getCurrentInventoryWeight();
        inventoryStatus += "/";
        inventoryStatus += getInventoryMaxWeight();
        inventoryStatus += "\n";

        return inventoryStatus;
    }

    /**
     * @param itemName get the item weight of a specific item
     * @return weight for an item
     */
    public int getItemWeight(String itemName) {
        return itemWeight.get(itemName);
    }

    /**
     * Method that iterates through Inventory HashMap and converts Item key and
     * value to a String
     *
     * @return String of item name and item quantity
     */
    @Override
    public String toString() {
        String inventoryString = "";
        Iterator iterator = inventory.entrySet().iterator();
        inventoryString += getInventoryStatus();

        while (iterator.hasNext()) {
            HashMap.Entry entry = (HashMap.Entry) iterator.next();

            inventoryString += (String) entry.getValue().toString();
            inventoryString += "x ";
            inventoryString += (String) entry.getKey();
            inventoryString += "\n";
        }
        return inventoryString;
    }

    /**
     * Method, converting Inventory HashMap to an ArrayList
     *
     * @return Inventory ArrayList
     */
    public ArrayList inventoryNames() {
        ArrayList<String> inventoryArrayList = new ArrayList<>();

        Iterator iterator = inventory.entrySet().iterator();
        int i = 0;

        while (i < inventory.size()) {
            HashMap.Entry entry = (HashMap.Entry) iterator.next();
            inventoryArrayList.add(i, (String) entry.getKey());
            i++;
        }

        return inventoryArrayList;
    }

    /**
     * Method checks if inventory contains a usable item
     *
     * @param itemName name of the item
     * @return true or false, depending on if the item is usable or not
     */
    public boolean getUseable(String itemName) {
        return useableItems.get(itemName);
    }
}
