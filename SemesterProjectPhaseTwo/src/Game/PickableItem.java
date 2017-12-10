package Game;

import java.util.Date;

public class PickableItem extends Item {

    // Respawn time for an item
    private Date respawnTime;
    
    /**
     * Constructor, creates instace of pickable item
     * @param name used to set the name for Item
     * @param weight used to set the weight for Item
     */
    PickableItem(String name, int weight) {
        super(name, "", weight);
    }

    /**
     * @param name used to set the name for Item
     * @param weight used to set the weight for Item
     */
    PickableItem(String name, String description, int weight, boolean _useable) {
        super(name, description, weight, _useable);
    }

    /**
     * Constructor, creates instance of pickable item
     * @param name used to set the name for Item
     * @param weight used to set the weight for Item
     * @param respawnTime used to set the repawn time for Item
     */
    PickableItem(String name, String description, int weight, Date _respawnTime, boolean _useable) {
        super(name, description, weight, _useable);
        respawnTime = _respawnTime;
        
    }

    /**
     * Constructor, creates instance of pickable item
     * @param name
     * @param weight
     * @param _useable 
     */
    PickableItem(String name, int weight, boolean _useable) {
        super(name, "", weight, _useable);
    }

    /**
     * Method to get an item's respawntime 
     * @return returns respawn time for given Pickable Item
     */
    public Date getRespawnTime() {
        return respawnTime;
    }

}
