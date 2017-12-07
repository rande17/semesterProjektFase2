package Game;

import java.util.Scanner;

/**
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Parser { // transformering fra udstruktureret data til struktureret
    // private variable. 

    private CommandWords commands;
    private Scanner reader;

    // Constructor
    public Parser() { // Methode
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    // Metode, get command, input reads input line.
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
    public void showCommands() {
        commands.showAll();
    }
}
