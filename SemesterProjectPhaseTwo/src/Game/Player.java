package Game;

public class Player extends Character {
    
    // Data field
    
    // Integer, refers to the status of the character
    private int ChStatus;

    
    // Constructor, creates instance of Player

    /**
     *
     * @param _name
     * @param _desc
     * @param _currentRoom
     * @param _ChStatus
     */
    public Player(String _name, String _desc, Room _currentRoom, int _ChStatus) {
        super(_name, _desc, _currentRoom);
        ChStatus = _ChStatus;
    }
}
