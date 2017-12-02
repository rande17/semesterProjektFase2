package Game;

/**
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public enum CommandWord { //enum is a mix of a class and a array, It define the type with a indentifier and what value that need to be in it
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?"), INSPECT("inspect"), TAKE("take"), USE("use"), TALK("talk"), DROP("drop"), CRAFT("craft"), SHOW("show"), MISSION("mission"), SAVE("save"), WIN("win"), LOSE("lose"), ESCAPE("escape"); //her ses de førnævnte værdier

    private String commandString; //private variable of the instance string
/**
 * 
 * @param _commandString 
 */
    CommandWord(String _commandString) { //Constructer with the parameterlist String commandString 
        commandString = _commandString; //Instance variable commandString is set to be the parameter commandString
    }
/**
 * toString method
 * @return commandString
 */
    @Override
    public String toString() { 
        return commandString; 
    }
}
