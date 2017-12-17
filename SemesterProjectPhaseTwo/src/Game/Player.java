package Game;

/**
 * @author Thomas
 * @author Nicolai
 * @author Rickie
 * @author Frederik
 * @author Julie 
 * @author Martin
*/
public class Player extends Character {

    private int health;
    private int energy;
    final private int maxHealth;
    final private int maxEnergy;
    private boolean hasLostEnergy = false;

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
        super(_name, _description, _currentRoom);
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
     * @param energyLoss is the amount which is to be lost
     */
    public void loseEnergy(int energyLoss) {
        if (Time.getSecondsPassed() % 15 == 0) {
            while (!hasLostEnergy) {
                energy -= energyLoss;
                hasLostEnergy = true;
                break;
            }
        }
        if (Time.getSecondsPassed() % 5 != 0) {
            hasLostEnergy = false;
        }
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
