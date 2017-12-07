/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

/**
 *
 * @author marti
 */
public class Item {
  
    private String itemDescribtion;
    private int weight;
    private String name;
    private String describtion;

    /**
     * constructer for item
     * @param _name of item
     */
    Item(){
        
    }
    Item(String _name) {
        name = _name;
    }

    /**
     * constructer for item
     * @param _name of the item
     * @param _weight of the item
     */
    Item(String _name, String _description, int _weight) {
        name = _name;
        weight = _weight;
        describtion = _description;
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
