/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author marti
 */
public class Mission {
//    Game game = new Game();

    HashMap<String, Boolean> missionStatus = new HashMap();

    Score score = new Score();

    private String name;

    /**
     * no-args constructor for mission
     */
    public Mission() {
    }

    /**
     * adds a mission to a missionstatus hashmap
     *
     * @param room sets the room of the mission do not work atm
     * @param _name sets the name of the mission
     */
    public void addMission(Room room, String _name) {
        name = _name;
        missionStatus.put(name, false);

    }

    /**
     * getter method to get name of mission
     *
     * @return the name of the mission
     */
    private String getName() {
        return name;
    }

    /**
     * method to set mission to true if complete
     *
     * @param key of the hashmap
     */
    public void setMissionComplete(String key) {
        missionStatus.replace(key, true);
    }

    @Override
    public String toString() {
        Game game = new Game();
        String missionStatusString = "";
        Iterator iteratorMissionStatus = game.getAllMissions().missionStatus.entrySet().iterator();

        while (iteratorMissionStatus.hasNext()) {
            HashMap.Entry entry = (HashMap.Entry) iteratorMissionStatus.next();
            missionStatusString += (String) entry.getKey();
            missionStatusString += ": ";
            missionStatusString += "\n";
            if ((boolean) (entry.getValue()) == false) {
                missionStatusString += "mission in progress\n";
            }
            if ((boolean) (entry.getValue()) == true) {
                missionStatusString += "mission is complete\n";
            }
            missionStatusString += "\n";

        }
        return missionStatusString;
    }
}
