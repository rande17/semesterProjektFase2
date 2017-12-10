package Game;

import java.util.HashMap;
import java.util.Set;

/**
 * @mod Julie Dittmann Weimar Andersen, added comments
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Room {

    private String description;
    private HashMap<String, Room> exits; //ask Rande << just an initialization of a HashMap with the identifier exits, that takes a String object as the first object and a Room as the second object, hope it makes sense

    /**
     * Constructor connect the new room with the description
     *
     * @param _description sets room describtion
     */
    public Room(String _description) {
        description = _description;
        exits = new HashMap<String, Room>();
    }

    /**
     * Connect current room with neighbor room from compass directions
     *
     * @param direction set exit direction
     * @param neighbor sets the rooms/room that is neighbors
     */
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    //returns the description of the room
    public String getShortDescription() {
        return description;
    }

    //returns the description of where you are, plus possible exits
    public String getLongDescription() {
        return "You are " + description + ".\n" + getExitString();
    }

    //returns all possible exis from current room
    public String getExitString() {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        //loops over all existing exits and adds the exits to the string
        for (String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * for given direction the function returns the room
     *
     * @param direction gets the direction
     * @return
     */
    public Room getExit(String direction) {
        return exits.get(direction);
    }
}
