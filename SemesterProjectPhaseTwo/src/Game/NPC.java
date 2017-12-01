/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author marti
 */
public class NPC {

    //String used for the describtion of the NPC
    private String desc;

    //String used for the name os the NPC
    private String name;

    //int used to store how much damage the NPC does
    private int dmgValue;

    //ArrayList used to store all dialog options of the NPC;
    private ArrayList<String> dialog = new ArrayList<String>();

    //Room to stre the current room
    private Room currentPosition;

    private ArrayList NPCExits;

    Room currentRoom;

    // no args Constructor, creates NPC's
    public NPC() {
    }

    //Constructor that set the name and location of the NPC's while creating it
    /**
     * @param _name used to set the name
     * @param _currentPosition used to set spawning location
     */
    public NPC(String _name, Room _currentPosition) {
        name = _name;
        currentPosition = _currentPosition;

    }

    /**
     *
     * @param _dialog used to add a dialog option to the NPC
     */
    public void addDialog(String _dialog) {
        dialog.add(_dialog);
    }

    public void setName(String _name) {
        name = _name;
    }

    /**
     *
     * @param i used to get the dialog option at this index
     * @return gives the dialog option at index i if it exists otherwise it
     * return a default string defined in here
     */
    public String getDialog(int i) {
        if (i > 0 && i < dialog.size()) {
            return "You have baffled me, I don't know what to say";
        }
        return dialog.get(i);
    }

    /**
     * @param _dmgValue sets how much damage this NPC should do
     */
    public void setDamageValue(int _dmgValue) {
        dmgValue = _dmgValue;
    }

    /**
     * @return returns how much damage this NPC should do
     */
    public int damageValue() {

        return dmgValue;
    }

    /**
     * @param _desc sets the describtion of the NPC, should be some defining
     * features of the characther
     */
    public void setDescribtion(String _desc) {
        desc = _desc;
    }

    /**
     * @return gets the description of the NPC
     */
    public String getDescribtion() {
        return desc;
    }

    //should be used to close any open dialog with the NPC
    public void quitDialog() {

    }

    /**
     * @return gets the NPC's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return gets the room the NPC is in
     */
    public Room getCurrentRoom() {
        return currentPosition;
    }

    /**
     * @param goToRoom moves the NPC to another room
     */
    public void setCurrentRoom(Room goToRoom) {
        currentPosition = goToRoom;
    }

    public void NPCMove() {
        while (Time.secondsPassed % 20 == 0) {
       
                
            }
//            String direction;
//            for (Iterator<String> i = NPCExits.iterator(); i.hasNext();) {
//                getCurrentRoom().getExit();
////                String item = i.next();
////                CString item = i.next();
////                System.out.println(item);
//            }
//        }
//        Room nextRoom;
////                    = currentRoom.getExit(direction);
//        NPCExits.add(currentPosition.getExit(desc));
//        setCurrentRoom(NPCExits);
//        setCurrentRoom(getCurrentRoom().getExit(desc));
//        getCurrentRoom().getExit(desc);
//        Math.random();
//        System.out.println(getCurrentRoom());
//    }
}

    public void setCurrentPosition(Room currentPosition) {
        this.currentPosition = currentPosition;
    }
    }

