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
public class PickableItem extends Item{
    private Date respawnTime;

    /**
     * 
     * @param name used to set the name for Item
     * @param weight used to set the weight for Item
     */
    PickableItem(String name, int weight) {
        super(name, weight);
    }

    /**
     * 
     * @param name used to set the name for Item
     * @param weight used to set the weight for Item
     * @param respawnTime used to set the repawn time for Item
     */
    PickableItem(String name, int weight, Date respawnTime) {
        super(name, weight);
        this.respawnTime = respawnTime;
    }
    
    /**
     * 
     * @return returns respawn time for given Pickable Item
     */
    public Date getRespawnTime() {
        return respawnTime;
    }
    
    
}
