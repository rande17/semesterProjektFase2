/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import static Game.Game.allMissions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

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
   
    @Override
    public String toString() {
        String missionInfoString ="";
        String missionStatusString ="";
        Iterator iteratorMissionInfo = allMissions.missionInfo.entrySet().iterator();
        Iterator iteratorMissionStatus = allMissions.missionStatus.entrySet().iterator();
        
            while (iteratorMissionInfo.hasNext()) {            
                HashMap.Entry entry = (HashMap.Entry) iteratorMissionInfo.next();
                missionInfoString += (String)entry.getKey();
                missionInfoString += ": ";
                missionInfoString += "\n";
                missionInfoString += (String)entry.getValue().toString();
                missionInfoString += "\n";
                missionInfoString += "\n";
        }
//            while(iteratorMissionStatus.hasNext()){
//                HashMap.Entry entry = (HashMap.Entry)iteratorMissionStatus.next();
////              missionStatusString += (String)entry.getKey();
////              missionStatusString += (String)entry.getValue().toString();
////              missionStatusString += " | ";
//            }
//            
//            if (missionS == false) {
//               
//            }
//
//            if (missionStatus.get() == true) {
//                System.out.println("Mission is complete");
//            }
        return missionInfoString + missionStatusString;
    }
}





