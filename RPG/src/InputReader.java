import java.util.Scanner;

/**
 * Klasse som leser brukerens input i terminal. 
 * 
 * @author skj006
 */
public class InputReader {
    
    private Commands commands;
    private Scanner reader;

    /**
     * Konstruktøren lager en skanner som leser tekst fra terminalen.
     */
    public InputReader() {
        this.commands = new Commands();
        this.reader = new Scanner(System.in);
    }

    /**
     * Leser en linje med tekst fra terminalen og sjekker om det er en kommando. 
     * @return GivenCommand en kommando
     */
    public GivenCommand getInput() {
        System.out.print("> ");
        String inputLine = reader.nextLine().trim().toLowerCase();
        
        Scanner eachWord = new Scanner(inputLine);
        String commandWord = null;
        String secondary = null;
        if(eachWord.hasNext()) {
            commandWord = eachWord.next();
            if(eachWord.hasNext()) {
                secondary = eachWord.next();
            }
        }
        GivenCommand input = new GivenCommand(commands.getCommandWord(commandWord), secondary);
        return input;
    }
}
