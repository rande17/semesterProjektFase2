package Game;

public class Character {

    // Data field
    // String, refers to name of the Character
    private String name;
    // String, refers to the describtion of the Character
    private String desc;
    // Room, refers to the Characters current room
    private Room currentRoom;

    private int health;
    private int energi;

    // Constructors, creates instance of Character
    public Character() {
    }

    /**
     *
     * @param _name sets the name of the character
     * @param _currentRoom sets the room of the character
     */
    public Character(String _name, Room _currentRoom) {
        name = _name;
        currentRoom = _currentRoom;
    }

    /**
     *
     * @param _name sets the name of the Character instance
     * @param _desc sets the describtion of the Character instance
     * @param _currentRoom sets the current room of the Character
     * @param _health sets the health of the character
     * @param _energi sets the energi of the character
     */
    public Character(String _name, String _desc, Room _currentRoom, int _health, int _energi) {
        name = _name;
        desc = _desc;
        currentRoom = _currentRoom;
        health = _health;
        energi = _energi;
    }
    
    /**
    * 
    * @param dmg sets the dmg the character is going to lose
    */
    public void LoseHealth(int dmg) {
        health -= dmg;
    }

    
    /**
     * getter method for name
     * @return name of the Character
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return describtion of the Character
     */
    public String getDescribtion() {
        return desc;
    }

    // Method, set Character name
    public void setName() {
    }

    /**
     * Method, set Character describtion
     *
     * @param _desc sets describtion
     */
    public void setDescribtion(String _desc) {
        _desc = desc;

    }

    /**
     *
     * @param goToRoom set Characters current room
     */
    public void setCurrentRoom(Room goToRoom) {
        currentRoom = goToRoom;
    }

    /**
     *
     * @return the current room
     */
    public Room getCurrentRoom() {
        return currentRoom;

    }

    /**
     *
     * @param newEnergi sets new value of energi
     */
    public void setEnergi(int newEnergi) {
        energi = newEnergi;
    }

    /**
     *
     * @param newHealth sets a new value of health
     */
    public void setHealth(int newHealth) {
        health = newHealth;
    }
/**
 * 
 * @return energi of the character
 */
    public int getEnergi() {
        return energi;
    }
/**
 * 
 * @return health of the character
 */
    public int getHealth() {
        return health;
    }
}
