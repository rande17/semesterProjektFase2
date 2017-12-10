package Game;

public class Item {
  
    private String itemDescribtion;
    private int weight;
    private String name;
    private boolean useable;
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
        itemDescribtion = _description;
    }
    
    Item(String _name, String _description, int _weight, boolean _useable) {
        name = _name;
        weight = _weight;
        itemDescribtion = _description;
        useable = _useable;
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
    
    public boolean getUseable(){
      return useable;  
    } 
}
