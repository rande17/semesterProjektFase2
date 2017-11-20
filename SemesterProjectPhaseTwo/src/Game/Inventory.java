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
     * @param newInventoryMaxWeight used to set the max weight of the inventory
     */
    Inventory(int newInventoryMaxWeight) {
        inventoryMaxWeight = newInventoryMaxWeight;
    }
//get max inventory weight

    /**
     *
     * @param newInventoryMaxWeight used to set the max weight of the inventory
     * @param newInventoryMaxQuantity used to set the max capacity of the inventory
     */
    Inventory(int newInventoryMaxWeight, int newInventoryMaxQuantity) {
        inventoryMaxWeight = newInventoryMaxWeight;
        inventoryMaxQuantity = newInventoryMaxQuantity;
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
     */
    public void addItemInInventory(Item _item) {
        int quantity = 0;
        String itemInInventory = "";
        String itemName = _item.getName();
        int weightOfItem = _item.getWeight();
        
        
        
        
            itemWeight.put(itemName, weightOfItem);
            if (currentQuantity < inventoryMaxQuantity && (weightOfItem + currentInventoryWeight) <= inventoryMaxWeight) {
                if (inventory.containsKey(itemName)) {
                    quantity = inventory.get(itemName) + 1;
                    
                } else {
                    quantity = 1;
                }
                inventory.put(itemName, quantity);
                currentQuantity += 1;
                currentInventoryWeight += weightOfItem;
                      
        } else {
            System.out.println("You can't pickup this item");
        }
    }

    // Drop item from inventory and add to Room 
    /**
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

//get current inventory weight
    /**
     * 
     * @return returns the current weight
     */
    public int getCurrentInventoryWeight() {
        //currentInventoryWeight = 0;
        //Iterates through list of item in inventory to get current inventory weight 

        //for (String items : inventory.keySet()) {
        //   int quantity = inventory.get(items);
//            int weight = items.getWeight();
        //   int weight = 1;
        //   int thisItemWeight = quantity * weight;
        //   currentInventoryWeight += thisItemWeight;
        //}
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

//Set new weight for the inventory
    /**
     * 
     * @param newInventoryMaxWeight used to set the max weight
     */
    void setInventoryMaxWeight(int newInventoryMaxWeight) {
        inventoryMaxWeight = newInventoryMaxWeight;
    }
    /**
     * 
     * @param newInventoryMaxQuantity used to set the max quantity
     */
    public void setinventoryMaxQuantity(int newInventoryMaxQuantity) {
        inventoryMaxQuantity = newInventoryMaxQuantity;
    }
    
    public void useItem() {
        
    }
    
    public void showInventory() {
        System.out.println(inventory + "\n");
    }
    
    public int getItemWeight(String itemName){
        return itemWeight.get(itemName);
    }

//    public void addItemToInventory() {
//        if (checkInventoryMaxWeigth()) {
//            inventory.put(Item.Name, Item.Weight)   
//        } else {
//            System.out.println("Inventory is full");
//        }
//    }
//    public void dropItemInventory(Item _item) {
//        if(inventory.containsKey(_item)){
//        
//        inventory.remove(_item);
//        }
//    }
//   
//Check max weigth of the inventory everytime you pick up a new item
//    public boolean checkInventoryMaxWeigth() {
//        if (currentInventoryWeight + item.getWeight <= inventoryMaxWeight) {
//            return true;
//        } else {
//            return false;
//        }
//    }
}