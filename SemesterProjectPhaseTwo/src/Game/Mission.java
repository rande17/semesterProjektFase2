/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author marti
 */
public class Mission {

    HashMap<String, Boolean> missionStatus = new HashMap();
    HashMap<String, String> missionInfo = new HashMap();
    
    Score score = new Score();
    

    //constructor
    public Mission() {
}
/**
 * 
 * @param room sets the room of the mission do not work atm
 * @param name sets the name of the mission
 * @param describtion set the describtion of the mission
 */
    public void addMission(Room room, String name, String describtion) {
        missionInfo.put(name, describtion);
        missionStatus.put(name, false);

      }
/**
 * method to get mission descibtion
 * @param key of the hashmap
 * @return 
 */
    public String getMissionDescribtion(String key) {
        return missionInfo.get(key);
    }
           
/**
 * method to set mission to true if complete
 * @param key of the hashmap
 */
    public void setMissionComplete(String key) {
        missionStatus.replace(key, true);
        //Kalder ukendt klasse

//        score.addToPoints(missionPoint.get(key));
    }

}



