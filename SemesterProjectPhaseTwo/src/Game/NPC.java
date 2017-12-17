/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.util.ArrayList;

/**
 * @author Thomas
 * @author Nicolai
 * @author Rickie
 * @author Frederik
 * @author Julie 
 * @author Martin
*/
public class NPC extends Character{

    //int used to store how much damage the NPC does
    private int damageValue;

    //ArrayList used to store all dialog options of the NPC;
    private ArrayList<String> dialog = new ArrayList<String>();

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
        super(_name, _currentRoom);
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
     * getter method to get the dialog of a NPC
     *
     * @param i used to get the dialog option at this index
     * @return gives the dialog option at index i if it exists otherwise it
     * returns a default string defined in here
     */
    public String getDialog(int i) {
        if (i > 0 && i < dialog.size()) {
            return "You have baffled me, I don't know what to say";
        }else if(dialog.size() > 1){
        String stringToReturn = dialog.get(i);
        dialog.remove(i);
        return stringToReturn;
        }else{
           return   dialog.get(i);
        }
        
    }

    /**
     *
     * @param i is the index which is used in the for-loop
     * @param remove whether to remove or not
     * @return the dialog at the given index
     */
    public String getDialog(int i, boolean remove) {
        if(remove){
        if (i > 0 && i < dialog.size()) {
            return "You have baffled me, I don't know what to say";
        }else if(dialog.size() > 1){
        String stringToReturn = dialog.get(i);
        dialog.remove(i);
        return stringToReturn;
        }else{
           return   dialog.get(i);
        }
        
    }else{
            return dialog.get(i);
        }
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
}
