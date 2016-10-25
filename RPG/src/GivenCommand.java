
/**
 * En klasse som samler en kommando på maks to ord. 
 * 
 * @author skj006
 */
public class GivenCommand {

    private CommandWord commandWord;
    private String secondary;
    
    /**
     * Constructor for objects of class GivenCommand
     * @param commandWord   Detektert kommando til programmet
     * @param secondWord    Ordet etter kommando
     */
    public GivenCommand(CommandWord commandWord, String secondWord) {
        setCommandWord(commandWord);
        setSecondary(secondWord);
    }

    /**
     * Aksessmetode for kommandoordet. 
     * @return CommandWord
     */
    public CommandWord getCommandWord() {
        return commandWord; 
    }
    
    /**
     * Mutasjonsmetode for kommandoordet. Settes til UNKNOWN hvis null.
     * @param input kommandoordet som skal settes
     */
    public void setCommandWord(CommandWord input) {
        if((this.commandWord = input) == null) {
            this.commandWord = CommandWord.UNKNOWN;
        }
    }
    
    /**
     * Aksessmetode for sekundærordet.
     * @return String
     */
    public String getSecondary() {
        return secondary; 
    }
    
    public void setSecondary(String input) {
        this.secondary = input;
    }
    
    /**
     * Sjekker om kommandoordet er ukjent.
     * @return boolean true hvis kommandoordet er designert UNKNOWN av enum
     */
    public boolean isUnknown() {
        return (commandWord == CommandWord.UNKNOWN);
    }
    
    /**
     * Sjekker om der et ord etter kommandoordet.
     * @return boolean true hvis objektet har et sekundærord
     */
    public boolean hasSecondary() {
        return (secondary != null);
    }
}
