/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import static Game.CommandWord.*;
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

    @Override
    public void takeItemGUI(String itemToTake) {
       Command command = new Command(TAKE, itemToTake);
       Game.takeItem(command);
    }

    @Override
    public void goGUI(String dir) {
       Command command = new Command(GO, dir);
       Game.goRoom(command);
    }

    @Override
    public String getRoom() {
    
    return Game.currentRoom.getShortDescription();
    
    }

    
    
    
   
    
}
