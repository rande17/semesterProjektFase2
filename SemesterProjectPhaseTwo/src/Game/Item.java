/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.util.Date;

/**
 *
 * @author marti
 */
public class Item {

    /*
    Dette dokument er ikke færdigarbejdet
     */
    
    private String itemDescribtion;
    private int weight;
    private String name;
    private String describtion;

    /*
    Klasse: Item
    Constructor: Item(name, weight, respawnTime, mapLocation(StringArray))
    Metoder: getItemOnMap, setItemOnMap, addItemInInventory, removeItemFromInventory, setItemDescribtions, getItemDescribtion, getRespawnTime
     */
    //Creating a constructor with a parameter-list, Room mapLocation maybe has to be changed
    /**
     * 
     * @param name 
     */
    Item(String name) {
        this.name = name;
    }

    /**
     * 
     * @param name
     * @param weight 
     */
    Item(String name, String description, int weight) {
        this.name = name;
        this.weight = weight;
        this.describtion = describtion;
    }

    /**
     * 
     * @return returns the weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * 
     * @param newDescribtion sets new description
     */
    public void setItemDescribtions(String newDescribtion) {
        itemDescribtion = newDescribtion;
    }

    /**
     * 
     * @return returns the description of the Item
     */
    public String getItemDescribtion() {
        return itemDescribtion;
    }

    /**
     * 
     * @return returns the name of the item
     */
    public String getName() {
        return name;
    }
}