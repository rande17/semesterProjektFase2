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

    //HashMap used for making a itemlist of each room
//    static HashMap<String, Item> craftableList = new HashMap<>();
    static ArrayList<Item> craftableListArray = new ArrayList<>();
//    private String name;
//    static Item campfire = new CraftableItem("Campfire", "Campfire: Lumber, Stick and Flint", 3);
//    static Item spear = new CraftableItem("Spear", "Spear: Stick, Fint and Rope or Lian", 3);
//    static Item axe = new CraftableItem("Axe", "Axe: Stick, Stone and Rope or Lian", 3);
//    static Item raft = new CraftableItem("Raft", "Raft: Lumber, Stick and Rope or Lian", 3);

    public CraftableItem() {

    }

    public CraftableItem(String name, String description, int weight) {
        super(name, description, weight, false);
    }

//    @Override
//    public String toString() {
//        String craftableItems = "";
//        Iterator iteratorCraftableItem = craftableList.entrySet().iterator();
//
//        
//            HashMap.Entry entry = (HashMap.Entry) iteratorCraftableItem.next();
//            craftableItems += (String) entry.getKey();
//            craftableItems += "\n";
//
//        return craftableItems;
//    }
   
}
