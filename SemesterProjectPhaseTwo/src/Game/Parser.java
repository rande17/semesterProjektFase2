package Game;

import java.util.Scanner;

/**
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Parser { //transforming unstructered data to structured
    // private variable. 

    private CommandWords commands;
    private Scanner reader;

    /**
     * no-args constructor for parser
     */
    public Parser() { // Methode
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    /**
     * getter method used to get a command from input
     *
     * @return new Command with input arguments
     */
    public Command getCommand() {
        String inputLine;
        String word1 = null;
        String word2 = null;

        System.out.print("> ");

        inputLine = reader.nextLine();
        // tokenizer
        Scanner tokenizer = new Scanner(inputLine);
        if (tokenizer.hasNext()) {
            word1 = tokenizer.next();
            if (tokenizer.hasNext()) {
                word2 = tokenizer.next();
            }
        }
        return new Command(commands.getCommandWord(word1), word2);
    }

    // metode, show commands, uden return
    /**
     * method used to show all commands
     */
    public void showCommands() {
        commands.showAll();
    }
}
