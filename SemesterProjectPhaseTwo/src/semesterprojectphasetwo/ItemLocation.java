/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojekt;

import java.util.ArrayList;
import java.util.HashMap;


public class ItemLocation {
    
    //HashMap used for making a itemlist of each room
    HashMap<Room, ArrayList> itemList = new HashMap<>();
    
    //sets the name of the arraylist to items
    private ArrayList items;

    //no args constructer
    public ItemLocation() {
    }
/**
 *
 * @param _room the room that the item will be added to
 * @param _item the name of the item
 */
//Used for if the room dont have an itemList it put a new itemList in that room and then put the items in the new itemList
    public void addItem(Room _room, Item _item) {
        if (!itemList.containsKey(_room)) {                                     
            itemList.put(_room, new ArrayList());                               
        }
        items = itemList.get(_room);
        items.add(_item);
        itemList.put(_room, items);
    }
//    public ArrayList getItems(Room room){
    
    /**
     * 
     * @param currentRoom get the items of the current room
     * @return the itemList of the current room
     */
    //getter method to get items for the current room
        ArrayList getItems(Room currentRoom) {
        return itemList.get(currentRoom);
    }
/**
 * 
 * @param _room sets the room/location
 * @param itemsInRoom the name of the arraylist
 */
//setter method to set an item to an new arraylist/room
public void setItem(Room _room, ArrayList itemsInRoom){
    itemList.replace(_room, itemsInRoom);
}

}
