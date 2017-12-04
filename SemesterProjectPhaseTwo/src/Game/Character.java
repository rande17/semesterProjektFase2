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
    private int energy;
    private int maxHealth;
    private int maxEnergy;
    private boolean hasLostEnergy = false;

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
     * @param _energy sets the energy of the character
     */
    public Character(String _name, String _desc, Room _currentRoom, int _health, int _energy) {
        name = _name;
        desc = _desc;
        currentRoom = _currentRoom;
        health = _health;
        energy = _energy;
        maxHealth = _health;
        maxEnergy = _energy;
    }

    /**
     *
     * @param dmg sets the dmg the character is going to lose
     */
    public void loseHealth(int dmg) {
        health -= dmg;
    }

    public void loseEnergy(int loss) {
        if (Time.getSecondsPassed() % 5 == 0) {
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
     * @param newEnergi sets new value of energy
     */
    public void setEnergy(int newEnergi) {
        if (maxEnergy < newEnergi) {
            energy = maxEnergy;
        } else {
            energy = newEnergi;
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
     * @return energy of the character
     */
    public int getMaxEnergy() {
        return maxEnergy;
    }

    /**
     *
     * @return health of the character
     */
    public int getMaxHealth() {
        return maxHealth;
    }
}
