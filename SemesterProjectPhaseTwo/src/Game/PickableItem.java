/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.util.Date;

/**
 *
 * @author Zuzu
 */
public class PickableItem extends Item {

    private Date respawnTime;

    PickableItem(String name, int weight) {
        super(name, "", weight);
    }

    /**
     *
     * @param name used to set the name for Item
     * @param weight used to set the weight for Item
     */
    PickableItem(String name, String description, int weight, boolean _useable) {
        super(name, description, weight, _useable);
    }

    /**
     *
     * @param name used to set the name for Item
     * @param weight used to set the weight for Item
     * @param respawnTime used to set the repawn time for Item
     */
    PickableItem(String name, String description, int weight, Date _respawnTime, boolean _useable) {
        super(name, description, weight, _useable);
        respawnTime = _respawnTime;
        
    }

    PickableItem(String name, int weight, boolean _useable) {
        super(name, "", weight, _useable);
    }

    /**
     *
     * @return returns respawn time for given Pickable Item
     */
    public Date getRespawnTime() {
        return respawnTime;
    }

}
