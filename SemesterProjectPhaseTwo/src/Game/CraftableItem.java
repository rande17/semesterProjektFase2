/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.util.ArrayList;

/**
 *
 * @author Nicolai
 */
public class CraftableItem extends Item {

    /**
     * ArrayList used for storing craftableItems
     */
    ArrayList<Item> craftableListArray = new ArrayList<>();
    
    /**
     * No-args contructor
     */
    public CraftableItem() {
        craftableListArray = new ArrayList<>();
    }
    
    public void addItemToCraftableList(Item item){
        craftableListArray.add(item);
    }

    /**
     * 
     * @param name is the name for which the item is represented
     * @param description stores the requirements when crafting
     * @param weight is the item weight
     */
    public CraftableItem(String name, String description, int weight) {
        super(name, description, weight, false);
    }
}
