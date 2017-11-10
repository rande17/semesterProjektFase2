package Game;

import java.util.HashMap;

/**
 * @mod Rickie, added comments to most of the lines to reflect what they do
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class CommandWords {

    /**
     * make HashMap variable to contain valid commands
     */
    private HashMap<String, CommandWord> validCommands;

    /**
     * Method to populate the validCommands HashMap with valid commands
     */
    public CommandWords() {
        //initialize validCommands HashMap that should contain commands
        validCommands = new HashMap<String, CommandWord>();

        //for loop to populate the validCommands hashMap with valid commands from the CommandWord class
        for (CommandWord command : CommandWord.values()) {
            //check if the command is valid
            if (command != CommandWord.UNKNOWN) {
                //populate the validCommands HashMap
                validCommands.put(command.toString(), command);
            }
        }
    }

    /**
     * @param commandWord should be defined in the CommandsWord class
     * @return either the command directly if its int he HashMap validCommands,
     * otherwise return a ? as defiend in the CommandWord class as UNKNOWN
     */
    public CommandWord getCommandWord(String commandWord) {
        //make a CommandWord instance called command, defiend from the parameter to the method
        CommandWord command = validCommands.get(commandWord);
        //check if the command if not a null value
        if (command != null) {
            //return the command
            return command;
            //if the value is null
        } else {
            //return the ? defiend in the CommandWord class
            return CommandWord.UNKNOWN;
        }
    }

    /**
     * @param aString value to be checked wheter or not is a valid command
     * @return true if the value is contained in the HashMap validCommands,
     * otherwise return false
     */
    public boolean isCommand(String aString) {
        return validCommands.containsKey(aString);
    }

    /**
     * A method to show all valid commands
     */
    public void showAll() {
        //for loop to iterate through the HashMap validCommands and print them out
        for (String command : validCommands.keySet()) {
            //printing out the command and a space so the commands are not bunched together
            System.out.print(command + "  ");
        }
        //just printing a newline so the next input is not right after the commands printed above
        System.out.println();
    }
}
