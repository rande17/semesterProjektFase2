/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import acquaintance.*;
import java.util.ArrayList;



/**
 *
 * @author rickie
 */
public class GameFacade implements InterfaceGame{

    @Override
    public ArrayList getItemsOnMap() {
        ArrayList itemArrayList = new ArrayList(Game.itemLocation.getItems(Game.currentRoom).size());
        ArrayList itemNameArrayList = new ArrayList(Game.itemLocation.getItems(Game.currentRoom).size());
        itemArrayList = Game.itemLocation.getItems(Game.currentRoom);
        for(int i = 0; i< itemArrayList.size(); i++){
            Item item = (Item)itemArrayList.get(i);
            itemNameArrayList.add(item.getName());
        }
        return itemNameArrayList;
    }

    
    
    
   
    
}
