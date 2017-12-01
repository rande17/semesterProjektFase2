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
    
    public Character (){
    }
    
    public Character (String _name, Room _currentRoom){
        name = _name;
        currentRoom = _currentRoom;
    }

    /**
     * 
     * @param _name sets the name of the Character instance
     * @param _desc sets the describtion of the Character instance
     * @param _currentRoom sets the current room of the Character
     */
    public Character (String _name, String _desc, Room _currentRoom, int _health, int _energi){
        name = _name;
        desc = _desc;
        currentRoom = _currentRoom;
        health = _health;
        energi = _energi;
        
    }
    
    // Methods

    /**
     * 
     * @return name of the Character
     */
    public String getName(){
        return name;
    }
  
    /**
     * 
     * @return describtion of the Character 
     */
    public String getDescribtion(){
        return desc;
    } 
            
    // Method, set Character name
    public void setName(){
    }
    
    // Method, set Character describtion
    public void setDescribtion(String _desc){
        _desc=desc;
        
    }
    
    // Method, set Characters current room
    public void setCurrentRoom(Room goToRoom){
        currentRoom = goToRoom;
    }
    
    public Room getCurrentRoom(){
        return currentRoom;
        
    }
    
        public void setEnergi(int newEnergi) {
        energi = newEnergi;
    }

    public void setHealth(int newHealth) {
        health = newHealth;
    }

    public int getEnergi() {
        return energi;
    }

    public int getHealth() {
        return health;
    }

    public void LoseHealth(){

         }
    
}
