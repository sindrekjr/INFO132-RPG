import java.util.HashMap;

/**
 * Denne klassen har et hashmap over alle kjente kommandoer, 
 * og gjenkjenner dem når de skrives av brukeren.
 * 
 * @author skj006
 */
public class Commands {
    
    private HashMap<String, CommandWord> allCommands;

    /**
     * Konstruktør for Commands-klassen. 
     */
    public Commands() {
        this.allCommands = new HashMap<>();
        for(CommandWord command : CommandWord.values()) {
            if(command != CommandWord.UNKNOWN) {
                allCommands.put((command.toString()), (command));
            }
        }
    }

    /**
     * Aksessmetode for kommando i hashmap.
     * @return CommandWord kommandoord funnet med streng-nøkkel
     */
    public CommandWord getCommandWord(String commandWord) {
        return allCommands.get(commandWord);
    }
    
    /**
     * Sjekker om en kommando eksisterer.
     * @param string    strengen som skal testes
     * @return boolean  true hvis strengen er en kommando
     */
    public boolean isCommand(String string) {
        return allCommands.containsKey(string);
    }
    
    /**
     * Printer alle kommandoer i tekstform til terminalen. 
     */
    public void printAll() {
        for(String command : allCommands.keySet()) {
            System.out.print(command + ", ");
        }
    }
}
