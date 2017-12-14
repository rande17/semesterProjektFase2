/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemLocation {

    //HashMap used for making a itemlist of each room
    static HashMap<String, ArrayList> itemList = new HashMap<>();

    //sets the name of the arraylist to items
    private static ArrayList items;
    
    @Override
    public String toString(){
        return itemList.toString();
    }

    /**
     * no-args constructor for itemLocation
     */
    public ItemLocation() {
        itemList = new HashMap();
    }

    /**
     * Used for if the room dont have an itemList it puts a new itemList in that
     * room and then puts the items in the new itemList
     *
     * @param _room the room that the item will be added to
     * @param _item the name of the item
     */
    public void addItem(Room _room, Item _item) {
        if (!itemList.containsKey(_room.getShortDescription())) {
            itemList.put(_room.getShortDescription(), new ArrayList());
        }
        items = itemList.get(_room.getShortDescription());
        items.add(_item);
        itemList.put(_room.getShortDescription(), items);
    }
    public void addItem(String _room, Item _item) {
        if (!itemList.containsKey(_room)) {
            itemList.put(_room, new ArrayList());
        }
        items = itemList.get(_room);
        items.add(_item);
        itemList.put(_room, items);
    }

    /**
     * getter method to get items for the current room
     *
     * @param currentRoom get the items of the current room
     * @return the itemList of the current room
     */
    ArrayList getItems(Room currentRoom) {
        return itemList.get(currentRoom.getShortDescription());
    }

    /**
     * setter method to set an item to an new arraylist/room
     *
     * @param _room sets the room/location
     * @param itemsInRoom the name of the arraylist
     */
    public void setItem(Room _room, ArrayList itemsInRoom) {
        itemList.replace(_room.getShortDescription(), itemsInRoom);
    }
    
    public void emptyItemLocation(){
        itemList = null;
    }
}
