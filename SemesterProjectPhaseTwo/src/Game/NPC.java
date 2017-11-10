package Game;

import java.util.ArrayList;

public class NPC extends Character {

    //int used to store how much damage the NPC does
    private int dmgValue;

    //ArrayList used to store all dialog options of the NPC;
    private ArrayList<String> dialog = new ArrayList<String>();

    
    // Constructor no args, creates NPC's
    public NPC() {
    }

    //Constructor that set the name and location of the NPC's while creating it
    /**
     * @param _name used to set the name og the NPC
     * @param _currentRoom used to set the current room of the NPC
     */
    
    public NPC (String _name, Room _currentRoom){
        super (_name, _currentRoom);
    }
            
    
    public NPC (String _name, String _desc, Room _currentRoom, int _dmgValue){
        super (_name, _desc, _currentRoom);
        dmgValue = _dmgValue;
    }
    
    /**
     *
     * @param _dialog used to add a dialog option to the NPC
     */
    public void addDialog(String _dialog) {
        dialog.add(_dialog);
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

    //should be used to close any open dialog with the NPC
    public void quitDialog() {

    }

}
