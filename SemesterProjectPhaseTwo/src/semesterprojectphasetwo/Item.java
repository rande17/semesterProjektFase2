package semesterprojekt;

import java.util.Date;

public class Item {

    // Data field
    // String used for describtion of the item
    private String itemDescribtion;

    // Int used for the max carry weight
    private int maxCarryWeight = 10;

    // Int used for the total weight in the backpack
    private int totalWeightInBackpack;

    // Int 
    private int weight = 1;
    private Date respawnTime;
    private Room mapLocation;
    private String name;

    /**
     *
     * @param _name refers to the name of the item
     */
    // Constructor, with arg, Room mapLocation maybe has to be changed
    Item(String _name) {
        name = _name;
    }

    /**
     *
     * @param _name refers to the name of the item
     * @param _weight refers to the weight of the item
     */
    // Constructor
    Item(String _name, int _weight) {
        name = _name;
        weight = _weight;
    }

    /**
     *
     * @param _name refers to the name of the item
     * @param _weight refers to the weight of the item
     * @param _respawnTime refers to the respawn time for an item
     * @param _mapLocation refers to where an item is located
     */
    // Constructor
    Item(String _name, int _weight, Date _respawnTime, Room _mapLocation) {
        name = _name;
        weight = _weight;
        respawnTime = _respawnTime;
        mapLocation = _mapLocation;
    }

    /**
     *
     * @returns weight of an item
     */
    // Method, get weight
    public int getWeight() {
        return weight;
    }

    /**
     *
     * @param newDescribtion sets a new itemdesribtion for an item
     */
    // Method, set item desribtion
    public void setItemDescribtions(String newDescribtion) {
        itemDescribtion = newDescribtion;
    }

    /**
     *
     * @returns the itemdesribtion
     */
    // Method, get item describtion
    public String getItemDescribtion() {
        return itemDescribtion;
    }

    /**
     *
     * @returns repawntime of the item
     */
    // Method, get respawntime
    public Date getRespawnTime() {
        return respawnTime;
    }

    /**
     *
     * @returns the name of an item
     */
    // Method, get name
    public String getName() {
        return name;
    }
}
