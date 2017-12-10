package Game;

public class Character {

    // Data field
    /**
     * a string which refers to the name of the character
     */
    private String name;

    /**
     * a string which refers to the describtion of the character
     */
    private String describtion;

    /**
     * a room variable which holds the currentRoom of the character
     */
    private Room currentRoom;

    private int health;
    private int energy;
    private int maxHealth;
    private int maxEnergy;
    private boolean hasLostEnergy = false;

    // Constructors, creates instance of Character
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
     * @param _desc sets the describtion of the Character instance
     * @param _currentRoom sets the current room of the Character
     * @param _health sets the health of the character
     * @param _energy sets the energy of the character
     */
    public Character(String _name, String _desc, Room _currentRoom, int _health, int _energy) {
        name = _name;
        describtion = _desc;
        currentRoom = _currentRoom;
        health = _health;
        energy = _energy;
        maxHealth = _health;
        maxEnergy = _energy;
    }

    /**
     * A method that subtracts the damage specified in the parameterlist
     *
     * @param dmg sets the dmg the character is going to lose
     */
    public void loseHealth(int dmg) {
        health -= dmg;
    }

    /**
     * A method that subtracts the energy specified in the parameterlist
     *
     * @param loss is the amount which is to be lost
     */
    public void loseEnergy(int loss) {
        if (Time.getSecondsPassed() % 45 == 0) {
            while (!hasLostEnergy) {
                energy -= loss;
                hasLostEnergy = true;
                break;
            }
        }
        if (Time.getSecondsPassed() % 5 != 0) {
            hasLostEnergy = false;
        }
    }

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
     * @return describtion of the Character
     */
    public String getDescribtion() {
        return describtion;
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
        _desc = describtion;

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
     * @param newEnergy sets new value of energy
     */
    public void setEnergy(int newEnergy) {
        if (maxEnergy < newEnergy) {
            energy = maxEnergy;
        } else {
            energy = newEnergy;
        }

    }

    /**
     *
     * @param newHealth sets a new value of health
     */
    public void setHealth(int newHealth) {
        if (maxHealth < newHealth) {
            health = maxHealth;
        } else {
            health = newHealth;
        }
    }

    /**
     *
     * @return energy of the character
     */
    public int getEnergy() {
        return energy;
    }

    /**
     *
     * @return health of the character
     */
    public int getHealth() {
        return health;
    }

    /**
     * 
     * @return maxEnergy of the character
     */
    public int getMaxEnergy() {
        return maxEnergy;
    }

    /**
     *
     * @return maxHealth of the character
     */
    public int getMaxHealth() {
        return maxHealth;
    }
}
