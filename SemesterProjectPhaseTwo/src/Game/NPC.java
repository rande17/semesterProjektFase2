/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.util.ArrayList;

/**
 *
 * @author marti
 */
public class NPC {

    //String used for the description of the NPC
    private String description;

    //String used for the name os the NPC
    private String name;

    //int used to store how much damage the NPC does
    private int damageValue;

    //ArrayList used to store all dialog options of the NPC;
    private ArrayList<String> dialog = new ArrayList<String>();

//    private ArrayList NPCExits;

    Room currentRoom;

    /**
     * no-args constructor for npc
     */
    public NPC() {
    }

    /**
     * Constructor that sets the name and location of the NPC while creating it
     *
     * @param _name used to set the name
     * @param _currentRoom used to set spawning location
     */
    public NPC(String _name, Room _currentRoom) {
        name = _name;
        currentRoom = _currentRoom;

    }

    /**
     * a method used for adding dialog to a NPC
     *
     * @param _dialog the dialog that is added
     */
    public void addDialog(String _dialog) {
        dialog.add(_dialog);
    }

    /**
     * setter method to set a name for a NPC
     *
     * @param _name the new name of the npc
     */
    public void setName(String _name) {
        name = _name;
    }

    /**
     * getter method to get the dialog of a NPC
     *
     * @param i used to get the dialog option at this index
     * @return gives the dialog option at index i if it exists otherwise it
     * returns a default string defined in here
     */
    public String getDialog(int i) {
        if (i > 0 && i < dialog.size()) {
            return "You have baffled me, I don't know what to say";
        }
        return dialog.get(i);
    }

    /**
     * setter method used to set a damagevalue for a NPC
     *
     * @param _damageValue is the amount of damage
     */
    public void setDamageValue(int _damageValue) {
        damageValue = _damageValue;
    }

    /**
     * getter method used for get the damageValue of a NPC
     *
     * @return the damageValue for the NPC
     */
    public int getDamageValue() {
        return damageValue;
    }

    /**
     * setter method used for setting the description for a NPC
     *
     * @param _description is the description
     */
    public void setDescription(String _description) {
        description = _description;
    }

    /**
     * getter method used to get the description for a NPC
     *
     * @return desc which is the description
     */
    public String getDescription() {
        return description;
    }

    //should be used to close any open dialog with the NPC
    public void quitDialog() {

    }

    /**
     * getter method used to get the name of a NPC
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method used to get the currentroom of the NPC
     *
     * @return currentPosition of the NPC
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Setter method used to set the currentroom of a NPC
     *
     * @param _currentRoom moves the NPC to another room
     */
    public void setCurrentRoom(Room _currentRoom) {
        currentRoom = _currentRoom;
    }
}
