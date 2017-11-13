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
    HashMap<String, Integer> missionPoint = new HashMap();
    
    Score score = new Score();
    

    //constructor
    public Mission() {
}

    public void addMission(Room room, String name, String describtion, int point) {
        missionInfo.put(name, describtion);
        missionStatus.put(name, false);
        missionPoint.put(name, point);
      }

    public String getMissionDescribtion(String key) {
        return missionInfo.get(key);
    }
           

    public void setMissionComplete(String key) {
        missionStatus.replace(key, true);
        //Kalder ukendt klasse

//        score.addToPoints(missionPoint.get(key));
    }

}



