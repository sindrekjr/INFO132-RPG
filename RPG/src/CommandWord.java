
/**
 * En oversikt over alle gyldige kommandoer i spillet. 
 * 
 * @author skj006
 */
public enum CommandWord {

    ATTACK("attack"), BUY("buy"), RUN("run"), SELL("sell"), UNKNOWN("?"), USE("use");
    
    private String commandString;
    
    /**
     * Initialiserer med kommandostrengen.
     * @param command   kommandostrengen.
     */
    CommandWord(String commandString) {
        this.commandString = commandString;
    }
    
    /**
     * @return Kommandostreng som streng.
     */
    public String toString() {
        return commandString;
    }
}
