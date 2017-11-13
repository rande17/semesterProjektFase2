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
    private int maxCarryWeight = 10;
    private int totalWeightInBackpack;
    private int weight;
    private Date respawnTime;
    private Room mapLocation;
    private String name;
    private String describtion;

    /*
    Klasse: Item
    Constructor: Item(name, weight, respawnTime, mapLocation(StringArray))
    Metoder: getItemOnMap, setItemOnMap, addItemInInventory, removeItemFromInventory, setItemDescribtions, getItemDescribtion, getRespawnTime
     */
    //Creating a constructor with a parameter-list, Room mapLocation maybe has to be changed
    Item(String name) {
        this.name = name;
    }

    Item(String name, String describtion, int weight) {
        this.name = name;
        this.weight = weight;
        this.describtion = describtion;
    }

    Item(String name, int weight, Date respawnTime, Room mapLocation) {
        //This body is left empty for now
        this.name = name;
        this.weight = weight;
        this.respawnTime = respawnTime;
        this.mapLocation = mapLocation;
    }

//    public Item getItemOnMap(Item[] allItems) {
////        This body is left empty for now
//    }
//    public void setItemOnMap(Item _item, Room location) {
////        Body is empty
//    }
    public int getWeight() {
        return weight;
    }

    public void removeItemFromInventory(Item _item) {
        //Her skal objekt"tingen" sættes til null, hvis en anden bedre måde ikke fremkommer
    }

    public void setItemDescribtions(String newDescribtion) {
        itemDescribtion = newDescribtion;
    }

    public String getItemDescribtion() {
        return itemDescribtion;
    }

    public Date getRespawnTime() {
        return respawnTime;
    }

    public String getName() {
        return name;
    }
     
    
}

