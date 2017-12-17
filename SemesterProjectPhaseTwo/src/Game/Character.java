package Game;

/**
 * @author Thomas
 * @author Nicolai
 * @author Rickie
 * @author Frederik
 * @author Julie
 * @author Martin
 */
public class Character {

    // Data field
    /**
     * a string which refers to the name of the character
     */
    private String name;

    /**
     * a string which refers to the description of the character
     */
    private String description;

    /**
     * a room variable which holds the currentRoom of the character
     */
    private Room currentRoom;

//    private int health;
//    private int energy;
//    private int maxHealth;
//    private int maxEnergy;
//    private boolean hasLostEnergy = false;
    // Constructors, creates instance of Character
    /**
     * No-args constructor for character
     */
    public Character() {
    }

    /**
     * a constructor for making a character
     *
     * @param _name sets the name of the character
     * @param _currentRoom sets the room of the character
     */
    public Character(String _name, Room _currentRoom) {
        name = _name;
        currentRoom = _currentRoom;
    }

    /**
     * a constructor with more parameters for making a character
     *
     * @param _name sets the name of the Character instance
     * @param _description sets the description of the Character instance
     * @param _currentRoom sets the current room of the Character / // * @param
     * _health sets the health of the character // * @param _energy sets the
     * energy of the character
     */
//    , int _health, int _energy
    public Character(String _name, String _description, Room _currentRoom) {
        name = _name;
        description = _description;
        currentRoom = _currentRoom;
//        health = _health;
//        energy = _energy;
//        maxHealth = _health;
//        maxEnergy = _energy;
    }

//    /**
//     * A method that subtracts the damage specified in the parameterlist
//     *
//     * @param dmg sets the dmg the character is going to lose
//     */
//    public void loseHealth(int dmg) {
//        health -= dmg;
//    }
//
//    /**
//     * A method that subtracts the energy specified in the parameterlist
//     *
//     * @param energyLoss is the amount which is to be lost
//     */
//    public void loseEnergy(int energyLoss) {
//        if (Time.getSecondsPassed() % 15 == 0) {
//            while (!hasLostEnergy) {
//                energy -= energyLoss;
//                hasLostEnergy = true;
//                break;
//            }
//        }
//        if (Time.getSecondsPassed() % 5 != 0) {
//            hasLostEnergy = false;
//        }
//    }
    /**
     * getter method for name
     *
     * @return name of the Character
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return description of the Character
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method, set Character name
     *
     * @param _name sets name
     */
    public void setName(String _name) {
        name = _name;
    }

    /**
     * Method, set Character description
     *
     * @param _desc sets description
     */
    public void setDescription(String _desc) {
        _desc = description;

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

//    /**
//     *
//     * @param newEnergy sets new value of energy
//     */
//    public void setEnergy(int newEnergy) {
//        if (maxEnergy < newEnergy) {
//            energy = maxEnergy;
//        } else {
//            energy = newEnergy;
//        }
//
//    }
//
//    /**
//     *
//     * @param newHealth sets a new value of health
//     */
//    public void setHealth(int newHealth) {
//        if (maxHealth < newHealth) {
//            health = maxHealth;
//        } else {
//            health = newHealth;
//        }
//    }
//
    /**
     *
     * @return energy of the character
     */
//    public int getEnergy() {
//        return energy;
//    }
//
//    /**
//     *
//     * @return health of the character
//     */
//    public int getHealth() {
//        return health;
//    }
//
//    /**
//     * 
//     * @return maxEnergy of the character
//     */
//    public int getMaxEnergy() {
//        return maxEnergy;
//    }
//
//    /**
//     *
//     * @return maxHealth of the character
//     */
//    public int getMaxHealth() {
//        return maxHealth;
//    }
}
