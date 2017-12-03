/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.util.*;

/**
 *
 * @author marti
 */
public class Inventory {

    private int inventoryMaxQuantity = 10;
    private int currentQuantity = 0;
    private int inventoryMaxWeight = 10; //set default inventory weight 
    private int currentInventoryWeight = 0;
    private HashMap<String, Integer> inventory = new HashMap<>(); //Create a HashMap
    private HashMap<String, Integer> itemWeight = new HashMap<>();
//constuctor  

    Inventory() {
    }
//constructer

    /**
     *
     * @param _InventoryMaxWeight used to set the max weight of the inventory
     */
    Inventory(int _InventoryMaxWeight) {
        inventoryMaxWeight = _InventoryMaxWeight;
    }
//get max inventory weight

    /**
     *
     * @param _InventoryMaxWeight used to set the max weight of the inventory
     * @param _InventoryMaxQuantity used to set the max capacity of the
     * inventory
     */
    Inventory(int _InventoryMaxWeight, int _InventoryMaxQuantity) {
        inventoryMaxWeight = _InventoryMaxWeight;
        inventoryMaxQuantity = _InventoryMaxQuantity;
    }

    /**
     *
     * @return returns the max weight of inventory
     */
    public int getInventoryMaxWeight() {
        return inventoryMaxWeight;
    }

    /**
     *
     * @param _item used for adding item to inventory
     * @return true or false whether there is enough room for the item in inventory
     */
    public boolean addItemInInventory(Item _item) {
        int quantity = 0;
        String itemInInventory = "";
        String itemName = _item.getName();
        int weightOfItem = _item.getWeight();

        itemWeight.put(itemName, weightOfItem);
        if (currentQuantity < inventoryMaxQuantity && (weightOfItem + currentInventoryWeight) <= inventoryMaxWeight) {
            if (inventory.containsKey(itemName)) {
                quantity = inventory.get(itemName) + 1;
//                return true;
            } else {
                quantity = 1;
            }
            inventory.put(itemName, quantity);
            currentQuantity += 1;
            currentInventoryWeight += weightOfItem;
            return true;

        } else {
            System.out.println("You can't pickup this item");
        }
        return false;
    }

//    public void addItemInInventory(Item _item) {
//        int quantity = 0;
//        String itemInInventory = "";
//        String itemName = _item.getName();
//        int weightOfItem = _item.getWeight();
//
//        itemWeight.put(itemName, weightOfItem);
//        if (currentQuantity < inventoryMaxQuantity && (weightOfItem + currentInventoryWeight) <= inventoryMaxWeight) {
//            if (inventory.containsKey(itemName)) {
//                quantity = inventory.get(itemName) + 1;
//
//            } else {
//                quantity = 1;
//            }
//            inventory.put(itemName, quantity);
//            currentQuantity += 1;
//            currentInventoryWeight += weightOfItem;
//
//        } else {
//            System.out.println("You can't pickup this item");
//        }
//    }
    /**
     * Drop item from inventory and add to Room
     *
     * @param _string used for ether remove or decrease number of items
     */
    public void dropItemInventory(String _string) {
        int quantity = inventory.get(_string) - 1;

        if (inventory.get(_string) <= 1) {
            inventory.remove(_string);

        } else {
            inventory.replace(_string, quantity);

        }
        currentInventoryWeight -= itemWeight.get(_string);
        currentQuantity = currentQuantity - 1;

    }

    public void useItem() {
    }

    //method to show inventory
    public void showInventory() {
        System.out.println(inventory + "\n");
    }

    /**
     * get current inventory weight
     *
     * @return returns the current weight
     */
    public int getCurrentInventoryWeight() {
        return currentInventoryWeight;
    }

    // get inventory
    /**
     *
     * @return return inventory
     */
    public HashMap getInventory() {
        return inventory;
    }

    /**
     * Set new weight for the inventory
     *
     * @param _InventoryMaxWeight used to set the max weight
     */
    void setInventoryMaxWeight(int _InventoryMaxWeight) {
        inventoryMaxWeight = _InventoryMaxWeight;
    }

    /**
     *
     * @param _InventoryMaxQuantity used to set the max quantity
     */
    public void setinventoryMaxQuantity(int _InventoryMaxQuantity) {
        inventoryMaxQuantity = _InventoryMaxQuantity;
    }

    /**
     *
     * @param itemName get the item weight of a specific item
     * @return
     */
    public int getItemWeight(String itemName) {
        return itemWeight.get(itemName);
    }
    @Override
    public String toString() {
        String inventoryString ="";
        Iterator iterator = inventory.entrySet().iterator();
        
            while (iterator.hasNext()) {            
                HashMap.Entry entry = (HashMap.Entry) iterator.next();
                
                inventoryString += (String)entry.getValue().toString();
                inventoryString += "x ";
                inventoryString += (String)entry.getKey();
                inventoryString += "\n";
        }
        return inventoryString;
    }
}
