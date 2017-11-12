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
//constuctor  

    Inventory() {
    }
//constructer

    Inventory(int newInventoryMaxWeight) {
        inventoryMaxWeight = newInventoryMaxWeight;
    }
//get max inventory weight

    public int getInventoryMaxWeight() {
        return inventoryMaxWeight;
    }

    public void addItemInInventory(Item _item) {
        int quantity = 0;
        String itemInInventory = "";
        String itemName = _item.getName();

        if (currentQuantity < inventoryMaxQuantity) {
            if (inventory.containsKey(itemName)) {
                quantity = inventory.get(itemName) + 1;

            } else {
                quantity = 1;
            }
            inventory.put(itemName, quantity);
            currentQuantity += 1;
        } else {
            System.out.println("You can't pickup this item");
        }
    }

    // Drop item from inventory and add to Room 
    public void dropItemInventory(String _string) {
        int quantity = inventory.get(_string) - 1;

        if (inventory.get(_string) <= 1) {
            inventory.remove(_string);

        } else {
            inventory.replace(_string, quantity);

        }
        
        currentQuantity = currentQuantity - 1;
//        getItems itemList
//        .put(_item, quantity);
//        quantity = itemList.get(_item)
    }

//get current inventory weight
    public int getCurrentInventoryWeight() {
        currentInventoryWeight = 0;
        //Iterates through list of item in inventory to get current inventory weight 

        for (String items : inventory.keySet()) {
            int quantity = inventory.get(items);
//            int weight = items.getWeight();
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
