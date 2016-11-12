import java.util.HashMap;
import java.util.Scanner;

/**
 * InputReader leser det en bruker skriver og gjør dette om til et objekt av Command-klassen. 
 *
 * @author skj006
 */
public class InputReader {
    private final static HashMap<String, CommandWord> commands = new HashMap<>();
    private Scanner reader;

    /**
     * Create a new InputReader that reads text from the text terminal.
     */
    public InputReader() {
        reader = new Scanner(System.in);
        mapCommands();
    }

    /**
     * Leser en linje skrevet av brukeren og returnerer de to første
     * ordene de skrev i form av et objekt av Command-klassen.
     * @return Command
     */
    public Command getInput() {
        System.out.print("> ");
        String inputLine = reader.nextLine().trim().toLowerCase();
        
        Scanner words = new Scanner(inputLine);
        String commandWord = null;
        String secondary = null;
        if(words.hasNext()) {
            commandWord = words.next();
            if(words.hasNext()) {
                secondary = words.next();
            }
        }
        return new Command(commands.get(commandWord), secondary);
    }
    
    /**
     * Leser en linje skrevet av en bruker, og returnerer det første ordet. 
     * @return String det første ordet spilleren skrev
     */
    public String getResponse() {
        System.out.print("> ");;
        String inputLine = reader.nextLine().trim().toLowerCase();
        
        return new String(new Scanner(inputLine).next());
    }
    
    /**
     * Aksessmetode for hashmap over kommandoer.
     * @return HashMap<String, CommandWord>
     */
    public HashMap<String, CommandWord> getCommands() {
        return commands;
    }
    
    /**
     * Fyller et hashmap med gyldige kommandoer, slik at de lett kan matches opp mot input. 
     */
    private void mapCommands() {
        for(CommandWord command : CommandWord.values()) {
            commands.put(command.toString(), command);
        }
    }
}
