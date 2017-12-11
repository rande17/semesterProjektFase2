package Game;

public class Player extends Character {

    // Data field
    // Constructor, creates instance of Player
    /**
     *
     * @param _name sets name of player
     * @param _description sets describtion of player
     * @param _currentRoom sets player current room
     * @param _health sets the health of player
     * @param _energy sets the energy of player
     */
    public Player(String _name, String _description, Room _currentRoom, int _health, int _energy) {
        super(_name, _description, _currentRoom, _health, _energy);

    }

}
