package Game;
/**
 * @author Thomas
 * @author Nicolai
 * @author Rickie
 * @author Frederik
 * @author Julie 
 * @author Martin
*/
public class Item {

    private String itemDescription;
    private int weight;
    private String name;
    private boolean useable;

    /**
     * no-args constructor for item
     */
    Item() {

    }

    /**
     * A constructor for item which takes one parameter
     *
     * @param _name is the name of the item
     */
    Item(String _name) {
        name = _name;
    }

    /**
     * constructer for item which takes three parameters
     *
     * @param _name of the item
     * @param _description is the describtion of the item
     * @param _weight of the item
     */
    Item(String _name, String _description, int _weight) {
        name = _name;
        weight = _weight;
        itemDescription = _description;
    }

    /**
     * A constructor for itemm which takes four parameters
     *
     * @param _name is the name of the item
     * @param _description is the describtion of the item
     * @param _weight is the weight of the item
     * @param _useable is whether the item can be used or notS
     */
    Item(String _name, String _description, int _weight, boolean _useable) {
        name = _name;
        weight = _weight;
        itemDescription = _description;
        useable = _useable;
    }

    /**
     * is used to return the wieght of item
     *
     * @return returns the weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * is used to set a new describtion
     *
     * @param newDescribtion sets new description
     */
    public void setItemDescribtions(String newDescribtion) {
        itemDescription = newDescribtion;
    }

    /**
     * is used to return the describtion
     *
     * @return returns the description of the Item
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     * is used to get the name of the item
     *
     * @return returns the name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * is used to get whether the item is useable
     *
     * @return returns the boolean useable
     */
    public boolean getUseable() {
        return useable;
    }
}
