package Game;

import java.util.*;

public class Inventory {

    // Data field
    // Int used for inventoryMaxQuantity
    private int inventoryMaxQuantity = 10;

    // Int used for current quantity in inventory
    private int currentQuantity = 0;

    // Int used for inventory max weight
    private int inventoryMaxWeight = 10; //set default inventory weight 

    // Int used for current inventory wieght
    private int currentInventoryWeight = 0;
    private HashMap<String, Integer> inventory = new HashMap<>(); //Create a HashMap

    // Constuctor, no args  
    Inventory() {
    }

    /**
     *
     * @param _InventoryMaxWeight used to set a new value for inventory
     * maxweight
     */
    // Constructor, for creating new instance of inventory with a certain maxwieght
    Inventory(int _InventoryMaxWeight) {
        inventoryMaxWeight = _InventoryMaxWeight;
    }

    /**
     *
     * @returns the inventory maxweight
     */
    // Method, get max inventory weight
    public int getInventoryMaxWeight() {
        return inventoryMaxWeight;
    }

    /**
     *
     * @param _item refers to the new item you want to pick up
     */
    // Method, add item to inventory
    public void addItemInInventory(Item _item) {
        int quantity = 0;
        String itemInInventory = "";
        String itemName = _item.getName();

        // Checks if you can carry more items
        if (currentQuantity < inventoryMaxQuantity) {
            // If you can carry more items, check if inventory already
            // contains an itme with the same name as the one you pick up.
            // if so, add 1 to the value of this item
            if (inventory.containsKey(itemName)) {
                quantity = inventory.get(itemName) + 1;

            } else {
                quantity = 1;
            }
            inventory.put(itemName, quantity);
            currentQuantity += 1;

            // If you cant carry more items, print following
        } else {
            System.out.println("You can't pickup this item");
        }
    }

    /**
     *
     * @param _string refers to the name as string for the item you want to
     * drop.
     */
    // Drop item from inventory and add to Room
    public void dropItemInventory(String _string) {
        int quantity = inventory.get(_string) - 1;

        // If the inventory contains the item with string name and
        // quantity is <= 1, remove this item
        if (inventory.get(_string) <= 1) {
            inventory.remove(_string);

            // Else, replace current quantity with new quantity (-1)
        } else {
            inventory.replace(_string, quantity);

        }

        currentQuantity = currentQuantity - 1;
    }

    /**
     *
     * @returns current inventory weight
     */
    public int getCurrentInventoryWeight() {
        currentInventoryWeight = 0;
        //Iterates through list of item in inventory to get current inventory weight 

        for (String items : inventory.keySet()) {
            int quantity = inventory.get(items);
            int weight = 1;
            int thisItemWeight = quantity * weight;
            currentInventoryWeight += thisItemWeight;

        }

        return currentInventoryWeight;
    }

    // get inventory
    public HashMap getInventory() {
        return inventory;
    }

    //Set new weight for the inventory
    void setInventoryMaxWeight(int newInventoryMaxWeight) {
        inventoryMaxWeight = newInventoryMaxWeight;
    }

    public void useItem() {

    }

    public void showInventory() {
        System.out.println(inventory + "\n");
    }
}
