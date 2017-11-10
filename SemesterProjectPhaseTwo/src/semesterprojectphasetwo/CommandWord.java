package semesterprojekt;

/**
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public enum CommandWord { // enum er en blanding af en klasse og et array, den definerer en type med en identifier og hvilke værdier der skal ligge i den
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?"), INSPECT("inspect"), TAKE("take"), USE("use"), TALK("talk"), DROP("drop"), CRAFT("craft"), SHOW("show"), MISSION("mission"); //her ses de førnævnte værdier

    private String commandString; //opretter en privat instansvariabel af typen string

    CommandWord(String commandString) { //laver en constructor med en parameterliste(String commandString)
        this.commandString = commandString; //instansvariablen commandString sættes til at være parameter-commandString
    }

    public String toString() { //en metode, toString(), oprettes 
        return commandString; //metoden returnerer commandString
    }
}
