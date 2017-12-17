package Game;

import java.util.Date;

/**
 * @author Thomas
 * @author Nicolai
 * @author Rickie
 * @author Frederik
 * @author Julie 
 * @author Martin
*/

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
    PickableItem(String name, String description, int weight, boolean useable) {
        super(name, description, weight, useable);
    }

    /**
     * Constructor, creates instance of pickable item
     * @param name used to set the name for Item
     * @param weight used to set the weight for Item
     * @param respawnTime used to set the repawn time for Item
     */
    PickableItem(String name, String description, int weight, Date _respawnTime, boolean useable) {
        super(name, description, weight, useable);
        respawnTime = _respawnTime;
        
    }

    /**
     * Constructor, creates instance of pickable item
     * @param name
     * @param weight
     * @param useable 
     */
    PickableItem(String name, int weight, boolean useable) {
        super(name, "", weight, useable);
    }

    /**
     * Method to get an item's respawntime 
     * @return returns respawn time for given Pickable Item
     */
    public Date getRespawnTime() {
        return respawnTime;
    }

}
