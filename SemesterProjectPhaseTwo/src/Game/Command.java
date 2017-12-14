package Game;

/**
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Command {

    /**
     * Command word used for the commandwords, fx "go" and "quit"
     */
    private CommandWord commandWord;

    /**
     * String used to hold secondword of input
     */
    private String secondWord;

   
    /**
     * Constructor that sets commandWord and secondWord while creating it
     *
     * @param _commandWord used to set the commandWord
     * @param _secondWord used to set the secondWord
     */
    public Command(CommandWord _commandWord, String _secondWord) {
        commandWord = _commandWord;
        secondWord = _secondWord;
    }

    
    /**
     * Method used for returning the commandWord
     *
     * @return gives the commandWord
     */
    public CommandWord getCommandWord() {
        return commandWord;
    }

    /**
     * Method used for return the secondWord
     *
     * @return gives the secondWord
     */
    public String getSecondWord() {
        return secondWord;
    }

    
    /**
     * Method used for checking if a commandWord is valid
     *
     * @return gives either true or false, depending on whether the commandWord
     * is valid or unknown
     */
    public boolean isUnknown() {
        return (commandWord == CommandWord.UNKNOWN);
    }


    /**
     * Method used for checking if there is a secondWord in a entered command
     * string
     *
     * @return gives true when a command string has a secondword
     */
    public boolean hasSecondWord() {
        return (secondWord != null);
    }
}
